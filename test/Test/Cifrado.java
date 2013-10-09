
package Test;

import aes.Cipher;
import aes.KeyGenerator;
import aes.Helper;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William
 */
public class Cifrado {
    
    public Cifrado() {}
    
    private String[][]  message;
    private KeyGenerator generador;
    private ArrayList<String[][]> roundKeys;
    private Cipher prueba;
        
    @Before
    public void setUp() {
        message= new String[4][4];
        generador = new KeyGenerator();
        generador.createStateArray("AES es muy facil", "2b7e151628aed2a6abf7158809cf4f3c");
        roundKeys = generador.createRoundkeys();
        prueba = new Cipher(roundKeys, message);        
    }

    @Test
    public void pruebaARK() {
        String[][] state = 
        {{"41","65","75","61"},
         {"45","73","79","63"},
         {"53","20","20","69"},
         {"20","6d","66","6c"}};
        String[][] roundKey = 
        {{"2b","28","ab","09"},
         {"7e","ae","f7","cf"},
         {"15","d2","15","4f"},
         {"16","a6","88","3c"}};
        String[][] answer = 
        {{"6a","4d","de","68"},
         {"3b","dd","8e","ac"},
         {"46","f2","35","26"},
         {"36","cb","ee","50"}};
        
        assertArrayEquals(answer, prueba.ARK(state, roundKey));
    }
    
    @Test
    public void pruebaSB() {
        String[][] state = 
        {{"6a","4d","de","68"},
         {"3b","dd","8e","ac"},
         {"46","f2","35","26"},
         {"36","cb","ee","50"}};
        
        String[][] answer = 
        {{"02","e3","1d","45"},
         {"e2","c1","19","91"},
         {"5a","89","96","f7"},
         {"05","1f","28","53"}};
        
        assertArrayEquals(answer, prueba.SB(state));
    }
    
    @Test
    public void pruebaSR() {
        String[][] state = 
        {{"02","e3","1d","45"},
         {"e2","c1","19","91"},
         {"5a","89","96","f7"},
         {"05","1f","28","53"}};
        
        String[][] answer = 
        {{"02","e3","1d","45"},
         {"c1","19","91","e2"},
         {"96","f7","5a","89"},
         {"53","05","1f","28"}};        
        
        assertArrayEquals(answer, prueba.SR(state)); 
    }
    
    @Test
    public void pruebaInvSR() { 
        String[][] state = 
        {{"02","e3","1d","45"},
         {"c1","19","91","e2"},
         {"96","f7","5a","89"},
         {"53","05","1f","28"}};    
        
        String[][] answer = 
        {{"02","e3","1d","45"},
         {"e2","c1","19","91"},
         {"5a","89","96","f7"},
         {"05","1f","28","53"}};
            
        assertArrayEquals(answer, prueba.invSR(state)); 
    }    
    
    @Test 
    public void pruebaMultiplicacionGF() {
        assertEquals("66", Helper.multiplyGF("6a", "97"));
    }
    
    @Test
    public void pruebaMC() {
        /*
        String[][] state = 
        {{"02","e3","1d","45"},
         {"c1","19","91","e2"},
         {"96","f7","5a","89"},
         {"53","05","1f","28"}};  
        
        String[][] answer =
        {{"99","04","d7","16"},
        {"69","d6","d5","32"},
        {"01","00","19","d6"},
        {"f7","da","d2","f4"}};*/
        
        String[][] answer =
        {{"99","04","d7","16"},
         {"69","d6","d5","32"},
         {"01","00","19","d6"},
         {"f7","da","d2","f4"}};
        String[][] state =
        {{"02","e3","1d","45"},
         {"c1","19","91","e2"},
         {"96","f7","5a","89"},
         {"53","05","1f","28"}};           
        
        assertArrayEquals(answer, prueba.MC(state)); 
    } 
    
    @Test
    public void pruebaInvMC() {   
        
//        String[][] state =
//        {{"a8","70","63","34"},
//         {"71","64","8f","2e"},
//         {"97","b5","e9","0a"},
//         {"7a","0c","2b","fd"}};
//        
//        String[][] answer =
//        {{"72","d3","fb","3c"},
//         {"05","d4","a0","c3"},
//         {"25","57","2d","c3"},
//         {"66","fd","58","d1"}};
        
        String[][] state =
        {{"99","04","d7","16"},
         {"69","d6","d5","32"},
         {"01","00","19","d6"},
         {"f7","da","d2","f4"}};
        String[][] answer =
        {{"02","e3","1d","45"},
         {"c1","19","91","e2"},
         {"96","f7","5a","89"},
         {"53","05","1f","28"}};        
        
        assertArrayEquals(answer, prueba.invMC(state)); 
    }     
}