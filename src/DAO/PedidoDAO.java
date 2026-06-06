package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import enums.StatusPedido;
import conexao.Conexao;
import entidades.ItemPedido;
import util.Console;

public class PedidoDAO {

    public static int finalizarVenda(int idClienteEndereco, int idVendedor, List<ItemPedido> carrinho, double total,
                                     String observacao, StatusPedido status) throws SQLException {
        String sqlPedido = "insert into pedido (id_cliente_endereco, id_vendedor, status_pedido, valor_total, observacao) values (?, ?, ?, ?, ?)";
        String sqlItem = "insert into item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) values (?, ?, ?, ?, ?)";
        String sqlEstoque = "update produtos set estoque = estoque - ? where id_produtos = ? and estoque >= ?";
        int idPrecisoComissao = 0;

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psItem = conn.prepareStatement(sqlItem);
             PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque)) {

            conn.setAutoCommit(false);

            psPedido.setInt(1, idClienteEndereco);
            psPedido.setInt(2, idVendedor);
            psPedido.setString(3, status.name());
            psPedido.setDouble(4, total);
            psPedido.setString(5, observacao);

            psPedido.executeUpdate();

            try (ResultSet rs = psPedido.getGeneratedKeys()) {
                int idPedidoGerado = 0;
                if (rs.next()) {
                    idPedidoGerado = rs.getInt(1);
                    idPrecisoComissao = rs.getInt(1);
                }

                for (ItemPedido item : carrinho) {
                    psEstoque.setInt(1, item.getQuantidade());
                    psEstoque.setInt(2, item.getIdProdutos());
                    psEstoque.setInt(3, item.getQuantidade());

                    int linhasAfetadas = psEstoque.executeUpdate();

                    if (linhasAfetadas == 0) {
                        conn.rollback();
                        throw new SQLException("Estoque insuficiente para o produto ID: " + item.getIdProdutos());
                    }

                    psItem.setInt(1, idPedidoGerado);
                    psItem.setInt(2, item.getIdProdutos());
                    psItem.setInt(3, item.getQuantidade());
                    psItem.setDouble(4, item.getPrecoUnitario());
                    psItem.setDouble(5, item.getSubtotal());

                    psItem.executeUpdate();
                }
            }

            conn.commit();
            return idPrecisoComissao;
        }
    }

    public static void imprimirPedidoS() {
        String inforPedido = """
                select p.id_pedido, c.nome_clientes, e.rua, e.numero, e.bairro, e.cidade, e.cep, p.observacao
                from pedido p 
                left join cliente_endereco ce on p.id_cliente_endereco = ce.id_cliente_endereco
                left join clientes c on ce.id_clientes = c.id_clientes
                left join endereco e on ce.id_endereco = e.id_endereco
                left join vendedor v on p.id_vendedor = v.id_vendedor
                """;

        try (Connection conn = Conexao.criarNovaConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(inforPedido)) {

            boolean temPedidos = false;

            while (rs.next()) {
                temPedidos = true;
                int idPedido = rs.getInt("id_pedido");
                String nome = rs.getString("nome_clientes");
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String cep = rs.getString("cep");
                String obs = rs.getString("observacao");
                
                Console.linha();
                System.out.println("|| PEDIDO: " + idPedido);
                System.out.println("|| CLIENTE: " + (nome != null ? nome : "Não informado"));
                
                if (rua != null) {
                    System.out.println("|| ENDEREÇO: " + rua + " | " + numero + " | " + bairro);
                    System.out.println("|| CIDADE: " + cidade + " \t CEP: " + cep);
                } else {
                    System.out.println("|| ENDEREÇO: Retirada no local / Não informado");
                }
                
                new ItemPedidoDAO().mostrarItemPedido(idPedido);
                
                System.out.println("|| OBSERVAÇÃO: " + (obs != null ? obs : "Sem observações."));
                Console.linha();
            }

            if (!temPedidos) {
                System.out.println("\n==============================================");
                System.out.println("   NENHUM PEDIDO ENCONTRADO NO BANCO DE DADOS ");
                System.out.println("==============================================");
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    public static void listarItensPedido(int idPedido) {
        String sql = "SELECT item_pedido.id_produtos, item_pedido.quantidade, item_pedido.preco_unitario, item_pedido.subtotal FROM item_pedido INNER JOIN produtos ON produtos.id_produtos = item_pedido.id_produtos WHERE item_pedido.id_pedido = ?";

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPedido);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n==============================================");
                System.out.println("ITENS DO PEDIDO #" + idPedido);
                System.out.println("==============================================");

                while (rs.next()) {
                    System.out.println(
                            "Produto ID: " + rs.getInt("id_produtos") +
                            " | Quantidade: " + rs.getInt("quantidade") +
                            " | Preço: R$ " + rs.getDouble("preco_unitario") +
                            " | Subtotal: R$ " + rs.getDouble("subtotal"));
                }
                System.out.println("==============================================");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar itens: " + e.getMessage());
        }
    }

    public static String buscarStatusPedido(int idPedido) {
        String sql = "select status_pedido from pedido where id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getString("status_pedido");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar status: " + e.getMessage());
        }
        return null;
    }

    public static boolean pedidoExiste(int idPedido) {
        String sql = "select 1 from pedido where id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static void alterarObservacao(int idPedido, String novaObs) throws SQLException {
        String sql = "UPDATE pedido SET observacao = ? WHERE id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novaObs);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        }
    }

    public static void alterarStatus(int idPedido, StatusPedido status) throws SQLException {
        String sql = "UPDATE pedido SET status_pedido = ? WHERE id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status.name());
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        }
    }

    public static void removerProdutoPedido(int idPedido, int idProduto) throws SQLException {
        String sqlBuscarQtd = "SELECT quantidade FROM item_pedido WHERE id_pedido = ? AND id_produtos = ?";
        String sqlEstoque = "UPDATE produtos SET estoque = estoque + ? WHERE id_produtos = ?";
        String sqlDelete = "DELETE FROM item_pedido WHERE id_pedido = ? AND id_produtos = ?";

        try (Connection conn = Conexao.criarNovaConexao()) {
            conn.setAutoCommit(false);
            int quantidade = 0;

            try (PreparedStatement ps = conn.prepareStatement(sqlBuscarQtd)) {
                ps.setInt(1, idPedido);
                ps.setInt(2, idProduto);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        quantidade = rs.getInt("quantidade");
                    } else {
                        throw new SQLException("Produto não encontrado no pedido.");
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlEstoque)) {
                ps.setInt(1, quantidade);
                ps.setInt(2, idProduto);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                ps.setInt(1, idPedido);
                ps.setInt(2, idProduto);
                ps.executeUpdate();
            }
            conn.commit();
        }
    }

    public static void recalcularTotalPedido(int idPedido) throws SQLException {
        String sqlSoma = "SELECT SUM(subtotal) AS total FROM item_pedido WHERE id_pedido = ?";
        String sqlUpdate = "UPDATE pedido SET valor_total = ? WHERE id_pedido = ?";

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement psSoma = conn.prepareStatement(sqlSoma);
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

            psSoma.setInt(1, idPedido);
            double total = 0;

            try (ResultSet rs = psSoma.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble("total");
                }
            }

            psUpdate.setDouble(1, total);
            psUpdate.setInt(2, idPedido);
            psUpdate.executeUpdate();
        }
    }

    public static void alterarQuantidadeProdutoPedido(int idPedido, int idProduto, int novaQuantidade) throws SQLException {
        String sqlBuscar = "SELECT quantidade, preco_unitario FROM item_pedido WHERE id_pedido = ? AND id_produtos = ?";
        String sqlEstoque = "UPDATE produtos SET estoque = estoque + ? WHERE id_produtos = ?";
        String sqlItem = "UPDATE item_pedido SET quantidade = ?, subtotal = ? WHERE id_pedido = ? AND id_produtos = ?";

        try (Connection conn = Conexao.criarNovaConexao()) {
            conn.setAutoCommit(false);
            int quantidadeAntiga = 0;
            double precoUnitario = 0;

            try (PreparedStatement ps = conn.prepareStatement(sqlBuscar)) {
                ps.setInt(1, idPedido);
                ps.setInt(2, idProduto);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        quantidadeAntiga = rs.getInt("quantidade");
                        precoUnitario = rs.getDouble("preco_unitario");
                    } else {
                        throw new SQLException("Produto não encontrado no pedido.");
                    }
                }
            }

            int diferenca = quantidadeAntiga - novaQuantidade;

            try (PreparedStatement ps = conn.prepareStatement(sqlEstoque)) {
                ps.setInt(1, diferenca);
                ps.setInt(2, idProduto);
                ps.executeUpdate();
            }

            double novoSubtotal = precoUnitario * novaQuantidade;

            try (PreparedStatement ps = conn.prepareStatement(sqlItem)) {
                ps.setInt(1, novaQuantidade);
                ps.setDouble(2, novoSubtotal);
                ps.setInt(3, idPedido);
                ps.setInt(4, idProduto);
                ps.executeUpdate();
            }

            recalcularTotalPedido(idPedido);
            conn.commit();
        }
    }

    public static void executarUpdateGenerico(String sql, int novoId, int idPedido) throws SQLException {
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, novoId);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        }
    }

    public static void executarUpdateDoubleGenerico(String sql, double valor, int idPedido) throws SQLException {
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, valor);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        }
    }

    public static void devolverEstoqueELimparItens(int idPedido) throws SQLException {
        String sqlItens = "SELECT id_produtos, quantidade FROM item_pedido WHERE id_pedido = ?";
        String sqlDel = "DELETE FROM item_pedido WHERE id_pedido = ?";

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement psItens = conn.prepareStatement(sqlItens)) {

            conn.setAutoCommit(false);
            psItens.setInt(1, idPedido);
            try (ResultSet rs = psItens.executeQuery()) {
                while (rs.next()) {
                    atualizarEstoqueProduto(rs.getInt("id_produtos"), rs.getInt("quantidade"));
                }
            }

            try (PreparedStatement psDel = conn.prepareStatement(sqlDel)) {
                psDel.setInt(1, idPedido);
                psDel.executeUpdate();
            }
            conn.commit();
        }
    }

    public static void atualizarEstoqueProduto(int idProd, int qtd) throws SQLException {
        String sql = "UPDATE produtos SET estoque = estoque + ? WHERE id_produtos = ?";
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qtd);
            ps.setInt(2, idProd);
            ps.executeUpdate();
        }
    }

    public static double calcularPrecoProduto(int idProd) throws SQLException {
        String sql = "SELECT preco FROM produtos WHERE id_produtos = ?";
        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getDouble("preco");
            }
        }
        return 0.0;
    }

    public static void inserirOuAtualizarItem(int idPedido, int idProd, int qtd, double precoUnitario) throws SQLException {
        String select = "SELECT quantidade FROM item_pedido WHERE id_pedido = ? AND id_produtos = ?";
        String insertReal = "INSERT INTO item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        String update = "UPDATE item_pedido SET quantidade = quantidade + ?, subtotal = subtotal + ? WHERE id_pedido = ? AND id_produtos = ?";

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idProd);

            try (ResultSet rs = ps.executeQuery()) {
                double subtotal = qtd * precoUnitario;

                if (rs.next()) {
                    try (PreparedStatement up = conn.prepareStatement(update)) {
                        up.setInt(1, qtd);
                        up.setDouble(2, subtotal);
                        up.setInt(3, idPedido);
                        up.setInt(4, idProd);
                        up.executeUpdate();
                    }
                } else {
                    try (PreparedStatement ins = conn.prepareStatement(insertReal)) {
                        ins.setInt(1, idPedido);
                        ins.setInt(2, idProd);
                        ins.setInt(3, qtd);
                        ins.setDouble(4, precoUnitario);
                        ins.setDouble(5, subtotal);
                        ins.executeUpdate();
                    }
                }
            }
        }
    }
}