/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.tradutor.controllers;

import java.util.HashMap;
import java.util.Map;

/**
 *Classe que representa o pool de labels do sistema. A classe consiste de um Map contendo
 * chave e valor
 * @author diego
 */
public class LabelsPool {
    private Map<String,Short> mapLabels;
    
    public LabelsPool(){
        mapLabels = new HashMap();
    }
    /**
     * Método que adiciona uma String e um valor de uma label ao no Pool
     * @param key Chave que identifica o valor no Map
     * @param value Valor que está contido no Map para uma dada chave
     */
    public void add(String key, short value){            
            mapLabels.put(key, value);
    }
    /**
     * Método que retorna um valor da partir da chave
     * @param key Chave para encontrar o valor no Map
     * @return Retorna o valor que a chave representa
     */
    public Short get(String key){
        return mapLabels.get(key);        
    }
}
