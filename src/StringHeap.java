import java.util.ArrayList;

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
	 * determines which String has higher priority, using rules in this order:
	 * 	0) convert each String to lower case first, since case does not matter
	 * 	1) if lengths are different, the largest length String has priority
	 * 	2) else if numVowels (a,e,i,o,u) are different, the String with the most vowels has priority
	 * 	3) else, alphabetical order (since already in lower case, use .compareTo() in the String class)
	 * 
	 * @param first the first String
	 * @param second the second String
	 * @return a positive integer if first has higher priority; a negative integer if
	 * second has higher priority; 0 if the priorities are equal
	 */
	public static int prioritize(String first, String second) {
		first = first.toLowerCase();
		second = second.toLowerCase();
		int priority;
		
		int dif = first.length() - second.length();
		if(dif != 0) {
			priority = dif;
			System.out.println("first priority criteria " + priority);
		}else{
		
		String string;
		int vowelCountOne = 0;
		int vowelCountTwo = 0;
		int count = 0;
		
		for(int i = 0; i < 2; i++) {
			
			if(i == 0) {
				string = first;
			}else {
				string = second;
			}
			
			for(int j = 0; j < string.length(); j ++) {
				
				char letter = string.charAt(j);
				
				if(letter == 'a' ||letter == 'e' ||letter == 'i' ||letter == 'o' ||letter == 'u') {
					count++;
				}
				
			}
			
			if(i == 0) {
				vowelCountOne = count;
			}else {
				vowelCountTwo = count;
			}
					
		}
			int countDif = vowelCountOne - vowelCountTwo;
			
			if(countDif != 0) {
				priority = countDif;
				System.out.println("Second Priority Criteria " + priority);
			}else {
				priority = first.compareTo(second);
				System.out.println("Third Priority Criteria " + priority);
			}
			
		}

		return priority;
	}

	/*
	 * adds a String to the heap and prioritizes using the 'prioritize' method
	 * 
	 * @param s the string to be added (duplicate values should be added again)
	 * @throws IllegalArgumentException if the value is null or an empty String
	 */
	public void add(String value) throws IllegalArgumentException {
		if(value == "" || value == null) {
			throw new IllegalArgumentException("Oh no!");
		}
		
		if(this.size == this.heap.length) {
			StringHeap newHeap = new StringHeap(2*this.size);
			
			for(int i = 0; i < this.heap.length; i++) {
				newHeap.heap[i] = this.heap[i];
			}
			
			this.heap = newHeap.heap;
		}
		
		
		this.heap[size] = value;
		
		//make sure the heap maintains it's structure
		StringHeap percHeap = this.percUp(size);
		
		this.heap = percHeap.heap;
		
		
		size++;	
		
		
	}

	/*
	 * removes the String with the highest priority from the queue and adjusts the heap
	 * to maintain priority rules
	 * 
	 * @return the String with the highest priority; null if the heap is empty
	
	 
	public String remove() {
		
		if(this.size > 0) {
			String removed = this.heap[0];
			
			this.heap[0] = this.heap[size];
			
			this.heap[size] = null;
			
		
			StringHeap percDownHeap = this.percDown(0);
			heap = percDownHeap.heap;
			size--;
			
			return removed;
			
		}
		return null;
	}
	*/

	/*
	 * @return true if the String has no elements, false otherwise
	 */
	public boolean isEmpty() {
		boolean empty = true;
		if(size > 0) {
			empty = false;
		}
		return empty;
	}

	/*
	 * @return the number of Strings stored in the heap
	 */
	public int getSize() {
		return size;
	}

	/*
	 * @return the element with the highest priority but do not remove it
	 */
	public String peek() {
		return heap[0];
	}

	/*
	 * calculates the height (the number of levels past the root); an empty heap has a
	 * height of 0 a heap with just a root has a height of 1
	 * 
	 * @return the height of the heap
	 */
	public int getHeight() {
		// TODO
		return 0;
	}

	/*
	 * a new ArrayList containing all Strings at this level
	 * 	- may not contain null values
	 * 	- an empty heap will result in returning an empty ArrayList
	 * 	- throws an IndexOutOfBoundsException if level is not appropriate for this heap ( < 1 or > height)
	 *	- Level 1 is the root, Level 2 is the next two items, etc.
	 * 
	 * @return a new String ArrayList that contains only the Strings at this level
	 */
	public ArrayList<String> getLevel(int level) {
		// TODO
		return null;
	}

	/*
	 * returns a deep copy of the heap array, but not the actual heap object so that
	 * the original heap remains unchangeable outside this class
	 * 	- will be used by the autograder
	 * 	- an empty heap will return null
	 * 
	 * @return a new array of length heap.length with copied Strings
	 * 
	 */
	public String[] getHeap() {
		// TODO
		return null;
	}

	/*
	 * prints out heap size, then prints out the heap elements
	 * 	- one per line following the indexing of the heap array
	 */
	public void printHeap() {
	System.out.println(this.size);
	
	for(int j = 0; j < this.size; j++) {
	System.out.println(this.heap[j]);
	}
	}

	/*
	 * prints out level-order traversal of the heap
	 * 	- one level per line with nodes delimited by a single whitespace
	 */
	public void printLevelOrderTraversal() {
		// TODO
	}

	// you are welcome to add private methods
	private int parent
	(int index) { 
		return index/2;
	}
	private int leftChild(int index) {
		
		return 2*index - 1;
	} 
	
	
	private int rightChild(int index) {
		
		return 2*index;
	} 


	// returns true if the element at index has a parent, leftchild, rightchild
	private boolean hasParent(int index) { 
		return index > 1;
	} 
	private boolean hasLeftChild(int index) {
		return leftChild(index) <= size;
} 
	private boolean hasRightChild(int index) {
		return rightChild(index) <= size; } 

	// swaps the elements at two indices in the array
	private void swap(String [] a, int index1, int index2) { 
		String temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	} 
	/*
	 * determines which String has higher priority, using rules in this order:
	 * 	0) convert each String to lower case first, since case does not matter
	 * 	1) if lengths are different, the largest length String has priority
	 * 	2) else if numVowels (a,e,i,o,u) are different, the String with the most vowels has priority
	 * 	3) else, alphabetical order (since already in lower case, use .compareTo() in the String class)
	 * 
	 * @param first the first String
	 * @param second the second String
	 * @return a positive integer if first has higher priority; a negative integer if
	 * second has higher priority; 0 if the priorities are equal
	 */
	private StringHeap percUp(int cur) {
		if(!hasParent(cur)) {
			return this;
		}
		
		if(prioritize(this.heap[cur], this.heap[parent(cur)]) > 0) {
			swap(this.heap, cur, parent(cur));
			System.out.println("");
			return percUp(parent(cur));
		}else {
			return this;
		}

	}
	
	/**
	private StringHeap percDown(int cur) {
		if(this.hasLeftChild(cur) && this.hasRightChild(cur)) {
			if(prioritize(this.heap[leftChild(cur)], this.heap[rightChild(cur)]) > 0) {
				
			}
		}
		if(prioritize(this.heap. cur,)
		
		
		return null;
	}
	*/

	public static void main(String[] args) {
		StringHeap myHeap = new StringHeap(10);
		myHeap.add("Dad");
		myHeap.add("Mom");
		myHeap.add("Jake");
		myHeap.add("Gabe");
		myHeap.add("Abby");
		myHeap.add("Sam");
		myHeap.add("Hannah");
		myHeap.add("Nate");
		myHeap.add("Eliza");
		myHeap.add("Lydia");
		
		myHeap.printHeap();
		
		
		
	}
}


