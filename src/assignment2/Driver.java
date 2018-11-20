/*  * EE422C Project 2 (Mastermind) submission by
 * Timberlon Gray
 * tg22698
 * Slip days used: <0>
 * Spring 2017
 */

package assignment2;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String input = "Y";
		
		System.out.println("Welcome to Mastermind.");
		
		// full game loop
		while(input.equals("Y")){
			System.out.println("Do you want to play a new game? (Y/N):");
			input = keyboard.next();
			if(input.equals("Y")) {
				boolean cheatEnable;
				if(args.length != 0 && args[0].equals("1")) 
					cheatEnable = true;
				else
					cheatEnable = false;
				Game currentGame = Game.createGame(cheatEnable, keyboard);
				currentGame.runGame();
			}
		}
		keyboard.close();
	}
}
