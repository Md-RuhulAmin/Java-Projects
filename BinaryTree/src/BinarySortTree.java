

/***
 * This is a non-grade exercise which should be posted to your learning journal.
 * 
 * In Subsection 9.4.2, the textbook says that "if the [binary sort] tree is created 
 * by inserting items in a random order, there is a high probability that the tree is 
 * approximately balanced." For this exercise, you will do an experiment to test 
 * whether that is true.
 * 
 * In the Introduction to this Learning Guide, you read that the depth of a node in 
 * a binary tree is the length of the path from the root of the tree to that node. 
 * That is, the root has depth 0, its children have depth 1, its grandchildren have 
 * depth 2, and so on. In a balanced tree, all the leaves in the tree are about the 
 * same depth. For example, in a perfectly balanced tree with 1023 nodes, all the leaves 
 * are at depth 9. In an approximately balanced tree with 1023 nodes, the average 
 * depth of all the leaves should be not too much bigger than 9.
 * 
 * On the other hand, even if the tree is approximately balanced, there might be a few 
 * leaves that have much larger depth than the average, so we might also want to look 
 * at the maximum depth among all the leaves in a tree.
 *  
 * For this exercise, you should create a random binary sort tree with 1023 nodes. The 
 * items in the tree can be real numbers, and you can create the tree by generating 
 * 1023 random real numbers and inserting them into the tree, using the usual treeInsert() 
 * method for binary sort trees. Once you have the tree, you should compute and output 
 * the average depth of all the leaves in the tree and the maximum depth of all the 
 * leaves. To do this, you will need three recursive subroutines: one to count the leaves, 
 * one to find the sum of the depths of all the leaves, and one to find the maximum 
 * depth. The latter two subroutines should have an int-valued parameter, depth, that 
 * tells how deep in the tree you've gone. When you call this routine from the main 
 * program, the depth parameter is 0; when you call the routine recursively, the 
 * parameter increases by 1.
 *  
 *  Build your solution on code and principles from section 9.4.2.
 *  
 */
public class BinarySortTree {
	/**
	 * An object of type TreeNode represents one node in a binary tree of real number.
	 */
	private static class TreeNode {
		double item;      // The data in this node.
		TreeNode left;    // Pointer to left subtree.
		TreeNode right;   // Pointer to right subtree.
		TreeNode(double nbr) {
			// Constructor.  Make a node containing the specified node of real number.
			// Note that left and right pointers are initially null.
			item = nbr;
		}
	}  // end nested class TreeNode

	private static TreeNode root;

	public static void main(String[] args) {
		root = null;  // Start with an empty tree.  Root is a global
		// variable, defined at the top of the class.

		// Calling the reandonBSTree() method to create a random 
		// tree of random 1023 items.
		TreeNode BinaryTree = randomBSTree(1023);

		// creating all the statistics of this binary main method
		int leafCount = countNodes(BinaryTree);
		int depthSum = sumOfLeavesDepths(BinaryTree,0);
		int depthMax = maximumLeafDepth(BinaryTree,0);
		double averageDepth = ((double)depthSum) / leafCount;
		

		// Display the results.

		System.out.println("Number of leaves:         " + leafCount);
		System.out.println("Average depth of leaves:  " + averageDepth);
		System.out.println("Maximum depth of leaves:  " + depthMax);


	}//End of main method

