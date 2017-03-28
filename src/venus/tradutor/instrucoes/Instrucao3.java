package venus.tradutor.instrucoes;

/**
 *
 * @author neche
 */
public class Instrucao3 implements Instrucao{
    private short func;
    private short rc;
    private short r;
    private short dontCare;
    private short off;
    
    public Instrucao3(short func,short rc,short r,short dontCare,short off){
        this.func = func;
        this.rc = rc;
        this.r = r;
        this.dontCare = dontCare;
        this.off = off;
    }
    
    /**
     * Retorna a instrucao em forma de binario
     * @return String da instrucao pronta para ser escrita
     */
    public String getBin(){
        int conta = (short)((func << 14) | (rc << 11) | (r << 10) | (dontCare << 8) | off);
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
     * @return the r
     */
    public short getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(short r) {
        this.r = r;
    }

    /**
     * @return the dontCare
     */
    public short getDontCare() {
        return dontCare;
    }

    /**
     * @param dontCare the dontCare to set
     */
    public void setDontCare(short dontCare) {
        this.dontCare = dontCare;
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
