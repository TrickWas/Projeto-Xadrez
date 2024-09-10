package Xadrez;

public class ExcecaoXadrez extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcecaoXadrez (String mensagem) {
        super(mensagem);
    }
}
