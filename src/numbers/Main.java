package numbers;
import strings.StrRBTree;


public class Main {

	public static void main(String[] args) {

		MyRBTree mytree = new MyRBTree();
		
		mytree.insert(3);
		mytree.insert(7);
		mytree.insert(10);
		mytree.insert(12);
		mytree.insert(14);
		mytree.insert(15);
		mytree.insert(16);
		mytree.insert(17);
		mytree.insert(19);
		mytree.insert(20);
	
				
		mytree.RBPrint();
		
		mytree.delete(12);
		
		mytree.RBPrint();
		
	}

}




