package JogoTabuleiro;

public class Excecao extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public Excecao(String mensagem) {
        super (mensagem);
    }
}
