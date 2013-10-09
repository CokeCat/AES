
package Test;

import aes.Helper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William
 */
public class Conversiones {
    
    public Conversiones() {
    }

    @Test
    public void deHexaaBinario() {
        String hexa1 = "ab211208";
        String bin1 = Helper.toBinaryFromHexa(hexa1);
        assertEquals("10101011001000010001001000001000", bin1);            
    }
    
    @Test
    public void deBinarioaHexa() {
        String bin1 = "10101011001000010001001000001000";
        String hexa1 = Helper.toHexaFromBinary(bin1);
        assertEquals("ab211208", hexa1);            
    }    
}