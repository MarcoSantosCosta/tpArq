package venus.tradutor.instrucoes;

/**
 *
 * @author neche
 */
public class Instrucao6 implements Instrucao{
    private short func;
    private short op;
    private short r;
    private short dontCare;
    private short rb;
    
    public Instrucao6(short func,short op,short r,short dontCare,short rb){
        this.func = func;
        this.op = op;
        this.r = r;
        this.dontCare = dontCare;
        this.rb = rb;
    }
    
    /**
     * Retorna a instrucao em forma de binario
     * @return String da instrucao pronta para ser escrita
     */
    public String getBin(){
        int conta = (short)((func << 14) | (op << 12) | (r << 11) | (dontCare << 3) | (rb&0b111));
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
     * @return the rb
     */
    public short getRb() {
        return rb;
    }

    /**
     * @param rb the rb to set
     */
    public void setRb(short rb) {
        this.rb = rb;
    }
    
}