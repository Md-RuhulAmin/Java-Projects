import java.util.Scanner;

public class TestingHashTable {

	public static void main(String[] args){
		HashTable hashTable = new HashTable(4);  // Initial size of table is 2.

		String key;
		String value;
		boolean reply = true;

		System.out.println("\nHash table size is " + hashTable.size());
		System.out.println("\nTesting the put(key,value) method: ");
		while (reply) {
			System.out.print("   Key = ");
			key = getString();
			System.out.print("\n  value = ");
			value = getString();
			hashTable.put(key, value);
			System.out.print("Want to add more key and values?\n"
					+ "Type (true or false):  ");
			reply = getBoolean();
		}
		System.out.println("\nNow the Hash table size is " 
				+ hashTable.size());

		System.out.println("-----------------------------------------");

		System.out.println("\nTesting get(key) method: ");
		System.out.print("   Key = ");
		key = getString();
		System.out.println("   Value is " + hashTable.get(key));

		System.out.println("-----------------------------------------");

		System.out.println("\nTesting containsKey(key)method: ");
		System.out.print("   Key = ");
		key = getString();
		System.out.println("   containsKey("+  key + ") is "
				+ hashTable.containsKey(key));

		System.out.println("-----------------------------------------");

		System.out.println("Testing remove(key) method: ");
		System.out.print("   Key = ");
		key = getString();
		hashTable.remove(key);
		System.out.println("\nNow the Hash table size is " 
				+ hashTable.size());
	}

	@SuppressWarnings("resource")
	private static String getString() {
		Scanner in = new Scanner (System.in);
		return in.nextLine();
	}

	private static boolean getBoolean() {
		String input = getString();
		if (input.equals("T") || input.equals("TRUE") || input.equals("t")
				|| input.equals("Y") || input.equals("YES") || input.equals("1")
				|| input.equals("yes") || input.equals("true")|| input.equals("y")
				) return true;
		return false;
	}
}
