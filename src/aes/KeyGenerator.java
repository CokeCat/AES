
package aes;
import java.util.ArrayList;

/**
 * @author William
 */
public class KeyGenerator {
    
    private String[][] message= new String[4][4];
    private String[][] key= new String[4][4];
    private ArrayList<String[][]> roundKeys= new ArrayList();
    
    public String[][] createStateArray(String plaintext, String initialKey ) {
        //Iniciaación de la matriz de estado del texto a cifrar
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int ascii = (int)plaintext.charAt((4*i)+j);
                message[i][j] = Integer.toHexString(ascii);
            }
        }
        message = transposeMatrix(message);
        
        //Iniciación de la matriz de estado de la key inicial
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                key[i][j] = initialKey.substring(index, index+2);
                index += 2;
            }
        }
        
        return message;
    }
    
    public String toWordfromStateArray(String state) {
        String word = ""; 
        for (int i = 0; i < state.length(); i+=2) {
                int ascii = Integer.parseInt(state.substring(i, i+2), 16);
                word += Character.toString((char)ascii);
                System.out.println("123456 "+((char)ascii));
            }
        return word;
    }        
    
    public ArrayList<String[][]> createRoundkeys() {
        //llave inicial
        roundKeys.add(transposeMatrix(key));
        //ciclo de generación de las 10 round keys
        for (int i = 0; i < 10; i++) {
            
            String[][] currentRoundKey = new String[4][4];
            //ciclo de cada palabra para generar la round key
            /** --La 1ra fila tiene un procedimiento adicional a las otras
             *    1) se toma la última columna de la round key anterior y se usa como fila
             *    2) a esta fila se le hace una trasnposición, una sustitución, y una Xor con una constante binaria
             *    3) se hace una Xor final con la 1ra columna de la roundkey anterior
             *  --A las otras filas (2,3 y 4) sólo se aplica un Xor con la fila equivalente de la roundkey anterior
             */
             
            String word = getWordFromColumn(roundKeys.get(i), 3); //Última columna de la roundkey anterior
            word = rotWord(word);
            word = subWord(word);
            word = rConXor(word, i);
            word = previousWXor(word, roundKeys.get(i), 0);
            currentRoundKey = passWordToRow(word, currentRoundKey, 0);
            
            for (int j = 1; j < 4; j++) {
                word = previousWXor(word, roundKeys.get(i), j);
                currentRoundKey = passWordToRow(word, currentRoundKey, j);
            }
            
            roundKeys.add(transposeMatrix(currentRoundKey));
        }
        
        return roundKeys;
    }
    
    /*MÉTODOS INTERNOS DE LA GENERACIÓN*/
    private String[][] transposeMatrix(String[][] matrix) {
        String[][] result = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[j][i]= matrix[i][j];  
            }
        }
        return result;
    }
    
    private String getWordFromRow(String[][] matrix, int row) {
        String word = "";
        for (int i = 0; i<4; i++) 
            word += matrix[row][i];
        return word;
    }

    private String getWordFromColumn(String[][] matrix, int col) {
        String word = "";
        for (int i = 0; i<4; i++) 
            word += matrix[i][col];
        return word;
    }    
    
    private String rotWord(String word) {
        return word.substring(2,4)+word.substring(4,6)+word.substring(6)+word.substring(0,2);
    }
    
    private String subWord(String word) {
        String answer = "";
        for (int i = 0; i<8; i+=2) {
            answer+=Helper.sBox(word.substring(i, i+1), word.substring(i+1, i+2));
        }
        return answer;
    }
    
    private String rConXor(String word, int rcon) {
        String xorBinario = Helper.xor(Helper.toBinaryFromHexa(word), Helper.getriConstant(rcon));
        return Helper.toHexaFromBinary(xorBinario);
    }
    
    private String previousWXor(String word, String[][] matrix, int col) {
        String xorBinario = Helper.xor( Helper.toBinaryFromHexa(word), Helper.toBinaryFromHexa(getWordFromColumn(matrix, col)) );
        return Helper.toHexaFromBinary(xorBinario);
    }
    
    private String[][] passWordToRow(String word, String[][] matrix, int row) {
        for (int i = 0; i < 3; i++) {
            matrix[row][i] = word.substring(2*i,(2*i)+2);
        }
        matrix[row][3] = word.substring(6);
        return matrix;
    }
}
