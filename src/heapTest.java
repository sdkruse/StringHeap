

public class heapTest {
	
	
	
	private static boolean testAdd() {
		
		boolean passed = true;
		boolean caught = false;
		StringHeap heap = new StringHeap(5);
		try {
			
			heap.add("");
		}catch(IllegalArgumentException e) {
			
			caught = true;
		}
		
		if(caught != true) {
		passed = false;
		}
		
		boolean caught2 = false;
	try {
			
			heap.add(null);
		}catch(IllegalArgumentException e) {
			
			caught2 = true;
		}
		
		if(caught2 != true) {
		passed = false;
		}
		

		System.out.println("size " + heap.getSize());
		
		System.out.println("Here" + heap.peek());
		
		heap.add("a");
		heap.add("b");
		heap.add("c");
		heap.add("d");
		heap.add("e");
		heap.add("f");
		heap.add("g");
		heap.add("h");
		heap.add("i");
		heap.add("j");
		
		String[] newHeap1 = heap.getHeap(); 
		
		for(int i = 0; i < newHeap1.length ; i++ ) {
			System.out.println(newHeap1[i]);
		}
		
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		
		
		System.out.println("Here " + heap.peek());
		String[] new2Heap = heap.getHeap(); 
		
		return passed;
	}
	
	
	
	
	

	public static void main(String[] args) {
		
		if(!testAdd()) {
			System.out.println("Failed Add.");
		}

	}

}
