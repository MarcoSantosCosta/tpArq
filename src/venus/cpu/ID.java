package venus.cpu;

import cpu.memoria.MemoriaRegistrador;

/**
 *
 * @author neche
 */
public class ID {
    private static ID instance = null;
    private MemoriaRegistrador banco;
    private short ra;
    private short rb;
    
    private ID(){
        banco = MemoriaRegistrador.getInstance();
        ra = 0;
        rb = 0;
    };
    
    public static ID getInstance(){
        if(instance == null)
            instance = new ID();
        return instance;
    }
    
    /*public void clock(){
        //gets do IF
        IF if = IF.getInstance();
        ra = if.getSubBin(10,3);
        rb = if.getSubBin(13,3);
    }*/
    
    /**
     * Retorna a saida ReadData1 do ID
     * @return short com o valor do registrador ra no banco de registradores
     */
    public short getReadData1(){
        return banco.get(ra);
    }
    
    /**
     * Retorna a saida ReadData2 do ID
     * @return short com o valor do registrador rb no banco de registradores
     */
    public short getReadData2(){
        return banco.get(rb);
    }
    
    
//    /**
//     * Recebe o numero do registrador [0...7] e define como o registrador de leitura 1
//     * @param registrador numero de 0 a 7
//     */
//    public void setRegistradorLeitura1(int registrador){
//        if(registrador < 0 || registrador > 7){
//            throw new RuntimeException("Registrador " + registrador + " e invalido.");
//        }
//        registradorLeitura1 = (short) registrador;
//    }
//    
//    /**
//     * Recebe o numero do registrador [0...7] e define como o registrador de leitura 2
//     * @param registrador numero de 0 a 7
//     */
//    public void setRegistradorLeitura2(int registrador){
//        if(registrador < 0 || registrador > 7){
//            throw new RuntimeException("Registrador " + registrador + " e invalido.");
//        }
//        registradorLeitura2 = (short) registrador;
//    }
}
