
package DAO;

import interface_1.ConexaoMySQL;
import interface_1.Livro;
import java.util.List;


public class LivroDAO {
    private final ConexaoMySQL conexao;

    public LivroDAO() {
        conexao = new ConexaoMySQL();
        conexao.conectar();
    }

    public void inserirLivro(Livro livro) {
        conexao.inserirLivro(livro);
    }

    public void atualizarLivro(Livro livro) {
        conexao.atualizarLivro(livro);
    }

    public void removerLivro(int id) {
        conexao.removerLivro(id);
    }

    public void deletarTodosLivros() {
        conexao.deletarTodosLivros();
    }

    public List<Livro> recuperarTodosLivros() {
        return conexao.recuperarTodosLivros();
    }
}
