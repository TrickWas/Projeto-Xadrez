package Aplicação;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.ExcecaoXadrez;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> captura = new ArrayList<>();

        while (true) {
            try {
                UI.limpaTela();
                UI.printPartida(partidaXadrez, captura);
                System.out.print("\nOrigem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(ler);

                boolean[][] movimentoPossivel = partidaXadrez.movimentoPossivel(origem);
                UI.limpaTela();
                UI.printTabuleiro(partidaXadrez.getPecas(), movimentoPossivel);
                System.out.print("\nAlvo: ");
                PosicaoXadrez alvo = UI.lerPosicaoXadrez(ler);

                PecaXadrez capturaPeca= partidaXadrez.movimentoXadrez(origem, alvo);
                
                if (capturaPeca != null) {
                    captura.add(capturaPeca);
                }
            }
            catch (ExcecaoXadrez e) {
                System.out.println(e.getMessage());
                ler.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                ler.nextLine();
            } 
        }
    }
}
