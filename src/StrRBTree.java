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
			System.out.println("IMPOSSÍVEL INSERIR! PALAVRA JÁ EXISTE NESTE DICIONÁRIO!");
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
	private void RBInsertFixup(StrRBNode n) {
		
		StrRBNode z = n;
									
		while(n.p.color == StrRBNode.RBColor.RED) {
			
			if (n.p == n.p.p.left) {
				
				StrRBNode y = StrRBNode.getRBNode(n.p.p.right);
								
				if (y.color == StrRBNode.RBColor.RED) {
					n.p.color = StrRBNode.RBColor.BLACK;
					y.color = StrRBNode.RBColor.BLACK;
					n.p.p.color = StrRBNode.RBColor.RED;
					n = n.p.p;
				} else {
																
					if (n == n.p.right) {
						
						n = n.p;	
						RBLeftRotate(n);
						
					}
					
					n.p.color = StrRBNode.RBColor.BLACK;
					n.p.p.color = StrRBNode.RBColor.RED;
					
					RBRightRotate(n.p.p);
					
				}
				
			} else {
				
				StrRBNode y = StrRBNode.getRBNode(n.p.p.left);
											
				if (y.color == StrRBNode.RBColor.RED) {
					n.p.color = StrRBNode.RBColor.BLACK;
					y.color = StrRBNode.RBColor.BLACK;
					n.p.p.color = StrRBNode.RBColor.RED;
					n = n.p.p;
				} else {
															
					if (n == n.p.left) {
						
						n = n.p;													
						RBRightRotate(n);
						
					}
							
					
					n.p.color = StrRBNode.RBColor.BLACK;
					n.p.p.color = StrRBNode.RBColor.RED;
										
					RBLeftRotate(n.p.p);
					
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
		
		StrRBNode n = this.RBSearch(this.root, num);
		
		if (n == null) {
			System.out.println("A palavra \"" + num +"\" foi removida anteriormente ou nao foi inserida.");
			
		} else {
			
			System.out.println("Removendo a palavra " + n.key);
			
			StrRBNode x;
			StrRBNode y = n;
					
			StrRBNode.RBColor yColor = y.color;
			
			if (n.left == StrRBTree.NIL) {
							
				x = StrRBNode.getRBNode(n).right;			
				RBTransplant(n, n.right);	
				
			} else if (n.right == StrRBTree.NIL) {
				
				x = StrRBNode.getRBNode(n.left);	
				RBTransplant(n, n.left);
			} else {
				
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
						
			
			if (yColor == StrRBNode.RBColor.BLACK) {
				
				RBDeleteFixup(x);
			}
			
			RBPrint(this.root);
			System.out.println();
			RBCheck(this.root);
			
		}
		
	}
	
	private void RBDeleteFixup(StrRBNode n) {
		
				
		while(n != this.root && n.color == StrRBNode.RBColor.BLACK) {
			
			if (n == n.p.left) {
								
				StrRBNode w = n.p.right;
				
				if (w.color == StrRBNode.RBColor.RED) { // caso 1
					w.color = StrRBNode.RBColor.BLACK;
					n.p.color = StrRBNode.RBColor.BLACK;
					RBLeftRotate(n.p);
					w = n.p.right;
				}
				
				if (w.left.color == StrRBNode.RBColor.BLACK && w.right.color == StrRBNode.RBColor.BLACK) { // caso 2
					w.color = StrRBNode.RBColor.RED;
					n = n.p;
				} else {
					
					if (w.right.color == StrRBNode.RBColor.BLACK) {
						
						w.left.color = StrRBNode.RBColor.BLACK;
						w.color = StrRBNode.RBColor.RED;
						RBRightRotate(w);
						w = n.p.right;						
					}
					
					w.color = n.p.color;
					n.p.color = StrRBNode.RBColor.BLACK;
					w.right.color = StrRBNode.RBColor.BLACK;
					RBLeftRotate(n.p);
					n = this.root;					
				}
				
				
			} else {
								
				StrRBNode w = StrRBNode.getRBNode(n.p).left;
				
				if (w.color == StrRBNode.RBColor.RED) { // caso 1
					w.color = StrRBNode.RBColor.BLACK;
					n.p.color = StrRBNode.RBColor.BLACK;
					RBRightRotate(n.p);
					w = n.p.left;
				}
				
				if (w.right.color == StrRBNode.RBColor.BLACK && w.left.color == StrRBNode.RBColor.BLACK) { // caso 2
					w.color = StrRBNode.RBColor.RED;
					n = n.p;
				} else {
					
					if (w.left.color == StrRBNode.RBColor.BLACK) {
						
						w.right.color = StrRBNode.RBColor.BLACK;
						w.color = StrRBNode.RBColor.RED;
						RBLeftRotate(w);
						w = n.p.left;						
					}
					
					w.color = n.p.color;
					n.p.color = StrRBNode.RBColor.BLACK;
					w.left.color = StrRBNode.RBColor.BLACK;
					RBRightRotate(n.p);
					n = this.root;					
				}
				
			}
			
		}
		
		n.color = StrRBNode.RBColor.BLACK;
		
	}
	
	private StrRBNode RBTreeMinimum(StrRBNode n) {
				
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
			if (n.color == StrRBNode.RBColor.BLACK) {
				i++;
			}
			n = n.left;
		}		
		return i+1;
	}
	
	private void RBTransplant(StrRBNode u, StrRBNode v) {
		
		if (u.p.isNil()) {
			this.root = v;
		} else if (u == u.p.left) {
			u.p.left = v;
			
		} else {
			u.p.right = v;
		}
		
		StrRBNode.getRBNode(v).p = StrRBNode.getRBNode(u).p;		
		
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
