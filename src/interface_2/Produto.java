package interface_2;

import java.util.Date;

public class Produto {
    private int id;
    private String nome;
    private String fornecedor;
    private double precoCompra;
    private double precoVenda;
    private int quantidade;
    private String categoria;
    private String marca;
    private String status;
    private Date data;

    // Construtor vazio
    public Produto() {
    }

    // Construtor com parâmetros
    public Produto(String nome, String fornecedor, double precoCompra, double precoVenda,
                   int quantidade, String categoria, String marca, String status, Date data) {
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.marca = marca;
        this.status = status;
        this.data = data;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", fornecedor='" + fornecedor + '\'' +
                ", precoCompra=" + precoCompra +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                ", categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}

