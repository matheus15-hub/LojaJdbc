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

    public static void finalizarVenda(int idCliente, int idVendedor, List<ItemPedido> carrinho, double total, String observacao, String status) {
        String sqlPedido = "insert into pedido (id_clientes, id_vendedor, status_pedido, valor_total) values (?, ?, ?, ?)";
        String sqlItem = "insert into item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) values (?, ?, ?, ?, ?)";
        String sqlEstoque = "update produtos set estoque = estoque - ? where id_produtos = ? and estoque >= ?";

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psItem = conn.prepareStatement(sqlItem);
             PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque)) {

            conn.setAutoCommit(false);

            psPedido.setInt(1, idCliente);
            psPedido.setInt(2, idVendedor);
            psPedido.setString(3, status); 
            psPedido.setDouble(4, total);

            psPedido.executeUpdate();

            try (ResultSet rs = psPedido.getGeneratedKeys()) {
                int idPedidoGerado = 0;
                if (rs.next()) {
                    idPedidoGerado = rs.getInt(1);
                }

                for (ItemPedido item : carrinho) {
                    psEstoque.setInt(1, item.getQuantidade());
                    psEstoque.setInt(2, item.getIdProdutos());
                    psEstoque.setInt(3, item.getQuantidade());

                    int linhasAfetadas = psEstoque.executeUpdate();

                    if (linhasAfetadas == 0) {
                        conn.rollback();
                        System.out.println("Estoque insuficiente para o produto ID: " + item.getIdProdutos());
                        return;
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
            System.out.println("Venda salva com sucesso no sistema!");

        } catch (SQLException e) {
            System.out.println("ERRO ao finalizar venda: " + e.getMessage());
        }
    }

    public static void imprimirPedidoS() {
        String sql = "select * from item_pedido i " +
                     "join pedido p on i.id_pedido = p.id_pedido " +
                     "join produtos ps on i.id_produtos = ps.id_produtos " +
                     "join vendedor v on p.id_vendedor = v.id_vendedor " +
                     "join clientes c on p.id_clientes = c.id_clientes";
        
        try (Connection conn = Conexao.criarNovaConexao();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
             
            while (resultSet.next()) {
                int idPedido = resultSet.getInt("id_pedido");
                String nomeProduto = resultSet.getString("nome_produto");
                int quantidade = resultSet.getInt("quantidade");
                float precouni = resultSet.getFloat("preco_unitario");
                float subTotal = resultSet.getFloat("subtotal");

                String nomeCliente = resultSet.getString("nome_clientes");
                String cpf = resultSet.getString("cpf");

                int idvendedor = resultSet.getInt("id_vendedor");
                String nomev = resultSet.getString("nome_vendedor");
                String telVendedor = resultSet.getString("telefone_vendedor");
                String status = resultSet.getString("status_pedido");
                
                linha();
                System.out.println("||\t\t\t\t\tPEDIDO: " + idPedido + " [" + status + "] \t\t\t\t\t||");
                linha();
                System.out.printf("|| Cliente: %-20s \t Cpf: %-14s\t||%n", nomeCliente, cpf);
                System.out.printf("|| Produto: %-20s \t Qtd: %-3d \t Preço: R$ %-6.2f \t Subtotal: R$ %-6.2f ||%n", nomeProduto, quantidade, precouni, subTotal);
                linha();
                System.out.printf("|| Vendedor: %3d\tNome: %-20s\tTelefone: %-14s\t\t||%n", idvendedor, nomev, telVendedor);
                linha();
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void relatorioVendasPorVendedor() {
        String sql = "select v.nome_vendedor, count(p.id_pedido) as total_pedidos, sum(p.valor_total) as total_faturado " +
                     "from pedido p " +
                     "inner join vendedor v on p.id_vendedor = v.id_vendedor " +
                     "group by v.id_vendedor, v.nome_vendedor " +
                     "order by total_faturado desc";

        try (Connection conn = Conexao.criarNovaConexao();
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
        String sql = "select pr.nome_produto, sum(ip.quantidade) as total_unidades, sum(ip.subtotal) as total_arrecadado " +
                     "from item_pedido ip " +
                     "inner join produtos pr on ip.id_produtos = pr.id_produtos " +
                     "group by pr.id_produtos, pr.nome_produto " +
                     "order by total_unidades desc";

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== RELATÓRIO: PRODUTOS MAIS VENDIDOS ===");
            System.out.printf("%-20s | %-15s | %-15s%n", "PRODUTO", "UNIDADES VENDIDAS", "TOTAL ARRECADADO");
            System.out.println("---------------------------------------------------------");

            boolean temDados = false;
            while (rs.next()) {
                temDados = true;
                String nome = rs.getString("nome_produto");
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

    public static String buscarStatusPedido(int idPedido) {
        String sql = "select status_pedido from pedido where id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status_pedido");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar status do pedido: " + e.getMessage());
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

    public static void cancelarPedido(int idPedido) {
        String sqlItens = "select id_produtos, quantidade from item_pedido where id_pedido = ?";
        String sqlUpdateEstoque = "update produtos set estoque = estoque + ? where id_produtos = ?";
        String sqlUpdatePedido = "update pedido set status_pedido = 'FINALIZADO' where id_pedido = ?"; // Tabela usa FINALIZADO no lugar de CANCELADO conforme o enum

        try (Connection conn = Conexao.criarNovaConexao()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psItens = conn.prepareStatement(sqlItens)) {
                psItens.setInt(1, idPedido);
                try (ResultSet rs = psItens.executeQuery()) {
                    try (PreparedStatement psEstoque = conn.prepareStatement(sqlUpdateEstoque)) {
                        while (rs.next()) {
                            psEstoque.setInt(1, rs.getInt("quantidade"));
                            psEstoque.setInt(2, rs.getInt("id_produtos"));
                            psEstoque.executeUpdate();
                        }
                    }
                }
            }

            try (PreparedStatement psPedido = conn.prepareStatement(sqlUpdatePedido)) {
                psPedido.setInt(1, idPedido);
                psPedido.executeUpdate();
            }

            conn.commit();
            System.out.println("Pedido #" + idPedido + " processado sob encerramento e estoque atualizado!");
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar pedido: " + e.getMessage());
        }
    }

    public static void alterarObservacao(int idPedido, String novaObs) {
        System.out.println("Nota: A coluna 'observacao' foi removida do banco de dados.");
        System.out.println("Pedido #" + idPedido + " validado, mas a observação não pôde ser salva.");
    }

    public static void linha() {
        System.out.println("============================================================================================");
    }
}