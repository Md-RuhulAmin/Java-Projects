import java.util.*;

public class PrtintABinaryTree extends BinarySortTree {

	public int idata;

	public PrtintABinaryTree left;
	public PrtintABinaryTree right;
	public PrtintABinaryTree parent;

	// variables needed to print the tree like a tree
	int depth=0;
	int level=0;
	int drawPos=0;

	/************ Actual functions that print the tree like a tree ********************/
	static void drawTree(PrtintABinaryTree root) 
	{

		System.out.println("\n\nLevel order traversal of tree:");
		int depth = depth(root);
		setLevels (root, 0);

		int depthChildCount[] = new int [depth+1];


		LinkedList<PrtintABinaryTree> q = new  LinkedList<PrtintABinaryTree> ();
		q.add(root.left);
		q.add(root.right);

		// draw root first
		root.drawPos = (int)Math.pow(2, depth-1)*H_SPREAD;
		currDrawLevel = root.level;
		currSpaceCount = root.drawPos;
		System.out.print(getSpace(root.drawPos) + root.idata);

		while (!q.isEmpty())
		{
			PrtintABinaryTree ele = q.pollFirst();
			drawElement (ele, depthChildCount, depth, q);
			if (ele == null)
				continue;
			q.add(ele.left);
			q.add(ele.right);
		}
		System.out.println();
	}

	static int currDrawLevel  = -1;
	static int currSpaceCount = -1;
	static final int H_SPREAD = 3; 
	
	static void drawElement(PrtintABinaryTree ele, int depthChildCount[], int depth, LinkedList<PrtintABinaryTree> q) 
	{
		if (ele == null)
			return;

		if (ele.level != currDrawLevel)
		{
			currDrawLevel = ele.level;
			currSpaceCount = 0;
			System.out.println();
			for (int i=0; i<(depth-ele.level+1); i++)
			{
				int drawn = 0;
				if (ele.parent.left != null)
				{
					drawn = ele.parent.drawPos - 2*i - 2;
					System.out.print(getSpace(drawn) + "/");
				}
				if (ele.parent.right != null)
				{
					int drawn2 = ele.parent.drawPos + 2*i + 2;
					System.out.print(getSpace(drawn2 - drawn) + "\\");
					drawn = drawn2;
				}

				PrtintABinaryTree doneParent = ele.parent;
				for (PrtintABinaryTree sibling: q)
				{
					if (sibling == null)
						continue;
					if (sibling.parent == doneParent)
						continue;
					doneParent = sibling.parent;
					if (sibling.parent.left != null)
					{
						int drawn2 = sibling.parent.drawPos - 2*i - 2;
						System.out.print(getSpace(drawn2-drawn-1) + "/");
						drawn = drawn2;
					}

					if (sibling.parent.right != null)
					{
						int drawn2 = sibling.parent.drawPos + 2*i + 2;
						System.out.print(getSpace(drawn2-drawn-1) + "\\");
						drawn = drawn2;
					}
				}
				System.out.println();
			}
		}
		int offset=0;
		int numDigits = (int)Math.ceil(Math.log10(ele.idata));
		if (ele.parent.left == ele)
		{
			ele.drawPos = ele.parent.drawPos - H_SPREAD*(depth-currDrawLevel+1);
			//offset = 2;
			offset += numDigits/2;
		}
		else
		{
			ele.drawPos = ele.parent.drawPos + H_SPREAD*(depth-currDrawLevel+1);
			//offset = -2;
			offset -= numDigits;
		}

		System.out.print (getSpace(ele.drawPos - currSpaceCount + offset) + ele.idata);
		currSpaceCount = ele.drawPos + numDigits/2;
	}


	// Utility functions
	public void inOrderInteger (String sep)
	{
		if (left != null)
			left.inOrderInteger(sep);
		System.out.print(idata + sep);
		if (right != null)
			right.inOrderInteger(sep);
	}

	static void setLevels (PrtintABinaryTree r, int level)
	{
		if (r == null)
			return;
		r.level = level;
		setLevels (r.left, level+1);
		setLevels (r.right, level+1);
	}

	static String getSpace (int i)
	{
		String s = "";
		while (i-- > 0)
			s += " ";
		return s;
	}

}