
package aes;

import sun.org.mozilla.javascript.internal.regexp.SubString;

/**
 * @author William
 */
public class Helper {
    
    private final static String [][] sBoxMatrix= 
    {{"63","7c","77","7b","f2","6b","6f","c5","30","01","67","2b","fe","d7","ab","76"},
    {"ca","82","c9","7d","fa","59","47","f0","ad","d4","a2","af","9c","a4","72","c0"}, 
    {"b7","fd","93","26","36","3f","f7","cc","34","a5","e5","f1","71","d8","31","15"},
    {"04","c7","23","c3","18","96","05","9a","07","12","80","e2","eb","27","b2","75"}, 
    {"09","83","2c","1a","1b","6e","5a","a0","52","3b","d6","b3","29","e3","2f","84"}, 
    {"53","d1","00","ed","20","fc","b1","5b","6a","cb","be","39","4a","4c","58","cf"}, 
    {"d0","ef","aa","fb","43","4d","33","85","45","f9","02","7f","50","3c","9f","a8"}, 
    {"51","a3","40","8f","92","9d","38","f5","bc","b6","da","21","10","ff","f3","d2"},
    {"cd","0c","13","ec","5f","97","44","17","c4","a7","7e","3d","64","5d","19","73"},
    {"60","81","4f","dc","22","2a","90","88","46","ee","b8","14","de","5e","0b","db"},
    {"e0","32","3a","0a","49","06","24","5c","c2","d3","ac","62","91","95","e4","79"},
    {"e7","c8","37","6d","8d","d5","4e","a9","6c","56","f4","ea","65","7a","ae","08"}, 
    {"ba","78","25","2e","1c","a6","b4","c6","e8","dd","74","1f","4b","bd","8b","8a"}, 
    {"70","3e","b5","66","48","03","f6","0e","61","35","57","b9","86","c1","1d","9e"}, 
    {"e1","f8","98","11","69","d9","8e","94","9b","1e","87","e9","ce","55","28","df"}, 
    {"8c","a1","89","0d","bf","e6","42","68","41","99","2d","0f","b0","54","bb","16"}};
    
    public static String sBox(String fil, String col) {
        int i = Integer.parseInt(fil, 16);
        int j = Integer.parseInt(col, 16);
        return sBoxMatrix[i][j];
    } 
    
    private final static String [][] invSBoxMatrix=
   {{"52","09","6a","d5","30","36","a5","38","bf","40","a3","9e","81","f3","d7","fb"}, 
    {"7c","e3","39","82","9b","2f","ff","87","34","8e","43","44","c4","de","e9","cb"},
    {"54","7b","94","32","a6","c2","23","3d","ee","4c","95","0b","42","fa","c3","4e"},
    {"08","2e","a1","66","28","d9","24","b2","76","5b","a2","49","6d","8b","d1","25"},
    {"72","f8","f6","64","86","68","98","16","d4","a4","5c","cc","5d","65","b6","92"},
    {"6c","70","48","50","fd","ed","b9","da","5e","15","46","57","a7","8d","9d","84"},
    {"90","d8","ab","00","8c","bc","d3","0a","f7","e4","58","05","b8","b3","45","06"},
    {"d0","2c","1e","8f","ca","3f","0f","02","c1","af","bd","03","01","13","8a","6b"}, 
    {"3a","91","11","41","4f","67","dc","ea","97","f2","cf","ce","f0","b4","e6","73"},
    {"96","ac","74","22","e7","ad","35","85","e2","f9","37","e8","1c","75","df","6e"}, 
    {"47","f1","1a","71","1d","29","c5","89","6f","b7","62","0e","aa","18","be","1b"},
    {"fc","56","3e","4b","c6","d2","79","20","9a","db","c0","fe","78","cd","5a","f4"}, 
    {"1f","dd","a8","33","88","07","c7","31","b1","12","10","59","27","80","ec","5f"},
    {"60","51","7f","a9","19","b5","4a","0d","2d","e5","7a","9f","93","c9","9c","ef"},
    {"a0","e0","3b","4d","ae","2a","f5","b0","c8","eb","bb","3c","83","53","99","61"},
    {"17","2b","04","7e","ba","77","d6","26","e1","69","14","63","55","21","0c","7d"}};
   
