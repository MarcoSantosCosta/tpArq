package venus.cpu.unidades;

import venus.cpu.Controle;
import venus.cpu.memoria.MemoriaRegistrador;
import venus.cpu.controller.ULA;

/**
 *
 * @author Marco
 */
public class WB implements Unidades{

    private static WB instance = null;
    private MemoriaRegistrador banco;
    private Controle controle;
    private ULA ula;
    private MEM mem;
    private IF instructionFetch;
    private short regDestino;
    private short valorEntrada;

    private WB() {
        this.controle = Controle.getInstance();
        this.mem = MEM.getInstance();
        this.banco = MemoriaRegistrador.getInstance();
        this.ula = ULA.getInstance();
        this.instructionFetch = IF.getInstance();
    }

    /**
     * Metodo para obter um instancia de WB
     *
     * @return instancia do Objeto WB
     */
    public static WB getInstance() {
        if (instance == null) {
            instance = new WB();
        }
        return instance;
    }

    private void setValorEtrada() {
        if (this.controle.getMemToReg()) {
            this.valorEntrada = mem.getReadData();
        } else {
            this.valorEntrada = ula.getResult();
        }
    }

    private void setRegDestino() {
        this.regDestino = instructionFetch.getSubBin(2, 3);
    }

    /**
     * Metodo que executo as operacoes das unidades funcionais.
     */
    @Override
    public void clock() {
        this.setValorEtrada();
        this.setRegDestino();
        if (controle.getRegWrite()) {
            banco.inserir(regDestino, valorEntrada);
        }
    }

}
