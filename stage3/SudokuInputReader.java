import java.util.Scanner;

/**
 * This class handles user input for a Sudoku game.
 */
public class SudokuInputReader {

    /**
     * The row input from the user (0-8 for rows 1-9)
     */
    private int rowInput;

    /**
     * The column input from the user (0-8 for columns 1-9)
     */
    private int colInput;

    /**
     * The value input from the user (1-9)
     */
    private int valueInput;

    /**
     * Indicates whether the user wants to quit the game
     */
    private boolean userWantsToQuit;

    /**
     * Indicates whether the user wants the computer to solve the puzzle
     */
    private boolean userWantstoSolve;

    /**
     * Scanner for reading user input
     */
    private Scanner scanner;

    /**
     * Constructor of the SudokuInputReader class.
     */
    public SudokuInputReader() {
        rowInput = -1;
        colInput = -1;
        valueInput = -1;
        userWantsToQuit = false;
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input for a Sudoku move or quit command.
     * Method keeps prompting the user until valid input is received.
     */
    public void readInput() {
        boolean validInput = false;
        while (!validInput && !userWantsToQuit) {
            System.out.println("Enter three numbers (row, column, value) separated by spaces to place a value, 's' to solve, or 'q' to quit:");
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("q")) {
                validInput = true;
                userWantsToQuit = true;
            }
            else {
                if (userInput.equalsIgnoreCase("s")) {
                    validInput = true;
                    userWantstoSolve = true;
                } else {
                    String[] parts = userInput.split(" ");
                    if (parts.length == 3) {
                        try {
                            int row = Integer.parseInt(parts[0]);
                            int col = Integer.parseInt(parts[1]);
                            int value = Integer.parseInt(parts[2]);
                            if (row >= 0 && row < 9 && col >= 0 && col < 9 && value >= 1 && value <= 9) {
                                rowInput = row;
                                colInput = col;
                                valueInput = value;
                                validInput = true;
                            } else {
                                System.out.println("Error: row and col must be between 0 and 8, value between 1 and 9.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. Please enter three numbers, 's' to solve, or 'q' to quit.");
                        }
                    } else {
                        System.out.println("Error: Please enter exactly three numbers, 's' to solve, or 'q' to quit.");
                    }
                }
            }
        }

    }

    /**
     * Returns whether the user wants to quit the game.
     * @return true if the user wants to quit, false otherwise
     */
    public boolean userWantsToQuit() {
        return userWantsToQuit;
    }

    /**
     * Returns whether the user wants the computer to solve the puzzle.
     * @return true if the user wants the computer to solve, false otherwise
     */
    public boolean userWantsToSolve() {
        return userWantstoSolve;
    }

    /**
     * Returns the row input from the user.
     * @return the row input (0-8)
     */
    public int getRowInput() {
        return this.rowInput;
    }

    /**
     * Returns the column input from the user.
     * @return the column input (0-8)
     */
    public int getColInput() {
        return this.colInput;
    }

    /**
     * Returns the value input from the user.
     * @return the value input (1-9)
     */
    public int getValueInput() {
        return this.valueInput;
    }
}