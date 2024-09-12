package JogoTabuleiro;

public abstract class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public abstract boolean[][] movimentoPossivel();

    public boolean movimentoPossivel (Posicao posicao) {
        return movimentoPossivel()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean temUmMovimentoPossivel() {
        boolean[][] matriz = movimentoPossivel();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j]) {
                    return true;
                }
            }
        }
        return false;

    }
}
