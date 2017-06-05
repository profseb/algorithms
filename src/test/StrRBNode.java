package test;
import numbers.RBColor;

public class StrRBNode {
	
	public enum RBColor {
		
		BLACK, RED;

	}
		
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
			
	public StrRBNode() {
		
		this.key = null;
		this.left = null;
		this.right = null;
		this.p = null;
		this.color = RBColor.BLACK;
		this.nil = true;
				
	}
		
	public boolean isNil() {
		return this.nil;
	}
		
}
