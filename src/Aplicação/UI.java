package Aplicação;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
    // cores da escrita

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    // cores do fundo

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limpaTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}	
    
    public static PosicaoXadrez lerPosicaoXadrez (Scanner ler) {
        try {
            String s = ler.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadrez(coluna, linha);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao instanciar Posição do Xadrez: Valores validos são de a1 à h8");
        }
    }

    public static void printPartida (PartidaXadrez partidaXadrez, List<PecaXadrez> captura) {
        printTabuleiro(partidaXadrez.getPecas());
        printCapturaPeca(captura);
        System.out.println("\nRodada: " + partidaXadrez.getVez());
        if (!partidaXadrez.getCheckMate()) {
            System.out.println("Esperando jogador: " + partidaXadrez.getJogadorAtual());
            if (partidaXadrez.getCheck()) {
                System.out.println("CHECK!");
            }
        }
        else {
            System.out.println("CHECKMATE!");
            System.out.println("Vencedor: " + partidaXadrez.getJogadorAtual());
        }
    }

    public static void printTabuleiro (PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printTabuleiro (PecaXadrez[][] pecas, boolean[][] movimentoPossivel) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], movimentoPossivel[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPeca (PecaXadrez peca, boolean fundo) {
            if (fundo) {
                System.out.print(ANSI_BLUE_BACKGROUND);
            }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturaPeca(List <PecaXadrez> captura) {
        List <PecaXadrez> branco = captura.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
        List <PecaXadrez> preto = captura.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
        System.out.println("\nPeças capturadas:");
        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(branco.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(preto.toArray()));
        System.out.print(ANSI_RESET);
    }
}
