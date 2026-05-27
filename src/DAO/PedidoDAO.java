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
    
    String sqlPedido = "insert into pedido (id_clientes, id_vendedor, status_pedido, observacao, valor_total) values (?, ?, ?, ?, ?)";
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
        
        psPedido.setString(4, observacao); 
        psPedido.setDouble(5, total);

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
                int idCliente = resultSet.getInt("id_clientes");
                int idVendedor = resultSet.getInt("id_vendedor");
                int idProduto = resultSet.getInt("id_produtos");

                String nomeProduto = resultSet.getString("nome_produtos");
                int quantidade = resultSet.getInt("quantidade");
                float precouni = resultSet.getFloat("preco_unitario");
                float subTotal = resultSet.getFloat("subtotal");
                float totalValor = resultSet.getFloat("valor_total");

                String nomeCliente = resultSet.getString("nome_clientes");
                String cpf = resultSet.getString("cpf_clientes");

                int idvendedor = resultSet.getInt("id_vendedor");
                String nomev = resultSet.getString("nome_vendedor");
                String telVendedor = resultSet.getString("telefone_vendedor");

                String status = resultSet.getString("status_pedido");
                
                String obs = resultSet.getString("observacao");
                
                LocalDateTime data = resultSet.getTimestamp("data_pedido").toLocalDateTime();
                
                linha();
                System.out.println("||\t\t\t\t\tPEDIDO: " + idPedido + " \t\t\t\t\t||");
                linha();
                System.out.printf("|| Cliente: %-20s \t Cpf: %-14s\t||%n", nomeCliente, cpf);
                
                if (obs != null && !obs.trim().isEmpty()) {
                    System.out.printf("|| OBSERVAÇÃO: %-71s ||%n", obs);
                }
                
                linha();
                
                new ItemPedidoDao().mostrarItemPedido(idPedido);
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
        String sql = "select pr.nome_produtos, sum(ip.quantidade) as total_unidades, sum(ip.subtotal) as total_arrecadado " +
                     "from item_pedido ip " +
                     "inner join produtos pr on ip.id_produtos = pr.id_produtos " +
                     "group by pr.id_produtos, pr.nome_produtos " +
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
        System.out.println("============================================================================================");
    }
}