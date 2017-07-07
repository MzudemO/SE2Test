package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GeldbetragTest
{   
    @Test
    public void testeEqualsUndHashcode()
    {
        Geldbetrag x = Geldbetrag.get(20, 0);
        Geldbetrag y = Geldbetrag.get(20, 0);
        Geldbetrag z = Geldbetrag.get(10,0);
        
        assertTrue(x.equals(y));
        assertFalse(x.equals(z));
        
        assertEquals(x.hashcode(), y.hashcode());
    }
    
    @Test
    public void testeAdd()
    {
        Geldbetrag x = Geldbetrag.get(20, 0);
        Geldbetrag y = Geldbetrag.get(10, 0);
        
        assertEquals(x.add(y), 30);
         
        Geldbetrag v = Geldbetrag.get(20, 50);
        Geldbetrag w = Geldbetrag.get(10, 50);
        
        assertEquals(v.add(w), 31);
        }
    
    @Test
    public void testeSub()
    {
        Geldbetrag x = Geldbetrag.get(20, 0);
        Geldbetrag y = Geldbetrag.get(10, 0);
        
        assertEquals(x.sub(y), 10);
        assertEquals(y.sub(x), 10);
        
        Geldbetrag v = Geldbetrag.get(20, 50);
        Geldbetrag w = Geldbetrag.get(10, 70);
        Geldbetrag u = Geldbetrag.get(9, 80);
        
        assertTrue(v.sub(w).equals(u));
        
    }
    
    @Test
    public void testeMul()
    {
        Geldbetrag x = Geldbetrag.get(20, 0);
        Geldbetrag y = Geldbetrag.get(10, 0);
        
        assertEquals(x.mul(y), 200);     
    }
    
    @Test
    public void testeKommaAkzeptanz()
    {
        Geldbetrag x = Geldbetrag.get("10,57");
        Geldbetrag y = Geldbetrag.get(10, 57);
        
        assertEquals(x, y);
    }
    
    @Test
    public void testeGreaterEqualsThan()
    {
        Geldbetrag x = Geldbetrag.get(15, 0);
        Geldbetrag y = Geldbetrag.get(20, 0);
        
        assertTrue(x.greaterEqualsThan(y));
        assertFalse(y.greaterEqualsThan(x));
        
        Geldbetrag z = Geldbetrag.get(15, 0);
        
        assertTrue(x.greaterEqualsThan(z));
    }
    
    
}
