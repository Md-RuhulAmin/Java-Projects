import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class ReadingDictonary {

	public static void main(String[] args) throws IOException {
		HashSet <String> wordsInput = new HashSet<>();
		Scanner filein = null, in = null;

		try {
			filein = new Scanner
					(new File("words.txt"));
			while (filein.hasNext()) {
				String tk = filein.next();
				tk.toLowerCase();
				wordsInput.add(tk);
			}
		} finally {
			filein.close();
		}

		System.out.println("The size of the set is  " 
				+ wordsInput.size());
		
		System.out.print("Give a word to check: ");
		try {
			in = new Scanner(System.in);
			String commonWord = in.nextLine();
			String lowerVersion = commonWord.toLowerCase();
			Boolean contains = wordsInput.contains(lowerVersion);
			if (contains) {
				System.out.println("The word \""+commonWord+ "\" is present in the set.");
			}else 
				System.out.println("The word \"" + commonWord+ "\" is not present in the set.");
			
		} finally {
			in.close();
		}
	}
}
