package Xadrez.Peças;

import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColunas()];
        return matriz;
    }
}
