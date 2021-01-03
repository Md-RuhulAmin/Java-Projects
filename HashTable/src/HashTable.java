public class HashTable {

	/**
	 * Keys that have the same hash code are placed together in a linked list. 
	 * A ListNode holds a (key,value) pair.  
	 */
	private static class ListNode {
		String key;
		String value;
		ListNode next;
	} //end of the ListNode class

	// Assigning a variable of the class ListNode
	private ListNode[] table; 

	private int count; // To get the size of the array
	
	/**
	 * Create a hash table with a specified initial size.
	 * Precondition: initalSize > 0.
	 */
	public HashTable(int initialSize) {
		table = new ListNode[initialSize];
	}


	/**
	 * Retrieve the value associated with the specified key in the table, 
	 * if there is any.  If not, the value null will be returned.
	 * @param key The key whose associated value we want to find
	 * @return the associated value, or null if there is no associated value
	 */
	public String get(String key) {
		int bucket = hash(key);
		ListNode list = table[bucket];

		while (list != null) {
			if (list.key.equals(key))
				return list.value;
			list = list.next; 
		}
		return null;  
	} // end of the get(key) method



	/**
	 * Associate the specified value with the specified key.
	 * Precondition:  The key is not null.
	 */
	public void put(String key, String value) {
		int bucket = hash(key); 
		ListNode list = table[bucket]; 
		while (list != null) {
			if (list.key.equals(key))
				break;
			list = list.next;
		}

		if (list != null) {
			list.value = value;
		}
		else {
			if (count >= 0.75*table.length) {
				resize();
			}
			ListNode newNode = new ListNode();
			newNode.key = key;
			newNode.value = value;
			newNode.next = table[bucket];
			table[bucket] = newNode;
			count++; 
		}
	} // end of the put(key, value) method

	/**
	 * Remove the key and its associated value from the table,
	 * if the key occurs in the table.  If it does not occur,
	 * then nothing is done.
	 */
	public void remove(String key) {  
		int bucket = hash(key);

		if (table[bucket] == null) {
			return; 
		}
		if (table[bucket].key.equals(key)) {
			table[bucket] = table[bucket].next;
			count--; 
			return;
		}

		ListNode prev = table[bucket];  
		ListNode curr = prev.next;  
		while (curr != null && ! curr.key.equals(key)) {
			curr = curr.next;
			prev = curr;
		}
		if (curr != null) {
			prev.next = curr.next;
			count--;  
		}
	} // end of the remove(key) method


	/**
	 * Test whether the specified key has an associated value in the table.
	 * @param key The key that we want to search for.
	 * @return true if the key exists in the table, false if not
	 */
	public boolean containsKey(String key) {

		int bucket = hash(key);  

		ListNode list = table[bucket];
		while (list != null) {
			if (list.key.equals(key))
				return true;
			list = list.next;
		}
		return false;
	} // end of the containsKey (key) method


	/**
	 * Return the number of key/value pairs in the table.
	 */
	public int size() {
		return count;
	} // end of the size() method


	/**
	 * Compute a hash code for the key; key cannot be null.
	 * The hash code depends on the size of the table as
	 * well as on the value returned by key.hashCode().
	 */
	private int hash(Object key) {
		return (Math.abs(key.hashCode())) % table.length;
	} // end of the hash(key) method


	/**
	 * Double the size of the table, and redistribute the
	 * key/value pairs to their proper locations in the
	 * new table.
	 */
	private void resize() {
		ListNode[] newtable = new ListNode[table.length*2];
		for (int i = 0; i < table.length; i++) {
			ListNode list = table[i];
			while (list != null) {
				ListNode next = list.next;
				int hash = (Math.abs(list.key.hashCode())) % newtable.length;
				list.next = newtable[hash];
				newtable[hash] = list;
				list = next; 
			}
		}
		table = newtable;
	} // end resize()


} // end class HashTable