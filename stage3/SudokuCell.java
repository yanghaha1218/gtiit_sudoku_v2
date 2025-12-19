/**
 * This class represents a single cell in a Sudoku puzzle.
 */
public class SudokuCell {

    /**
     * The value of the cell (0 if empty, 1-9 for filled cells)
     */
    private int value;

    /**
     * Indicates whether the cell is fixed (part of the original puzzle) or not
     */
    private boolean isFixed;

    /**
     * Constructor of the SudokuCell class.
     * @param value the value of the cell (0 if empty)
     * @param isFixed true if the cell is fixed, false otherwise
     * Precondition: value is between 0 and 9 inclusive
     * Precondition: if isFixed is true, value must be between 1 and 9
     */
    public SudokuCell(int value, boolean isFixed) {
        this.value = value;
        this.isFixed = isFixed;
    }

    /**
     * Returns the value of the cell.
     * @return the value of the cell
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the cell.
     * @param value the new value of the cell
     * Precondition: the cell is not fixed
     */
    public void setValue(int value) {
        assert !isFixed : "The cell is not fixed";
        this.value = value;
    }

    /**
     * Marks the cell as fixed.
     * Precondition: the cell is not already fixed
     * Precondition: the cell has a non-zero value
     */
    public void makeFixed() {
        assert !isFixed : "Cell is already fixed.";
        assert value !=0 : "The cell has a non-zero value";
        isFixed = true;
    }

    /**
     * Marks the cell as unfixed.
     * Precondition: the cell is currently fixed
     */
    public void makeUnfixed() {
        assert isFixed : "Cell is already fixed.";
        isFixed = false;
    }

    /**
     * Returns whether the cell is fixed.
     * @return true if the cell is fixed, false otherwise
     */
    public boolean isFixed() {
        return isFixed;
    }

    /**
     * Returns a string representation of the cell.
     * @return string representation of the cell
     */
    public String toString() {
        if ( value == 0 ){
            return " _ ";
        }
        return isFixed ? "[" + value + "]" : " " + value + " ";
    }


}
