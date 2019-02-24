package pl.jacekgalka;

public class Hangman {

	public int getLineNumberFor(String lastName) {
		int lineNumber = 0;
    /*
      lineNumber should be set based on the first character of the person's last name
      Line 1 - A thru M
      Line 2 - N thru Z

     */
		if (lastName.charAt(0) >= 'A' && lastName.charAt(0) <= 'M') {
			lineNumber = 1;
		} else {
			lineNumber = 2;
		}
		return lineNumber;
	}

    public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Hangman <answer>");
			System.out.println("answer is required");
			System.exit(1);
		}
		String answer = args[0];
		Game game = new Game(answer);
		Prompter prompter = new Prompter(game);
		do {
			prompter.displayProgress();
			prompter.displayRemainingTries();
			prompter.promptForGuess();
		} while (game.getRemainingTries() > 0 && !game.isWon());
		prompter.displayResults();
    }
}
