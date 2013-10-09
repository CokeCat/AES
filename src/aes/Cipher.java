
package aes;

import java.util.ArrayList;


public class Cipher {
    
    private ArrayList<String[][]> roundKeys;
    private String[][] message;
    private String[][] cipherText;
    private String[][] plainText;

    public Cipher(ArrayList<String[][]> roundKeys, String[][] message) {
        this.roundKeys = roundKeys;
        this.message = message;
        
        //imprimir(message, "Mensaje original");
    }
    
    public String cipher(){
        String[][] cipherText = new String[4][4];
        /*RONDA INICIAL = = = = = = */
        cipherText = ARK(this.message, roundKeys.get(0));         
        /*RONDAS 1--9 = = = = = = = */
        for (int i = 1; i < 10; i++) {
            cipherText = SB(cipherText);
            cipherText = SR(cipherText);
            cipherText = MC(cipherText);  
            cipherText = ARK(cipherText, roundKeys.get(i));
        }
        /*RONDA 10 = = = = = = = = = */
        cipherText = SB(cipherText);
        cipherText = SR(cipherText);
        cipherText = ARK(cipherText, roundKeys.get(10)); 
        
        this.cipherText = cipherText;
        //imprimir(cipherText, "cifrado final");
        
        String ans="";
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                  ans += cipherText[i][j];
            }   
        }        
        return ans;
    }
    
    public String decipher(){
        String[][] plainText = new String[4][4];
        /*RONDA INICIAL = = = = = = */
        plainText = ARK(this.cipherText, roundKeys.get(10));         
        plainText = invSB(plainText);
        plainText = invSR(plainText);
        /*RONDAS 1--9 = = = = = = = */
        for (int i = 9; i > 0; i--) {
            plainText = ARK(plainText, roundKeys.get(i));
            plainText = invMC(plainText);
            plainText = invSB(plainText);
            plainText = invSR(plainText);
        }
        /*RONDA 10 = = = = = = = = = */
        plainText = ARK(plainText, roundKeys.get(0)); 
        
        this.plainText = plainText;
        //imprimir(plainText, "descifrado final");
        
        //se pasa la matriz a un string
        String ans="";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                  ans+= plainText[j][i];
            }   
        }
        return ans;
    }    
    
    public void imprimir(String[][] cipherText, String titulo) {
        System.out.println("=============================");
        System.out.println(titulo+":");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(" "+cipherText[i][j]);
            }
            System.out.println("");
        } 
        System.out.println("");
    }
    
    /*MÉTODOS PARA CIFRAR*/
    public String[][] ARK(String[][] state, String[][] roundKey) {
        String[][] ark = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        String xorBinario;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                xorBinario = Helper.xor(Helper.toBinaryFromHexa(state[i][j]), Helper.toBinaryFromHexa(roundKey[i][j]));
                ark[i][j] = Helper.toHexaFromBinary(xorBinario);
            } 
        }
        return ark;
    }
    
    public String [][] SB(String [][] state) {
        String[][] sb = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb[i][j] = Helper.sBox(state[i][j].substring(0, 1), state[i][j].substring(1));
            } 
        }        
        return sb;
    }
    
    public String [][] SR(String [][] state) {
        String[][] sr = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        int col;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                col = Math.abs((j+i)%4);
                sr[i][j] = state[i][col];
            } 
        }        
        return sr;
    }    
    
    public String [][] MC(String [][] state) {
        String[][] mc = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        String acum;
        String  gf;
        //Los 1ros 2 ciclos recorrerarn cada una de las celdas
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                //El 3er ciclo se usa para realizar la multiplicación GF en la celda ij
                acum = Helper.toBinaryFromHexa(Helper.multiplyGF(state[0][j], Helper.MCM(i,0)) );
                for (int k = 1; k < 4; k++) {
                    gf = Helper.multiplyGF( state[k][j], Helper.MCM(i,k));
                    acum = Helper.xor(acum, Helper.toBinaryFromHexa(gf) );
                }
                mc[i][j] = Helper.toHexaFromBinary(acum);
            }  
        }
        return mc;
    }
  
    /*MÉTODOS PARA DESCIFRAR*/
    public String [][] invSB(String [][] state) {
        String[][] sb = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb[i][j] = Helper.invSBox(state[i][j].substring(0, 1), state[i][j].substring(1));
            } 
        }        
        return sb;
    }  
    
    public String [][] invSR(String [][] state) {
        String[][] sr = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        int col;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                col = (4+j-i)%4;
                sr[i][j] = state[i][col];
            } 
        }        
        return sr;
    }   
    
    public String [][] invMC(String [][] state) {
        String[][] mc = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        String acum;
        String  gf;
        //Los 1ros 2 ciclos recorrerarn cada una de las celdas
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                //El 3er ciclo se usa para realizar la multiplicación GF en la celda ij
                acum = Helper.toBinaryFromHexa(Helper.multiplyGF(state[0][j], Helper.invMCM(i,0)) );
                for (int k = 1; k < 4; k++) {
                    gf = Helper.multiplyGF( state[k][j], Helper.invMCM(i,k));
                    acum = Helper.xor(acum, Helper.toBinaryFromHexa(gf) );
                }
                mc[i][j] = Helper.toHexaFromBinary(acum);
            }  
        }
        
        
        //imprimir(cipherText, "invMC * * * * * * * *");
        return mc;
    }    
}
