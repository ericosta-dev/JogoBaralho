package jogodecartas;

import java.util.Random;

public class Baralho {

    private final Carta[] CARTAS;
    private final Random ALEATORIO;
    private int contador;
    private int primeiraCarta = 51;//Criado para o jogador pegar a primeira carta da pilha

    public Baralho() {
        ALEATORIO = new Random();
        CARTAS = new Carta[52];
        //Às = 1 / J = 11 / Q = 12 / K= 13
        int[] face = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] naipe = {"COPAS", "ESPADAS", "OUROS", "PAUS"};

        int cont = 0;
        for (String naipe1 : naipe) {
            for (int j = 0; j < face.length; j++) {
                CARTAS[cont++] = new Carta(face[j], naipe1);
            }
        }
    }
     
    
    public Carta comprarBaralho(){
        return CARTAS[primeiraCarta--];
    }
    
    
    public Carta[] distribuirCartas(int qtdCartas) {
        System.out.println("Jogo =>   DISTRIBUINDO BARALHO");

        Carta[] cartasJogador = new Carta[qtdCartas];

        for (int i = 0; i < qtdCartas; i++) {
            cartasJogador[i] = CARTAS[contador];
            contador++;
        }

        return cartasJogador;
    }
    public int getPrimeiraCarta() {
        return primeiraCarta;
    }
   
    public void embaralhar() {
        System.out.println("Jogo =>   EMBARALHANDO");
        int num, num2;
        Carta temp;
        for (int i = 0; i < CARTAS.length; i++) {
            num = ALEATORIO.nextInt(CARTAS.length);
            num2 = ALEATORIO.nextInt(CARTAS.length);
            temp = CARTAS[num];
            CARTAS[num] = CARTAS[num2];
            CARTAS[num2] = temp;
        }
    }
    public void mostrarBaralho() {
        System.out.println("Jogo =>   APRESENTANDO BARALHO");
        for (Carta carta : CARTAS) {
            System.out.println(carta.toString());
        }
    }

    
}
