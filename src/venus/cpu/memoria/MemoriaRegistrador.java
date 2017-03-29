package venus.cpu.memoria;

/**
 * Ordem dos registradores: 0-r0 1-r1 2-r2 3-r3 4-r4 5-r5 6-r6 7-r7 8-PC
 */
public class MemoriaRegistrador {

    private short[] mem;
    private short pc;
    private static MemoriaRegistrador instance = null;

    private MemoriaRegistrador() {
        this.mem = new short[7];
        this.pc = 0;
    }

    public static MemoriaRegistrador getInstance() {
        if (instance == null) {
            instance = new MemoriaRegistrador();
        }
        return instance;
    }

    /**
     * Insere no registrador o valor passado em valor
     *
     * @param registrador numero do registrador[0...7]
     * @param valor valor a ser colocado no registrador
     */
    public void inserir(short registrador, short valor) {
        if (registrador < 0 || registrador > 8) {
            throw new RuntimeException("Registrador " + registrador + " e invalido.");
        }
        if (valor < -32768 || valor > 32767) {
            throw new RuntimeException("Valor a ser inserido no registrador " + registrador + " e invalido.");
        }
        this.mem[registrador] = valor;

    }

    /**
     * Retorna o valor contido no registrador
     *
     * @param registrador numero do registrador[0...7]
     * @return valor do registrador
     */
    public short get(short registrador) throws RuntimeException {
        if (registrador < 0 || registrador > 7) {
            throw new RuntimeException("Registrador " + registrador + " e invalido.");
        }
        return mem[registrador];
    }

    public short getPc() {
        return this.pc;
    }

    public void setPc(short pc) {
        this.pc = pc;
    }

//    public void printaMemoria() {
//        for (Short i : mem) {
//            System.out.println("valor " + i + "\n");
//        }
//    }
}
