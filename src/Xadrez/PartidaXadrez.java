package Xadrez;

import java.util.ArrayList;
import java.util.List;

import JogoTabuleiro.Peca;
import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Peças.Rei;
import Xadrez.Peças.Torre;

public class PartidaXadrez {

    private int vez;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;

    private List<Peca> pecaNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        vez = 1;
        jogadorAtual = Cor.BRANCO;
        configuracaoInicial();
    }

    public int getVez() {
        return vez;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinha(); i++) {
            for (int j = 0;j < tabuleiro.getColunas(); j++) {
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return matriz;
    }

    public boolean[][] movimentoPossivel(PosicaoXadrez origemPosicao) {
        Posicao posicao = origemPosicao.toPosicao();
        validaOrigemPosicao(posicao);
        return tabuleiro.peca(posicao).movimentoPossivel();
    }

    public PecaXadrez movimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez alvoPosica) {
        Posicao origem = origemPosicao.toPosicao();
        Posicao alvo = alvoPosica.toPosicao();
        validaOrigemPosicao(origem);
        validarAlvoPosicao(origem, alvo);
        Peca capturaPeca = fazerMovimento(origem, alvo);
        proximaVez();
        return (PecaXadrez) capturaPeca;
    }

    private Peca fazerMovimento (Posicao origem, Posicao alvo) {
        Peca p = tabuleiro.removePeca(origem);
        Peca capturaPeca = tabuleiro.removePeca(alvo);
        tabuleiro.lugarPeca(p, alvo);

        if (pecasCapturadas != null) {
            pecaNoTabuleiro.remove(capturaPeca);
            pecasCapturadas.add(capturaPeca);
        }
        return capturaPeca;
    }

    private void validaOrigemPosicao(Posicao posicao) {
        if (!tabuleiro.temUmaPeca(posicao)) {
            throw new ExcecaoXadrez("Não existe peça na posição de origem");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
            throw new ExcecaoXadrez("A peça escolhida não é sua");
        }
        if (!tabuleiro.peca(posicao).temUmMovimentoPossivel()) {
            throw new ExcecaoXadrez("Não existe movimentos possiveis para a peça");
        }
    }

    private void validarAlvoPosicao(Posicao origem, Posicao alvo) {
        if (!tabuleiro.peca(origem).movimentoPossivel(alvo)) {
            throw new ExcecaoXadrez("A peça escolhida não pode ser movida para a posição destinada");
        }
    }

    private void proximaVez() {
        vez++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private void lugarNovoPeca (char coluna, int linha, PecaXadrez peca) {
        tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecaNoTabuleiro.add(peca);
    }

    private void configuracaoInicial() {
        lugarNovoPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        lugarNovoPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovoPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovoPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovoPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovoPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovoPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));

    }
}
