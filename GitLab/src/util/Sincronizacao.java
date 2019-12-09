/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import pojoWebService.CentroRecursoWebService;
import pojoWebService.DefeitoWebService;
import pojoWebService.FichaTecnicaWebService;
import pojoWebService.MotivoParadaWebService;
import pojoWebService.OperadorWebService;
import pojoWebService.OrdemProducaoWebService;
import pojoWebService.PrioridadeMaquinaWebService;
import pojoWebService.TurnoTrabalhoWebService;
import pojoWebService.TurnoWebService;

/**
 *
 * @author diogo.melo
 */
public class Sincronizacao {

    public static boolean sincOperadores() {
        OperadorWebService operadorWebService = new OperadorWebService();
        return operadorWebService.buscarOperadoresSapiens();
    }

    public static boolean sincCentroRecurso() {
        CentroRecursoWebService centroRecursoWebService = new CentroRecursoWebService();
        return centroRecursoWebService.buscarCentroRecurso();
    }

    public static boolean sintTurnoTrabalho() {
        TurnoTrabalhoWebService centroRecursoWebService = new TurnoTrabalhoWebService();
        return centroRecursoWebService.buscarTurnoTrabalho();
    }

    public static boolean sincFichaTecnica() {
        FichaTecnicaWebService fichaTecnicaWebService = new FichaTecnicaWebService();
        return fichaTecnicaWebService.buscarFichaTenicaSapiens();
    }

    public static boolean sincTurno() {
        TurnoWebService turnoWebService = new TurnoWebService();
        return turnoWebService.buscarTurnoSapiens();
    }

    public static boolean sincMotivoParada() {
        MotivoParadaWebService motivoParadaWebService = new MotivoParadaWebService();
        return motivoParadaWebService.buscarMotivoParadaSapiens();
    }

    public static boolean sincOrdemProducao(boolean atualizarParcial) {
        OrdemProducaoWebService odemProducaoWebService = new OrdemProducaoWebService();
        return odemProducaoWebService.buscarOrdemProducaoSapiens(atualizarParcial);
    }

    public static boolean sincDefeito() {
        DefeitoWebService defeitoWebService = new DefeitoWebService();
        return defeitoWebService.buscarDefeitoSapiens();
    }
    
      public static boolean sincPrioridade() {
          PrioridadeMaquinaWebService prioridadeWebService = new PrioridadeMaquinaWebService();
        return prioridadeWebService.buscarPrioridadeSapiens();
    }
}
