package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;                
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoOracle {

    public static String status = "Não conectou...";

    public ConexaoOracle() {

    }

    public static java.sql.Connection getConexaoOracle() {
        Connection connection = null;          //atributo do tipo Connection
        try {
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            //String url = "jdbc:mysql://10.1.1.234:3306/nutri_op?zeroDateTimeBehavior=convertToNull";
            String url = "jdbc:oracle:thin:@10.1.1.237:1521:ORA11GT";
            String username = "INTEGRACAO_OP";        //nome de um usuário de seu BD      
            String password = "ERPintegracao.4651";      //sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);
            //Testa sua conexão//  
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println(e);
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }

    public static ResultSet executaQuery(String sql) {
        try {
            Statement stmt = ConexaoOracle.getConexaoOracle().createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        try {
            ConexaoOracle.getConexaoOracle().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoOracle.getConexaoOracle();
    }

}