    public static String invSBox(String fil, String col) {
        int i = Integer.parseInt(fil, 16);
        int j = Integer.parseInt(col, 16);
        return invSBoxMatrix[i][j];
    }    
    
    private final static String [][] ETableMatrix= 
    {{"01","03","05","0F","11","33","55","FF","1A","2E","72","96","A1","F8","13","35"},
     {"5F","E1","38","48","D8","73","95","A4","F7","02","06","0A","1E","22","66","AA"},
     {"E5","34","5C","E4","37","59","EB","26","6A","BE","D9","70","90","AB","E6","31"},
     {"53","F5","04","0C","14","3C","44","CC","4F","D1","68","B8","D3","6E","B2","CD"},
     {"4C","D4","67","A9","E0","3B","4D","D7","62","A6","F1","08","18","28","78","88"},
     {"83","9E","B9","D0","6B","BD","DC","7F","81","98","B3","CE","49","DB","76","9A"},
     {"B5","C4","57","F9","10","30","50","F0","0B","1D","27","69","BB","D6","61","A3"},
     {"FE","19","2B","7D","87","92","AD","EC","2F","71","93","AE","E9","20","60","A0"},
     {"FB","16","3A","4E","D2","6D","B7","C2","5D","E7","32","56","FA","15","3F","41"},
     {"C3","5E","E2","3D","47","C9","40","C0","5B","ED","2C","74","9C","BF","DA","75"},
     {"9F","BA","D5","64","AC","EF","2A","7E","82","9D","BC","DF","7A","8E","89","80"},
     {"9B","B6","C1","58","E8","23","65","AF","EA","25","6F","B1","C8","43","C5","54"},
     {"FC","1F","21","63","A5","F4","07","09","1B","2D","77","99","B0","CB","46","CA"},
     {"45","CF","4A","DE","79","8B","86","91","A8","E3","3E","42","C6","51","F3","0E"},
     {"12","36","5A","EE","29","7B","8D","8C","8F","8A","85","94","A7","F2","0D","17"},
     {"39","4B","DD","7C","84","97","A2","FD","1C","24","6C","B4","C7","52","F6","01"}};

    public static String ETable(String fil, String col) {
        int i = Integer.parseInt(fil, 16);
        int j = Integer.parseInt(col, 16);
        return ETableMatrix[i][j];
    }
    
    private final static String [][] LTableMatrix= 
    {{"00","00","19","01","32","02","1A","C6","4B","C7","1B","68","33","EE","DF","03"},
     {"64","04","E0","0E","34","8D","81","EF","4C","71","08","C8","F8","69","1C","C1"},
     {"7D","C2","1D","B5","F9","B9","27","6A","4D","E4","A6","72","9A","C9","09","78"},
     {"65","2F","8A","05","21","0F","E1","24","12","F0","82","45","35","93","DA","8E"},
     {"96","8F","DB","BD","36","D0","CE","94","13","5C","D2","F1","40","46","83","38"},
     {"66","DD","FD","30","BF","06","8B","62","B3","25","E2","98","22","88","91","10"},
     {"7E","6E","48","C3","A3","B6","1E","42","3A","6B","28","54","FA","85","3D","BA"},
     {"2B","79","0A","15","9B","9F","5E","CA","4E","D4","AC","E5","F3","73","A7","57"},
     {"AF","58","A8","50","F4","EA","D6","74","4F","AE","E9","D5","E7","E6","AD","E8"},
     {"2C","D7","75","7A","EB","16","0B","F5","59","CB","5F","B0","9C","A9","51","A0"},
     {"7F","0C","F6","6F","17","C4","49","EC","D8","43","1F","2D","A4","76","7B","B7"},
     {"CC","BB","3E","5A","FB","60","B1","86","3B","52","A1","6C","AA","55","29","9D"},
     {"97","B2","87","90","61","BE","DC","FC","BC","95","CF","CD","37","3F","5B","D1"},
     {"53","39","84","3C","41","A2","6D","47","14","2A","9E","5D","56","F2","D3","AB"},
     {"44","11","92","D9","23","20","2E","89","B4","7C","B8","26","77","99","E3","A5"},
     {"67","4A","ED","DE","C5","31","FE","18","0D","63","8C","80","C0","F7","70","07"}};
    
