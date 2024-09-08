package JogoTabuleiro;

public class Tabuleiro {

    private int linha;
    private int coluna;
    private Peca[][] pecas;

    public Tabuleiro(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        pecas = new Peca[linha][coluna];
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public Peca peca (int linha, int coluna) {
        return pecas[linha][coluna];
    }

    public Peca peca (Posicao posicao) {
        return pecas[posicao.getLinha()][posicao.getColuna()];
    } 

    public void lugarPeca (Peca peca, Posicao posicao) {
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }
}
