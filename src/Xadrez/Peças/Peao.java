package Xadrez.Peças;

import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
        
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.BRANCO) {
            p.setValor(posicao.getLinha() - 1, posicao.getColuna());
            if ( getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if ( getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temUmaPeca(p2) && getContaMovimentos() == 0) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if ( getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if ( getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
        }
        else {
            p.setValor(posicao.getLinha() + 1, posicao.getColuna());
            if ( getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if ( getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temUmaPeca(p2) && getContaMovimentos() == 0) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if ( getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if ( getTabuleiro().existePosicao(p) && temUmaPecaAdversaria(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }

        }

        return matriz;
    }

    @Override
    public String toString() {
        return "P";
    }
    
}
