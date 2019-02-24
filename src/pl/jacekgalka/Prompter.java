package pl.jacekgalka;
import java.util.Scanner;

public class Prompter {
	private Game game;

	public Prompter(Game game) {
		this.game = game;
	}

	public boolean promptForGuess() {
		Scanner scanner = new Scanner(System.in);
		boolean isHit = false;
		boolean isAcceptable = false;

		do {
			System.out.print("Enter a letter: ");
			String guessInput = scanner.nextLine();

			try {
				isHit = game.applyGuess(guessInput);
				isAcceptable = true;
			} catch (IllegalArgumentException iae) {
				System.out.printf("%s. Please try again. %n", iae.getMessage());
			}
		} while (! isAcceptable);
		return isHit;
	}

	public void displayProgress() {
		System.out.printf("Try to solve: %s\n", game.getCurrentProgress());
	}

	public void displayRemainingTries() {
		System.out.printf("Remaining tries: %d\n\n", game.getRemainingTries());
	}

	public void displayResults() {
		if (game.isWon()) {
			System.out.printf("\nCONGRATULATIONS! You won with %d tries remaining.", game.getRemainingTries());
		} else {
			System.out.println("\nBummer! You loose! :(");
		}
		System.out.printf("\nThe word was %s'\n", game.getAnswer());

	}
}
