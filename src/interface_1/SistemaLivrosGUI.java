package interface_1;

import DAO.LivroDAO;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaLivrosGUI extends JFrame {

    private DefaultTableModel tableModel;
    private JTable tableLivros;
    private JTextField textFieldEditora;
    private JTextField textFieldNome;
    private JTextField textFieldISBN;
    private LivroDAO livroDAO;

    public SistemaLivrosGUI() {
        livroDAO = new LivroDAO();

        setTitle("Cadastro de Livros");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelCadastro = new JPanel(new GridLayout(4, 2));

        JLabel labelEditora = new JLabel("Editora:");
        textFieldEditora = new JTextField();

        JLabel labelNome = new JLabel("Nome do Livro:");
        textFieldNome = new JTextField();

        JLabel labelISBN = new JLabel("ISBN:");
        textFieldISBN = new JTextField();

        tableModel = new DefaultTableModel();
        tableLivros = new JTable(tableModel);

        JButton buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
                atualizarListaLivros();
            }

        });

        panelCadastro.add(labelEditora);
        panelCadastro.add(textFieldEditora);
        panelCadastro.add(labelNome);
        panelCadastro.add(textFieldNome);
        panelCadastro.add(labelISBN);
        panelCadastro.add(textFieldISBN);
        panelCadastro.add(new JLabel());
        panelCadastro.add(buttonCadastrar);

        tableModel = new DefaultTableModel();
        tableLivros = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Editora");
        tableModel.addColumn("ISBN");

        TableColumn columnId = tableLivros.getColumnModel().getColumn(0);
        columnId.setPreferredWidth(1);


        TableColumn columnIsbn = tableLivros.getColumnModel().getColumn(3);
        columnIsbn.setPreferredWidth(1);

        JPanel panelLivros = new JPanel(new BorderLayout());
        panelLivros.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        JLabel labelLivrosCadastrados = new JLabel("Livros Cadastrados:");
        labelLivrosCadastrados.setFont(new Font("Arial", Font.BOLD, 16));
        panelLivros.add(labelLivrosCadastrados, BorderLayout.NORTH);
        panelLivros.add(new JScrollPane(tableLivros), BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        JButton buttonDeletar = new JButton("Deletar");
        buttonDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarLivroSelecionado();
            }
        });

        JButton buttonAtualizar = new JButton("Atualizar");
        buttonAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLivroSelecionado();
                atualizarListaLivros();
            }
        });

        JButton buttonDeletarTodos = new JButton("Deletar Todos");
        buttonDeletarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarTodosLivros();
            }
        });

        panelBotoes.add(buttonDeletar);
        panelBotoes.add(buttonAtualizar);
        panelBotoes.add(buttonDeletarTodos);

        add(panelCadastro, BorderLayout.NORTH);
        add(panelLivros, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);


        setVisible(true);
    }

    private void cadastrarLivro() {
        String editora = textFieldEditora.getText();
        String nome = textFieldNome.getText();
        String isbn = textFieldISBN.getText();

        Livro livro = new Livro(editora, nome, isbn);
        livroDAO.inserirLivro(livro);
    }

    private void atualizarListaLivros() {
        List<Livro> livros = livroDAO.recuperarTodosLivros();
        tableModel.setRowCount(0);
        for (Livro livro : livros) {
            Object[] rowData = {livro.getId(), livro.getNome(), livro.getEditora(), livro.getIsbn()};
            tableModel.addRow(rowData);
        }
    }

    private void deletarLivroSelecionado() {
        int selectedRow = tableLivros.getSelectedRow();
        if (selectedRow != -1) {
            int livroId = (int) tableModel.getValueAt(selectedRow, 0);
            livroDAO.removerLivro(livroId);
            atualizarListaLivros();
        }
    }

    private void atualizarLivroSelecionado() {
        int selectedRow = tableLivros.getSelectedRow();
        if (selectedRow != -1) {
            int livroId = (int) tableModel.getValueAt(selectedRow, 0);
            String editora = textFieldEditora.getText();
            String nome = textFieldNome.getText();
            String isbn = textFieldISBN.getText();

            Livro livroSelecionado = new Livro(editora, nome, isbn);

            livroDAO.atualizarLivro(livroSelecionado);
            atualizarListaLivros();
        }
    }

    private void deletarTodosLivros() {
        livroDAO.deletarTodosLivros();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaLivrosGUI::new);
    }

}

