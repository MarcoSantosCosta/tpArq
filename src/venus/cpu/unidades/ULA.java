/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu.unidades;

import venus.cpu.unidades.unidadesComplementares.ExtensorSinal;

public class ULA {

    private boolean neg;    //0100 Resultado ALU negativo .neg
    private boolean zero;   //0101 Resultado ALU zero .zero
    private boolean carry;  //0110 Carry da ALU .carry
    private boolean negzero;    //0111 Resultado ALU negativo ou zero .negzero
    private boolean truee = true;  //0000 TRUE .true
    private boolean overflow;   //0011 Resultado ALU overflow .overflow
    private static ULA instance;
    /*
    
    
     */
    private short ALUOp;
    private short result;
    private short a;
    private short b;

    public void clock() {
        a = ID.getInstance().getReadData1();
        if (Controle.getInstance().getULAsrc()) {
            if (((Controle.getInstance().getULAop() >>> 5) & 0b11) == 0b10) {
                b = ExtensorSinal.getInstance().exetender8();
            } else if (((Controle.getInstance().getULAop() >>> 5) & 0b11) == 0b11) {
                b = ExtensorSinal.getInstance().exetender11();
            }
        } else {
            b = ID.getInstance().getReadData2();
        }
        ALUOp = (short) (Controle.getInstance().getULAop() & 0b11111);
        int op = (ALUOp & 0b11000) >>> 3;
        switch (op) {
            case 0b10:
                resolveShift();
                break;
            case 0b11:
                resolveArithmetic();
                break;
            default:
                if (((ALUOp >>> 1) & 0b11) == 0b00) {
                    //VERIFICAR OPERACAO
                    if (op == 0) {
                        result = b;
                    } else {
                        resolveControl();
                    }
                } else {
                    resolveLogic();
                }
        }
        System.out.println("ALUP: " + this.ALUOp);
        System.out.println("RESULT: " + this.result);
        System.out.println("a: " + this.a);
        System.out.println("b: " + this.b);
    }

    private ULA() {
    }

    /**
     * resolve operaçoes do tipo shift:<br> {@code lsl c,a;}<br>
     * {@code lsr c,a;}<br> {@code asr c,a;}<br> {@code asl c,a;}
     */
    private void resolveShift() {
        //SHIFT LEFT && SHIFT LOGIC
        if ((((ALUOp & 0b10) >>> 1) == 0) && (!((ALUOp & 0b1) == 1))) {
            result = (short) ((a << 1));
            overflow = true;
            carry = ((a & 0b1000000000000000) >>> 15) == 1;
        }
        //SHIFT LEFT && SHIFT ARITHMETIC
        if ((((ALUOp & 0b10) >>> 1) == 0) && ((ALUOp & 0b1) == 1)) {
            result = (short) (a << 1);
            overflow = (((a & 0b1000000000000000) >>> 15) == 1) ^ (((a & 0b0100000000000000) >>> 14) == 1);
            carry = ((a & 0b1000000000000000) >>> 15) == 1;
        }
        //SHIFT RIGHT && SHIFT LOGIC
        if ((!((((ALUOp & 0b10) >>> 1)) == 0)) && (!((ALUOp & 0b1) == 1))) {
            result = (short) ((a & 0b1111111111111111) >>> 1);
            overflow = false;
            carry = false;
        }
        //SHIFT RIGHT && SHIFT ARITHMETIC
        if ((!(((((ALUOp & 0b10) >>> 1))) == 0)) && ((ALUOp & 0b1) == 1)) {

            result = (short) (a >> 1);
            overflow = false;
            carry = false;
        }

        neg = (getResult() < 0);    //0100 Resultado ALU negativo .neg
        zero = (getResult() == 0);   //0101 Resultado ALU zero .zero
    }

