//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           String Heap
// Files:           StringHeap.java, HeapRunner.java
// Course:          CS 400 Fall 2019
//
// Author:          Sam Kruse
// Email:           sdkruse@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NA
// Partner Email:   NA
// Partner Lecturer's Name: NA
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NA
// Online Sources:  NA
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.lang.Math;

public class StringHeap {
	// instance variables; you may add additional private fields
	private String[] heap;
	private int size;

	/*
	 * constructor (this code is done for you)
	 * 
	 * @param the initial heap capacity
	 */
	public StringHeap(int capacity) {
		heap = new String[capacity];
		size = 0;
	}

	/*
	 * determines which String has higher priority, using rules in this order: 0)
	 * convert each String to lower case first, since case does not matter 1) if
	 * lengths are different, the largest length String has priority 2) else if
	 * numVowels (a,e,i,o,u) are different, the String with the most vowels has
	 * priority 3) else, alphabetical order (since already in lower case, use
	 * .compareTo() in the String class)
	 * 
	 * @param first the first String
	 * 
	 * @param second the second String
	 * 
	 * @return a positive integer if first has higher priority; a negative integer
	 * if second has higher priority; 0 if the priorities are equal
	 */
	/**
	 * Prioritizes the strings based on the criteria above
	 * @param first 
	 * @param second
	 * @return the int stating the priority
	 */
	public static int prioritize(String first, String second) {
		first = first.toLowerCase();
		second = second.toLowerCase();
		int priority;
		//First Criteria
		int dif = first.length() - second.length();
		//Second Criteria
		if (dif != 0) {
			priority = dif;
		} else {
			

			String string;
			int vowelCountOne = 0;
			int vowelCountTwo = 0;
			int count = 0;

			for (int i = 0; i < 2; i++) {

				if (i == 0) {
					string = first;
				} else {
					string = second;
				}

				for (int j = 0; j < string.length(); j++) {

					char letter = string.charAt(j);

					if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
						if (i == 0) {
							vowelCountOne++;
						} else {
							vowelCountTwo++;
						}

					}

				}

			}
			int countDif = vowelCountOne - vowelCountTwo;
			//Third Criteria
			if (countDif != 0) {
				priority = countDif;

			} else {
				priority = second.compareTo(first);
			}

		}

