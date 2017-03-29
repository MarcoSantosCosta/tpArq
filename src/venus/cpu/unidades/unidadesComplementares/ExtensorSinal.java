/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu.unidades.unidadesComplementares;

/**
 *
 * @author Marco
 */
public class ExtensorSinal {

    private static ExtensorSinal instance = null;

    private ExtensorSinal() {

    }

    public static ExtensorSinal getInstance() {
        if (instance == null) {
            instance = new ExtensorSinal();
        }
        return instance;
    }

    /**
     * Extende um valor de 12 bits para 16 bits
     * @param num numero de 12 bits a ter o sinal extedido
     * @return numero de 16 bits com sinal extendido
     */
    public static short exetender12(short num) {
        if ((num >>> (11)) == 1) {
            short aux = (short) (0b1111111111111111);
            aux = (short) (aux ^ ((short) Math.pow(2, 12) - 1));
            return (short) (num | aux);
        } else {
            return num;
        }
    }

    /**
     * Extende um valor de 8 bits para 16 bits
     * @param num numero de 8 bits a ter o sinal extedido
     * @return numero de 16 bits com sinal extendido
     */
    public static short exetender8(short num) {
        if ((num >>> (7)) == 1) {
            short aux = (short) (0b1111111111111111);
            aux = (short) (aux ^ ((short) Math.pow(2, 8) - 1));
            return (short) (num | aux);
        } else {
            return num;
        }
    }
}
