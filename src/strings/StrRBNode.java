package strings;

public class StrRBNode {
		
	public String key;
	public StrRBNode p;
	public StrRBNode left;
	public StrRBNode right;
	public RBColor color;
	
	private boolean nil = true;
	
	public StrRBNode(String key) {
		
		this.key = key;
		this.left = new StrRBNode();
		this.right = new StrRBNode();
		this.p = new StrRBNode();
		this.color = RBColor.RED;
		this.nil = false;
		
	}
	
	public static StrRBNode getRBNode(StrRBNode n) {
		if (n == null) {
			return new StrRBNode();
		} else {
			return n;
		}
	}
		
	public StrRBNode() {
		
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
