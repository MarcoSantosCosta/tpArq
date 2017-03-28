package venus.tradutor.instrucoes;

/**
 *
 * @author neche
 */
public class Instrucao4 {
    private short func;
    private short op;
    private short cond;
    private short off;
    
    public Instrucao4(short func,short op,short cond,short off){
        this.func = func;
        this.op = op;
        this.cond = cond;
        this.off = off;
    }
    
    /**
     * Retorna a instrucao em forma de binario
     * @return String da instrucao pronta para ser escrita
     */
    public String getBin(){
        int conta = (short)((func << 14) | (op << 12) | (cond << 8) | off);
        String retorno = Integer.toBinaryString(conta);
        String aux = "";
        for(int i = 0;i < 16 - retorno.length();i++){
            aux += "0";
        }
        return (aux + retorno);
    }
    
    /**
     * @return the func
     */
    public short getFunc() {
        return func;
    }

    /**
     * @param func the func to set
     */
    public void setFunc(short func) {
        this.func = func;
    }

    /**
     * @return the op
     */
    public short getOp() {
        return op;
    }

    /**
     * @param op the op to set
     */
    public void setOp(short op) {
        this.op = op;
    }

    /**
     * @return the cond
     */
    public short getCond() {
        return cond;
    }

    /**
     * @param cond the cond to set
     */
    public void setCond(short cond) {
        this.cond = cond;
    }

    /**
     * @return the off
     */
    public short getOff() {
        return off;
    }

    /**
     * @param off the off to set
     */
    public void setOff(short off) {
        this.off = off;
    }
    
}
