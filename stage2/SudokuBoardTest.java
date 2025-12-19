

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class containing tests of the SudokuBoard class.
 *
 * @author  Nazareno Aguirre
 * @version 0.1
 */
public class SudokuBoardTest {
    
    //Stage one test
    private SudokuBoard board = new SudokuBoard("puzzle_1.txt");

    /**
     * Test whether we can create a board
     */
    @Test
    public void testCreateBoard(){
        assertNotNull(board);
    }
    
    /**
     * Test whether we can set values for a cell
     */
    @Test
    public void testSetCellValue(){
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        board.setCellValue(0, 0, 5);
        assertEquals(5, board.getBoard()[0][0].getValue());
    }
    
    /**
     * Test containsInRow method
     */
    @Test
    public void testContainsInRow() {
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        assertTrue(board.containsInRow(0, 1));
        assertFalse(board.containsInRow(0, 5));
    }
    
    /**
     * Test containsInColumn method
     */
    @Test
    public void testContainsInColumn() {
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        assertTrue(board.containsInColumn(0, 6));
        assertFalse(board.containsInColumn(0, 5));
    }
    
    /**
     * Test containsInBox method
     */
    @Test
    public void testContainsInBox() {
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        assertTrue(board.containsInBox(0, 0, 6));
        assertFalse(board.containsInBox(0, 0, 5));
    }
    
    /**
     * Test isValidOption method
     */
    @Test
    public void testIsValidOption() {
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        assertTrue(board.isValidOption(0, 0, 5));
        assertFalse(board.isValidOption(0, 1, 6));
    }
    
    /**
     * Test isCellFixed method
     */
    @Test
    public void testIsCellFixed() {
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        assertTrue(board.isCellFixed(0, 1), "Cell (0,1) should be fixed");
        assertFalse(board.isCellFixed(0, 0), "Cell (0,0) should not be fixed");
    }
}