    /**
     * resolve operaçoes do tipo aritimetico:
     * <br> {@code add c,a,b;}
     * <br> {@code addinc c,a,b;}
     * <br> {@code inca c,a;}
     * <br> {@code subdec c,a,b;}
     * <br> {@code sub c,a,b;}
     * <br> {@code deca c,a;}
     */
    private void resolveArithmetic() {
        short inc = (short) (((((ALUOp & 0b100) >>> 2) == 0) ? ((ALUOp & 0b10) >>> 1) == 1 : ((ALUOp & 0b10) >>> 1) == 0) ? 1 : 0);
        short bvalue = ((((ALUOp & 0b100) >>> 2) == 0)) ? b : 0;
        if (((ALUOp & 0b1) == 0)) {
            short ab = (short) (a + bvalue);
            result = (short) (ab + inc);
            overflow = checkOverflow(a, bvalue, ab, true) || checkOverflow(ab, inc, ab, true);
            carry = (((a + bvalue + inc) & 0b10000000000000000) >> 16) == 1;
        } else {
            short ab = (short) (a - bvalue);
            result = (short) (ab - inc);
            overflow = checkOverflow(a, bvalue, ab, false) || checkOverflow(ab, inc, ab, false);
            carry = (((a - bvalue - inc) & 0b10000000000000000) >> 16) == 1;

        }
        neg = (getResult() < 0);    //0100 Resultado ALU negativo .neg
        zero = (getResult() == 0);   //0101 Resultado ALU zero .zero
    }

    /**
     * resolve operaçoes do tipo controle:<br> {@code passa c,a;}<br>
     * {@code passnota c,a;}<br> {@code zeros c;}<br> {@code ones c}
     */
    private void resolveControl() {
        if ((ALUOp & 0b01000) >>> 3 == 1) {
            if ((ALUOp & 0b1) == 1) {
                result = a;
                zero = (a == 0);
            } else {
                zero = (a != 0);
                result = (short) (a ^ ~0);
            }
        } else if ((ALUOp & 0b1) == 1) {
            result = 1;
            zero = false;
        } else {
            result = 0;
            zero = true;
        }
        neg = (getResult() < 0);
        overflow = true;
        carry = true;
    }

    private void resolveLogic() {
        short tempResult = (short) ((((ALUOp & 0b01000) >>> 3) == 1) ? (a ^ ~0) : a);
        switch ((ALUOp >>> 1) & 0b11) {
            case 0b10: //OR
                tempResult = (short) (tempResult | b);
                break;
            case 0b01: //AND

                tempResult = (short) (tempResult & b);
                break;
            case 0b11: //XOR
                tempResult = (short) (tempResult ^ b);
                break;
        }
        result = ((ALUOp & 0b1) == 1) ? (short) (tempResult ^ ~0) : tempResult;
        if ((ALUOp & 0b1) == 1) {
            zero = tempResult != 0;
        } else {
            zero = tempResult == 0;
        }
        neg = getResult() < 0;    //0100 Resultado ALU negativo .neg
        carry = false;
        overflow = false;
    }

    private boolean checkOverflow(short a, short b, short result, boolean sum) {
        b = (short) (sum ? b : (b * -1));
        if ((a < 0) && (b < 0)) {
            return result >= 0;
        } else if ((a > 0) && (b > 0)) {
            return result < 0;
        } else {
            return false;
        }

    }

    private byte boolToByte(boolean b) {
        return (byte) (b ? 0b1 : 0b0);
    }

    public static ULA getInstance() {
        if (instance == null) {
            instance = new ULA();
        }
        return instance;
    }

    /**
     * @return the neg
     */
    public boolean getNeg() {
        return neg;
    }

    /**
     * @return the zero
     */
    public boolean getZero() {
        return zero;
    }

    /**
     * @return the carry
     */
    public boolean getCarry() {
        return carry;
    }

    /**
     * @return the negzero
     */
    public boolean getNegzero() {
        return negzero;
    }

    /**
     * @return the truee
     */
    public boolean getTrue() {
        return truee;
    }

    /**
     * @return the overflow
     */
    public boolean getOverflow() {
        return overflow;
    }

    /**
     * @return the result
     */
    public short getResult() {
        return result;
    }

}
