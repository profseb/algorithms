package numbers;

public class RBNode {
		
	public Integer key;
	public RBNode p;
	public RBNode left;
	public RBNode right;
	public RBColor color;
	
	private boolean nil = true;
	
	public RBNode(int key) {
		
		this.key = key;
		this.left = new RBNode();
		this.right = new RBNode();
		this.p = new RBNode();
		this.color = RBColor.RED;
		this.nil = false;
		
	}
	
	public static RBNode getRBNode(RBNode n) {
		if (n == null) {
			return new RBNode();
		} else {
			return n;
		}
	}
		
	public RBNode() {
		
		this.key = null;
		this.left = null;
		this.right = null;
		this.p = null;
		this.color = null;		
				
	}
		
	public boolean isNil() {
		return this.nil;
	}
	
	public void setNil() {
		this.nil = true;
	}
	
	
}
