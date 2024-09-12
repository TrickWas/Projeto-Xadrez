package Xadrez.Peças;

import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Torre extends PecaXadrez  {

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);  
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColunas()];
        return matriz;
    }
}
