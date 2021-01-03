/**
 * For this week's lab, you will use two of the classes 
 * in the Java Collection Framework: HashSet and TreeSet. 
 * You will use these classes to implement a spell checker.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import javax.swing.JFileChooser;

public class SpellChecker {


	public static void main(String[] args) {

		HashSet <String> wordsInput = new HashSet<String>();
		Scanner filein, in;

		try {

			filein = new Scanner
					(new File("words.txt"));
			while (filein.hasNext()) {
				String tk = filein.next();
				tk.toLowerCase();
				wordsInput.add(tk);
			}


			in = new Scanner 
					(getInputFileNameFromUser());

			in.useDelimiter("[^a-zA-Z]+");

			HashSet <String> badWords = new HashSet<String>();

			while (in.hasNextLine()) {
				String token = in.nextLine();
				String caselower = token.toLowerCase();
				if (!wordsInput.contains(caselower) && 
						!badWords.contains(caselower)) {

					badWords.add(caselower);

					TreeSet<String> goodWords = new TreeSet<String>();

					goodWords = corrections(caselower, wordsInput);

					System.out.print(caselower + ": ");
					if (goodWords.isEmpty())
						System.out.println("(no suggestions)");
					else {
						int count = 0;
						for (String goodWord: goodWords) {
							System.out.print(goodWord);
							if (count < goodWords.size() - 1)
								System.out.print(", ");
							else
								System.out.print("\n");
							count++;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lets the user select an input file using a standard file
	 * selection dialog box.  If the user cancels the dialog
	 * without selecting a file, the return value is null.
	 */
	static File getInputFileNameFromUser() {
		JFileChooser fileDialog = new JFileChooser();
		fileDialog.setDialogTitle("Select File for Input");
		int option = fileDialog.showOpenDialog(null);
		if (option != JFileChooser.APPROVE_OPTION)
			return null;
		else
			return fileDialog.getSelectedFile();
	}

	/**
	 * Gives a list of possible correct spellings for misspelled words which
	 * are variations of a a given word that are present in the dictionary.
	 * @param A string parameter and hashSet parameter
	 * @return A tree set containing a list of possible corrections to the
	 * misspelled word.
	 */
	static TreeSet<String> corrections(String word, HashSet<String> dictionary) {

		TreeSet<String> possibleWords =  new TreeSet<String>();

		String subStr1, subStr2, possibility;

		for (int i = 0; i < word.length(); i++) {

			// Remove character i from the word.
			subStr1 = word.substring(0, i);
			subStr2 = word.substring(i + 1);

			// Delete any one of the letters from the misspelled word.
			possibility = subStr1 + subStr2;
			if (dictionary.contains(possibility))
				possibleWords.add(possibility);

			// Change any letter in the misspelled word into any other
			// letter.    
			for (char ch = 'a'; ch <= 'z'; ch++) {
				possibility = subStr1 + ch + subStr2;
				if (dictionary.contains(possibility))
					possibleWords.add(possibility);
			}

			// Divide the word into two substrings.
			subStr1 = word.substring(0, i);
			subStr2 = word.substring(i);

			// Insert any letter at any point in the misspelled word.
			for (char ch = 'a'; ch <= 'z'; ch++) {
				possibility = subStr1 + ch + subStr2;
				if (dictionary.contains(possibility))
					possibleWords.add(possibility);
			}

			// Insert a space at any point in the misspelled word and check
			// that both of the words that are produced are in the dictionary.
			char ch = ' ';
			possibility = subStr1 + ch + subStr2;
			if (dictionary.contains(subStr1) && dictionary.contains(subStr2))
				possibleWords.add(possibility);

		}

		// Swap any two neighbouring characters in the misspelled word.
		for (int i = 1; i < word.length(); i++) {
			subStr1 = word.substring(0, i - 1);
			char ch1 = word.charAt(i - 1);
			char ch2 = word.charAt(i);
			subStr2 = word.substring(i + 1);
			possibility = subStr1 + ch2 + ch1 + subStr2;
			if (dictionary.contains(possibility))
				possibleWords.add(possibility);
		}

		return possibleWords;
	}
}
