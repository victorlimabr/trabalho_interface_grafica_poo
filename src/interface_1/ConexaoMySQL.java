package interface_1;

import java.sql.*;
import  java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;


public class ConexaoMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/cadastrolivros";
    private static final String USUARIO = "joao_victor";
    private static final String SENHA = "Poring123*";

    private Connection connection;

    public void conectar() {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("A conexão foi estabelecida.");
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Desconectado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }

    public void inserirLivro(Livro livro) {
        try {
            String sql = "INSERT INTO livros (editora, nome, isbn) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getEditora());
            statement.setString(2, livro.getNome());
            statement.setString(3, livro.getIsbn());
            statement.executeUpdate();
            System.out.println("Livro inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir livro: " + e.getMessage());
        }
    }

    public boolean removerLivro(int id) {
        try {
            String sql = "DELETE FROM livros WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Livro removido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao remover livro: " + e.getMessage());
        }
        return false;
    }

    public boolean atualizarLivro(Livro livro) {
        try {
            String sql = "UPDATE livros SET editora = ?, nome = ?, isbn = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getEditora());
            statement.setString(2, livro.getNome());
            statement.setString(3, livro.getIsbn());
            statement.setInt(4, livro.getId());
            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
            return false;
        }
    }

    public void deletarTodosLivros() {
        try {
            String sql = "DELETE FROM livros";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Todos os livros foram removidos.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar todos os livros: " + e.getMessage());
        }
    }
    public List<Livro> recuperarTodosLivros() {
        List<Livro> livros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM livros";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String editora = resultSet.getString("editora");
                String nome = resultSet.getString("nome");
                String isbn = resultSet.getString("isbn");
                Livro livro = new Livro(editora, nome, isbn);
                livro.setId(id);
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar todos os livros: " + e.getMessage());
        }
        return livros;
    }
}

