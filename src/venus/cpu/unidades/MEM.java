package venus.cpu.unidades;

import venus.cpu.memoria.MemoriaPrincipal;
import venus.cpu.unidades.ULA;

/**
 *
 * @author neche
 */
public class MEM implements Unidades{
    private static MEM instance = null;
    private MemoriaPrincipal banco;
    private int endereco;
    private short readData;
    
    private MEM(){
        banco = MemoriaPrincipal.getInstance();
        endereco = 0;
        readData = 0;
    };
    
    public static MEM getInstance(){
        if(instance == null)
            instance = new MEM();
        return instance;
    }
    
    @Override
    public void clock(){
        //pega o controle
        Controle c = Controle.getInstance();
        IF pif = IF.getInstance();
        ULA ula = ULA.getInstance();
        ID id = ID.getInstance();
        
        if(c.getMemRead()){//se tiver que ler
            endereco = ula.getResult();//onde deve ler
            readData = banco.get(endereco);
        }else if(c.getMemWrite()){//se tiver que escrever
            endereco = ula.getResult();//onde deve escrever
            banco.inserir(endereco, (short) id.getReadData2());
        }
    }
    
    public String getStatus(){
        return "Endereco: "+this.endereco+" ReadData: "+this.readData;
    }
    
    public short getReadData(){
        return readData;
    }
}
