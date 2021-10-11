package tests.prog.basics.task4;

import java.io.IOException;
import java.io.PrintStream;

import cursus.tests.ITest;
import cursus.tests.TestHarness;
import cursus.tests.TestUtils;
import oop.games.TicTacToe;

public class TicTacToeTests implements ITest {

  public static void main(String args[]) {
    ITest test = new TicTacToeTests();
    TestHarness.run(test);
  }

  public void run(PrintStream ps) {
    try {
      Test00();
      Test01();
      Test02();
    } catch (IOException ex) {
      safeEchoGame();
      TestUtils.failed(ex, "Unexpected IOException");
    } catch (Throwable th) {
      safeEchoGame();
      TestUtils.failed(th, "Unexpected exception");
    }
    TestUtils.passed();
  }

  static void safeEchoGame() {
    try {
      TicTacToe.echoGame();
    } catch (Throwable th) {
    }
  }

  static void fill(int row, int col) {
    if (TicTacToe.gameOver()) {
      safeEchoGame();
      System.out.println("The game should not be over");
      TestUtils.failed();
    }
    if (!TicTacToe.available(row, col)) {
      safeEchoGame();
      System.out.println("The cell (" + row + "," + col + ") should be available");
      System.out.println("so either your board is wrong");
      System.out.println("or your available method is wrong");
      TestUtils.failed();
    }
    TicTacToe.play(row, col);
    if (TicTacToe.winner() != ' ') {
      safeEchoGame();
      System.out.println("There should be no winner");
      System.out.println("so either your board is wrong");
      System.out.println("or your check for a winner is wrong");
      TestUtils.failed();
    }
  }

  /*
  -----
  |OXX|
  |XXO|
  |OOX|
  -----
   */
  static void Test00() throws IOException {
    System.out.println("--------------------------------------"); 
    System.out.println("Test00 goal:");
    System.out.println("  -----\n" + 
        "  |OXX|\n" + 
        "  |XXO|\n" + 
        "  |OOX|\n" + 
        "  -----\n" + 
        "");
    TicTacToe.initGame('X');
    fill(1, 1);
    fill(1, 2);
    fill(0, 2);
    fill(2, 0);
    fill(2, 2);
    fill(0, 0);
    fill(0, 1);
    fill(2, 1);
    TicTacToe.play(1, 0);

    if (!TicTacToe.gameOver()) {
      safeEchoGame();
      TestUtils.failed("The game should be over.");
    }
    if (TicTacToe.winner() != ' ') {
      safeEchoGame();
      TestUtils.failed("It should be a tie");
    }
  }

  /*
-----
|OOX|
|XXX|
|O  |
-----
   */
  static void Test01() throws IOException {
    System.out.println("--------------------------------------"); 
    System.out.println("Test01 goal:");
    System.out.println("  -----\n" +
        "|OOX|\n" + 
        "|XXX|\n" + 
        "|O  |\n" + 
        "-----\n" + 
        "");
    TicTacToe.initGame('X');
    fill(1, 1);
    fill(0, 0);
    fill(0, 2);
    fill(2, 0);    
    fill(1, 0);    
    fill(0, 1);
    TicTacToe.play(1, 2);
    
    if (!TicTacToe.gameOver()) {
      safeEchoGame();
      TestUtils.failed("The game should be over.");
    }
    if (TicTacToe.winner() != 'X') {
      safeEchoGame();
      TestUtils.failed("Winner should be X");
    }
  }

  /*
-----
|OOX|
|XXX|
|O  |
-----
   */
  static void Test02() throws IOException {
    System.out.println("--------------------------------------"); 
    System.out.println("Test02 goal:");
    System.out.println("-----\n" + 
        "|OOX|\n" + 
        "|XXX|\n" + 
        "|O  |\n" + 
        "-----\n" + 
        "");
    TicTacToe.initGame('X');
    fill(1, 1);
    fill(0, 0);
    fill(0, 2);
    fill(2, 0);    
    fill(1, 0);    
    fill(1, 2);
    fill(2, 1);
    fill(2, 2);
    TicTacToe.play(0, 1);
    
    if (!TicTacToe.gameOver()) {
      safeEchoGame();
      TestUtils.failed("The game should be over.");
    }
    if (TicTacToe.winner() != 'X') {
      safeEchoGame();
      TestUtils.failed("Winner should be X");
    }
  }

}
