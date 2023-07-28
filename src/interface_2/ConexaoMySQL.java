package interface_2;

import java.sql.*;
import  java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoMySQL {
    private final String URL = "jdbc:mysql://localhost:3306/atividade_produtos";
    private final String USUARIO = "joao_victor";
    private final String SENHA = "Poring123*";

    // Método para realizar a conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Método para inserir um novo registro na tabela "produto"
    public void inserirProduto(Produto produto) {
        try (Connection connection = conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO produto (nome, fornecedor, preco_compra, preco_venda, quantidade, categoria, marca, status, data) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getFornecedor());
            preparedStatement.setDouble(3, produto.getPrecoCompra());
            preparedStatement.setDouble(4, produto.getPrecoVenda());
            preparedStatement.setInt(5, produto.getQuantidade());
            preparedStatement.setString(6, produto.getCategoria());
            preparedStatement.setString(7, produto.getMarca());
            preparedStatement.setString(8, produto.getStatus());
            preparedStatement.setDate(9, new java.sql.Date(produto.getData().getTime()));

            preparedStatement.executeUpdate();
            System.out.println("Produto inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    // Método para atualizar um registro na tabela "produto" com base no ID
    public void atualizarProduto(Produto produto) {
        try (Connection connection = conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE produto SET nome=?, fornecedor=?, preco_compra=?, preco_venda=?, quantidade=?, " +
                             "categoria=?, marca=?, status=?, data=? WHERE id=?")) {

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getFornecedor());
            preparedStatement.setDouble(3, produto.getPrecoCompra());
            preparedStatement.setDouble(4, produto.getPrecoVenda());
            preparedStatement.setInt(5, produto.getQuantidade());
            preparedStatement.setString(6, produto.getCategoria());
            preparedStatement.setString(7, produto.getMarca());
            preparedStatement.setString(8, produto.getStatus());
            preparedStatement.setDate(9, new java.sql.Date(produto.getData().getTime()));
            preparedStatement.setInt(10, produto.getId());

            preparedStatement.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // Método para apagar um registro na tabela "produto" com base no ID
    public void apagarProduto(int id) {
        try (Connection connection = conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM produto WHERE id=?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Produto apagado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao apagar produto: " + e.getMessage());
        }
    }

    // Método para consultar todos os registros da tabela "produto"
    public void consultarProdutos() {
        try (Connection connection = conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM produto")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String fornecedor = resultSet.getString("fornecedor");
                double precoCompra = resultSet.getDouble("preco_compra");
                double precoVenda = resultSet.getDouble("preco_venda");
                int quantidade = resultSet.getInt("quantidade");
                String categoria = resultSet.getString("categoria");
                String marca = resultSet.getString("marca");
                String status = resultSet.getString("status");
                Date data = resultSet.getDate("data");

                // Crie uma instância de Produto com os dados consultados ou faça o que for necessário com os resultados
                Produto produto = new Produto(nome, fornecedor, precoCompra, precoVenda, quantidade, categoria, marca, status, data);
                produto.setId(id);

                System.out.println(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar produtos: " + e.getMessage());
        }
    }
}

