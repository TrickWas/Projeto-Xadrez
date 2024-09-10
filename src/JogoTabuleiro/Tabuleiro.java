package JogoTabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new Excecao("Erro na criação do tabuleiro: é necessario que haja 1 linha e 1 coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinha() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca (int linha, int coluna) {
        if (!existePosicao(linha, coluna)) {
            throw new Excecao("Posição não está no Tabuleiro");
        }
        return pecas[linha][coluna];
    }

    public Peca peca (Posicao posicao) {
        if (!existePosicao(posicao)) {
            throw new Excecao("Posição não está no Tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    } 

    public void lugarPeca (Peca peca, Posicao posicao) {
        if (temUmaPeca(posicao)) {
            throw new Excecao("Ja existe uma peça na posição " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    private boolean existePosicao (int linha,int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean existePosicao (Posicao posicao) {
        return existePosicao(posicao.getLinha(), posicao.getColuna());

    }

    public boolean temUmaPeca (Posicao posicao) {
        if (!existePosicao(posicao)) {
            throw new Excecao("Posição não está no Tabuleiro");
        }
         return peca(posicao) != null;
    }
}
