/***
	<sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]

	<simple_sentence> ::= <noun_phrase> <verb_phrase>

	<noun_phrase> ::= <proper_noun> |
	<determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]

	<verb_phrase> ::= <intransitive_verb> |
	<transitive_verb> <noun_phrase> |
	is <adjective> |
	believes that <simple_sentence>

	<conjunction> ::= and | or | but | because

	<proper_noun> ::= Fred | Jane | Richard Nixon | Miss America

	<common_noun> ::= man | woman | fish | elephant | unicorn

	<determiner> ::= a | the | every | some

	<adjective> ::= big | tiny | pretty | bald

	<intransitive_verb> ::= runs | jumps | talks | sleeps

	<transitive_verb> ::= loves | hates | sees | knows | looks for | finds

	As in SimpleRandomSentences.java, you can use arrays to implement the last seven rules 
	in this list. (You might improve on that program by writing a single method 
	"void String randomItem(String[] listOfStrings)" for picking a random item from an array 
	of strings.) You are welcome to add to or modify the items in the lists given here.

	For each of the first three rules, you should write a subroutine to represent that rule. 
	Note that a choice of alternatives (represented in the rules by "|") can be implemented 
	using a switch or if..else statement; the various choices don't necessarily have to have 
	the same probability. An optional element (represented by brackets, "[ xxx ]") can be 
	implemented by a simple if. And a repeated optional element (represented by brackets with 
	dots, "[ xxx ]...") can be represented by a while loop. You should implement the first four 
	rules exactly as stated here. The main routine should call the <sentence> subroutine to 
	generate random sentences.
 */

public class RandomSentence {
	
	//The conjunction string array
	static final private String[] conjunction = {"and", "or", "but", "because"};
	
	//The propper_noun String array
	static final private String[] propperNoun = {"Fred", "Jane", "Richard Nixon", 
			"Miss America"};

	//The common noun string array
	static final private String[] commonNoun = {"man", "woman", "fish", "elephant", 
			"unicorn"};

	//This array is for determiners
	static final private String[] determiner = {"a", "the", "every", "some"};

	//This array is created for the adjectives
	static final private String[] adjective = {"big", "tiny", "pretty", "bald"};

	//The array of intransitive verbs
	static final private String[] intransitiveVerb = {"runs", "jumps", 
			"talks", "sleeps"};

	//The array of transitive verbs
	static final private String[] transitiveVerb = {"loves", "hates", "sees", 
			"knows", "looks for", "finds"};


	//The main method that is calling sentence method
	public static void main(String[] args) {
		
		//calling the sentence method inside of a for loop because 
		//I want to print 30 sentence in one run of the program.
		for(int i = 1; i <= 30; i++) {
			System.out.print("(" + i +") ");
			sentence(); //Calling the sentence method
			System.out.print(".\n\n");
		}
	}//End of main

	//Defining the sentence method from the first role
	private static void sentence () {
		
		simpleSentence();
		
		if (Math.random() > 0.2) { //The provability of execution is  20%
			System.out.print(" ");
			randomItem(conjunction);
			System.out.print(" ");
			sentence(); 
		}
	}//End of sentence method

	//Creating the simple sentence method depending on the second role
	private static void simpleSentence() {
		nounPhrase();
		verbPhrase();
	}//End of simpleSentence method

	/*
	 * <verb_phrase> ::= <intransitive_verb> |
	 * <transitive_verb> <noun_phrase> |
	 * is <adjective> |
	 * believes that <simple_sentence>
	 */
	//depending on the fourth role creating this verb phrase method
	private static void verbPhrase() {
		
		int caseTravelNo = (int)(Math.random()*4+1);
		switch (caseTravelNo) {
		
			case 1:randomItem(intransitiveVerb);
					break;
				
			case 2:randomItem(transitiveVerb);
					System.out.print(" ");
					nounPhrase();
					break;
			
			case 3:System.out.print("is ");
					randomItem(adjective);
					break;
					
			case 4:System.out.print("believes that ");
					simpleSentence();
					break;
		}
	}//End of verb phrase

	//<noun_phrase> ::= <proper_noun> |
	//<determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]
	//this is the noun phrase method
	private static void nounPhrase() {
		int caseTravelNo = (int)(Math.random()*2+1);

		switch (caseTravelNo) {
		
			case 1:randomItem(propperNoun);
					System.out.print(" ");
					break;
				
			case 2:randomItem(determiner);
					System.out.print(" ");
					
					while(Math.random() > 0.5) {
						randomItem(adjective);
						System.out.print(" ");
						break;
					}
					randomItem(commonNoun);
					System.out.print(" ");
					
					//This condition has 50% chance to execute
					if (Math.random() > 0.5) {
						System.out.print("who ");
						verbPhrase();
						System.out.print(" ");
						}
					break;
		}
	}//End of noun phrase

	//Defining this method select an item from any list
	//The selection is going to done as random
	static void randomItem(String[] listOfStrings) {

		int item = (int) (Math.random() * listOfStrings.length);
		System.out.print(listOfStrings[item]);
	}
}
