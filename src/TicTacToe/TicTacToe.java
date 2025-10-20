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

        while (true) {
            System.out.print("Enter the coordinates: ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            if (parts.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int x, y;
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

            field[x - 1][y - 1] = 'X';
            break;
        }

        printField(field);
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
}
