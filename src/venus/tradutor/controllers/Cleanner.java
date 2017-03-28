package venus.tradutor.controllers;

/**
 *
 * @author costa
 */
public class Cleanner {

    private static Cleanner instance = null;

    private Cleanner() {

    }

    public static Cleanner getInstance() {
        if (instance == null) {
            instance = new Cleanner();
        }
        return instance;
    }

    public String clean(String arq) {
        arq = removerComentarios(arq);
        arq = removerTabs(arq);
        arq = removerEspacos(arq);
        arq = removerVacuos(arq);
        arq = corrigirLabels(arq);
        return arq;
    }

    private String removerVacuos(String arq) {
        String arqFinal = "";
        char last = '\n';
        for (char c : arq.toCharArray()) {
            if (!((c == '\n') && (last == '\n'))) {
                arqFinal = arqFinal + c;
            }
            last = c;
        }
        return arqFinal;
    }

    private String removerEspacos(String arq) {
        String arqFinal = "";
        char last = ' ';
        for (char c : arq.toCharArray()) {
            if (!((c == ' ') && (last == ' ') || (last == '\n'))) {
                arqFinal = arqFinal + c;
            }
            last = c;
        }
        return arqFinal;
    }

    private String removerComentarios(String arq) {
        String arqFinal = "";
        boolean coment = false;
        for (char c : arq.toCharArray()) {
            if (c == ';') {
                coment = true;
            }
            if (c == '\n') {
                coment = false;
            }
            if (!coment) {
                arqFinal = arqFinal + c;
            }
        }
        return arqFinal;
    }

    private String corrigirLabels(String arq) {
        String arqFinal = "";
        char last = 'a';
        for (char c : arq.toCharArray()) {
            if (!((c == '\n') && (last == ':'))) {
                arqFinal = arqFinal + c;
            }
            last = c;
        }
        return arqFinal;

    }

    private String removerTabs(String arq) {
        arq = arq.replace("\t", "");
        return arq;
    }

}
