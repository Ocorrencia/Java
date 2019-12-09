/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import pojo.Motivo;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosMotivoParada {

    private final String INCLUIRSQL = "INSERT INTO nutri_op_sinc.op018mtv VALUES(?,?,?,?,?)";

    private final String ATUALIZARTABELAPADRAO = "INSERT IGNORE\n"
            + "   INTO nutri_op.op018mtv\n"
            + " SELECT *\n"
            + "   FROM nutri_op_sinc.op018mtv \n"
            + "      ;\n"
            + "      \n"
            + "      ";

    private final String LIMPARTABELA = "DELETE FROM nutri_op_sinc.op018mtv";
    List<Motivo> motivo = new ArrayList<Motivo>();

    public void EnviarDadosMotivoParada(List<Motivo> motivo) {
        this.motivo = motivo;
        enviarDados();
    }

    public void enviarDados() {
        limparDados();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            for (Motivo motivo : motivo) {
                ps.setInt(1, motivo.getCodEmp());
                ps.setString(2, motivo.getCodMtv());
                ps.setString(3, motivo.getDesMtv());
                ps.setInt(4, motivo.getCodAmp());
                ps.setString(5, motivo.getDesAmp());
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
