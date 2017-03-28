/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 *Classe que contem um Map com chave|valor onde valor é um short que representa a chave
 * @author Diego
 */
public class Dicionario {
    private static Dicionario instance = null;
    private Map<String,Short> dicionarioDeOps;
    private Map<String,Short> dicionarioDeFuncs;
    private Map<String,Short> dicionarioDeTypes;
    private Map<String,Short> dicionarioDeRegs;
    private Map<String,Short> dicionarioDeRs;
    private Map<String,Short> dicionarioDeConds;
    private String arqOp;
    private String arqFunc;
    private String arqType;
    private String arqReg;
    private String arqR;
    private String arqCond;
        
    
    public static Dicionario getInstance(){
        if(instance != null)
            return instance;
        throw new ExceptionInInitializerError("Erro a instancia ainda nao foi criada");
    }
    /**
     * Método que retorna o objeto Dicionario(Singleton)
     * @param arqOp Nome do arquivo que contem o par chave|valor par os OPs
     * @param arqFunc Nome do arquivo que contem o par chave|valor par os Funcs
     * @param arqType Nome do arquivo que contem o par chave|valor par os Types
     * @param arqReg Nome do arquivo que contem o par chave|valor par os Registradores
     * @param arqR Nome do arquivo que contem o par chave|valor par os Rs
     * @param arqCond Nome do arquivo que contem o par chave|valor par os Conds
     * @return 
     */
    public static Dicionario getInstance(String arqOp,String arqFunc,String arqType,String arqReg,String arqR,String arqCond){
        if(instance != null){
            return instance;
        }
        instance = new Dicionario(arqOp,arqFunc,arqType, arqReg,arqR,arqCond);
        return instance;
    }
    /**
     * Construtor que inicializa o Map
     */
    private Dicionario(String arqOp,String arqFunc,String arqType,String arqReg,String arqR,String arqCond){
        dicionarioDeOps = new HashMap<String,Short>();
        dicionarioDeFuncs = new HashMap<String,Short>();
        dicionarioDeTypes = new HashMap<String,Short>();
        dicionarioDeRegs = new HashMap<String,Short>();
        dicionarioDeRs = new HashMap<String,Short>();
        dicionarioDeConds = new HashMap<String,Short>();
        this.arqOp = arqOp;
        this.arqFunc = arqFunc;
        this.arqType = arqType;
        this.arqReg = arqReg;
        this.arqR = arqR;
        this.arqCond = arqCond;
        inicializarDicionarioDeDados();
    }
    /**
     * Método que abre um arquivo e coloca o par chave|valor que está no arquivo no Map
     * @param nomeArq nome do arquivo a ser lido
     */
    private void carregar(String nomeArq){
        try{                       
            BufferedReader arquivo = new BufferedReader(new FileReader(nomeArq));
            String linha = arquivo.readLine();
            while(linha != null){
                String termos[] = linha.split(":");                
                add(termos[0],Short.parseShort(termos[1]),nomeArq);
                linha = arquivo.readLine();
            }
            arquivo.close();
        }
        catch(Exception e){
            //Erro
        }        
    }
    /**
     * Método que inicializa o dicionario lendo dos arquivos os pares chaves|valor.
     * Arquivos: op, func, type
     */
    private void inicializarDicionarioDeDados(){                
            carregar(arqOp);                
            carregar(arqFunc);
            carregar(arqType);
            carregar(arqReg);
            carregar(arqR);
            carregar(arqCond);
    }
    /**
     * Método que adiciona elementos com chave|valor
     * @param key chave do elemento a ser inserido
     * @param value valor a ser inserido
     */
    private void add(String key,short value,String tipoArq){
        if(tipoArq.equals(arqOp)){
            dicionarioDeOps.put(key, value);
        }
        else if(tipoArq.equals(arqFunc)){
            dicionarioDeFuncs.put(key, value);
        }
        else if(tipoArq.equals(arqType)){
            dicionarioDeTypes.put(key, value);
        }
        else if(tipoArq.equals(arqReg)){
            dicionarioDeRegs.put(key, value);
        }
        else if(tipoArq.equals(arqR)){
            dicionarioDeRs.put(key, value);
        }
        else{
            dicionarioDeConds.put(key, value);
        }
    }    
    /**
     * Método que retorna um valor a partir da chave passada, representando o OP
     * @param key chave que é usada para encontrar uma valor no map de OPS
     * @return retorna o valor que esta no dicionario
     */
    public short getFunc(String key){
        return dicionarioDeFuncs.get(key);
    }
    /**
     * Método que retorna um valor a partir da chave passada, representando o Func
     * @param key chave que é usada para encontrar uma valor no map de Funcs
     * @return retorna o valor que esta no dicionario de funcs
     */
    public short getOp(String key){
        return dicionarioDeOps.get(key);
    }
    /**
     * Método que retorna um valor a partir da chave passada, representando o Type
     * @param key chave que é usada para encontrar uma valor no map de Types
     * @return retorna o valor que esta no dicionario de types
     */
    public short getType(String key){
        return dicionarioDeTypes.get(key);
    }
    /**
     * Método que retorna um valor a partir da chave passada, representando o Cond
     * @param key chave que é usada para encontrar uma valor no map de Conds
     * @return retorna o valor que esta no dicionario
     */
    public short getCond(String key){
        return dicionarioDeConds.get(key);
    }
    /**
     * Método que retorna um valor a partir da chave passada, representando o Registrador
     * @param key chave que é usada para encontrar uma valor no map de Regs
     * @return retorna o valor que esta no dicionario
     */
    public short getReg(String key){
        return dicionarioDeRegs.get(key);
    }
    /**
     * Método que retorna um valor a partir da chave passada, representando o R
     * @param key chave que é usada para encontrar uma valor no map de Rs
     * @return retorna o valor que esta no dicionario
     */
    public short getR(String key){
        return dicionarioDeRs.get(key);
    }
}
