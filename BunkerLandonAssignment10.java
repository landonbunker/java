/* LandonBunker, 
CS 1450,
section T and TR, 
Assignment 10, 
due: 12/2/21, 
create your own binary search tree so that you can read a song from a file
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class BunkerLandonAssignment10 {

	public static void main(String[] args) throws FileNotFoundException {
		
		//create Binary tree
		BinaryTree bt = new BinaryTree(null);
		
		//open new file
		File inputFileName = new File("parrots.txt");
		Scanner inputFile = new Scanner (inputFileName);

		//create the parrots and add them to the binary tree
		while (inputFile.hasNext()) {
		
			int id = inputFile.nextInt();
			String name = inputFile.next();
			String songPhrase = inputFile.nextLine();
			
			Parrot tempParrot = new Parrot(id, name, songPhrase);
			
			bt.insert(tempParrot);
			
		}
		
		// call the level order
		System.out.println("Parrot Chirstmas Song");
		System.out.println("---------------------\n");
		bt.levelOrder();
		
		//reach the leaves and print the names of the parrots
		System.out.println("\nParrots on Leave Nodes");
		System.out.println("---------------------\n");
		bt.visitLeaves();
		
	}
	
	
}
//parrot class
class Parrot {
	private int id;
	private String name;
	private String songPhrase;
	
	Parrot(int id, String name, String songPhrase) {
		this.id = id;
		this.name = name;
		this.songPhrase = songPhrase;
	}
	
	public String getName() {
		return name;
	}
	
	public String sing() {
		return songPhrase;
	}
	
	public int compareTo(Parrot otherParrot) {
		int returnValue = 0;
		
		if (this.id < otherParrot.id) {
			returnValue = -1;
		}
		
		else if(this.id == otherParrot.id) {
			returnValue = 0;
		}
		else if (this.id > otherParrot.id) {
			returnValue = 1;
		}
		
		return returnValue;
	}
			
}

// binary tree class
class BinaryTree {
	private TreeNode root;
	
	BinaryTree(TreeNode root) {
		this.root = null;
	}
	
	//insert method
	public boolean insert(Parrot parrotToAdd) {
		if(root == null) {
			root = new TreeNode (parrotToAdd, null, null);
		}
		
		else {
			TreeNode parent = null;
			TreeNode current = root;
			
			while(current != null) {
				if (parrotToAdd.compareTo(current.parrot) == -1) {
					parent = current;
					current = current.left;
				}
				
				else if (parrotToAdd.compareTo(current.parrot) == 1) {
					parent = current;
					current = current.right;
				}
				
				else {
					return false;
				}
				
			}
			
			if (parrotToAdd.compareTo(parent.parrot) == -1) {
				parent.left = new TreeNode(parrotToAdd, null, null);
			}
			
			else {
				parent.right = new TreeNode(parrotToAdd, null, null);
			}
		
		}
		return true;
	}
	
	//print the parrots out by level
	public void levelOrder() {
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			
			while(!queue.isEmpty()) {
				TreeNode tempNode = queue.remove();
				System.out.println(tempNode.parrot.sing());
				if(tempNode.left != null) {
					queue.add(tempNode.left);
				}
				
				if(tempNode.right != null) {
					queue.add(tempNode.right);
				}
			}
		}
	}
	
	//visit leaf method call
	public void visitLeaves() {
		visitLeaves(this.root);
	}
	
	//visit leaf method include recursion
	private void visitLeaves(TreeNode aNode) {
		
		if (aNode != null) {
			
			
			visitLeaves(aNode.left);
			System.out.println(aNode.parrot.getName());
			visitLeaves(aNode.right);
		}
	
	}
	
	//treenode for the self referencial class
	class TreeNode {
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;
		
		TreeNode(Parrot parrot, TreeNode left, TreeNode right) {
			this.parrot = parrot;
			this.left = null;
			this.right = null;
		}
	}	
	
}