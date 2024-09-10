package Aplicação;

import java.util.Scanner;

import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            UI.printTabuleiro(partidaXadrez.getPecas());
            System.out.print("\nOrigem: ");
            PosicaoXadrez origem = UI.lerPosicaoXadrez(ler);

            System.out.print("\nAlvo: ");
            PosicaoXadrez alvo = UI.lerPosicaoXadrez(ler);

            PecaXadrez capturaXadrez = partidaXadrez.movimentoXadrez(origem, alvo);
        }
    }
}
