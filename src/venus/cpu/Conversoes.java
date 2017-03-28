package cpu;

/**
 *
 * @author neche
 */
public class Conversoes {
    /**
     * Convert um short para uma string do binario
     * @param num numero a ser convertido
     * @return string do binario
     */
    public static String shortToBin(int num){
        String bin = "";
        int aux = 0;
        while(num > 1){
            aux = num % 2;
            bin = aux + bin;
            num = num / 2;
        }
        bin = num + bin;
        return bin;
    }
}
