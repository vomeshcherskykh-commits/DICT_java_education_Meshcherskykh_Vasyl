package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();

        char[][] field = new char[3][3];
        for (int i = 0; i < 9; i++) {
            field[i / 3][i % 3] = input.charAt(i);
        }

        printField(field);
        System.out.println(analyze(field));
    }

    private static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String analyze(char[][] field) {

        int xCount = 0;
        int oCount = 0;
        for (char[] row : field) {
            for (char c : row) {
                if (c == 'X') xCount++;
                if (c == 'O') oCount++;
            }
        }

        boolean xWins = checkWin(field, 'X');
        boolean oWins = checkWin(field, 'O');

        if ((xWins && oWins) || Math.abs(xCount - oCount) > 1) return "Impossible";
        if (xWins) return "X wins";
        if (oWins) return "O wins";
        if (xCount + oCount == 9) return "Draw";
        return "Game not finished";
    }

    private static boolean checkWin(char[][] field, char player) {

        for (int i = 0; i < 3; i++) {
            if (field[i][0] == player && field[i][1] == player && field[i][2] == player) return true;
            if (field[0][i] == player && field[1][i] == player && field[2][i] == player) return true;
        }

        return (field[0][0] == player && field[1][1] == player && field[2][2] == player)
                || (field[0][2] == player && field[1][1] == player && field[2][0] == player);
    }
}
