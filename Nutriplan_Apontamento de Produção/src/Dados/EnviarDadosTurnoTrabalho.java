/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import pojo.Operador;
import pojo.TurnoTrabalho;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosTurnoTrabalho {

    private final String INCLUIRSQL = "INSERT INTO nutri_op_sinc.op803trb VALUES(?,?,?,?,?,?,?,?)";
    private final String ATUALIZARTABELAPADRAO = "REPLACE\n"
            + "   INTO nutri_op.op803trb\n"
            + " SELECT *\n"
            + "   FROM nutri_op_sinc.op803trb \n"
            + "      ;\n"
            + "      \n"
            + "      ";

    private final String LIMPARTABELA = "DELETE FROM nutri_op_sinc.op803trb";
    List<TurnoTrabalho> turnTrabalho = new ArrayList<TurnoTrabalho>();

    public void EnviarDadosTurnoTrabalho(List<TurnoTrabalho> turnoTrabalhoobj) {
        this.turnTrabalho = turnoTrabalhoobj;
        enviarDados();
    }

    public void enviarDados() {
        limparDados();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            for (TurnoTrabalho turnoTrabalho : turnTrabalho) {
                ps.setInt(1, turnoTrabalho.getCodEmp());
                ps.setInt(2, turnoTrabalho.getCodEtg());
                ps.setString(3, turnoTrabalho.getCodCre());
                ps.setInt(4, turnoTrabalho.getTurTrb());
                ps.setInt(5, turnoTrabalho.getSeqHtr());
                ps.setString(6, turnoTrabalho.getHorIni());
                ps.setString(7, turnoTrabalho.getHorFim());
                ps.setString(8, turnoTrabalho.getTipHtr());
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
