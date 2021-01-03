import java.io.File;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user.  The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 */
public class DirectoryList {


	public static void main(String[] args) {

		String directoryName;  // Directory name entered by the user.
		File directory;        // File object referring to the directory.
		Scanner scanner;       // For reading a line of input from the user.

		scanner = new Scanner(System.in);  // scanner reads from standard input.

		System.out.print("Enter a directory name: ");
		directoryName = scanner.nextLine().trim();
		directory = new File(directoryName);

		if (directory.isDirectory() == false) {
			if (directory.exists() == false)
				System.out.println("There is no such directory!");
			else
				System.out.println("That file is not a directory.");
		}
		else {
			File (directory, "");
		}
		scanner.close();
	} // end main()

	/***
	 * This subroutine will take any directory and returns all the files 
	 * and the directories are inside of that directory.
	 * This is a recursive subroutine that is recursively looking inside other
	 * directories or file are available.
	 * @param dir This is a File parameter.
	 * @param fileName this parameter is to give a line indentations.
	 */
	public static void File( File dir, String fileName ) {
		String[] files;  // Assigning A list variable for list of the file
		System.out.println(fileName + "Files in Directory \"" + dir.getName() + "\":");
		fileName += "   ";
		files = dir.list();
		int fileAmmount = 0;
		while (fileAmmount < files.length) {
			File search = new File(dir, files[fileAmmount]);
			if (search.isDirectory())
				File(search, fileName); // Recursive call for the other directories inside.
			else
				System.out.println(fileName + files[fileAmmount]);
			fileAmmount ++;
		}
	}


} // end class DirectoryList
