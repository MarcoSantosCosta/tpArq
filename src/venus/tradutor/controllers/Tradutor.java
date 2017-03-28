/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.tradutor.controllers;

/**
 *
 * @author costa
 */
public class Tradutor {

    private static Tradutor instance = null;
    private LabelsPool labels;

    private Tradutor() {
        this.labels = new LabelsPool();
    }

    public static Tradutor getInstance() {
        if (instance == null) {
            instance = new Tradutor();
        }
        return instance;
    }

    /**
     * Metodo que realiza a tradução de um arquivo em assemble para sua
     * codificacao binaria.
     *
     * @param nomeOrigen nome do arquivo contendo o codigo que sera convertido.
     * @param nomeDestino nome do arquivo onde sera escrito o o codigo
     * convertido.
     */
    public void traduzir(String nomeOrigen, String nomeDestino) {
        
    }

    private String lerLabels(String arq) {
        String arqFinal = "";
        String[] instrucoes = arq.split("\n");
        short line = 0;
        for (String instrucao : instrucoes) {
            if (instrucao.contains(":")) {
                String[] retorno = instrucao.split(":");
                labels.add(retorno[0], line);
                arqFinal += retorno[1];
            } else {
                arqFinal += instrucao;
            }
        }
        return arqFinal;
    }
    
    
}
