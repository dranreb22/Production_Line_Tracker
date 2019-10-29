import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TicTacToe game written in a structured style for debugging & review. Simple Text based tic-tac
 * toe game.
 * <p>
 * Used as unit review before OOP introduction. Algorithm & logic is not optimal to allow for code
 * review practice.
 */
public class asdf {

  /**
   * Player = Use to set a game "State" Currently could be String but will expand logic
   */
  public enum Player {CROSS, CIRCLE}

  /**
   * Main game logic: Gets player move and validates player input
   *
   * @param player This current Player (O or X - CROSS or CIRCLE) p      This is the current
   *               instance of the PlayerInteractions object
   * @return int array with the row & col for board (2d char array)
   */
  public static int[] getPlayerMove(Player player) {
    int[] move = new int[]{-1};

    while (move[0] == -1) {
      if (player == Player.CROSS) {
        System.out.print("Enter your move Player X (A-C 1-3, like 'A 3'): ");
      } else {
        System.out.print("Enter your move Player O (A-C 1-3, like 'A 3'): ");
      }
      //move = p.getPlayerMove();
    }
    return move;
  }

  /**
   * Builds board from Board class, calls for player move, updates winner and scoreboard
   *
   * @param args The arguements passed through command line call
   */
  public static void main(String[] args) {
//    Board board = new Board();
//    PlayerInteractions pi = new PlayerInteractions();

    System.out.println("X always goes first in this game, so X starts:");

    Player curPlayer = Player.CROSS;
    Boolean movesLeft = true;
    String winner = null;

    Map<String, Integer> scoreboard = Stream.of(new Object[][]{
        {"X", 0},
        {"O", 0},
        {"Tie", 0},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

//    while (true) {
//      while (movesLeft) {
//
//        if (curPlayer == Player.CROSS) {
//          if (board.drawMove(getPlayerMove(curPlayer, pi), 'X')) {
//            curPlayer = Player.CIRCLE;
//          }
//        } else {
//          if (board.drawMove(getPlayerMove(curPlayer, pi), 'O')) {
//            curPlayer = Player.CROSS;
//          }
//        }
//        winner = board.endGame();
//        movesLeft = (winner == "Continue") ? true : false;
//
//      }

    //board.clrScreen();

    scoreboard.put(winner, scoreboard.get(winner) + 1);
    System.out.printf("Current Scoreboard: X: %d, O: %d, Ties: %d %n",
        scoreboard.get("X"), scoreboard.get("O"), scoreboard.get("Tie"));
    System.out.printf("Nice Game, Winner is %s %n", winner);

//      if (!pi.checkQuit()) {
//        // If player does not want to quit - reset round variables
//        curPlayer = Player.CROSS;
//        movesLeft = true;
//        winner = null;
//        board.initBoard();
//      }

    //}
  }
}