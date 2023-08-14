package br.com.fiap.jdbc.dao;

import br.com.fiap.jdbc.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvarComCategoria(Produto produto) {
        try {
            String sql = "INSERT INTO PRODUTO (NOME,DESCRICAO,idCategoria) VALUES (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getIdCategoria());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<Produto>();
        try {
            String sql = "SELECT * FROM PRODUTO";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdproduto(rs.getInt(1));
                produto.setNome(rs.getString(2));
                produto.setDescricao(rs.getString(3));
                produto.setIdCategoria(rs.getInt(4));
                produtos.add(produto);
            }
            stmt.close();
            rs.close();
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> ListarPorCategoria(int idCategoria) {
        List<Produto> produtos = new ArrayList<Produto>();
        try {
            String sql = "SELECT * FROM PRODUTO WHERE idCategoria = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdproduto(rs.getInt(1));
                produto.setNome(rs.getString(2));
                produto.setDescricao(rs.getString(3));
                produto.setIdCategoria(rs.getInt(4));
                produtos.add(produto);
            }
            stmt.close();
            rs.close();
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String descricao, int idCategoria, int idProduto) {
        try {
            String sql = "UPDATE PRODUTO P SET P.nome = ?, P.descricao = ?, P.idCategoria=? WHERE idProduto = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setInt(3, idCategoria);
            stmt.setInt(4, idProduto);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(int id) {
        try {
            String sql = "DELETE FROM PRODUTO WHERE idProduto = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
