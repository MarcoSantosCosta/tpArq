package venus.tradutor.instrucoes;

/**
 *
 * @author neche
 */
public class Instrucao1 {
    private short func;
    private short rc;
    private short op;
    private short ra;
    private short rb;
    
    public Instrucao1(short func,short rc,short op,short ra,short rb){
        this.func = func;
        this.rc = rc;
        this.op = op;
        this.ra = ra;
        this.rb = rb;
    }
    
    /**
     * Retorna a instrucao em forma de binario
     * @return String da instrucao pronta para ser escrita
     */
    public String getBin(){
        int conta = (short)((func << 14) | (rc << 11) | (op << 6) | (ra << 3) | rb);
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
     * @return the ra
     */
    public short getRa() {
        return ra;
    }

    /**
     * @param ra the ra to set
     */
    public void setRa(short ra) {
        this.ra = ra;
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
