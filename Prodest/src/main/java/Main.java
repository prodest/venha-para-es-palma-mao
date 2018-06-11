
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import testprodest.cgt.Apl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ISM
 */
public class Main {
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException{
        
        Apl apl = new Apl();
        apl.leArquivo();
    }
    
}
