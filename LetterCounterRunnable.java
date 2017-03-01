
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mayara
 */
public class LetterCounterRunnable implements Runnable{
    
    private Map<Integer,Integer> lista = new HashMap<Integer, Integer>();
    
    public LetterCounterRunnable(Map<Integer,Integer>lista){
        this.lista = lista;
    }

    @Override
    public void run() {
       
        PrintStream out = null;
        try {
            out = new PrintStream(System.out, true, "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LetterCounterRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Map.Entry<Integer, Integer> e : lista.entrySet()) {
        	int key;
                key = e.getKey().intValue();
        	if (key < 0){
                    out.println("Erro");
        	}        		       		
        	
        	if(key > 1000){
                    out.println("Letra: " + (char) (key - 65000) + " Quantidade: " + e.getValue() + " Key: " +  (key - 65300));        		                
        	} else {
        		out.println("Letra: " + (char) key + " Quantidade: " + e.getValue() + " Key: " +  key);
        	}
        }
    }
    
}
