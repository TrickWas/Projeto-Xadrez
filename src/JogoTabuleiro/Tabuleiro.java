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

    

    
    
}
