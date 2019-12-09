/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosMaquina {

    private final String ATUALIZAR = "UPDATE nutri_op.op000maq SET PRIOMAQ = ?";
    int prioridade;

    public void EnviarDadosMaquina(int prioridade) {
        this.prioridade = prioridade;
        enviarDados();
    }

    public void enviarDados() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(ATUALIZAR);
            ps.setInt(1, prioridade);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (SQLException e) {
        }
    }
}
