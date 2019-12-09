/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import pojo.Defeito;
import pojo.Operador;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosDefeito {

    private final String INCLUIRSQL = "INSERT INTO nutri_op_sinc.op011def VALUES(?,?,?)";
    private final String ATUALIZARTABELAPADRAO = "INSERT IGNORE\n"
            + "   INTO nutri_op.op011def\n"
            + " SELECT *\n"
            + "   FROM nutri_op_sinc.op011def\n"
            + "      ;\n"
            + "      \n"
            + "      ";
    private final String LIMPARTABELA = "DELETE FROM nutri_op_sinc.op011def";
    List<Defeito> defeito = new ArrayList<Defeito>();

    public void EnviarDadosDefeito(List<Defeito> operadorObj) {
        this.defeito = operadorObj;
        enviarDados();
    }

    public void enviarDados() {
        limparDados();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            for (Defeito objDefeito : defeito) {
                ps.setInt(1, objDefeito.getCodEmp());
                ps.setString(2, objDefeito.getCodDft());
                ps.setString(3, objDefeito.getDesDft());
                ps.executeUpdate();
            }
            ConexaoMysql.FecharConexao();
            atualizarTabelaPadrao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarTabelaPadrao() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(ATUALIZARTABELAPADRAO);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limparDados() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(LIMPARTABELA);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