	/**
	 * Add the item to the binary sort tree to which the global variable 
	 * "root" refers.  (Note that root can't be passed as a parameter to 
	 * this routine because the value of root might change, and a change 
	 * in the value of a formal parameter does not change the actual parameter.)
	 */
	private static void treeInsert(double newItem) {
		if ( root == null ) {
			// The tree is empty.  Set root to point to a new node containing
			// the new item.  This becomes the only node in the tree.
			root = new TreeNode( newItem );
			return;
		}
		TreeNode runner;  // Runs down the tree to find a place for newItem.
		runner = root;   // Start at the root.
		while (true) {
			if ( newItem < runner.item  ) {
				// Since the new item is less than the item in runner,
				// it belongs in the left subtree of runner.  If there
				// is an open space at runner.left, add a new node there.
				// Otherwise, advance runner down one level to the left.
				if ( runner.left == null ) {
					runner.left = new TreeNode( newItem );
					return;  // New item has been added to the tree.
				}
				else
					runner = runner.left;
			}
			else {
				// Since the new item is greater than or equal to the item in
				// runner it belongs in the right subtree of runner.  If there
				// is an open space at runner.right, add a new node there.
				// Otherwise, advance runner down one level to the right.
				if ( runner.right == null ) {
					runner.right = new TreeNode( newItem );
					return;  // New item has been added to the tree.
				}
				else
					runner = runner.right;
			}
		} // end while
	}  // end treeInsert()

	/***
	 * To create a random binary sort tree with a specific nodes number
	 * creating a method called reandomBinarySortTree, in short randomBSTree.
	 */
	private static TreeNode randomBSTree (int nodes) {
		for (int i = 0; i < nodes; i++)
			//Calling the treeInsert() method to crate a Binary tree of
			//the specific node of real number.
			treeInsert(Math.random());
		return root;
	}//end of the randonBSTree() method.

	
	/***
	 * Return the number of leaves in the tree to which node points.
	 */
	private static int countNodes(TreeNode node) {
		if ( node == null )
			// Tree is empty, so it contains no nodes.
			return 0;
		else if (node.left == null && node.right == null)
			// left node is empty and right node is also empty
			// So the tree contains only 1 root node.
			return 1;
		else
			// 
			return countNodes(node.right) + countNodes (node.left);
	} // end countNodes()


	/***
	 * When called as sumOfLeavesDepths(root,0), this will compute the
	 * sum of the depths of all the leaves in the tree to which root
	 * points.  When called recursively, the depth parameter gives
	 * the depth of the node, and the routine returns the sum of the
	 * depths of the leaves in the subtree to which node points.
	 * In each recursive call to this routine, depth goes up by one.
	 */   
	private static int sumOfLeavesDepths( TreeNode node, int depth ) {
		if ( !(node != null) ) {
			// In an empty tree, leaves sum is zero.
			return 0;
		}
		else if ( node.left == null && node.right == null) {
			// The node is a leaf, and there are no subtrees of node, so
			// the sum of the leaf depth is just the depths of this node.
			return depth;
		}
		else {
			// The node is not a leaf.  Return the sum of the
			// the depths of the leaves in the subtrees.
			return sumOfLeavesDepths(node.left, depth + 1) 
					+ sumOfLeavesDepths(node.right, depth + 1);
		}
	} // end sumOfLeavesDepths()


	/**
	 * When called as maximumLeafDepth(root,0), this will compute the
	 * max of the depths of all the leaves in the tree to which root
	 * points.  When called recursively, the depth parameter gives
	 * the depth of the node, and the routine returns the max of the
	 * depths of the leaves in the subtree to which node points.
	 * In each recursive call to this routine, depth goes up by one.
	 */
	private static int maximumLeafDepth( TreeNode node, int depth ) {
		if ( node == null ) {
			// The tree is empty.  Return 0.
			return 0;
		}
		else if ( node.left == null && node.right == null) {
			// The node is a leaf, so the maximum depth in this
			// subtree is the depth of this node (the only leaf 
			// that it contains).
			return depth;
		}
		else {
			// Get the maximum depths for the two subtrees of this
			// node.  Return the larger of the two values, which
			// represents the maximum in the tree overall.
			int leftD = maximumLeafDepth(node.left, depth + 1);
			int rightD =  maximumLeafDepth(node.right, depth + 1);
			if (leftD > rightD)
				return leftD;
			else
				return rightD;
		}
	} // end maximumLeafDepth()
}// end of the BinarySortTree class.
