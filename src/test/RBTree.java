package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RBTree {
	
	public static void main(String[] args) throws IOException {
		
					
		FileReader freader = new FileReader(new File(args[0])); 
	    
		BufferedReader txt = new BufferedReader(freader);
	    
	    String linha = null;
	    String word;
	    int operation;
	    
	    StrRBTree dicionario = new StrRBTree();
	        
	    while ((linha = txt.readLine()) != null) {
	        
	    	word = linha.replaceAll("[0-9]", "").trim();
	    	operation = Integer.parseInt(linha.replaceAll("[^0-9]", ""));
	    	
	    	if (operation == 1) {
	    		dicionario.RBInsert(word);
	    	} else if (operation == 0) {
	    		dicionario.RBDelete(word);
	    	}
	    		    	
	    }	   
	    
	    dicionario.RBCheck(dicionario.root);	    
	    dicionario.RBPrint(dicionario.root);
	    
	}

}
