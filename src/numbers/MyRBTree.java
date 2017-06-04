package numbers;

public class MyRBTree {
	
	public static final RBNode NIL = null; // sentinela apontando para null
	
	public RBNode root;
	
	/* Construtor vazio
	 * inicialização da árvore vazia
	*/
	public MyRBTree() {
		
		this.root = null;
		
	}
	
	/* Construtor não vazio
	 * inicialização da árvore já passando a raiz (caso queira) 
	*/
	
	
	public void insert(int num) {
		
		RBNode n = new RBNode(num);		
		RBNode y = MyRBTree.NIL;
		RBNode x = this.root;
								
		while(x != MyRBTree.NIL) {
			
			y = x;	
			
			x = (n.key < x.key) ? x.left:x.right; // operador ternária para simplificar...
						
		}
				
		n.p = RBNode.getRBNode(y);
				
		if (y == MyRBTree.NIL) {
			this.root = n;
		} else if (n.key < y.key) { // removi o n.key == null ||
			y.left = n;
		} else {
			y.right = n;
		}
		
		n.left = MyRBTree.NIL;
		n.right = MyRBTree.NIL;
		n.color = RBColor.RED;
		
		RBInsertFixup(n);
				
	}
	
	private void RBDump(RBNode n) {
		
		System.out.print("k(" + n.key + ") l(" + RBNode.getRBNode(n.left).key + ") r(" + RBNode.getRBNode(n.right).key + ") p(" + RBNode.getRBNode(n.p).key + ")" + " [" + n.color + "]");
		System.out.println((this.root == n) ? " {ROOT}":"");
		
	}
		
	/* Rotina de inserção
	 * Insere um nó na árvore 
	*/	
	private void RBInsertFixup(RBNode n) {
		
		RBNode z = n;
									
		while(n.p.color == RBColor.RED) {
			
			if (n.p == n.p.p.left) {
				
				RBNode y = RBNode.getRBNode(n.p.p.right);
								
				if (y.color == RBColor.RED) {
					n.p.color = RBColor.BLACK;
					y.color = RBColor.BLACK;
					n.p.p.color = RBColor.RED;
					n = n.p.p;
				} else {
																
					if (n == n.p.right) {
						
						n = n.p;	
						RBLeftRotate(n);
						
					}
					
					n.p.color = RBColor.BLACK;
					n.p.p.color = RBColor.RED;
					
					RBRightRotate(n.p.p);
					
				}
				
			} else {
				
				RBNode y = RBNode.getRBNode(n.p.p.left);
											
				if (y.color == RBColor.RED) {
					n.p.color = RBColor.BLACK;
					y.color = RBColor.BLACK;
					n.p.p.color = RBColor.RED;
					n = n.p.p;
				} else {
															
					if (n == n.p.left) {
						
						n = n.p;													
						RBRightRotate(n);
						
					}
							
					
					n.p.color = RBColor.BLACK;
					n.p.p.color = RBColor.RED;
										
					RBLeftRotate(n.p.p);
					
				}
			
			}
			
		}
		
		this.root.color = RBColor.BLACK;	
		
		
		//System.out.println("# Insere " + z.key);
		//RBPrintERD(this.root);
		//System.out.println("--------------------------------------------");
		
	}
	
	public void RBPrint() {
		
		RBPrintERD(this.root);
		System.out.println("--------------------------------------------");
		
	}
	
	private void RBLeftRotate(RBNode n) {
			
		RBNode y = RBNode.getRBNode(n.right);
								
		n.right = y.left;
			
		if (y.left != MyRBTree.NIL) {
			y.left.p = n;
		}
	
		y.p = RBNode.getRBNode(n.p);
							
		if (n.p.isNil()) {
			this.root = y;
		} else {
			if (n == n.p.left) {		
				n.p.left = y;
			} else {
				n.p.right = y;
			}			
		}
				
		y.left = n;				
		n.p = y;	
			
		
	}
	
	private void RBRightRotate(RBNode n) {
				
		RBNode y = RBNode.getRBNode(n.left);
						
		n.left = y.right;	
			
		if (y.right != MyRBTree.NIL) {
			y.right.p = n;
		}
		
		y.p = n.p;
						
		if (n.p.isNil()) {
			this.root = y;
		} else {
			if (n == n.p.right) {
				n.p.right = y;
			} else {
				n.p.left = y;
			}
		}
			
		y.right = n;
		n.p = y;
		
		
	}
	
