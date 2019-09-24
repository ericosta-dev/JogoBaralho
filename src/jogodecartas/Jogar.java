package jogodecartas;

/**
 *
 * @author eric
 */
public class Jogar {
    public static void main(String[] args) {
        Jogo executar = new Jogo();
        executar.iniciarJogo();
        executar.distribuirCartas(9);
        executar.mostrarCartas();
        executar.executar();
    }
    
}
