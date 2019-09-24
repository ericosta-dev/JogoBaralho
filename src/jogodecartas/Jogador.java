package jogodecartas;


public class Jogador {

    private final String NOME;
    private Carta[] cartas;

    public Jogador(String nome) {
        this.NOME = nome;
    }

    // escolher carta da mão
    public Carta getCarta(int i) {
        return cartas[i - 1];
    }

    /*
    trocar carta da mão por uma selecionada
    */
    public void trocarCarta(int i, Carta c) {
        cartas[i - 1] = c;
    }

    public String getNome() {
        return this.NOME;
    }

    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }

    public void mostrarCartas() {
        ordenarCartas(cartas);
        for (int i = 0; i < cartas.length; i++) {
            System.out.println(i + 1 + " - " + cartas[i]);
        }
    }

    // Bolha para organizar a mão
    public Carta[] ordenarCartas(Carta[] cartas) {
        int tam = cartas.length;
        Carta aux;
        boolean cont;

        do {
            tam--;
            cont = false;
            for (int i = 0; i < tam; i++) {
                if (cartas[i].getFACE() > cartas[i + 1].getFACE()) {
                    aux = cartas[i];
                    cartas[i] = cartas[i + 1];
                    cartas[i + 1] = aux;
                    cont = true;
                }
            }
        } while (cont);
        return cartas;
    }
}