    public static String LTable(String fil, String col) {
        int i = Integer.parseInt(fil, 16);
        int j = Integer.parseInt(col, 16);
        return LTableMatrix[i][j];
    }    
    
    private final static String riConstant[] =
    {"00000001000000000000000000000000", "00000010000000000000000000000000",
     "00000100000000000000000000000000", "00001000000000000000000000000000",
     "00010000000000000000000000000000", "00100000000000000000000000000000",
     "01000000000000000000000000000000", "10000000000000000000000000000000",
     "00011011000000000000000000000000", "00110110000000000000000000000000"};
    
    public static String getriConstant(int i) {
        return riConstant[i];
    }

    private final static String [][] MCMatrix =
    {{"02","03","01","01"},
     {"01","02","03","01"},
     {"01","01","02","03"},
     {"03","01","01","02"}};
    
    public static String MCM(int i, int j) {
        return MCMatrix[i][j];
    }
    
    private final static String [][] invMCMatrix =
    {{"0e","0b","0d","09"},
     {"09","0e","0b","0d"},
     {"0d","09","0e","0b"},
     {"0b","0d","09","0e"}};
    
    public static String invMCM(int i, int j) {
        return invMCMatrix[i][j];
    }    
    
    public static String toBinaryFromHexa(String hexa) {
        String binary = "";
        int decimal;
        for (int i = 0; i < hexa.length(); i+=2) {
            decimal = Integer.parseInt(hexa.substring(i, i+2), 16);
            String auxBinary = Integer.toBinaryString(decimal);
            //para números menores hay que insertar ceros suficientes para
            //tener 8 dígitos binarios por 2 hexadecimales
            for (int j = 0; j < 8-auxBinary.length(); j++) 
                binary += "0";
            
            binary += auxBinary;
            
        }
        return binary;
    }
    
    public static String toHexaFromBinary(String binary) {
        String hexa = "";
        int decimal;
        for (int i = 0; i < binary.length(); i+=8) {  
            decimal = Integer.parseInt(binary.substring(i, i+8), 2);
            if(decimal<16)
                hexa += "0";
            hexa += Integer.toHexString(decimal);
        }
        return hexa;
    }
    
    public static String xor(String a, String b) {
        String answer = "";
        for (int i = 0; i < a.length(); i++) {
            answer += ( a.substring(i,i+1).equals( b.substring(i,i+1)) )? "0":"1";
        }
        return answer;
    }
    
    public static String multiplyGF (String a, String b) {   
        
        if(a.equals("00") || b.equals("00")) return "00";
        
        String aL = Helper.LTable(a.substring(0, 1), a.substring(1));
        String bL = Helper.LTable(b.substring(0, 1), b.substring(1));
        //System.out.println("        **aL="+aL+"  bL="+bL);
          
        int decimal = Integer.parseInt(aL, 16) + Integer.parseInt(bL, 16);
        decimal %= 255;
        //System.out.println("        **decimal="+decimal);
        
        String hexa = Integer.toHexString(decimal);
        if(decimal<16) hexa = "0"+hexa;
        //System.out.println("        **llava con la que buscaremos en ET="+hexa);
        
        
        
        return Helper.ETable(hexa.substring(0, 1), hexa.substring(1));
    }
    
    public static String[][] transposeMatrix(String[][] matrix) {
        String[][] result = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[j][i]= matrix[i][j];  
            }
        }
        return result;
    }    

}
