package jogodecartas;

public class Carta {

    private final int FACE;
    private final String NAIPE;

    public Carta(int face, String naipe) {
        this.FACE = face;
        this.NAIPE = naipe;
    }

    public int getFACE() {
        return this.FACE;
    }

    public String getNAIPE() {
        return this.NAIPE;
    }
    
    @Override
    public String toString() {
        String aux = "";
        
        if ("1".equals(FACE)){        //Se a FACE for igual a 1,trocar para Ás
            aux = "Ás";
        }else if ("11".equals(FACE)){ //Se a FACE for igual a 11,trocar para J
            aux = "J";
        
        }else if ("12".equals(FACE)){ //Se a FACE for igual a 12,trocar para Q
            aux = "Q";
        }else if ("13".equals(FACE)){ //Se a FACE for igual a 13,trocar para K
            aux = "K";
        }
        /*
        Se o aux for vazio, continua a face normal,
        caso seja diferente de vazio, o valor da 
        face é igual ao valor do aux
        */
        return ("".equals(aux) ? FACE : aux) + " - " + NAIPE;
    }

}
