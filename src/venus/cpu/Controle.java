package venus.cpu;

import venus.cpu.unidades.IF;

/**
 *
 * @author neche
 */
public class Controle {

    private short func;
    private short op;
    private short r;

    private short ALUop;
    private boolean regDst;
    private boolean regWrite;
    private boolean ALUsrc;
    private boolean memWrite;
    private boolean memToReg;
    private boolean memRead;
    private boolean jump;

    private static Controle instance = null;

    private Controle() {
        this.func = 0;
        this.op = 0;
        this.r = 0;

        ALUop = 0;
        regDst = false;
        regWrite = false;
        ALUsrc = false;
        memWrite = false;
        memToReg = false;
        memRead = false;
        jump = false;
    }

    ;
    
    public static Controle getInstance() {
        if (instance == null) {
            instance = new Controle();
        }
        return instance;
    }

    public void clock() {
        //gets do IF
        IF pif = IF.getInstance();
        func = pif.getSubBin(0, 2);

        switch (getFunc()) {
            case 0:
                op = pif.getSubBin(2, 2);
                ALUop = (short) ((getFunc() << 5) | (getOp() & 0b11));
                regDst = false;
                regWrite = false;
                ALUsrc = false;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = true;
                if (getOp() == 3 && pif.getSubBin(6, 1) == 0) {
                    regDst = true;
                    regWrite = true;
                }
                break;
            case 1:
                op = pif.getSubBin(5, 5);
                ALUop = (short) ((getFunc() << 5) | (getOp() & 0b11111));
                regDst = false;
                regWrite = false;
                ALUsrc = false;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = false;
                break;
            case 2:
                op = (short) 0;
                ALUop = (short) ((getFunc() << 5) | getOp());
                regDst = true;
                regWrite = true;
                ALUsrc = true;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = false;
                break;
            case 3:
                op = (short) 0;
                ALUop = (short) ((getFunc() << 5) | getOp());
                regDst = true;
                regWrite = true;
                ALUsrc = true;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = false;
                break;
            default:
                break;
        }

    }

    /**
     * @return the func
     */
    public short getFunc() {
        return func;
    }

    /**
     * @return the op
     */
    public short getOp() {
        return op;
    }

    /**
     * @return the r
     */
    public short getR() {
        return r;
    }

    /**
     * @return the ALUop
     */
    public short getALUop() {
        return ALUop;
    }

    /**
     * @return the regDst
     */
    public boolean getRegDst() {
        return regDst;
    }

    /**
     * @return the regWrite
     */
    public boolean getRegWrite() {
        return regWrite;
    }

    /**
     * @return the ALUsrc
     */
    public boolean getALUsrc() {
        return ALUsrc;
    }

    /**
     * @return the memWrite
     */
    public boolean getMemWrite() {
        return memWrite;
    }

    /**
     * @return the memToReg
     */
    public boolean getMemToReg() {
        return memToReg;
    }

    /**
     * @return the memRead
     */
    public boolean getMemRead() {
        return memRead;
    }

    /**
     * @return the jump
     */
    public boolean getJump() {
        return jump;
    }

}
