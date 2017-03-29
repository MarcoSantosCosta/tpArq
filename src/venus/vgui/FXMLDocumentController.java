/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.vgui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import venus.cpu.Cpu;
import venus.cpu.memoria.MemoriaPrincipal;
import venus.cpu.memoria.MemoriaRegistrador;
import venus.cpu.unidades.Controle;
import venus.cpu.unidades.ID;
import venus.cpu.unidades.IF;
import venus.cpu.unidades.MEM;
import venus.cpu.unidades.WB;
import venus.tradutor.controllers.Tradutor;
import venus.tradutor.instrucoes.Instrucao;

/**
 *
 * @author Marco
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lbR0;
    @FXML
    private Label lbR1;
    @FXML
    private Label lbR2;
    @FXML
    private Label lbR3;
    @FXML
    private Label lbR4;
    @FXML
    private Label lbR5;
    @FXML
    private Label lbR6;
    @FXML
    private Label lbR7;
    @FXML
    private Label lbPC;
    @FXML
    private Label lbNeg;
    @FXML
    private Label lbZero;
    @FXML
    private Label lbcCarry;
    @FXML
    private Label lbNegZero;
    @FXML
    private Label lbOverFlow;
    @FXML
    private Label lblclock;
    @FXML
    private Button btnClock;
    @FXML
    private Button btnExec;
    @FXML
    private Button btnPlay;
    @FXML
    private TextArea instrucoes;
    @FXML
    private TextArea status;
    @FXML
    private TextField parametros;
    @FXML
    private TextArea txtMemoria;

    private Tradutor tradutor;
    private Cpu cpu;
    private MemoriaRegistrador banco;
    private MemoriaPrincipal mem;
    private IF If;
    private ID id;
    private MEM memoria;
    private Controle controle;
    private WB wb;
    private boolean dump;
    private int inicio;
    private int qtdPalavras;
    private boolean screen;
    private boolean pause;
    private String nomeArquivo;
    private int clock;

    public void setParametros(String[] args) {
        nomeArquivo = args[0];
        int i = 1;
        while (args.length > i) {
            String parametro = args[i].trim();
            if (parametro.equals("-d")) {
                dump = true;
                i++;
                parametro = args[i].trim();
                if (parametro.startsWith("0x")) {
                    parametro = parametro.substring(2);
                }
                inicio = Integer.parseInt(parametro);
                i++;
                parametro = args[i].trim();
                if (parametro.startsWith("0x")) {
                    parametro = parametro.substring(2);
                }
                qtdPalavras = Integer.parseInt(parametro);
            } else if (parametro.equals("-s")) {
                screen = true;
            } else if (parametro.equals("-p")) {
                pause = true;
            }
            i++;
        }
    }

    private void atualizaRegistradores() {
        lbR0.setText("" + banco.get((short) 0));
        lbR0.setText("" + banco.get((short) 1));
        lbR0.setText("" + banco.get((short) 3));
        lbR0.setText("" + banco.get((short) 4));
        lbR0.setText("" + banco.get((short) 5));
        lbR0.setText("" + banco.get((short) 6));
        lbR0.setText("" + banco.get((short) 7));
        lbPC.setText("" + banco.getPc());
    }

    private void mostarDump() {
        txtMemoria.clear();
        for (int i = inicio; i < inicio + qtdPalavras; i++) {
            txtMemoria.appendText(i + "-" + mem.get(i) + "\n");
        }
    }

    private void mostraReg() {
        status.appendText("PC: " + banco.getPc() + "\n");
        status.appendText("R0: " + banco.get((short) 0) + " ");
        status.appendText("R1: " + banco.get((short) 1) + " ");
        status.appendText("R2: " + banco.get((short) 2) + " ");
        status.appendText("R3: " + banco.get((short) 3) + " ");
        status.appendText("R4: " + banco.get((short) 4) + " ");
        status.appendText("R5: " + banco.get((short) 5) + " ");
        status.appendText("R6: " + banco.get((short) 6) + " ");
        status.appendText("R7: " + banco.get((short) 7) + " ");
    }

    private void mostraInfoCPU() {
        status.appendText("\n----IF----\n");
        status.appendText(If.getStatus());
        status.appendText("\n----CONTROLE----\n");
        status.appendText(controle.getStatus());
        status.appendText("\n----ID----\n");
        status.appendText(id.getStatus());
        status.appendText("\n----ULA----\n");
        //status.appendText(ula.getStatus());
        status.appendText("\n----MEM----\n");
        status.appendText(memoria.getStatus());
        status.appendText("\n----WB----\n");
        status.appendText(wb.getStatus());

    }

    private void atualizar() {
        clock++;
        lblclock.setText("" + clock);
        atualizaRegistradores();
        if (screen) {
            status.appendText("------------------\nCLOCK: "+clock+"\n-------");
            mostraReg();
            mostraInfoCPU();
        }
        if (dump) {
            mostarDump();
        }
    }

    @FXML
    private void clock(ActionEvent event) {
        cpu.clock();
        atualizar();
        if (!MemoriaRegistrador.TESTE) {
            btnClock.setDisable(true);
        }

    }

    @FXML
    private void exec(ActionEvent event) {
        while (MemoriaRegistrador.TESTE) {
            cpu.clock();
            atualizar();
        }
    }

    @FXML
    public void iniciar(ActionEvent event) {
        String[] param = parametros.getText().split(" ");
        setParametros(param);
        ArrayList<Instrucao> bins = tradutor.traduzir(nomeArquivo, "OUT" + nomeArquivo);
        btnClock.setDisable(false);
        btnExec.setDisable(false);
        for (Instrucao i : bins) {
            instrucoes.appendText(i.getBin() + "\n");
        }
        cpu = new Cpu("OUT" + nomeArquivo);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tradutor = Tradutor.getInstance();
        banco = MemoriaRegistrador.getInstance();
        mem = MemoriaPrincipal.getInstance();
        btnClock.setDisable(true);
        btnExec.setDisable(true);
        clock = 0;
    }

}
