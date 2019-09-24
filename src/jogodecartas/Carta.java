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
    //Verificar com laura o pq se não fazer a mudança
    //Ver se é possível o SwitchCase
    @Override
    public String toString() {
        String aux = "";
        /*
        ("x".equals(FACE))
        Se o x for igual a face, usa o auxiliar para mudar o valor 
        da face da carta
        */
        if ( 1 == FACE){        
            aux = "Ás";
        }else if (11 == FACE){ 
            aux = "J";
        
        }else if (12 == FACE){ 
            aux = "Q";
        }else if (13 == FACE){ 
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