		return priority;
	}

	/*
	 * adds a String to the heap and prioritizes using the 'prioritize' method
	 * 
	 * @param s the string to be added (duplicate values should be added again)
	 * 
	 * @throws IllegalArgumentException if the value is null or an empty String
	 */
	/**
	 * A method to add a string to the heap. uses the percUp method to maintain heap structure.
	 * @param value is the string you want to add.
	 * @throws IllegalArgumentException
	 *
	 */
	public void add(String value) throws IllegalArgumentException {
		if (value == "" || value == null) {
			throw new IllegalArgumentException("Oh no!");
		}

		size++;
		if (this.size == this.heap.length) {
			StringHeap newHeap = new StringHeap(2 * this.size);

			for (int i = 0; i < this.heap.length; i++) {
				newHeap.heap[i] = this.heap[i];
			}

			this.heap = newHeap.heap;
		}

		if (this.isEmpty()) {
			this.heap[1] = value;
		} else {

			this.heap[size] = value;

			// make sure the heap maintains it's structure
			StringHeap percHeap = this.percUp(size);

			this.heap = percHeap.heap;
		}
		

	}

	/*
	 * removes the String with the highest priority from the queue and adjusts the
	 * heap to maintain priority rules
	 * 
	 * @return the String with the highest priority; null if the heap is empty
	 */
	/**
	 * removes the root node and replaces it with the last node in the heap structure. 
	 * Then percDown is called on the root to maintain heap structure
	 * @return
	 */
	public String remove() {
		String removed;
		if (this.size == 1) {
			removed = this.heap[1];
			this.heap[1] = null;
			return removed;
		} else if (this.size > 1) {

			removed = this.heap[1];
			this.heap[1] = this.heap[size];

			this.heap[size] = null;
			size--;
			StringHeap percDownHeap = this.percDown(1);
			this.heap = percDownHeap.heap;

			return removed;

		} else {
			return null;
		}

	}

	/*
	 * @return true if the String has no elements, false otherwise
	 */
	/**
	 * Is there a root node?
	 * @return a boolean that states whether or not it's empty.
	 */
	public boolean isEmpty() {
		boolean empty = true;
		if (size > 0) {
			empty = false;
		}
		return empty;
	}

	/*
	 * @return the number of Strings stored in the heap
	 */
	/**
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/*
	 * @return the element with the highest priority but do not remove it
	 */
	/**
	 * look at the highest priority
	 * 
	 * @return
	 */
	public String peek() {
		String value ="";
		if(size > 0) {
			value = this.heap[1];
		}
		return value;
	}

	/*
	 * calculates the height (the number of levels past the root); an empty heap has
	 * a height of 0 a heap with just a root has a height of 1
	 * 
	 * @return the height of the heap
	 */
	
	/**
	 * 
	 * @return the height of the heap
	 */
	public int getHeight() {
		int height;
		if (size == 0) {
			height = 0;
		} else if (size == 1) {
			height = 1;
		} else {
			height = 1 + log2(size);
		}
		return height;
	}

	/*
	 * a new ArrayList containing all Strings at this level - may not contain null
	 * values - an empty heap will result in returning an empty ArrayList - throws
	 * an IndexOutOfBoundsException if level is not appropriate for this heap ( < 1
	 * or > height) - Level 1 is the root, Level 2 is the next two items, etc.
	 * 
	 * @return a new String ArrayList that contains only the Strings at this level
	 */
	/**
	 * creates a list of all the values on this level of the heap.
	 * @param level
	 * @return an arraylist at that level.
	 * @throws IndexOutOfBoundsException
	 */
	public ArrayList<String> getLevel(int level) throws IndexOutOfBoundsException{
		if(!this.isEmpty()) {
			//an equation giving the first index of the level
			//an equation giving the last index of the level
			//I felt really smart when I realized this :)
		int begin = (int) Math.pow(2, level - 1);
		int end = (int) Math.pow(2, level) - 1;

		if (end > size) {
			end = size;
		}
		

		int arraySize = end - begin;

		ArrayList<String> stringLevel = new ArrayList<String>(arraySize);

		for (int arrayIndex = begin; arrayIndex <= end; arrayIndex++) {
			stringLevel.add(this.heap[arrayIndex]);
		}
		
		

		return stringLevel;
		
		}else {
			return new ArrayList<String>(0);
		}
		
		
	}

	/*
	 * returns a deep copy of the heap array, but not the actual heap object so that
	 * the original heap remains unchangeable outside this class - will be used by
	 * the autograder - an empty heap will return null
	 * 
	 * @return a new array of length heap.length with copied Strings
	 * 
	 */
	/**
	 * Creates a deep copy of the heap array
	 * @return a copy
	 */
	public String[] getHeap() {
		if (this.isEmpty()) {
			return null;
		} else {
			int newLength = this.heap.length;
			String[] copy = new String[newLength];

			for (int i = 0; i < newLength; i++) {
				copy[i] = this.heap[i];
			}
			return copy;
		}
	}

	/*
	 * prints out heap size, then prints out the heap elements - one per line
	 * following the indexing of the heap array
	 */
	/**
	 * Prints the heap in the array order
	 */
	public void printHeap() {
		System.out.println(this.size);

		for (int j = 1; j <= this.size; j++) {
			System.out.println(this.heap[j]);
		}
	}

	/*
	 * prints out level-order traversal of the heap - one level per line with nodes
	 * delimited by a single whitespace
	 */
	/**
	 * prints the heap in the heap structure.
	 */
	public void printLevelOrderTraversal() {
		int height = this.getHeight();
		System.out.println("height " + height);
		for (int ind = 1; ind <= height; ind++) {
			ArrayList<String> level = getLevel(ind);

			for (int arrayInd = 0; arrayInd < level.size(); arrayInd++) {
				System.out.print(level.get(arrayInd) + " ");
			}

			System.out.println("");

		}

	}

	// you are welcome to add private methods
	/**
	 * compute the log2
	 * @param x
	 * @return the result
	 */
	private int log2(int x) {

		double value = Math.log(x) / Math.log(2);
		int fin = (int) Math.floor(value);
		return fin;

	}

	private int parent(int index) {
		return index / 2;
	}

	private int leftChild(int index) {

		return 2 * index;
	}

	private int rightChild(int index) {

		return 2 * index + 1;
	}

	// returns true if the element at index has a parent, leftchild, rightchild
	private boolean hasParent(int index) {
		return index > 1;
	}

	private boolean hasLeftChild(int index) {

		return this.heap[leftChild(index)] != null && leftChild(index) <= size;
	}

	private boolean hasRightChild(int index) {
		return this.heap[rightChild(index)] != null && rightChild(index) <= size;
	}

	// swaps the elements at two indices in the array
	private void swap(String[] a, int index1, int index2) {
		String temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

/**
 * Uses the swap and prioritize methods to determine when a child should be swapped up
 * with it's parent.
 * @param cur
 * @return the heap
 */
	private StringHeap percUp(int cur) {
		if (!hasParent(cur)) {
			return this;
		}
		if (prioritize(this.heap[cur], this.heap[parent(cur)]) > 0) {
			swap(this.heap, cur, parent(cur));
			return percUp(parent(cur));
		} else {
			return this;
		}

	}
	/**
	 * called from the remove method to keep heap structure by
	 * recursively swapping parents with children based on priority.
	 * @param cur
	 * @return
	 */
	private StringHeap percDown(int cur) {
			//if there are two children
		if (this.hasLeftChild(cur) && this.hasRightChild(cur)) {
			// and if the left is bigger than the right
			if (prioritize(this.heap[leftChild(cur)], this.heap[rightChild(cur)]) > 0) {
				// and the left is larger than the current
				if (prioritize(this.heap[leftChild(cur)], this.heap[cur]) > 0) {
					// swap their places
					swap(this.heap, cur, leftChild(cur));
					// and call this method again on the
					return percDown(leftChild(cur));
				}
			} else {
				if (prioritize(this.heap[rightChild(cur)], this.heap[cur]) > 0) {
					swap(this.heap, cur, rightChild(cur));
					return percDown(rightChild(cur));
				}
			}
		} else if (this.hasLeftChild(cur) && prioritize(this.heap[leftChild(cur)], this.heap[cur]) > 0) {
			//if there aren't two children and the left is bigger than the right.
			swap(this.heap, cur, leftChild(cur));
			// and call this method again on the
			return this;
		} else if (this.hasRightChild(cur) && prioritize(this.heap[rightChild(cur)], this.heap[cur]) > 0) {
			swap(this.heap, cur, rightChild(cur));
			// and call this method again on the
			return this;
		}

		return this;
	}

	public static void main(String[] args) {
		StringHeap myHeap = new StringHeap(4);
		myHeap.add("Dad");
		myHeap.add("Mom");
		myHeap.add("Jake");
		myHeap.add("Gabe");
		myHeap.add("Abby");
		myHeap.add("Sam");
		myHeap.add("Hannah");
		myHeap.add("Nate");
		myHeap.add("Eliza");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		myHeap.printLevelOrderTraversal();
		
		myHeap.add("Lydia");

	

	}
}
