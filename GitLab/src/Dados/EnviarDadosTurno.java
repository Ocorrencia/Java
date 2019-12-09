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
import pojo.Turno;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosTurno {

    private final String INCLUIRSQL = "INSERT INTO nutri_op_sinc.op000tur VALUES(?,?)";

    private final String ATUALIZARTABELAPADRAO = "INSERT IGNORE\n"
            + "   INTO nutri_op.op000tur\n"
            + " SELECT *\n"
            + "   FROM nutri_op_sinc.op000tur \n"
            + "      ;\n"
            + "      \n"
            + "      ";

    private final String LIMPARTABELA = "DELETE FROM nutri_op_sinc.op000tur";
    List<Turno> turno = new ArrayList<Turno>();

    public void EnviarDadosTurno(List<Turno> turno) {
        this.turno = turno;
        enviarDados();
    }

    public void enviarDados() {
        limparDados();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            for (Turno turno : turno) {
                ps.setInt(1, turno.getTurTrb());
                ps.setString(2, turno.getDesTrb().toUpperCase());
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
