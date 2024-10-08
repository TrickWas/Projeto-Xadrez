package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import JogoTabuleiro.Peca;
import JogoTabuleiro.Posicao;
import JogoTabuleiro.Tabuleiro;
import Xadrez.Peças.Bispo;
import Xadrez.Peças.Cavalo;
import Xadrez.Peças.Dama;
import Xadrez.Peças.Peao;
import Xadrez.Peças.Rei;
import Xadrez.Peças.Torre;

public class PartidaXadrez {

    private int vez;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkMate;
    private PecaXadrez enPassantVulneravel;
    private PecaXadrez promocao;

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

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public PecaXadrez getEnPassantVulneravel() {
        return enPassantVulneravel;
    }

    public PecaXadrez getPromocao() {
        return promocao;
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
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

        if (testarCheck(jogadorAtual)) {
            desfazerMovimento(origem, alvo, capturaPeca);
            throw new ExcecaoXadrez("Você não pode se colocar em Check");
        }

        PecaXadrez pecaMove = (PecaXadrez) tabuleiro.peca(alvo);

        // #jogada especial promocao
        promocao = null;
        if (pecaMove instanceof Peao) {
            if ((pecaMove.getCor() == Cor.BRANCO && alvo.getLinha() == 0)
                    || (pecaMove.getCor() == Cor.PRETO && alvo.getLinha() == 7)) {
                promocao = (PecaXadrez) tabuleiro.peca(alvo);
                promocao = substituirPromocaoPeca("D");
            }
        }

        check = (testarCheck(adversario(jogadorAtual))) ? true : false;

        if (testarCheckMate(adversario(jogadorAtual))) {
            checkMate = true;
        } else {
            proximaVez();
        }

        // #jogada especial En passant
        if (pecaMove instanceof Peao
                && (alvo.getLinha() == origem.getLinha() - 2 || alvo.getLinha() == origem.getLinha() + 2)) {
            enPassantVulneravel = pecaMove;
        } else {
            enPassantVulneravel = null;
        }

        return (PecaXadrez) capturaPeca;
    }

    public PecaXadrez substituirPromocaoPeca(String tipo) {
        if (promocao == null) {
            throw new IllegalStateException("Não tem peça para ser promovida");
        }
        if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("D")) {
            return promocao;
        }

        Posicao pos = promocao.getPosicaoXadrez().toPosicao();
        Peca p = tabuleiro.removePeca(pos);
        pecaNoTabuleiro.remove(p);

        PecaXadrez novaPeca = novaPeca(tipo, promocao.getCor());
        tabuleiro.lugarPeca(novaPeca, pos);
        pecaNoTabuleiro.add(novaPeca);

        return novaPeca;
    }

    private PecaXadrez novaPeca(String tipo, Cor cor) {
        if (tipo.equals("B"))
            return new Bispo(tabuleiro, cor);
        if (tipo.equals("C"))
            return new Cavalo(tabuleiro, cor);
        if (tipo.equals("T"))
            return new Torre(tabuleiro, cor);
        return new Dama(tabuleiro, cor);
    }

    private Peca fazerMovimento(Posicao origem, Posicao alvo) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(origem);
        p.aumentaContaMovimentos();
        Peca capturaPeca = tabuleiro.removePeca(alvo);
        tabuleiro.lugarPeca(p, alvo);

        if (capturaPeca != null) {
            pecaNoTabuleiro.remove(capturaPeca);
            pecasCapturadas.add(capturaPeca);
        }

        // #jogada especial Roque para o lado do rei Torre
        if (p instanceof Rei && alvo.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemT);
            tabuleiro.lugarPeca(torre, alvoT);
            torre.aumentaContaMovimentos();
        }
        // #jogada especial Roque para o lado da dama Torre
        if (p instanceof Rei && alvo.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemT);
            tabuleiro.lugarPeca(torre, alvoT);
            torre.aumentaContaMovimentos();
        }

        // #jogada especial En passant
        if (p instanceof Peao) {
            if (origem.getColuna() != alvo.getColuna() && capturaPeca == null) {
                Posicao peaoPosicao;
                if (p.getCor() == Cor.BRANCO) {
                    peaoPosicao = new Posicao(alvo.getLinha() + 1, alvo.getColuna());
                } else {
                    peaoPosicao = new Posicao(alvo.getLinha() - 1, alvo.getColuna());
                }
                capturaPeca = tabuleiro.removePeca(peaoPosicao);
                pecasCapturadas.add(capturaPeca);
                pecaNoTabuleiro.remove(capturaPeca);
            }
        }
        return capturaPeca;
    }

    private void desfazerMovimento(Posicao origem, Posicao alvo, Peca capturPeca) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(alvo);
        p.diminuiContaMovimentos();
        tabuleiro.lugarPeca(p, origem);

        if (capturPeca != null) {
            tabuleiro.lugarPeca(capturPeca, alvo);
            pecasCapturadas.remove(capturPeca);
            pecaNoTabuleiro.add(capturPeca);
        }

        // #jogada especial Roque para o lado do rei Torre
        if (p instanceof Rei && alvo.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(alvoT);
            tabuleiro.lugarPeca(torre, origemT);
            torre.diminuiContaMovimentos();
        }
        // #jogada especial Roque para o lado da dama Torre
        if (p instanceof Rei && alvo.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(alvoT);
            tabuleiro.lugarPeca(torre, origemT);
            torre.diminuiContaMovimentos();
        }

        // #jogada especial En passant
        if (p instanceof Peao) {
            if (origem.getColuna() != alvo.getColuna() && capturPeca == enPassantVulneravel) {
                PecaXadrez peao = (PecaXadrez) tabuleiro.removePeca(alvo);
                Posicao peaoPosicao;
                if (p.getCor() == Cor.BRANCO) {
                    peaoPosicao = new Posicao(3, alvo.getColuna());
                } else {
                    peaoPosicao = new Posicao(4, alvo.getColuna());
                }
                tabuleiro.lugarPeca(peao, peaoPosicao);
            }
        }
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

    private Cor adversario(Cor cor) {
        return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
                .collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não há rei " + cor + " no tabuleiro");
    }

    private boolean testarCheck(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> pecasAdversario = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == adversario(cor))
                .collect(Collectors.toList());
        for (Peca p : pecasAdversario) {
            boolean[][] matriz = p.movimentoPossivel();
            if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testarCheckMate(Cor cor) {
        if (!testarCheck(cor)) {
            return false;
        }
        List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
                .collect(Collectors.toList());
        for (Peca p : list) {
            boolean[][] matriz = p.movimentoPossivel();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (matriz[i][j]) {
                        Posicao origem = ((PecaXadrez) p).getPosicaoXadrez().toPosicao();
                        Posicao alvo = new Posicao(i, j);
                        Peca pecaCapturada = fazerMovimento(origem, alvo);
                        boolean testaCheck = testarCheck(cor);
                        desfazerMovimento(origem, alvo, pecaCapturada);
                        if (!testaCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void lugarNovoPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecaNoTabuleiro.add(peca);
    }

    private void configuracaoInicial() {
        lugarNovoPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('d', 1, new Dama(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovoPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        lugarNovoPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

        lugarNovoPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovoPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        lugarNovoPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        lugarNovoPeca('d', 8, new Dama(tabuleiro, Cor.PRETO));
        lugarNovoPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        lugarNovoPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        lugarNovoPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovoPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
        lugarNovoPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));

    }
}
