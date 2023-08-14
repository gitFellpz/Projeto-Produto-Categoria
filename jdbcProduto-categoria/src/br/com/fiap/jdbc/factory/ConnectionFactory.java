package br.com.fiap.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory{
    private final static String ORACLE = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public Connection conectarOracle() {
        try{
            System.out.println("Conectado");
            return DriverManager.getConnection(ORACLE,"rm93507","081002");
        }catch (SQLException e){
            System.out.println("Erro ao conectar: " + e);
            throw new RuntimeException(e);
        }
    }
}
