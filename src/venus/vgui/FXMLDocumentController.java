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
import venus.cpu.memoria.MemoriaRegistrador;
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
    private Button btnClock;
    @FXML
    private Button btnExec;
    @FXML
    private Button btnTrad;
    @FXML
    private Button btnPlay;
    @FXML
    private TextArea instrucoes;
    @FXML
    private TextArea status;
    @FXML
    private TextField parametros;
    

    private Tradutor tradutor;
    private Cpu cpu;
    private boolean dump;
    private int inicio;
    private int qtdPalavras;
    private boolean screen;
    private boolean pause;
    private String nomeArquivo;

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
    
    private void atualizaRegistradores(){
        
    }

    @FXML
    private void clock(ActionEvent event) {

    }

    @FXML
    private void exec(ActionEvent event) {
         while (MemoriaRegistrador.TESTE){
             
         }
    }

    @FXML
    public void iniciar(ActionEvent event) {
        String[] param =  parametros.getText().split(" ");
        setParametros(param);
        ArrayList<Instrucao> bins = tradutor.traduzir(nomeArquivo,"OUT"+nomeArquivo);
        btnClock.setDisable(false);
        btnExec.setDisable(false);
        btnTrad.setDisable(false);
        for(Instrucao i: bins){
            instrucoes.appendText(i.getBin()+"\n");
        }
        cpu = new Cpu(nomeArquivo);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tradutor = Tradutor.getInstance();
        btnClock.setDisable(true);
        btnExec.setDisable(true);
        btnTrad.setDisable(true);
    }

}
