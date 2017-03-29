/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.tradutor.controllers;

import java.io.FileWriter;
import java.util.ArrayList;
import venus.persist.Dicionario;
import venus.persist.Arquivo;
import venus.tradutor.instrucoes.Halt;
import venus.tradutor.instrucoes.Instrucao;
import venus.tradutor.instrucoes.Instrucao1;
import venus.tradutor.instrucoes.Instrucao2;
import venus.tradutor.instrucoes.Instrucao3;
import venus.tradutor.instrucoes.Instrucao4;
import venus.tradutor.instrucoes.Instrucao5;
import venus.tradutor.instrucoes.Instrucao6;
import venus.tradutor.instrucoes.Instrucao7;

/**
 *
 * @author costa
 */
public class Tradutor {

    private static Tradutor instance = null;
    private LabelsPool labels;
    private Dicionario dic;

    private Tradutor() {
        this.labels = new LabelsPool();
        this.dic = Dicionario.getInstance("op", "func", "func", "reg", "r", "cond");
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
    public ArrayList<Instrucao> traduzir(String nomeOrigen, String nomeDestino) {
        Arquivo arquivo = Arquivo.getInstance();
        Cleanner cleanner = Cleanner.getInstance();
        String arq = arquivo.ler(nomeOrigen);
        arq = cleanner.clean(arq);
        arq = lerLabels(arq);
//        System.err.println("LABELS TEST");
//        labels.test();
        ArrayList<Instrucao> instrucoes = factory(arq);
        escrever(instrucoes, nomeDestino);
        return instrucoes;
    }

    private void escrever(ArrayList<Instrucao> instrucoes, String nomeArquivo) {
        try {
            FileWriter arq = new FileWriter(nomeArquivo);
            for (Instrucao instrucao : instrucoes) {
//                if (instrucao instanceof Instrucao1) {
//                    System.err.print("TIPO1:");
//                } else if (instrucao instanceof Instrucao2) {
//                    System.err.print("TIPO2:");
//                } else if (instrucao instanceof Instrucao3) {
//                    System.err.print("TIPO3:");
//                } else if (instrucao instanceof Instrucao4) {
//                    System.err.print("TIPO4:");
//                } else if (instrucao instanceof Instrucao5) {
//                    System.err.print("TIPO5:");
//                } else if (instrucao instanceof Instrucao6) {
//                    System.err.print("TIPO6:");
//                } else if (instrucao instanceof Instrucao7) {
//                    System.err.print("TIPO7:");
//                }
//
//                System.err.println(instrucao.getBin());

                arq.write(instrucao.getBin() + "\n");
            }
            arq.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Instrucao factoryTipo1(String instrucao) {
        String[] parts = instrucao.split(" ");
        String[] parametros = parts[1].split(",");
        short func = dic.getFunc(parts[0]);
        short rc = dic.getReg(parametros[0]);
        short op = dic.getOp(parts[0]);
        short ra = 0;
        short rb = 0;
        if (parametros.length > 1) {
            //  System.err.println("Parametro 2: " + dic.getReg(parametros[1]));
            ra = dic.getReg(parametros[1]);
        }
        if (parametros.length > 2) {
            //System.err.println("Parametro 3: " + dic.getReg(parametros[2]));
            rb = dic.getReg(parametros[2]);
        }
        return new Instrucao1(func, rc, op, ra, rb);
    }

    private Instrucao factoryTipo2(String instrucao) {
        String[] parts = instrucao.split(" ");
        String[] parametros = parts[1].split(",");
        short func = dic.getFunc(parts[0]);
        short rc = dic.getReg(parametros[0]);
        short offset11 = Short.parseShort(parametros[1]);
        return new Instrucao2(func, rc, offset11);
    }

    private Instrucao factoryTipo3(String instrucao) {
//        System.out.println("INSTRUCAO: " + instrucao);
        String[] parts = instrucao.split(" ");
//        System.out.println("PART 0: " + parts[0]);
        String[] parametros = parts[1].split(",");
        short func = dic.getFunc(parts[0]);
        short rc = dic.getReg(parametros[0]);
        short r = dic.getR(parts[0]);
        short offset8 = 0;
        int temp = Integer.parseInt(parametros[1]);
        if (r == 0) {
            offset8 = (short) (temp & 0b11111111);
        } else {
            temp = (short) (temp >>> 8);
            offset8 = (short) (temp & 0b11111111);
        }
        return new Instrucao3(func, rc, r, (short) 0, offset8);
    }

    private Instrucao factoryTipo4(String instrucao, short pc) {
        //System.err.println("Instrucao: F4: " + instrucao);
        String[] parts = instrucao.split(" ");
        //System.err.println("Parte 0: " + parts[0]);
        String[] funcao = parts[0].split("\\.");
        //System.err.println("Antes " + funcao[0]);
        short func = dic.getFunc(funcao[0]);
        short op = dic.getOp(funcao[0]);
        short cond = dic.getCond(funcao[1]);
        short offset8 = labels.get(parts[1]);
        offset8 = ((short) (offset8 - pc));
        System.err.println("FUNC: " + func);
        System.err.println("OP: " + op);
        System.err.println("COND: " + cond);
        System.err.println("OFSSET: " + offset8);
        return new Instrucao4(func, op, cond, offset8);
    }

    private Instrucao factoryTipo5(String instrucao, int pc) {
        String[] parts = instrucao.split(" ");
        short func = dic.getFunc(parts[0]);
        short op = dic.getOp(parts[0]);
        short offset12 = labels.get(parts[1]);
        offset12 = (short) (offset12 - pc);
        if (offset12 == 0) {
            return new Halt();
        } else {
            return new Instrucao5(func, op, offset12);
        }
    }

    private Instrucao factoryTipo6(String instrucao) {
        String[] parts = instrucao.split(" ");
        short func = dic.getFunc(parts[0]);
        short op = dic.getOp(parts[0]);
        short r = dic.getR(parts[0]);
        short rb = dic.getReg(parts[1]);
        return new Instrucao6(func, op, r, (short) 0, rb);
    }

    private Instrucao factoryTipo7(String instrucao) {
        String[] parts = instrucao.split(" ");
        String[] parametros = parts[1].split(",");
        short func = dic.getFunc(parts[0]);
        short op = dic.getOp(parts[0]);
        short rc = 0;
        short ra = 0;
        short rb = 0;
        if (op == 10100) {
            rc = dic.getReg(parametros[0]);
            ra = dic.getR(parametros[1]);
        } else {
            ra = dic.getReg(parametros[0]);
            rb = dic.getReg(parametros[1]);
        }
        return new Instrucao7(func, rc, op, ra, rb);
    }

    private ArrayList<Instrucao> factory(String arq) {
//        System.err.println("Arq Entrada:\n---------------\n" + arq + "\n---------------\n");
        ArrayList<Instrucao> bins = new ArrayList();
        String instrucoes[] = arq.split("\n");
        short pc = 0;
        for (String instrucao : instrucoes) {
//            System.err.println("PC:" + pc);
            String[] parts = instrucao.split(" ");
            if (parts[0].contains(".")) {
                bins.add(factoryTipo4(instrucao, pc));
            } else {
                String operacao = parts[0];
                System.err.println("Instrucao: " + instrucao);
                System.err.println("Operacao: " + operacao);
                short func = dic.getFunc(operacao);
                short op;
                switch (func) {
                    case 1:
                        op = dic.getOp(operacao);
                        if (op == 20 || op == 22) {
                            bins.add(factoryTipo7(instrucao));
                        } else {
                            bins.add(factoryTipo1(instrucao));
                        }
                        break;
                    case 2:
                        bins.add(factoryTipo2(instrucao));
                        break;
                    case 3:
                        bins.add(factoryTipo3(instrucao));
                        break;
                    case 0:
                        op = dic.getOp(operacao);
                        switch (op) {
                            case 2:
                                bins.add(factoryTipo5(instrucao, pc));
                                break;
                            case 3:
                                bins.add(factoryTipo6(instrucao));
                                break;
                            default:
                                bins.add(factoryTipo4(instrucao, pc));
                                break;
                        }

                }
            }
            pc++;
        }
        return bins;
    }

    private String lerLabels(String arq) {
        String arqFinal = "";
        String[] instrucoes = arq.split("\n");
        short line = 0;
        for (String instrucao : instrucoes) {

            if (instrucao.contains(":")) {
                String[] retorno = instrucao.split(":");
                retorno[1] = retorno[1].trim();
                labels.add(retorno[0], line);
                arqFinal += retorno[1] + "\n";
            } else {
                arqFinal += instrucao + "\n";
            }
            line++;
        }
        return arqFinal;
    }

}
