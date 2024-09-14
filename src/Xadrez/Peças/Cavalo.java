package Xadrez.Peças;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "C";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        return matriz;
    }

}
