package br.com.fiap.jdbc.model;

public class Produto {
    private int idproduto, idCategoria;
    private  String nome,descricao;
    public  Produto(){}

    public Produto( String nome, String descricao, int idCategoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
