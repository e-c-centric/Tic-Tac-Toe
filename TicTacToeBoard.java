import java.util.Scanner;

/**
 * This class represents the board for a game of
 * TicTacToe
 * 
 * @author G. Ayorkor Korsah
 * @author Elikem Asudo Gale-Zoyiku
 */
public class TicTacToeBoard {

  private String[][] board; // the board
  private int numEmpty; // The number of empty slots
  private boolean detectedWin; // Whether or not a winner has been seen
  private String winningSymbol; // The symbol of the winner
  public static final int SIZE = 3;
  public static final String EMPTY = ".";

  /**
   * The constructor for the class.
   * It instantiates the 3x3 board and initializes the board to be empty.
   * You should also initialize the number of empty slots
   * 
   */
  public TicTacToeBoard() {
    board = new String[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = EMPTY;
      }
    }
    numEmpty = SIZE * SIZE;
    detectedWin = false;
    winningSymbol = "";
  }

  /**
   * It prints the board
   * 
   * @param none
   *  
   */
  public void printBoard() {
    System.out.print("  ");
    for (int i = 0; i < board[0].length; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (int i = 0; i < board.length; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * If the number of empty spaces is 0, then the board is full
   * 
   * @return whether or not the board is full.
   * @param none
   */
  public boolean isBoardFull() {
    return numEmpty == 0;
  }

  /**
   * Return true if a given location on the board,
   * specified by a given row index and column index, is empty
   * 
   * @param row The row of the board.
   * @param col the column of the board
   * @return Whether or not the location is empty.
   */
  public boolean isEmpty(int row, int col) {
    return board[row][col].equals(EMPTY);
  }

  /**
   * check the game for a win
   * If all the elements a row, column, or diagonal are the same,
   * then a win has been detected.
   * 
   * If a win is detected, the detectedWin instance variable should be set to
   * true,
   * and the winningSymbol instance variable should be set to the symbol of the
   * winner.
   *
   * Hint: There are 8 ways to win a 3x3 game of tic-tac-toe
   * 
   * This method works for all sizes of TicTacToe boards, e.g. 4x4 or 5x5
   * 
   * @param none
   *  
   * 
   */
  private void detectWin() {
    int test = 0;
    boolean forwardDiagonalWin = true;
    boolean backwardDiagonalWin = true;

    // Check diagonals
    for (int i = 0; i < SIZE - 1; i++) {
      // Check forward diagonal
      if (!board[i][i].equals(board[i + 1][i + 1]) || board[i][i].equals(EMPTY)) {
        forwardDiagonalWin = false;
      }

      // Check backward diagonal
      if (!board[i][SIZE - i - 1].equals(board[i + 1][SIZE - i - 2]) || board[i][SIZE - i - 1].equals(EMPTY)) {
        backwardDiagonalWin = false;
      }
    }

    if (forwardDiagonalWin && !board[test][test].equals(EMPTY)) {
      detectedWin = true;
      winningSymbol = board[test][test];
      return;
    } else if (backwardDiagonalWin && !board[test][SIZE - 1].equals(EMPTY)) {
      detectedWin = true;
      winningSymbol = board[test][SIZE - 1];
      return;
    }

    // Check rows and columns
    for (int i = 0; i < SIZE; i++) {
      boolean rowWin = true;
      boolean colWin = true;

      for (int j = 0; j < SIZE - 1; j++) {
        // Check row
        if (!board[i][j].equals(board[i][j + 1]) || board[i][j].equals(EMPTY)) {
          rowWin = false;
        }

        // Check column
        if (!board[j][i].equals(board[j + 1][i]) || board[j][i].equals(EMPTY)) {
          colWin = false;
        }
      }

      // Check for a win in row or column
      if (rowWin && !board[i][test].equals(EMPTY)) {
        detectedWin = true;
        winningSymbol = board[i][test];
        return;
      } else if (colWin && !board[test][i].equals(EMPTY)) {
        detectedWin = true;
        winningSymbol = board[test][i];
        return;
      }
    }
  }

  /**
   * If the board is empty at the given row and column,
   * then place the symbol on the board,
   * decrement the number of empty spaces,
   * and check for a win
   * 
   * If the board is not empty at the given row and column,
   * then the play is not successful and the method returns {@code false}.
   * 
   * 
   * @param row    the row of the board (0 indexed)
   * @param col    The column number of the board (0 indexed).
   * @param symbol the symbol to be placed on the board
   * @return Whether a move was successful or not.
   */
  public boolean play(int row, int col, String symbol) {
    if (isEmpty(row, col)) {
      board[row][col] = symbol;
      numEmpty--;
      detectWin();
      return true;
    } else {
      return false;
    }
  }

  /**
   * If the game is over,and has a winner, return true. Otherwise, return false
   * 
   * @return The boolean value of detectedWin.
   * @param None
   */
  public boolean hasWinner() {
    return detectedWin;
  }

  /**
   * This function returns the winning symbol.
   * It is only valid to call this method if hasWinner returns true.
   * 
   * @param none
   * @return The winningSymbol.
   */
  public String getWinningSymbol() {
    if (!this.hasWinner()){
  
    }
    return winningSymbol;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    TicTacToeBoard board = new TicTacToeBoard();
    board.printBoard();

    int row, col;
    boolean xTurn = true;
    while (!board.isBoardFull() && !board.hasWinner()) {
      System.out.print("Enter row & col to play: ");
      row = input.nextInt();
      col = input.nextInt();
      board.play(row, col, xTurn ? "X" : "O");
      board.printBoard();
      if (board.isBoardFull())
        System.out.println("Board is full.");
      if (board.hasWinner())
        System.out.println("Has winner: " + board.getWinningSymbol());
      xTurn = !xTurn;
    }
    System.out.println("Goodbye!");
    input.close();
  }
}