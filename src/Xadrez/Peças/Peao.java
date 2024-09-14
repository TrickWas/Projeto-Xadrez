package Xadrez.Peças;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.BRANCO) {
            p.setValor(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2)
                    && !getTabuleiro().temUmaPeca(p2) && getContaMovimentos() == 0) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }

            // #jogada especial En passant Branco
            if (posicao.getLinha() == 3) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(esquerda) && temUmaPecaAdversaria(esquerda)
                        && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVulneravel()) {
                    matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existePosicao(direita) && temUmaPecaAdversaria(direita)
                        && getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVulneravel()) {
                    matriz[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }
        } else {
            p.setValor(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2)
                    && !getTabuleiro().temUmaPeca(p2) && getContaMovimentos() == 0) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }

            // #jogada especial En passant Preto
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(esquerda) && temUmaPecaAdversaria(esquerda)
                        && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVulneravel()) {
                    matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existePosicao(direita) && temUmaPecaAdversaria(direita)
                        && getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVulneravel()) {
                    matriz[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }

        }

        return matriz;
    }

    @Override
    public String toString() {
        return "P";
    }

}
