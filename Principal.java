
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Mayara
 */
public class Principal {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
     
        
    List<Integer> list = new ArrayList<Integer>();
    Map<Integer,Integer> lista = new HashMap<Integer,Integer>();
    LetterCounterRunnable ltr = new LetterCounterRunnable(lista);
   
    final long duration;
    final long startTime = System.nanoTime();
    //read all files in a directory    
    try(Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\Mayara\\Documents\\Producao")).parallel()) {
    paths.forEach(new Consumer<Path>() {
        @Override
        public void accept(Path filePath) {
            File file = new File(filePath.toString());
            try {
                if(!file.isDirectory()){
                    final FileReader fileReader = new FileReader(file);
                    BufferedReader bf = new BufferedReader(fileReader);
                    
                    String line = null;
                       
                    while ((line = bf.readLine()) !=null)
                    {
                        char c;
                        
                        for (int i=0; i<line.length(); i++){
                            c = line.charAt(i);
                            c = Character.toLowerCase(c);
                            int value = lista.getOrDefault(i, 0);
                            lista.put(i, value+ 1);
                            
                        }
                       
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    });  
    
        int tamanho = lista.size();
        System.out.println(tamanho);
         Thread th1, th2;
         th1 = new Thread(ltr);
         th2 = new Thread(ltr);
         th1.start();
         th2.start();
         th1.join();
         th2.join();
        final long endTime = System.nanoTime();
        System.out.println(duration= (endTime - startTime)/1000000);
        
        
       
        
    }
    }
}

    

