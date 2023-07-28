package interface_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroProdutosGUI extends JFrame {
    private JTextField nomeField, fornecedorField, precoCompraField, precoVendaField, quantidadeField;
    private JComboBox<String> categoriaComboBox, marcaComboBox, statusComboBox;
    private JFormattedTextField dataField;
    private JButton salvarButton, atualizarButton, apagarButton, limparButton;
    private ConexaoMySQL conexaoMySQL;

    public CadastroProdutosGUI() {
        super("Cadastro de Produtos");
        conexaoMySQL = new ConexaoMySQL();
        setupUI();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        JPanel buttonPanel = new JPanel();

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);
        JLabel fornecedorLabel = new JLabel("Fornecedor:");
        fornecedorField = new JTextField(20);
        JLabel precoCompraLabel = new JLabel("Preço de Compra:");
        precoCompraField = new JTextField(10);
        JLabel precoVendaLabel = new JLabel("Preço de Venda:");
        precoVendaField = new JTextField(10);
        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadeField = new JTextField(5);
        JLabel categoriaLabel = new JLabel("Categoria:");
        categoriaComboBox = new JComboBox<>(new String[]{"HD", "MONITOR", "TECLADO", "MOUSE", "CPU", "GPU", "GABINETE", "MEMÓRIA", "COOLER", "ROTEADOR", "NOTEBOOK"});
        JLabel marcaLabel = new JLabel("Marca:");
        marcaComboBox = new JComboBox<>(new String[]{"Samsung", "HP", "Apple", "LeNovo", "Motorola", "Microsoft"});
        JLabel statusLabel = new JLabel("Status:");
        statusComboBox = new JComboBox<>(new String[]{"Novo", "Usado"});
        JLabel dataLabel = new JLabel("Data (dd/MM/yyyy):");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dataField = new JFormattedTextField(dateFormat);
        dataField.setColumns(10);

        salvarButton = new JButton("Salvar");
        atualizarButton = new JButton("Atualizar");
        apagarButton = new JButton("Apagar");
        limparButton = new JButton("Limpar");

        formPanel.add(nomeLabel);
        formPanel.add(nomeField);
        formPanel.add(fornecedorLabel);
        formPanel.add(fornecedorField);
        formPanel.add(precoCompraLabel);
        formPanel.add(precoCompraField);
        formPanel.add(precoVendaLabel);
        formPanel.add(precoVendaField);
        formPanel.add(quantidadeLabel);
        formPanel.add(quantidadeField);
        formPanel.add(categoriaLabel);
        formPanel.add(categoriaComboBox);
        formPanel.add(marcaLabel);
        formPanel.add(marcaComboBox);
        formPanel.add(statusLabel);
        formPanel.add(statusComboBox);
        formPanel.add(dataLabel);
        formPanel.add(dataField);

        buttonPanel.add(salvarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(apagarButton);
        buttonPanel.add(limparButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Ação do botão "Salvar"
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });

        // Ação do botão "Atualizar"
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarProduto();
            }
        });

        // Ação do botão "Apagar"
        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apagarProduto();
            }
        });

        // Ação do botão "Limpar"
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void salvarProduto() {
        String nome = nomeField.getText();
        String fornecedor = fornecedorField.getText();
        double precoCompra = Double.parseDouble(precoCompraField.getText());
        double precoVenda = Double.parseDouble(precoVendaField.getText());
        int quantidade = Integer.parseInt(quantidadeField.getText());
        String categoria = (String) categoriaComboBox.getSelectedItem();
        String marca = (String) marcaComboBox.getSelectedItem();
        String status = (String) statusComboBox.getSelectedItem();
        Date data = null;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(dataField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data inválida. Utilize o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Produto produto = new Produto(nome, fornecedor, precoCompra, precoVenda, quantidade, categoria, marca, status, data);
        conexaoMySQL.inserirProduto(produto);
    }

    private void atualizarProduto() {
        // Implemente a lógica para atualizar o produto com os campos preenchidos pelo usuário
        // Use o método conexaoMySQL.atualizarProduto(produto) para atualizar o produto no banco de dados
    }

    private void apagarProduto() {
        // Implemente a lógica para apagar o produto com base no ID informado pelo usuário
        // Use o método conexaoMySQL.apagarProduto(id) para apagar o produto no banco de dados
    }

    private void limparCampos() {
        nomeField.setText("");
        fornecedorField.setText("");
        precoCompraField.setText("");
        precoVendaField.setText("");
        quantidadeField.setText("");
        categoriaComboBox.setSelectedIndex(0);
        marcaComboBox.setSelectedIndex(0);
        statusComboBox.setSelectedIndex(0);
        dataField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroProdutosGUI();
            }
        });
    }
}

