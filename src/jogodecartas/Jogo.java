package jogodecartas;

import java.util.Scanner;

public class Jogo {

    private final Scanner entrada = new Scanner(System.in);
    private final Baralho BARALHO;
    private Jogador[] jogadores;
    private Carta fora;

    public Jogo() {
        BARALHO = new Baralho();
        BARALHO.mostrarBaralho();
        BARALHO.embaralhar();
        BARALHO.mostrarBaralho();
    }

    public void iniciarJogo() {
        int qtdJogadores = 2; // QUANTIDADE SETADA PARA 2
        /* RETIRADO PARA NÃO PERGUNTAR QNT DE JOGADORES
        do {
            System.out.println("Quantos jogadores irao participar? (maximo de 7 jogadores)");
            qtdJogadores = entrada.nextInt();
        } while (qtdJogadores > 7);
        */
        jogadores = new Jogador[qtdJogadores];

        for (int i = 0; i < qtdJogadores; i++) {
            System.out.println("Jogador " + (i+1) + ", digite seu nome:");
            jogadores[i] = new Jogador(entrada.next());
        }
    }

    public void distribuirCartas(int qtdCartas) {
        for (Jogador jogadore : jogadores) {
            jogadore.setCartas(BARALHO.distribuirCartas(qtdCartas));
        }
    }

    public void mostrarCartas() {
        for (Jogador jogadore : jogadores) {
            jogadore.mostrarCartas();
        }
    }
    private boolean verificaTrinca(Carta a, Carta b, Carta c) {
        boolean cont= false;
        if (a.getFACE() == b.getFACE() && a.getFACE() == c.getFACE()) {
            System.out.println("\nTRINCA COM AS CARTAS: ");
            System.out.println(a + "\n" + b + "\n" + c);
            cont = true;
        }
        return cont;
    }
    private boolean verificaSeq(Carta a, Carta b, Carta c) {
        boolean cont = false;
        if (b.getFACE() == (a.getFACE() + 1) && c.getFACE() == (a.getFACE() + 2) && verificaNaipe(a, b, c)) {
            System.out.println("\nSEQUÊNCIA COM AS CARTAS: ");
            System.out.println(a + "\n" + b + "\n" + c);
            cont = true;
        }
        return cont;
    }
    private boolean verificaNaipe(Carta a, Carta b, Carta c) {
        return (a.getNAIPE() != b.getNAIPE() && b.getNAIPE() != c.getNAIPE());
    }
    private boolean verificaVitoria(int jogador) {
        int cont = 0;
        boolean acabar = false;
        Carta a, b, c;
        do {
            System.out.println("\nSelecione o jogo EM ORDEM: ");
            a = jogadores[jogador].getCarta(entrada.nextInt());
            b = jogadores[jogador].getCarta(entrada.nextInt());
            c = jogadores[jogador].getCarta(entrada.nextInt());
            if (cont < 3) {
                if (verificaTrinca(a, b, c)) {
                    cont++;
                } else if (verificaSeq(a, b, c)) {
                    cont++;
                } else {
                    System.out.println("Você PERDEU (Mão não vitoriosa)");
                    acabar = true;
                }
            } else if (cont == 3) {
                acabar = true;
            }
        } while (cont < 3 && acabar == false);
        return acabar;
    }
    private Carta opcPuxar(int jogador) {
        Carta cartaPuxada = null;
        do {
            System.out.println("\n------------------------");
            System.out.println("\nJogo => De onde pretende puxar? ");
            System.out.println("1 => Puxar do baralho");
            System.out.println("2 => Puxar dos descartes");
            System.out.println("------------------------");
            System.out.print("Escolha: ");
 
            switch (entrada.nextInt()) {
                case 1:
                    if (BARALHO.getPrimeiraCarta()> 0) {
                        cartaPuxada = BARALHO.comprarBaralho();
                        System.out.println("Jogo => " + jogadores[jogador].getNome() + " puxou um " + cartaPuxada);
                    } else {
                        System.out.println("erro : Não há mais cartas no baralho");
                    }
                    break;
                case 2:
                    if (fora != null) {
                        cartaPuxada = fora;
                        System.out.println("Jogo => " + jogadores[jogador].getNome() + " puxou um " + cartaPuxada);
                    } else {
                        System.out.println("Jogo => Não há cartas para puxar dos descartes");
                    }
                    break;
                default:
                    System.out.println("erro: Opção não existente");
            }
            if (cartaPuxada == null) {
                System.out.print("\nJogo => Digite novamente: ");
            } 

        } while (cartaPuxada == null);
        return cartaPuxada;
    }
    private void opcDescartar(int jogador, Carta cartaPuxada) {
        int remover;
        do {
            System.out.print("\nJogo => Insira o número da carta a ser descartada (para a carta puxada digite 10): ");
            remover = entrada.nextInt();
            if (remover < 10) {
                fora = jogadores[jogador].getCarta(remover);
                System.out.println("\n Jogo =>" + jogadores[jogador].getCarta(remover) + " - Descartado\n");
                jogadores[jogador].trocarCarta(remover, cartaPuxada);
            } else if (remover > 10) {
                System.out.println("\n erro : Posição da carta incorreta.");
            } else {
                System.out.println("\nJogo => Carta puxada descartada\n");
                fora = cartaPuxada;
            }
        } while (remover > 10 || remover < 1);
    }
    public void executar() {
        boolean fim = false;
        int jogador = 0;
        Carta cartaPuxada;

        do {
            System.out.println("Jogo => Cartas jogador:"+jogadores[jogador].getNome());
            jogadores[jogador].mostrarCartas();

            if (fora != null) {
                System.out.println("Jogo => "+jogadores[jogador].getNome()+ "\nUltima carta descartada: " + fora);
            }

            cartaPuxada = opcPuxar(jogador);
            opcDescartar(jogador, cartaPuxada);

            jogadores[jogador].mostrarCartas();
            System.out.println("Jogo => "+jogadores[jogador].getNome()+ " deseja bater?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print(jogadores[jogador].getNome()+": ");

            if (entrada.nextInt() == 1) {
                fim = verificaVitoria(jogador);
            }
            jogador = jogador == 0 ? 1 : 0;
        } while (fim == false);
    }
}
