package venus.cpu.unidades;

/**
 *
 * @author neche
 */
public class Controle {

    private short func;
    private short op;
    private short r;

    private short ULAop;
    private boolean regWrite;
    private boolean ULAsrc;
    private boolean memWrite;
    private boolean memToReg;
    private boolean memRead;
    private boolean jump;

    private static Controle instance = null;

    private Controle() {
        this.func = 0;
        this.op = 0;
        this.r = 0;
        this.ULAop = 0;
        this.regWrite = false;
        this.ULAsrc = false;
        this.memWrite = false;
        this.memToReg = false;
        this.memRead = false;
        this.jump = false;
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
        switch (func) {
            case 0:
                op = pif.getSubBin(2, 2);
                ULAop = (short) ((getFunc() << 5) | (getOp() & 0b11));
                regWrite = false;
                ULAsrc = false;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = true;
                break;
            case 1:
                op = pif.getSubBin(5, 5);
                ULAop = (short) ((getFunc() << 5) | (getOp() & 0b11111));
                regWrite = true;
                ULAsrc = false;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = false;
                
                if(op == 0b10100){
                    memRead = true;
                }
                if(op == 0b10110){
                    memWrite = true;
                }
                break;
            case 2:
                op = (short) 0;
                ULAop = (short) ((getFunc() << 5) | getOp());
                regWrite = true;
                ULAsrc = true;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = false;
                break;
            case 3:
                op = (short) 0;
                ULAop = (short) ((getFunc() << 5) | getOp());
                regWrite = true;
                ULAsrc = true;
                memWrite = false;
                memToReg = false;
                memRead = false;
                jump = false;
                break;
            default:
                break;
        }
        test();
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
     * @return the ULAop
     */
    public short getULAop() {
        return ULAop;
    }

    /**
     * @return the regWrite
     */
    public boolean getRegWrite() {
        return regWrite;
    }

    /**
     * @return the ULAsrc
     */
    public boolean getULAsrc() {
        return ULAsrc;
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

    public String getStatus(){
        return "ULAop: "+ULAop+" regWrite: "+regWrite+" ULAsrc: "+ULAsrc+" memWrite: " +memWrite+" memToReg: "+memToReg+"\n"
                +"memRead: "+memRead+" jump: "+jump+" func: "+func+" op: "+op+" r: "+r;
    }
    
    public void test() {
        System.out.println("------------Control------------");
        System.out.println("ULAop: " + ULAop);
        System.out.println("regWrite: " + regWrite);
        System.out.println("ULAsrc: " + ULAsrc);
        System.out.println("memWrite: " + memWrite);
        System.out.println("memToReg: " + memToReg);
        System.out.println("memRead: " + memRead);
        System.out.println("jump: " + jump);
    }

}
