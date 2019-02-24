package pl.jacekgalka;

public class Game {
	public static final int MAX_MISSES = 7;
	private final String BLANK_CHARACTER = "-";
	private String answer;
	private String hits;
	private String misses;

	private boolean isInHits(char letter) {
		return hits.indexOf(letter) != -1;
	}

	private boolean isInMisses(char letter) {
		return misses.indexOf(letter) != -1;
	}

	private char normalizeGuess(char letter) {
		if (! Character.isLetter(letter)) {
			throw new IllegalArgumentException("A letter is required");
		}
		letter = Character.toLowerCase(letter);
		if (isInHits(letter) || isInMisses(letter)) {
			throw new IllegalArgumentException("You already tried " + letter);
		}
		return letter;
	}

	public Game(String answer) {
		this.answer = answer.toLowerCase();
		hits = "";
		misses = "";
	}

	public boolean applyGuess(char letter) {
		letter = normalizeGuess(letter);
		boolean isHit = answer.indexOf(letter) != -1;
		if (isHit) {
				hits += letter;
		} else {
			misses += letter;
		}
		return isHit;
	}

	public boolean applyGuess(String letters) {
		if (letters.length() == 0) {
			throw new IllegalArgumentException("No letter found");
		}
		return applyGuess(letters.charAt(0));
	}

	public String getCurrentProgress() {
		String progress = "";
		for (char letter : answer.toCharArray()) {
			String toAppend = BLANK_CHARACTER;
			if (hits.indexOf(letter) != -1) {
				toAppend = Character.toString(letter);
			}
			progress += toAppend;
		}
		return progress;
	}

	public boolean isWon() {
		return !getCurrentProgress().contains(BLANK_CHARACTER);
	}

	public int getRemainingTries() {
		return MAX_MISSES - misses.length();
	}

	public String getAnswer() {
		return answer;
	}
}
