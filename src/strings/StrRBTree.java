package strings;

public class StrRBTree {
	
	public static final StrRBNode NIL = null; // sentinela apontando para null
	
	public StrRBNode root;
	
	/* Construtor vazio
	 * inicialização da árvore vazia
	*/
	public StrRBTree() {
		
		this.root = null;
		
	}
	
	/* Construtor não vazio
	 * inicialização da árvore já passando a raiz (caso queira) 
	*/
	
	
	public void insert(String num) {
		
		StrRBNode n = new StrRBNode(num);		
		StrRBNode y = StrRBTree.NIL;
		StrRBNode x = this.root;
								
		while(x != StrRBTree.NIL) {
			
			y = x;	
			
			x = (n.key.compareTo(x.key) < 1) ? x.left:x.right; // operador ternária para simplificar...
						
		}
				
		n.p = StrRBNode.getRBNode(y);
				
		if (y == StrRBTree.NIL) {
			this.root = n;
		} else if (n.key.compareTo(y.key) < 1) { // removi o n.key == null ||
			y.left = n;
		} else {
			y.right = n;
		}
		
		n.left = StrRBTree.NIL;
		n.right = StrRBTree.NIL;
		n.color = RBColor.RED;
		
		RBInsertFixup(n);
				
	}
	
	private void RBDump(StrRBNode n) {
		
		System.out.print("k(" + n.key + ") l(" + StrRBNode.getRBNode(n.left).key + ") r(" + StrRBNode.getRBNode(n.right).key + ") p(" + StrRBNode.getRBNode(n.p).key + ")" + " [" + n.color + "]");
		System.out.println((this.root == n) ? " {ROOT}":"");
		
	}
		
	/* Rotina de inserção
	 * Insere um nó na árvore 
	*/	
	private void RBInsertFixup(StrRBNode n) {
		
		StrRBNode z = n;
									
		while(n.p.color == RBColor.RED) {
			
			if (n.p == n.p.p.left) {
				
				StrRBNode y = StrRBNode.getRBNode(n.p.p.right);
								
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
				
				StrRBNode y = StrRBNode.getRBNode(n.p.p.left);
											
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
	
	private void RBLeftRotate(StrRBNode n) {
			
		StrRBNode y = StrRBNode.getRBNode(n.right);
								
		n.right = y.left;
			
		if (y.left != StrRBTree.NIL) {
			y.left.p = n;
		}
	
		y.p = StrRBNode.getRBNode(n.p);
							
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
	
	private void RBRightRotate(StrRBNode n) {
				
		StrRBNode y = StrRBNode.getRBNode(n.left);
						
		n.left = y.right;	
			
		if (y.right != StrRBTree.NIL) {
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
	
	public StrRBNode getRBNodeByKey(StrRBNode r, String key) {
		
		while (r != null && r.key != key) {
	       if (r.key.compareTo(key) > 0) 
	          r = r.left;
	       else
	          r = r.right;
	    }
	    return r;
	}
	
	public void delete(String num) {
		
		StrRBNode n = this.getRBNodeByKey(this.root, num);
		
		if (n == null) {
			System.out.println("NÃO ENCONTRADO!");
		} else {
				
			StrRBNode x;
			StrRBNode y = n;
					
			RBColor yColor = y.color;
			
			if (n.left == StrRBTree.NIL) {
				System.out.println("left NIL");
			
				x = StrRBNode.getRBNode(n.right);			
				RBTransplant(n, n.right);	
				
			} else if (n.right == StrRBTree.NIL) {
				System.out.println("right NIL");
				x = StrRBNode.getRBNode(n.left);	
				RBTransplant(n, n.left);
			} else {
				
				System.out.println("else NIL");
				
				y = RBTreeMinimum(n.right);
													
				yColor = y.color;
						
				x = StrRBNode.getRBNode(y.right);	
										
				if (y.p == n) {
					x.p = y;
				} else {
					RBTransplant(y, y.right);
								
					y.right = StrRBNode.getRBNode(n.right);				
					y.right.p = y;
				}
							
				RBTransplant(n, y);
				
				y.left = StrRBNode.getRBNode(n.left);
				y.left.p = y;
				y.color = n.color;
				
			}
						
			
			if (yColor == RBColor.BLACK) {
				
				RBDeleteFixup(x);
			}
		}
		
	}
	
	private void RBDeleteFixup(StrRBNode n) {
		
		System.out.println("Called RBDeleteFixup");
		
		
		//System.out.println(this.root);
		
		
		while(n != this.root && n.color == RBColor.BLACK) {
			
			if (n == n.p.left) {
				
				System.out.println("fixup then");
				
				StrRBNode w = n.p.right;
				
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
								
				StrRBNode w = StrRBNode.getRBNode(n.p).left;
				
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
	
	private StrRBNode RBTreeMinimum(StrRBNode n) {
				
		while(n.left != StrRBTree.NIL) {
			n = n.left;
		}
		return n;
	}
	
	private void RBTransplant(StrRBNode u, StrRBNode v) {
		
		if (u.p == StrRBTree.NIL) {
			this.root = v;
		} else if (u == u.p.left) {
			u.p.left = v;
			
		} else {
			u.p.right = v;
		}
		
		StrRBNode.getRBNode(v).p = StrRBNode.getRBNode(u).p;		
		
	}
	
	public void RBPrintERD(StrRBNode r) {
		
		if (r != StrRBTree.NIL) {
			RBPrintERD(r.left);
			RBDump(r);
			RBPrintERD(r.right);
		}
		
	}
	
}
