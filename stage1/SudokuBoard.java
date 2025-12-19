import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a Sudoku board.
 */
public class SudokuBoard {
    
    /**
     * The size of the Sudoku board (9x9)
     */
    public static final int SIZE = 9;

    /**
     * The 9x9 grid of Sudoku cells
     */
    private List<List<SudokuCell>> board;

    /**
     * Strategy for toString representation
     */
    private ToStringStrategy toStringStrategy;

    /**
     * Constructor of the SudokuBoard class. Loads a Sudoku board from a file.
     *
     * @param contents   the name of the file containing the Sudoku board data. Each line has 9 digits (0-9), where 0 represents an empty cell.
     */
    public SudokuBoard(String contents) {
        setDefaultToStringStrategy();
        board = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(contents))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < SIZE) {
                String digits = line.replaceAll("[^0-9]", "");
                if (digits.length() != SIZE) {
                    throw new IllegalArgumentException("Row " + (row) + " invalid: expecting 9 digits.");
                }
                List<SudokuCell> rowList = new ArrayList<>();
                for (int col = 0; col < SIZE; col++) {
                    int val = Character.getNumericValue(digits.charAt(col));
                    if (val < 0 || val > 9) {
                        throw new IllegalArgumentException("Invalid value in row " + (row) + ", column " + (col));
                    }
                    rowList.add(new SudokuCell(val, val != 0));
                }
                board.add(rowList);
                row++;
            }
            if (board.size() != SIZE) {
                throw new IllegalArgumentException("File must contain exactly 9 lines with 9 digits each.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be opened: " + contents, e);
        }
    }
    
    public List<List<SudokuCell>> getBoard(){
        return this.board;
    }

    /**
     * Copy constructor of the SudokuBoard class.
     *
     * @param board the SudokuBoard to copy
     */
    public SudokuBoard(SudokuBoard board) {
        //TODO Implement this method, including precondition checking
    }

    /**
     * Sets the value of a cell and marks it as fixed.
     * Precondition: row and col are between 0 and 8 inclusive
     * Precondition: value is between 1 and 9 inclusive
     * Precondition: the cell at (row, col) is not already fixed
     * Precondition: the value does not already exist in the same row, column, or 3x3 box
     *
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @param value the value to set in the cell
     */
    public void setFixedCellValue(int row, int col, int value) {
        assert row >= 0 && row < SIZE : "Row index out of bounds.";
        assert col >= 0 && col < SIZE : "Column index out of bounds.";
        assert value >= 1 && value <= 9 : "Value out of range.";
        assert !board.get(row).get(col).isFixed(): "Cannot change the value of a fixed cell.";
        assert !containsInRow(row, value) : "Value already exists in the same row.";
        assert !containsInColumn(col, value) : "Value already exists in the same column.";
        assert !containsInBox(row, col, value) : "Value already exists in the same 3x3 box.";
        SudokuCell cell = board.get(row).get(col);
        cell.setValue(value);
        cell.makeFixed();
    }

    /**
     * Checks if a value exists in a specific row.
     *
     * @param row   the row index to check
     * @param value the value to look for
     * @return true if the value exists in the row, false otherwise
     */
    public boolean containsInRow(int row, int value) {
        assert row >= 0 && row < SIZE : "Row index out of bounds.";
        assert value >= 1 && value <= 9 : "Value out of range.";
        for (int col = 0; col < SIZE; col++) {
            if (board.get(row).get(col).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value exists in a specific column.
     *
     * @param col   the column index to check
     * @param value the value to look for
     * @return true if the value exists in the column, false otherwise
     */
    public boolean containsInColumn(int col, int value) {
        assert col >= 0 && col < SIZE : "Column index out of bounds.";
        assert value >= 1 && value <= 9 : "Value out of range.";
        for (int row = 0; row < SIZE; row++) {
            if (board.get(row).get(col).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value exists in the 3x3 box containing the specified cell.
     *
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @param value the value to look for
     * @return true if the value exists in the 3x3 box, false otherwise
     */
    public boolean containsInBox(int row, int col, int value) {
        assert row >= 0 && row < SIZE : "Row index out of bounds.";
        assert col >= 0 && col < SIZE : "Column index out of bounds.";
        assert value >= 1 && value <= 9 : "Value out of range.";
        
        //Fix the start-Row and start-col
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        
        //Check the box
        for (int i = boxRow; i < boxRow + 3; i++){
            for (int j = boxCol; j < boxCol + 3; j++){
                if(board.get(i).get(j).getValue() == value){
                    return true;
                }
            }    
        }
        return false;
    }

    /**
     * Checks if a value can be placed in a specific cell without violating Sudoku rules.
     *
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @param value the value to check
     * @return true if the value can be placed in the cell, false otherwise
     */
    public boolean isValidOption(int row, int col, int value) {
        assert row >= 0 && row < SIZE : "Row index out of bounds.";
        assert col >= 0 && col < SIZE : "Column index out of bounds.";
        assert value >= 1 && value <= 9 : "Value out of range.";
        return !containsInRow(row, value) && !containsInColumn(col, value) && !containsInBox(row, col, value);
    }

    /**
     * Checks if a cell is fixed.
     *
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @return true if the cell is fixed, false otherwise
     */
    public boolean isCellFixed(int row, int col) {
        assert row >= 0 && row < SIZE : "Row index out of bounds.";
        assert col >= 0 && col < SIZE : "Column index out of bounds.";
        return board.get(row).get(col).isFixed();
    }

    /**
     * Checks if the Sudoku board is completely and correctly solved.
     *
     * @return true if the board is solved, false otherwise
     */
    public boolean isSolved() {
        //Check the value
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                if(board.get(row).get(col).getValue() < 1 || board.get(row).get(col).getValue() > 9){
                    return false;
                }
            }
        }
        //Check the row, I'll check all the values appeared, and since we have only 9 cells, if 1-9 appeared, then they will only appear once
        for (int row = 0; row < SIZE; row++){
            for (int val = 1; val <= SIZE; val++){
                if(!containsInRow(row,val)){
                    return false;
                }
            }
        }
        //Check the col, I'll check all the values appeared, and since we have only 9 cells, if 1-9 appeared, then they will only appear once
        for (int col = 0; col < SIZE; col++){
            for (int val = 1; val <= SIZE; val++){
                if(!containsInColumn(col,val)){
                    return false;
                }
            }
        }
        //Check the box, I'll check all the values appeared, and since we have only 9 cells, if 1-9 appeared, then they will only appear once
        for (int boxRow = 0; boxRow < (SIZE/3); boxRow++){
            for (int boxCol = 0; boxCol < (SIZE/3); boxCol++){
                int boxStartRow = boxRow * 3;
                int boxStartCol = boxCol * 3;
                for(int val = 1; val <= SIZE; val++){
                    if(!containsInBox(boxStartRow,boxStartCol,val)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Attempts to solve the Sudoku board. If solvable, returns a solved SudokuBoard instance;
     * otherwise, returns null.
     *
     * @return a solved SudokuBoard instance if solvable, null otherwise
     */
    public SudokuBoard solve() {
       //TODO Implement this method, replacing/removing the following line.
       return null;
    }


    /**
     * Sets the value of a cell.
     * Precondition: row and col are between 0 and 8 inclusive
     * Precondition: value is between 1 and 9 inclusive
     * Precondition: the cell at (row, col) is not fixed
     *
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @param value the value to set in the cell
     */
    public void setCellValue(int row, int col, int value) {
        assert row >= 0 && row < SIZE : "Row index out of bounds.";
        assert col >= 0 && col < SIZE : "Column index out of bounds.";
        assert value >= 1 && value <= 9 : "Value out of range.";
        SudokuCell cell = board.get(row).get(col);
        assert !cell.isFixed(): "Cannot change the value of a fixed cell.";
        cell.setValue(value);
    }

    /**
     * Checks if cell has valid value. A cell has a valid value
     * iff it's unset (zero) or the value is greater or equal to one, smaller or equal to 9, and
     * is not repeated in the corresponding row, column or 3x3 box.
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @return true iff cell is unset or has valid (non-conflicting with rows, columns and boxes) value 
     * according to Sudoku rules.
     */
    public boolean validValueInCell(int row, int col) {
        //TODO Implement this method
        return false;
    }

    /**
     * Sets the toString strategy to use colored output.
     */
    public void setColoredToStringStrategy() {
        toStringStrategy = new ColoredBoardToString();
    }

    /**
     * Sets the toString strategy to use default output.
     */
    public void setDefaultToStringStrategy() {
        toStringStrategy = new DefaultBoardToString();
    }

    /**
     * Returns a string representation of the Sudoku board.
     * The specific format is determined by the toString strategy in use.
     *
     * @return a string representation of the board
     */
    public String toString() {
        return toStringStrategy.toString(this);
    }

    /**
     * Strategy interface for toString representation
     */
    public interface ToStringStrategy {

        /**
         * Converts the Sudoku board to a string representation.
         * @param board the Sudoku board to convert
         * @return string representation of the board
         */
        String toString(SudokuBoard board);
    }

    /**
     * Implementation of ToStringStrategy that provides colored output.
     */
    public class ColoredBoardToString implements ToStringStrategy {

        /**
         * Converts the given SudokuBoard object to its string representation
         * with colors.
         *
         * @param sb the SudokuBoard to be converted
         * @return the string representation of the board
         */
        public String toString(SudokuBoard sb) {
            String output = "";
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    SudokuCell cell = sb.board.get(i).get(j);
                    if (!cell.isFixed()) {
                        if (validValueInCell(i, j)) {
                            output += "\u001B[34m" + cell.toString() + "\u001B[0m ";
                        } else {
                            output += "\u001B[31m" + cell.toString() + "\u001B[0m ";
                        }
                    } else {
                        output += "\u001B[30m" + cell.toString() + "\u001B[0m ";
                    }
                }
                output += "\n";
            }
            return output;
        }
        
       

    }

    /**
     * Default implementation of ToStringStrategy without colors.
     */
    public class DefaultBoardToString implements ToStringStrategy {

        /**
         * Converts the given SudokuBoard object to its string representation.
         *
         * @param sb the SudokuBoard to be converted
         * @return the string representation of the board
         */
        public String toString(SudokuBoard sb) {
            String output = "";
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    SudokuCell cell = sb.board.get(i).get(j);
                    output += cell.toString() + " ";
                }
                output += "\n";
            }
            return output;
        }

    }
    
    
}