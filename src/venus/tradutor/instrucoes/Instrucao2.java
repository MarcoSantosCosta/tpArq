package venus.tradutor.instrucoes;

/**
 *
 * @author neche
 */
public class Instrucao2 implements Instrucao{
    private short func;
    private short rc;
    private short off;
    
    public Instrucao2(short func,short rc,short off){
        this.func = func;
        this.rc = rc;
        this.off = off;
    }
    
    /**
     * Retorna a instrucao em forma de binario
     * @return String da instrucao pronta para ser escrita
     */
    public String getBin(){
        int conta = ((func << 14) | (rc << 11) | (off&0b11111111111));
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
     * @return the rc
     */
    public short getRc() {
        return rc;
    }

    /**
     * @param rc the rc to set
     */
    public void setRc(short rc) {
        this.rc = rc;
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