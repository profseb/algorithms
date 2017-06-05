package temp;
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
	
	
	public void RBInsert(String num) {
		
		StrRBNode q = this.RBSearch(this.root, num);
		
		if (q != null) {
			System.out.println("A palavra \"" + num +"\" foi inserida anteriormente.");
		} else {
		
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
			n.color = StrRBNode.RBColor.RED;
			
			RBInsertFixup(n);				
		}
	}
	
	private void RBDump(StrRBNode n) {
		
		System.out.print("k(" + n.key + ") l(" + StrRBNode.getRBNode(n.left).key + ") r(" + StrRBNode.getRBNode(n.right).key + ") p(" + StrRBNode.getRBNode(n.p).key + ")" + " [" + n.color + "]");
		System.out.println((this.root == n) ? " {ROOT}":"");
		
	}
	
	private void RBDumpCheck(StrRBNode n) {
		
		System.out.print("(" + ((StrRBNode.getRBNode(n.p).key == null) ? "NIL":StrRBNode.getRBNode(n.p).key) + ", "); 
		System.out.print(n.key + ", ");
		System.out.print(((n.color == StrRBNode.RBColor.BLACK) ? "preto":"vermelho") + ", ");
		System.out.print(RBBlackHeight(n) + ", "); // calcular altura negra
		System.out.print(((StrRBNode.getRBNode(n.left).key == null) ? "NIL":StrRBNode.getRBNode(n.left).key)+ ", ");
		System.out.println(((StrRBNode.getRBNode(n.right).key == null) ? "NIL":StrRBNode.getRBNode(n.right).key)+")");
		
	}
		
	/* Rotina de inserção
	 * Insere um nó na árvore 
	*/	
	private void RBInsertFixup(StrRBNode z) {
		
		StrRBNode n = z;
									
		while(z.p.color == StrRBNode.RBColor.RED) {
			
			if (z.p == z.p.p.left) {
				
				StrRBNode y = StrRBNode.getRBNode(z.p.p.right);
								
				if (y.color == StrRBNode.RBColor.RED) {
					z.p.color = StrRBNode.RBColor.BLACK;
					y.color = StrRBNode.RBColor.BLACK;
					z.p.p.color = StrRBNode.RBColor.RED;
					z = z.p.p;
				} else {
																
					if (z == z.p.right) {
						
						z = z.p;	
						RBLeftRotate(z);
						
					}
					
					z.p.color = StrRBNode.RBColor.BLACK;
					z.p.p.color = StrRBNode.RBColor.RED;
					
					RBRightRotate(z.p.p);
					
				}
				
			} else {
				
				StrRBNode y = StrRBNode.getRBNode(z.p.p.left);
											
				if (y.color == StrRBNode.RBColor.RED) {
					z.p.color = StrRBNode.RBColor.BLACK;
					y.color = StrRBNode.RBColor.BLACK;
					z.p.p.color = StrRBNode.RBColor.RED;
					z = z.p.p;
				} else {
															
					if (z == z.p.left) {
						
						z = z.p;													
						RBRightRotate(z);
						
					}
							
					
					z.p.color = StrRBNode.RBColor.BLACK;
					z.p.p.color = StrRBNode.RBColor.RED;
										
					RBLeftRotate(z.p.p);
					
				}
			
			}
			
		}
		
		this.root.color = StrRBNode.RBColor.BLACK;	
				
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
	
	public StrRBNode RBSearch(StrRBNode r, String key) {
		
		while (r != null && !r.key.equals(key)) {
	       if (r.key.compareTo(key) > 0) 
	          r = r.left;
	       else
	          r = r.right;
	    }
	    return r;
	}
	
	public void RBDelete(String num) {
		
		StrRBNode z = this.RBSearch(this.root, num);
		
		StrRBNode x = null;
		
		if (z == null) {
			System.out.println("A palavra \"" + num +"\" foi removida anteriormente ou nao foi inserida.");
			
		} else {
			
			System.out.println("Removendo a palavra " + z.key);

			StrRBNode y = z;
										
			StrRBNode.RBColor yColor = y.color;
			
			if (z.left == StrRBTree.NIL) {
							
				x = StrRBNode.getRBNode(z.right);			
				RBTransplant(z, z.right);	
				
			} else if (z.right == StrRBTree.NIL) {
				
				x = StrRBNode.getRBNode(z.left);	
				RBTransplant(z, z.left);
			} else {
								
				y = RBTreeMinimum(z.right);
				
				yColor = y.color;
										
				x = StrRBNode.getRBNode(y.right);	
										
				if (y.p == z) {					
					x.p = y;
				} else {
					RBTransplant(y, y.right);
								
					y.right = StrRBNode.getRBNode(z.right);				
					y.right.p = y;
				}
							
				RBTransplant(z, y);
				
				y.left = z.left;
				y.left.p = y;
				y.color = z.color;
				
			}
			
			
			if (yColor == StrRBNode.RBColor.BLACK) {				
				RBDeleteFixup(x);
			}
			
			RBPrint(this.root);
			System.out.println();
			RBCheck(this.root);
			
		}
		
	}
	
	private void RBDeleteFixup(StrRBNode x) {
		
		System.out.println("##########");

		System.out.println(x != this.root && x.color == StrRBNode.RBColor.BLACK);
				
		while(x != this.root && x.color == StrRBNode.RBColor.BLACK) {
			
			if (x == StrRBNode.getRBNode(x.p.left)) {
								
				StrRBNode y = StrRBNode.getRBNode(x.p.right);
				
				if (y.color == StrRBNode.RBColor.RED) { // caso 1
					y.color = StrRBNode.RBColor.BLACK;
					x.p.color = StrRBNode.RBColor.RED;
					RBLeftRotate(x.p);
					y = StrRBNode.getRBNode(x.p.right);
				}
				
				if (y.left.color == StrRBNode.RBColor.BLACK && y.right.color == StrRBNode.RBColor.BLACK) { // caso 2
					y.color = StrRBNode.RBColor.RED;
					x = x.p;
				} else {
					
					if (y.right.color == StrRBNode.RBColor.BLACK) {
						
						y.left.color = StrRBNode.RBColor.BLACK;
						y.color = StrRBNode.RBColor.RED;
						RBRightRotate(y);
						y = x.p.right;						
					}
					
					y.color = x.p.color;
					x.p.color = StrRBNode.RBColor.BLACK;
					y.right.color = StrRBNode.RBColor.BLACK;
					RBLeftRotate(x.p);
					x = this.root;					
				}
				
				
			} else {
								
				StrRBNode y = StrRBNode.getRBNode(x.p.left);
				
				if (y.color == StrRBNode.RBColor.RED) { // caso 1
					y.color = StrRBNode.RBColor.BLACK;
					x.p.color = StrRBNode.RBColor.RED;
					RBRightRotate(x.p);
					y = x.p.left;
				}
				
				if (y.right.color == StrRBNode.RBColor.BLACK && y.left.color == StrRBNode.RBColor.BLACK) { // caso 2
					y.color = StrRBNode.RBColor.RED;
					x = x.p;
				} else {
					
					if (y.left.color == StrRBNode.RBColor.BLACK) {
						
						y.right.color = StrRBNode.RBColor.BLACK;
						y.color = StrRBNode.RBColor.RED;
						RBLeftRotate(y);
						y = x.p.left;						
					}
					
					y.color = x.p.color;
					x.p.color = StrRBNode.RBColor.BLACK;
					y.left.color = StrRBNode.RBColor.BLACK;
					RBRightRotate(x.p);
					x = this.root;					
				}
				
			}
			
		}
		
		x.color = StrRBNode.RBColor.BLACK;
		
	}
	
	private StrRBNode RBTreeMinimum(StrRBNode n) {
				
		//while(!StrRBNode.getRBNode(n.left).isNil()) {
		while(n.left != StrRBTree.NIL) {
			n = n.left;
		}
		return n;
	}
	
	private StrRBNode RBTreeMaximum(StrRBNode n) {
		
		while(n.right != StrRBTree.NIL) {
			n = n.right;
		}
		return n;
	}
	
	private int RBBlackHeight(StrRBNode n) {
		
		int i = 0;
		while (n.left != StrRBTree.NIL) {
			n = n.left;
			if (n.color == StrRBNode.RBColor.BLACK) {
				i++;
			}			
		}		
		return i+1;
	}
	
	private void RBTransplant(StrRBNode u, StrRBNode v) {
		
		if (u.p == StrRBTree.NIL) {
			this.root = v;
		} else if (u == u.p.left) {
			u.p.left = v;
			
		} else {
			u.p.right = v;
		}
		
		StrRBNode.getRBNode(v).p = StrRBNode.getRBNode(u.p);		
		
	}
	
	public void RBCheck(StrRBNode r) {
		
		if (r != StrRBTree.NIL) {
			RBDumpCheck(r);
			RBCheck(r.left);			
			RBCheck(r.right);
		}
		
	}
	
	public void RBPrint(StrRBNode r) {
		
		if (r != StrRBTree.NIL) {
			RBPrint(r.left);
			System.out.print(r.key+" ");
			RBPrint(r.right);
		}
		
	}
	
}
