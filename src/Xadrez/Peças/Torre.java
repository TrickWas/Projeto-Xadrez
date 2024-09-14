package Xadrez.Peças;

import JogoTabuleiro.Posicao;
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
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //acima
        p.setValor(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda
        p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //direita
        p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //abaixo
        p.setValor(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }


        return matriz;
    }
}
