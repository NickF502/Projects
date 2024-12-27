package hw3;

import java.util.Stack;


public class Main {

	public static void main(String[] args) {
		
		System.out.println("Exercise 1\n");

		/* Exercise 1*/
		Library myLibrary = new Library();
		
		User N = new User("Nick", "101");
		User M = new User("Mike", "200");
		
		Book a = new Book("A", "a", "0");
		Book b = new Book("B", "b", "1");
		Book c = new Book("C", "c", "2");
		Book d = new Book("D", "d", "3");
		
		Magazine z = new Magazine("Z", "z", "4");
		Magazine y = new Magazine("Y", "y", "5");
		Magazine x = new Magazine("X", "x", "6");
		Magazine w = new Magazine("W", "w", "7");

		

		myLibrary.addItem(a);
		myLibrary.addItem(b);
		myLibrary.addItem(c);
		myLibrary.addItem(d);
		myLibrary.addItem(z);
		myLibrary.addItem(y);
		myLibrary.addItem(x);
		myLibrary.addItem(w);

		System.out.println();
		
		myLibrary.checkoutItem("0", N);
		myLibrary.checkoutItem("2", N);
		myLibrary.checkoutItem("5", N);
		myLibrary.checkoutItem("1", M);
		myLibrary.checkoutItem("2", M);
		myLibrary.checkoutItem("7", M);
		
		System.out.println();

		N.viewCheckedOutItems();
		M.viewCheckedOutItems();
		myLibrary.print();

		System.out.println();

		myLibrary.returnItem("0", N);
		myLibrary.returnItem("0", N);
		myLibrary.returnItem("2", N);
		myLibrary.returnItem("7", M);		

		System.out.println();

		N.viewCheckedOutItems();
		M.viewCheckedOutItems();
		myLibrary.print();
		
		System.out.println();
		System.out.println();
		System.out.println();

		
		System.out.println("Exercise 2\n");
		/* Exercise 2 */
		PrintQueue queue = new PrintQueue();

		PrintJob physTextbook = new PrintJob("Physics Textbook", 400);
		PrintJob Advertisement = new PrintJob("Advertisement", 1);
		PrintJob newsArticle = new PrintJob("News Article", 5);
		
		queue.enqueue(physTextbook);
		queue.enqueue(Advertisement);
		queue.enqueue(newsArticle);

		queue.dequeue();
		queue.peek();
		queue.dequeue();
		queue.peek();
		queue.dequeue();
		queue.peek();
		queue.dequeue();
		
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("Exercise 3\n");

		/* Exercise 3 */
		/* 3.1 --------------------------------------------------------*/
		int[] sortedArray1 = {1, 2, 3, 4, 5, 6};
		int[] sortedArray2 = {1, 2, 3, 4, 5};
		
		System.out.println(sumToS(sortedArray1, 10));
		System.out.println(sumToS(sortedArray2, 10));
		System.out.println();

		/* 3.2 --------------------------------------------------------*/
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		
        printLinkedList(head);
        head = iterativeReverseLinkedList(head);
        printLinkedList(head);
        System.out.println();
        

		/* 3.3 --------------------------------------------------------*/
		
        printLinkedList(head);
        head = recursiveReverseLinkedList(head);
        printLinkedList(head);
        System.out.println();


		/* 3.4 --------------------------------------------------------*/
		System.out.println(prefixToPostfix("/*+AB-CDF"));	// [(A+B) * (C+D)] / F
		System.out.println(prefixToPostfix("+/-ABC**DEF"));	// (A-B)/C + (D*E)*F
		System.out.println();
		
		/* 3.5 --------------------------------------------------------*/
		System.out.println(PalindromeChecker("racecar"));
		System.out.println(PalindromeChecker("Nick"));
		System.out.println();
		
		
	}
	
	public static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	    	val = x;
	    }
	}
	
	public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println();
    }
	
	public static boolean sumToS(int[] a, int S) {
		int left = 0;
		int right = a.length-1;
		
		while(left < right) {
			
			int arrSum = a[left] + a[right];
			
			if(arrSum == S) return true;
			if(arrSum > S) right--;
			if(arrSum < S) left++;
		}

		return false;
	}
	
	
	
	public static ListNode recursiveReverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversedList = recursiveReverseLinkedList(head.next);

        head.next.next = head;
        head.next = null;

        return reversedList;
    }
	

	
	
	
	
	private static ListNode iterativeReverseLinkedList(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode prev, cur, next;
		prev = next = null;
		cur = head;
		
		while(cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
			
		head = prev;
		
		
		return head;
		
		
	}
	
	
	
	public static String prefixToPostfix(String prefix) {
		Stack<String> stack =  new Stack<String>();
				
		for(int i=0; i<prefix.length(); i++) {
			char c = prefix.charAt(i);
			stack.push(Character.toString(c));
			
			
			while(stack.size() > 3 && Character.isLetter(stack.peek().charAt(0)) && Character.isLetter(stack.get(stack.size()-2).charAt(0))) {
				String secondToAdd = stack.pop();
				String temp = ("" + stack.pop() + secondToAdd + stack.pop());
				stack.push(temp);
			}
			
		}
		
		String secondToAdd = stack.pop();
		String temp = ("" + stack.pop() + secondToAdd + stack.pop());
		stack.push(temp);
		
		return stack.toString();
	}
	
	

	private static boolean PalindromeChecker(String string) {		
		
		/*
		 * If the string has fewer than 2 characters (1 or 0), then the string is a palindrome. Return true
		 *  Otherwise compare the first and last characters. If they are different, return false.
		 *  If they are the same, create a substring of the string, taking off the first and last characters.
		 *  Run this substring through the function again.
		 * */
		if(string.length() < 2) return true;
		
		if(string.charAt(0) != string.charAt(string.length()-1)) return false;
		else return PalindromeChecker(string.substring(1, string.length()-1));
		
	}
	
	

}
