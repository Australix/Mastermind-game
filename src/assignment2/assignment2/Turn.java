/*  * EE422C Project 2 (Mastermind) submission by
 * Timberlon Gray
 * tg22698
 * Slip days used: <0>
 * Spring 2017
 */

package assignment2;
import java.util.Scanner;

public class Turn extends GameConfiguration {
	String guess; // stores the guess for the turn
	
	/**
	 * Gets a keyboard input as guess, verifies it, and either completes turn
	 * or returns to the Game to get the history.
	 * @param code - correct code
	 * @param keyboard - Scanner used to get input from keyboard
	 * @param guessesLeft - how many turns left in the game
	 * @return 0 = turn completed, 1 = history was requested
	 */
	public int completeTurn(String code, Scanner keyboard, int guessesLeft) {
		// loop to get a correct input or HISTORY
		do {
			System.out.println("");
			System.out.println("You have " + guessesLeft + " guess(es) left.");
			System.out.println("Enter guess:");
			guess = this.getInput(keyboard);
		} while (!verifyInput(guess));
		
		// HISTORY is handled by Game (needs info from other turns)
		if(guess.equals("HISTORY")) return 1;
		this.outputResult(code);
		return 0;
	}
	
	/**
	 * Calculates the black/white pegs and outputs to the display.
	 * @param code - correct code
	 */
	public void outputResult(String code) {
		int black = 0;
		int white = 0;
		// refers to which of the CODE colors have been attributed to an answer color
		boolean[] codeUsed = new boolean[pegNumber];
		// refers to which of the GUESS colors have been attributed to a code color
		boolean[] used = new boolean[pegNumber];
		
		// black peg checker
		for(int i = 0; i<pegNumber; i++) {
			if(code.charAt(i) == guess.charAt(i)) {
				black++;
				codeUsed[i] = true;
				used[i] = true;
			}
		}
		// white peg checker
		for(int j = 0; j<pegNumber; j++) { // j = index for CODE string
			for(int k = 0; !codeUsed[j] && k<pegNumber; k++) { // k = index for GUESS string
				if(!used[k] && (code.charAt(j) == guess.charAt(k))) {
					codeUsed[j] = true;
					used[k] = true;
					white++;
					break;
				}
			}
		}
		System.out.println(guess + " -> " + black + "b_" + white + "w");
	}
	
	/**
	 * Returns the code guessed on this turn.
	 * @return - the guess
	 */
	public String getGuess() {
		return this.guess;
	}
	
	/**
	 * Returns the user input on keyboard.
	 * @param keyboard - Scanner connected to keyboard
	 * @return - guess input by user
	 */
	private String getInput(Scanner keyboard) {	
		return  keyboard.next();
	}
	
	/**
	 * Checks to make sure the guess input is valid (either HISTORY or
	 * a code with correct length and valid colors)
	 * @param input - guess input by user
	 * @return - whether or nor the input is valid
	 */
	private boolean verifyInput(String input) {
		boolean ret = true;
		
		// other cases - "HISTORY" and wrong length of guess
		if(input.equals("HISTORY")) return true;
		if(input.length() != pegNumber) {
			System.out.println("INVALID_GUESS");
			return false;
		}
		
		// i = index of input string (whatever the user submits as a guess)
		for(int i = 0; i<pegNumber; i++) {
			boolean isAColor = false;
			// j = index of colors array (from GameConfiguration)
			for(int j = 0; j<colors.length; j++) {
				if (input.charAt(i) == colors[j].charAt(0)) isAColor = true;
			}
			if(isAColor == false) ret = false;
		}
		
		if(ret == false) System.out.println("INVALID_GUESS");
		return ret;
	}
}
