package Xadrez.Peças;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
        
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Noroeste
        p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Nordeste
        p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Sudeste
        p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //Sudoeste
        p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        return matriz;
    }
}

