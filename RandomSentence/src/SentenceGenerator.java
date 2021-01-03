import java.util.Random;



public class SentenceGenerator {



	 static final String[] conjunction = {"and","or","but","because"};

	 static final String[] proper_nouns = {"Fred","Jane","Richard Nixon","Miss America"};

	 static final String[] common_nouns = {"man","woman","fish","elephant","unicorn"};

	 static final String[] determiner = {"a","the","every","some"};

	 static final String[] adjective = {"big","tiny","pretty","bad"};

	 static final String[] intransitive_verb = {"runs","jumps","talks","sleeps"};

	 static final String[] transitive_verb = {"loves","hates","sees","knows","looks for","finds"};



	 // Creates and prints random sentences	  

	 static void createSentence(boolean ss){

		 Random ms = new Random(); 

		 int c = (int)(Math.random()*conjunction.length);			System.out.print(simpleSentence()+" "+conjunction[c]+" ");

			 multiples = ms.nextBoolean(); 

		 } else {System.out.print(simpleSentence());}

	 }

	 static String simpleSentence(){

		 //calls on subroutines

		 return (nounPhrase()+" "+verbPhrase());

	 }

	  //random integers

		 int pn = (int)(Math.random()*proper_nouns.length);//random proper noun selector

		 int d = (int)(Math.random()*determiner.length);//random determiner selector

		 int a = (int)(Math.random()*adjective.length);//random adjective selector

		 int cn = (int)(Math.random()*common_nouns.length);//random common noun selector

		 

		 //random objects - choose options

		 Random porc = new Random(); 

		 boolean proper = porc.nextBoolean();

		 Random desc = new Random(); 

		 boolean descript = desc.nextBoolean

		 if (proper == true) {

			 return(proper_nouns[pn]);

		 } else if(descript == true){

			 return (determiner[d]+" "+adjective[a]+" "+common_nouns[cn]+" who "+verbPhrase());

		 }else {return (determiner[d]+" "+common_nouns[cn]);}

	 }

	 

	 static String verbPhrase(){

		 int type = (int)(Math.random()*4); //random number 

		 switch (type){

		 case 0: {

			 int iv = (int)(Math.random()*intransitive_verb.length);

			 return(intransitive_verb[iv]);

		 }

		 case 1: {

			 int tv = (int)(Math.random()*transitive_verb.length);

			 return (transitive_verb[tv]+" "+nounPhrase());

		 }

		 case 2: {

			 int a = (int)(Math.random()*adjective.length);//selects an adjective

			 return ("is "+adjective[a]);

		 }

		 default: {

			 return("believes that "+simpleSentence());

		 }

		 }

	 }

	 

	 public static void main(String[] args) {

		 int numbofsent = (int)(Integer.MAX_VALUE*Math.random());//number of sentences to print 

		 Random ss = new Random();//random boolean generator

		 		 for (int i=0;i<=numbofsent;i++){

			 boolean simples = ss.nextBoolean();

			 createSentence(simples);

			 System.out.println(".\n\n");

			 try {

				 Thread.sleep(3000);

			 }

			 catch (InterruptedException e) {

			 }

		 }

	 }

	

}

