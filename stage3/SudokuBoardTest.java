

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
     * Test whether we can set values for a non-fixed cell
     */
    @Test
    public void testSetCellValue(){
        board.setCellValue(6, 8, 5);
        assertEquals(5, board.boardGetter()[6][8].getValue());
    }
    
    /**
     * Test validValueInCell
     */
    @Test
    public void testValidValueInCell() {
        SudokuBoard board = new SudokuBoard("puzzle_2.txt");
        assertTrue(board.validValueInCell(0, 0));
        board.setCellValue(0, 0, 6);
        assertFalse(board.validValueInCell(0, 0));
    }
    
    /**
     * Test isCellFixed method
     */
    @Test
    public void testIsCellFixed() {
        SudokuBoard board = new SudokuBoard("puzzle_2.txt");
        assertTrue(board.isCellFixed(0, 1));
        assertFalse(board.isCellFixed(0, 0));
    }
    
    /**
     * Tests that the board:
     * 034678912
     * 672195348
     * 198042567
     * 859761423
     * 426853791
     * 713924856
     * 961537204
     * 280419635
     * 345286079
     * is solvable. 
     */
    @Test
    public void testSolvableBoard() {
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        SudokuBoard solvedBoard = board.solve();
        assertNotNull(solvedBoard);
        assertTrue(solvedBoard.isSolved());
    }
    
    /**
     * Tests that the board:
     * 034678912
     * 672195348
     * 198042567
     * 859761423
     * 426853791
     * 713924856
     * 961537204
     * 280419635
     * 345286079
     * becomes unsolvable if we set coordinate (8, 6) with 9 (repeated value in row).
     */
    @Test
    public void testUnsolvableBoard() {
        SudokuBoard board = new SudokuBoard("solvable_puzzle_2.txt");
        board.setCellValue(8, 6, 9);
        SudokuBoard solvedBoard = board.solve();
        assertNull(solvedBoard);
    }
    
}