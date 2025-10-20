package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char[][] field = {2
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        boolean xTurn = true;
        printField(field);

        while (true) {
            int x, y;
            while (true) {
                System.out.print("Enter the coordinates: ");
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                if (parts.length != 2) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                try {
                    x = Integer.parseInt(parts[0]);
                    y = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (field[x - 1][y - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                break;
            }

            field[x - 1][y - 1] = xTurn ? 'X' : 'O';
            printField(field);

            String result = analyze(field);
            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;
            }

            xTurn = !xTurn;
        }
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

    private static boolean checkWin(char[][] f, char p) {

        for (int i = 0; i < 3; i++) {
            if (f[i][0] == p && f[i][1] == p && f[i][2] == p) return true;
            if (f[0][i] == p && f[1][i] == p && f[2][i] == p) return true;
        }
        return (f[0][0] == p && f[1][1] == p && f[2][2] == p)
                || (f[0][2] == p && f[1][1] == p && f[2][0] == p);
    }

    private static String analyze(char[][] f) {

        boolean xWins = checkWin(f, 'X');
        boolean oWins = checkWin(f, 'O');
        if (xWins) return "X wins";
        if (oWins) return "O wins";

        for (char[] row : f) {
            for (char c : row) {
                if (c == '_') return "Game not finished";
            }
        }
        return "Draw";
    }
}
