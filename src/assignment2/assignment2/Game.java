/*  * EE422C Project 2 (Mastermind) submission by
 * Timberlon Gray
 * tg22698
 * Slip days used: <0>
 * Spring 2017
 */

package assignment2;
import java.util.Scanner;

public class Game extends GameConfiguration {
	Turn[] history;
	String code;
	boolean cheatmode;
	Scanner keyboard;
	int turnCount = 0;
	
	// constructor
	Game(boolean cheat, Scanner keyboard) {
		history = new Turn[guessNumber];
		code = SecretCodeGenerator.getInstance().getNewSecretCode();
		cheatmode = cheat;
		this.keyboard = keyboard;
	}
	
	/**
	 * Calls Game constructor.
	 * @param cheat - whether or not the correct code should be shown at beginning of game
	 * @param keyboard - keyboard input (must be passed)
	 * @return - the Game object
	 */
	public static Game createGame(boolean cheat, Scanner keyboard) {
		Game currentGame = new Game(cheat, keyboard);
		return currentGame;
	}
	
	/**
	 * Plays a full game.
	 */
	public void runGame() {
		// secret code output
		if(cheatmode) System.out.println("Secret code: " + code);
		
		// the main turn loop
		boolean win = false;
		while(turnCount < guessNumber && win == false) {
			history[turnCount] = new Turn();
			int requestHistory = history[turnCount].completeTurn(code, keyboard, guessNumber - turnCount);
			win = code.equals(history[turnCount].getGuess());
			if(requestHistory == 1) gameHistory();
			else turnCount++;
		}
		
		// game end outputs
		if(win) System.out.println("You win!");
		else System.out.println("You lose! The pattern was " + code);
		System.out.println("");
	}
	
	/**
	 * Outputs the history of guesses made to the display. 
	 */
	public void gameHistory() {
		for(int i = 0; i < turnCount; i++) {
			history[i].outputResult(code);
		}
	}
}
