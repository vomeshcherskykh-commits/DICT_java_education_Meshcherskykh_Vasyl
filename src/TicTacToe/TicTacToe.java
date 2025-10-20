package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {

    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private boolean xTurn = true;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        this.board = new Board();
        this.playerX = new Player('X');
        this.playerO = new Player('O');
    }

    public void start() {

        board.print();

        while (true) {

            Player current = xTurn ? playerX : playerO;
            makeMove(current);

            board.print();

            String result = board.analyze();
            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;
            }

            xTurn = !xTurn;
        }
    }

    private void makeMove(Player player) {

        while (true) {

            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
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

            if (!board.isCellEmpty(x - 1, y - 1)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board.setCell(x - 1, y - 1, player.getSymbol());
            break;
        }
    }
}

class Board {

    private final char[][] field = new char[3][3];

    public Board() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                field[i][j] = '_';
    }

    public void print() {
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

    public boolean isCellEmpty(int x, int y) {
        return field[x][y] == '_';
    }

    public void setCell(int x, int y, char symbol) {
        field[x][y] = symbol;
    }

    public String analyze() {

        if (checkWin('X')) return "X wins";
        if (checkWin('O')) return "O wins";

        for (char[] row : field)
            for (char c : row)
                if (c == '_') return "Game not finished";

        return "Draw";
    }

    private boolean checkWin(char p) {

        for (int i = 0; i < 3; i++) {
            if (field[i][0] == p && field[i][1] == p && field[i][2] == p) return true;
            if (field[0][i] == p && field[1][i] == p && field[2][i] == p) return true;
        }
        return (field[0][0] == p && field[1][1] == p && field[2][2] == p) || (field[0][2] == p && field[1][1] == p && field[2][0] == p);
    }
}

// ---------------- Клас гравця ----------------
class Player {
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
