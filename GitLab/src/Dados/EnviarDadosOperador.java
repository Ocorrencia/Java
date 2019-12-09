/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.Operador;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosOperador {

    private final String INCLUIRSQL = "INSERT INTO nutri_op_sinc.op906ope VALUES(?,?,?,?)";
    private final String ATUALIZARTABELAPADRAO = "INSERT IGNORE\n"
            + "   INTO nutri_op.op906ope\n"
            + " SELECT *\n"
            + "   FROM nutri_op_sinc.op906ope\n"
            + "      ;\n"
            + "      \n"
            + "      ";
    private final String LIMPARTABELA = "DELETE FROM nutri_op_sinc.op906ope";
    List<Operador> operador = new ArrayList<Operador>();

    public void EnviarDadosOperador(List<Operador> operadorObj) {
        this.operador = operadorObj;
        enviarDados();
    }

    public void enviarDados() {
        limparDados();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            for (Operador operador : operador) {
                ps.setInt(1, operador.getCodEmp());
                ps.setInt(2, operador.getNumCad());
                ps.setString(3, operador.getNomOpe());
                ps.setInt(4, operador.getTurTrb());
                ps.executeUpdate();
            }
            ConexaoMysql.FecharConexao();
            atualizarTabelaPadrao();
        } catch (SQLException e) {
        }
    }

    public void atualizarTabelaPadrao() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(ATUALIZARTABELAPADRAO);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (SQLException e) {
        }
    }

    public void limparDados() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(LIMPARTABELA);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (SQLException e) {
        }
    }
}
