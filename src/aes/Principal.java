/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;

import java.util.ArrayList;

/**
 *
 * @author William
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        KeyGenerator prueba = new KeyGenerator();
        String[][] message = prueba.createStateArray("AES es muy facil", "2b7e151628aed2a6abf7158809cf4f3c");
        ArrayList<String[][]> roundKeys = prueba.createRoundkeys();
        
        Cipher cipher = new Cipher(roundKeys, message);
        cipher.cipher();
        cipher.decipher();
        //Ventana ventana = new Ventana();
        //ventana.setVisible(true);
    }
}