	public RBNode getRBNodeByKey(RBNode r, int key) {
		
		while (r != null && r.key != key) {
	       if (r.key > key) 
	          r = r.left;
	       else
	          r = r.right;
	    }
	    return r;
	}
	
	public void delete(int num) {
		
		RBNode n = this.getRBNodeByKey(this.root, num);
		
		if (n == null) {
			System.out.println("NÃO ENCONTRADO!");
		} else {
				
			RBNode x;
			RBNode y = n;
					
			RBColor yColor = y.color;
			
			if (n.left == MyRBTree.NIL) {
				System.out.println("left NIL");
			
				x = RBNode.getRBNode(n.right);			
				RBTransplant(n, n.right);	
				
			} else if (n.right == MyRBTree.NIL) {
				System.out.println("right NIL");
				x = RBNode.getRBNode(n.left);	
				RBTransplant(n, n.left);
			} else {
				
				System.out.println("else NIL");
				
				y = RBTreeMinimum(RBNode.getRBNode(n.right));
													
				yColor = y.color;
						
				x = RBNode.getRBNode(y.right);	
										
				if (y.p == n) {
					x.p = y;
				} else {
					RBTransplant(y, y.right);
								
					y.right = RBNode.getRBNode(n.right);				
					y.right.p = y;
				}
							
				RBTransplant(n, y);
				
				y.left = RBNode.getRBNode(n.left);
				y.left.p = y;
				y.color = n.color;
				
			}
						
			
			if (yColor == RBColor.BLACK) {
				
				RBDeleteFixup(x);
			}
		}
		
	}
	
	private void RBDeleteFixup(RBNode n) {
		
		System.out.println("Called RBDeleteFixup");
		
		
		//System.out.println(this.root);
		
		
		while(n != this.root && n.color == RBColor.BLACK) {
			
			if (n == n.p.left) {
				
				System.out.println("fixup then");
				
				RBNode w = n.p.right;
				
				if (w.color == RBColor.RED) { // caso 1
					w.color = RBColor.BLACK;
					n.p.color = RBColor.BLACK;
					RBLeftRotate(n.p);
					w = n.p.right;
				}
				
				if (w.left.color == RBColor.BLACK && w.right.color == RBColor.BLACK) { // caso 2
					w.color = RBColor.RED;
					n = n.p;
				} else {
					
					if (w.right.color == RBColor.BLACK) {
						
						w.left.color = RBColor.BLACK;
						w.color = RBColor.RED;
						RBRightRotate(w);
						w = n.p.right;						
					}
					
					w.color = n.p.color;
					n.p.color = RBColor.BLACK;
					w.right.color = RBColor.BLACK;
					RBLeftRotate(n.p);
					n = this.root;					
				}
				
				
			} else {
				
				System.out.println("fixup else");
								
				RBNode w = RBNode.getRBNode(n.p).left;
				
				if (w.color == RBColor.RED) { // caso 1
					w.color = RBColor.BLACK;
					n.p.color = RBColor.BLACK;
					RBRightRotate(n.p);
					w = n.p.left;
				}
				
				if (w.right.color == RBColor.BLACK && w.left.color == RBColor.BLACK) { // caso 2
					w.color = RBColor.RED;
					n = n.p;
				} else {
					
					if (w.left.color == RBColor.BLACK) {
						
						w.right.color = RBColor.BLACK;
						w.color = RBColor.RED;
						RBLeftRotate(w);
						w = n.p.left;						
					}
					
					w.color = n.p.color;
					n.p.color = RBColor.BLACK;
					w.left.color = RBColor.BLACK;
					RBRightRotate(n.p);
					n = this.root;					
				}
				
			}
			
		}
		
		n.color = RBColor.BLACK;
		
	}
	
	private RBNode RBTreeMinimum(RBNode n) {
				
		while(!RBNode.getRBNode(n.left).isNil()) {
			n = n.left;
		}
		return n;
	}
	
	private RBNode RBTreeMaximum(RBNode n) {
		
		while(n.right != MyRBTree.NIL) {
			n = n.right;
		}
		return n;
	}
	
	private void RBTransplant(RBNode u, RBNode v) {
		
				
		if (u.p.isNil()) {
			this.root = v;
		} else if (u == u.p.left) {
			u.p.left = v;
			
		} else {
			u.p.right = v;
		}
		
		RBNode.getRBNode(v).p = RBNode.getRBNode(u).p;		
		
	}
	
	public void RBPrintERD(RBNode r) {
		
		if (r != MyRBTree.NIL) {
			RBPrintERD(r.left);
			RBDump(r);
			RBPrintERD(r.right);
		}
		
	}
	
}
