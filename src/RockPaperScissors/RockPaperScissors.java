package RockPaperScissors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

interface GameOption {
    String getName();
    boolean beats(GameOption other);
}

class Option implements GameOption {

    private final String name;
    private final List<String> allOptions;
    private final int position;

    public Option(String name, List<String> allOptions, int position) {
        this.name = name;
        this.allOptions = allOptions;
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean beats(GameOption other) {
        if (!(other instanceof Option)) {
            return false;
        }

        Option otherOption = (Option) other;
        int otherPosition = otherOption.position;

        // Calculate which options this one beats
        int totalOptions = allOptions.size();
        int halfOptions = totalOptions / 2;

        // Get positions this option beats
        for (int i = 1; i <= halfOptions; i++) {
            int beatenPosition = (position + i) % totalOptions;
            if (beatenPosition == otherPosition) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return name.equals(option.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

interface GameResult {
    void display(String computerChoice);
    int getScore();
}

class WinResult implements GameResult {

    @Override
    public void display(String computerChoice) {
        System.out.println("Well done. The computer chose " + computerChoice + " and failed");
    }

    @Override
    public int getScore() {
        return 100;
    }
}

class DrawResult implements GameResult {

    @Override
    public void display(String computerChoice) {
        System.out.println("There is a draw (" + computerChoice + ")");
    }

    @Override
    public int getScore() {
        return 50;
    }
}

class LoseResult implements GameResult {

    @Override
    public void display(String computerChoice) {
        System.out.println("Sorry, but the computer chose " + computerChoice);
    }

    @Override
    public int getScore() {
        return 0;
    }
}

class Player {

    private final String name;
    private int rating;

    public Player(String name, int initialRating) {
        this.name = name;
        this.rating = initialRating;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void addScore(int score) {
        rating += score;
    }
}

class RatingManager {

    private static final String RATING_FILE = "./src/RockPaperScissors/rating.txt";

    public int getPlayerRating(String playerName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RATING_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[0].equals(playerName)) {
                    return Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
        } catch (NumberFormatException e) {
            System.err.println("Error parsing rating file");
        }
        return 0;
    }
}

class GameEngine {

    private final List<String> optionNames;
    private final Map<String, Option> options;
    private final Random random;
    private final Player player;

    public GameEngine(List<String> optionNames, Player player) {
        this.optionNames = optionNames;
        this.options = new HashMap<>();
        this.random = new Random();
        this.player = player;

        for (int i = 0; i < optionNames.size(); i++) {
            String name = optionNames.get(i);
            options.put(name, new Option(name, optionNames, i));
        }
    }

    public boolean isValidOption(String input) {
        return options.containsKey(input);
    }

    public GameResult play(String userChoice) {
        Option userOption = options.get(userChoice);
        String computerChoice = getRandomOption();
        Option computerOption = options.get(computerChoice);

        GameResult result;
        if (userOption.equals(computerOption)) {
            result = new DrawResult();
        } else if (userOption.beats(computerOption)) {
            result = new WinResult();
        } else {
            result = new LoseResult();
        }

        result.display(computerChoice);
        player.addScore(result.getScore());

        return result;
    }

    private String getRandomOption() {
        int index = random.nextInt(optionNames.size());
        return optionNames.get(index);
    }
}

public class RockPaperScissors {

    private static final List<String> DEFAULT_OPTIONS = Arrays.asList("rock", "paper", "scissors");
    private static final String EXIT_COMMAND = "!exit";
    private static final String RATING_COMMAND = "!rating";

    private final Scanner scanner;
    private final RatingManager ratingManager;
    private Player player;
    private GameEngine gameEngine;

    public RockPaperScissors() {
        this.scanner = new Scanner(System.in);
        this.ratingManager = new RatingManager();
    }

    public void start() {
        initializePlayer();
        initializeGameOptions();
        System.out.println("Okay, let's start");
        gameLoop();
    }

    private void initializePlayer() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        int initialRating = ratingManager.getPlayerRating(name);
        player = new Player(name, initialRating);
        System.out.println("Hello, " + name);
    }

    private void initializeGameOptions() {
        String input = scanner.nextLine().trim();
        List<String> options;

        if (input.isEmpty()) {
            options = DEFAULT_OPTIONS;
        } else {
            options = new ArrayList<>();
            String[] parts = input.split(",");
            for (String part : parts) {
                options.add(part.trim());
            }
        }

        gameEngine = new GameEngine(options, player);
    }

    private void gameLoop() {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals(EXIT_COMMAND)) {
                System.out.println("Bye!");
                break;
            } else if (input.equals(RATING_COMMAND)) {
                System.out.println("Your rating: " + player.getRating());
            } else if (gameEngine.isValidOption(input)) {
                gameEngine.play(input);
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        game.start();
    }
}