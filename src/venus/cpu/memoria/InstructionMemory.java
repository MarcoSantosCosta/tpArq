/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu.memoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a memória de instrução do processador venus
 *
 * @author Diego
 */
public class InstructionMemory {

    private static InstructionMemory instance = null;
    private List<String> memoryPositions;
    private final int limit;

    /**
     * Méodo que retorna o limite da memória de instruções
     *
     * @return O limite da memória de instruções
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Método que retorna a memoria de instrução, sendo um singleton
     *
     * @return memória de instrução
     */
    public static InstructionMemory getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new InstructionMemory();
        return instance;
    }

    /**
     * Método que constroi a memória de instrução
     */
    private InstructionMemory() {
        memoryPositions = new ArrayList<String>();
        //tamMax da memória de instruções
        limit = 1528;
    }

    /**
     * Método que adiciona uma instrução na memória de instruções. É sempre
     * adicionada no final da memória
     *
     * @param instruction instrução a ser adicionada
     */
    public void add(String instruction) {
        if (memoryPositions.size() < limit) {
            memoryPositions.add(instruction);
        } else {
            throw new java.lang.IndexOutOfBoundsException("Posicao de memoria invalida. Ela deve estar entre 0 e " + limit);
        }
    }

    /**
     * Método que retorna a instrução que está contida na memória de instruções
     *
     * @param index indice onde encontra-se a a instrução desejada
     * @return instrução de 16 bits em formato int
     */

    public String get(int index) {
        if ((index >= 0) && (index < limit)) {
            try {
                return memoryPositions.get(index);
            } catch (IndexOutOfBoundsException e) {


                throw new IndexOutOfBoundsException("Nao existe valor nessa posicao");
            } catch (Exception e) {
                //outro erro
            }
        } else {
            throw new IndexOutOfBoundsException("Posicao invalida de memoria");
        }
        return null;
    }
}
