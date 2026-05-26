package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import conexao.Conexao;
import entidades.ItemPedido;

public class PedidoDAO {

    public static void finalizarVenda(int idCliente, int idVendedor,
            List<ItemPedido> carrinho, double total) {

        Connection conn = Conexao.getConexao();

        String sqlPedido = "INSERT INTO pedido (id_clientes, id_vendedor, status_pedido, valor_total) " +
                "VALUES (?, ?, ?, ?)";

        try {

            conn.setAutoCommit(false);

            PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);

            psPedido.setInt(1, idCliente);
            psPedido.setInt(2, idVendedor);
            psPedido.setString(3, "FILA");
            psPedido.setDouble(4, total);

            psPedido.executeUpdate();

            ResultSet rs = psPedido.getGeneratedKeys();

            int idPedidoGerado = 0;

            if (rs.next()) {
                idPedidoGerado = rs.getInt(1);
            }

            String sqlItem = "INSERT INTO item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement psItem = conn.prepareStatement(sqlItem);

            String sqlEstoque = "UPDATE produtos SET estoque = estoque - ? WHERE id_produtos = ? AND estoque >= ?";

            PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque);

            for (ItemPedido item : carrinho) {

                psEstoque.setInt(1, item.getQuantidade());
                psEstoque.setInt(2, item.getIdProdutos());
                psEstoque.setInt(3, item.getQuantidade());

                int linhasAfetadas = psEstoque.executeUpdate();

                if (linhasAfetadas == 0) {

                    conn.rollback();

                    System.out.println("Estoque insuficiente para o produto ID: "
                            + item.getIdProdutos());

                    return;
                }

                psItem.setInt(1, idPedidoGerado);
                psItem.setInt(2, item.getIdProdutos());
                psItem.setInt(3, item.getQuantidade());
                psItem.setDouble(4, item.getPrecoUnitario());
                psItem.setDouble(5, item.getSubtotal());

                psItem.executeUpdate();
            }

            conn.commit();

            System.out.println("Venda finalizada com sucesso!");

        } catch (SQLException e) {

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("ERRO ao finalizar venda: " + e.getMessage());

        } finally {

            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão");
            }
        }
    }

    public static void imprimirPedidoS() {
        ResultSet resultSet = null;
        Statement statement = null;
        String sql = "select * from item_pedido i join pedido p  on i.id_pedido = p.id_pedido join produtos ps on i.id_produtos = ps.id_produtos join vendedor v on p.id_vendedor = v.id_vendedor join clientes c on p.id_clientes = c.id_clientes;";
        try {
            statement = conexao.Conexao.getConexao().createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                // id
                int idPedido = resultSet.getInt("id_pedido");
                int idCliente = resultSet.getInt("id_clientes");
                int idVendedor = resultSet.getInt("id_vendedor");
                int idProduto = resultSet.getInt("id_produtos");

                // Produtos
                String nomeProduto = resultSet.getString("id_produtos");
                String medida = resultSet.getString("medida");
                int quantidade = resultSet.getInt("quantidade");
                float precouni = resultSet.getFloat("preco_unitario");
                float subTotal = resultSet.getFloat("preco_unitario");
                float totalValor = resultSet.getFloat("valor_total");

                // Clientes
                String nomeCliente = resultSet.getString("nome_clientes");
                String cpf = resultSet.getString("cpf");

                // vendedor
                int idvendedor = resultSet.getInt("id_vendedor");
                String nomev = resultSet.getString("nome_vendedor");
                String telVendedor = resultSet.getString("telefone_vendedor");

                // Pedido
                String status = resultSet.getString("status_pedido");
                LocalDateTime data = resultSet.getTimestamp("data_pedido").toLocalDateTime();
                linha();
                System.out.println("||\t\t\t\t\tPEDIDO: " + idPedido + " \t\t\t\t\t||");
                linha();
                System.out.printf("|| Cliente: %-20s \t Cpf: %-14s\t||%n", nomeCliente, cpf);
                linha();
                System.out.println("|| Produtos\t\tNome\t\t\tQuan\t\tValor Uni\t\tMedida\t\tSub total\t\t||");
                new ItemPedidoDao().mostrarItemPedido(idPedido);
                linha();
                System.out.printf("|| Vendedor: %3d\tNome: %-20s\tTelefone: %-14s\t\t||", idvendedor, nomev,
                        telVendedor);
                linha();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void relatorioVendasPorVendedor() {
    String sql = "SELECT v.nome_vendedor, COUNT(p.id_pedido) AS total_pedidos, SUM(p.valor_total) AS total_faturado " +
                 "FROM pedido p " +
                 "INNER JOIN vendedor v ON p.id_vendedor = v.id_vendedor " +
                 "GROUP BY v.id_vendedor, v.nome_vendedor " +
                 "ORDER BY total_faturado DESC";

    try (Connection conn = conexao.Conexao.criarNovaConexao();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        System.out.println("\n=== RELATÓRIO: FATURAMENTO POR VENDEDOR ===");
        System.out.printf("%-20s | %-15s | %-15s%n", "VENDEDOR", "QTD PEDIDOS", "TOTAL FATURADO");
        System.out.println("---------------------------------------------------------");

        boolean temDados = false;
        while (rs.next()) {
            temDados = true;
            String nome = rs.getString("nome_vendedor");
            int qtd = rs.getInt("total_pedidos");
            double total = rs.getDouble("total_faturado");
            System.out.printf("%-20s | %-15d | R$ %-12.2f%n", nome, qtd, total);
        }
        if (!temDados) System.out.println("Nenhum pedido processado até o momento.");
        System.out.println("---------------------------------------------------------");

    } catch (Exception e) {
        System.out.println("Erro ao gerar relatório de vendedores: " + e.getMessage());
    }
}

public static void relatorioProdutosMaisVendidos() {
    String sql = "SELECT pr.nome_produtos, SUM(ip.quantidade) AS total_unidades, SUM(ip.subtotal) AS total_arrecadado " +
                 "FROM item_pedido ip " +
                 "INNER JOIN produtos pr ON ip.id_produtos = pr.id_produtos " +
                 "GROUP BY pr.id_produtos, pr.nome_produtos " +
                 "ORDER BY total_unidades DESC";

    try (Connection conn = conexao.Conexao.criarNovaConexao();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        System.out.println("\n=== RELATÓRIO: PRODUTOS MAIS VENDIDOS ===");
        System.out.printf("%-20s | %-15s | %-15s%n", "PRODUTO", "UNIDADES VENDIDAS", "TOTAL ARRECADADO");
        System.out.println("---------------------------------------------------------");

        boolean temDados = false;
        while (rs.next()) {
            temDados = true;
            String nome = rs.getString("nome_produtos");
            int qtd = rs.getInt("total_unidades");
            double total = rs.getDouble("total_arrecadado");
            System.out.printf("%-20s | %-15d | R$ %-12.2f%n", nome, qtd, total);
        }
        if (!temDados) System.out.println("Nenhum produto vendido até o momento.");
        System.out.println("---------------------------------------------------------");

    } catch (Exception e) {
        System.out.println("Erro ao gerar relatório de produtos: " + e.getMessage());
    }
}

    public static void linha() {
        System.out.println(
                "============================================================================================");
    }
}