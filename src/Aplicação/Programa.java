package Aplicação;

import Xadrez.PartidaXadrez;

public class Programa {
    public static void main(String[] args) {

        PartidaXadrez partidaXadrez = new PartidaXadrez();
        UI.printTabuleiro(partidaXadrez.getPecas());

    }
}
