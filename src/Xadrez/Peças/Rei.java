package Xadrez.Peças;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    private boolean testarRoqueTorre(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContaMovimentos() == 0;
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // acima
        p.setValor(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // abaixo
        p.setValor(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // esquerda
        p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // direita
        p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // Noroeste
        p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // Nordeste
        p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // Sudoeste
        p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // Sudeste
        p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        // #jogada especial Roque
        if (getContaMovimentos() == 0 && !partidaXadrez.getCheck()) {
            // #jogada especial Roque para o lado do rei Torre
            Posicao posicaoT01 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testarRoqueTorre(posicaoT01)) {
                Posicao p01 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p02 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(p01) == null && getTabuleiro().peca(p02) == null) {
                    matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }

            // #jogada especial Roque para o lado do rainha Torre
            Posicao posicaoT02 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testarRoqueTorre(posicaoT02)) {
                Posicao p01 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p02 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p03 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().peca(p01) == null && getTabuleiro().peca(p02) == null
                        && getTabuleiro().peca(p03) == null) {
                    matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }

        return matriz;
    }
}
