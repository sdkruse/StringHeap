import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HeapRunner {
	public static void main(String[] args) {
		// TODO: instantiate your heap
		StringHeap heap = new StringHeap(5);
		// TODO: add a try/catch block to make the code below compile
		try {
			Scanner scnr = new Scanner(new File("C:\\Users\\skruse\\Downloads\\words2.txt"));
			while (scnr.hasNextLine()) {
				String word = scnr.nextLine();
				heap.add(word);
			}
			heap.remove();
			heap.printHeap();

			System.out.println("------------------------------");

			heap.printLevelOrderTraversal();

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
}
