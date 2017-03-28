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
    private String arqOp;
    private String arqFunc;
    private String arqType;
        
    /**
     * Método que retorna o objeto Dicionario(Singleton)
     * @param arqOp Nome do arquivo que contem o par chave|valor par os OPs
     * @param arqFunc Nome do arquivo que contem o par chave|valor par os Funcs
     * @param arqType Nome do arquivo que contem o par chave|valor par os Types
     * @return 
     */
    public static Dicionario getInstance(String arqOp,String arqFunc,String arqType){
        if(instance != null){
            return instance;
        }
        instance = new Dicionario(arqOp,arqFunc,arqType);
        return instance;
    }
    /**
     * Construtor que inicializa o Map
     */
    private Dicionario(String arqOp,String arqFunc,String arqType){
        dicionarioDeOps = new HashMap<String,Short>();
        dicionarioDeFuncs = new HashMap<String,Short>();
        dicionarioDeTypes = new HashMap<String,Short>();
        this.arqOp = arqOp;
        this.arqFunc = arqFunc;
        this.arqType = arqType;
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
        else{
            dicionarioDeTypes.put(key, value);
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
}
