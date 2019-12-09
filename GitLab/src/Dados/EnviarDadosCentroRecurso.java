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
import pojo.CentroRecurso;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosCentroRecurso {

    private final String INCLUIRSQL = "INSERT INTO nutri_op_sinc.op725cre VALUES(?,?,?,?)";
    private final String ATUALIZARTABELAPADRAO = "INSERT IGNORE\n"
            + "   INTO nutri_op.op725cre\n"
            + " SELECT *\n"
            + "   FROM nutri_op_sinc.op725cre\n"
            + "      ;\n"
            + "      \n"
            + "      ";
    private final String LIMPARTABELA = "DELETE FROM nutri_op_sinc.op725cre";
    List<CentroRecurso> centroRecursoObj = new ArrayList<CentroRecurso>();

    public void EnviarDadosCentroRecurso(List<CentroRecurso> centroRecursoObj) {
        this.centroRecursoObj = centroRecursoObj;
        enviarDados();
    }

    public void enviarDados() {
        limparDados();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            for (CentroRecurso centroRecurso : centroRecursoObj) {
                ps.setInt(1, centroRecurso.getCodEmp());
                ps.setString(2, centroRecurso.getCodCre());
                ps.setString(3, centroRecurso.getDescCre());
                ps.setInt(4, centroRecurso.getCodEtg());
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
