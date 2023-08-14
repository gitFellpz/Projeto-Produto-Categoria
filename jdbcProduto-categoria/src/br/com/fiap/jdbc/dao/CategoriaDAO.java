package br.com.fiap.jdbc.dao;

import br.com.fiap.jdbc.model.Categoria;
import br.com.fiap.jdbc.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private final Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listarTodos() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        try {
            String sql = "SELECT * FROM categoria";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria produto = new Categoria();
                produto.setIdCategoria(rs.getInt(1));
                produto.setNome(rs.getString(2));
                categorias.add(produto);
            }
            stmt.close();
            rs.close();
            return categorias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  List<Categoria> listarComProdutos(){
        try {
            Categoria categoriaAtual = null;
            List<Categoria> categorias = new ArrayList<Categoria>();
            String sql = "SELECT C.idCategoria, C.nome, P.idProduto,P.nome,P.descricao,P.idCategoria" +
                    "SELECT CATEGORIA C INNER JOIN PRODUTO P ON C.idCategoria = P.idCategoria order by C.idCategoria";
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.execute();
                ResultSet rs = stmt.getResultSet();
                while (rs.next()){
                    if(categoriaAtual == null || !categoriaAtual.getNome().equals(rs.getString(2))){
                        Categoria categoria = new Categoria();
                        categoria.setIdCategoria(rs.getInt(1));
                        categoria.setNome(rs.getString(2));
                        categorias.add(categoria);
                        categoriaAtual = categoria;
                    }
                    Produto produto = new Produto();
                    produto.setIdproduto(rs.getInt(3));
                    produto.setNome(rs.getString(4));
                    produto.setDescricao(rs.getString(5));
                    produto.setIdproduto(rs.getInt(6));
                    categoriaAtual.adicionarProduto(produto);
                }
                rs.close();
                stmt.close();
                return categorias;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
