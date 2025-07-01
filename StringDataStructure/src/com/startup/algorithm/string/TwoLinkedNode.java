package com.startup.algorithm.string;

public class TwoLinkedNode {

	ListNode add(ListNode a, ListNode b) {
		
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		int carry = 0;
		
		while(a != null || b!= null) {
			int x = (a != null) ? a.val : 0;
			int y = (b != null) ? b.val : 0;
			int sum = carry + x + y;
			carry = sum/10;
			tail.next = new ListNode(sum % 10);
			tail = tail.next;
			if(a != null) a = a.next;
			if(b != null) b = b.next;
			
		}
		if(carry > 0) {
			tail.next = new ListNode(carry);
		}
		
		return dummy.next;
	}
	
	void printList(ListNode head) {
		while (head != null) {
			if(head != null) {
				System.out.print(head.val);
			}
			if(head.next != null) {
				System.out.print(" -> ");
			}
			head = head.next;
		}
	}
	public static void main(String[] args) {
		TwoLinkedNode solution = new TwoLinkedNode();
		
		ListNode a = new ListNode(3);
		a.next = new ListNode(4);
		a.next.next = new ListNode(3);
		
		ListNode b = new ListNode(5);
		b.next = new ListNode(6);
		b.next.next = new ListNode(4);
		
		ListNode result = solution.add(a, b);
		
		System.out.println("Result: ");
		solution.printList(result);
		
	}
}

class ListNode {
	int val;
	ListNode next;
	
	public ListNode(int val) {
		this.val = val;
	}
}
