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
        
        assertEquals(x.add(y), Geldbetrag.get(30,0));
         
        Geldbetrag v = Geldbetrag.get(20, 50);
        Geldbetrag w = Geldbetrag.get(10, 50);
        
        assertEquals(v.add(w), Geldbetrag.get(31,0));
        
        Geldbetrag q = Geldbetrag.get(Integer.MAX_VALUE - 2, 0);
        Geldbetrag r = Geldbetrag.get(4, 0);
        
        assertEquals(q.add(r), Geldbetrag.get(Integer.MAX_VALUE, 99));

        }
    
    @Test
    public void testeSub()
    {
        Geldbetrag x = Geldbetrag.get(20, 0);
        Geldbetrag y = Geldbetrag.get(10, 0);
        
        assertEquals(x.sub(y), Geldbetrag.get(10,0));
        assertEquals(y.sub(x), Geldbetrag.get(10,0));
        
        Geldbetrag v = Geldbetrag.get(20, 50);
        Geldbetrag w = Geldbetrag.get(10, 70);
        Geldbetrag u = Geldbetrag.get(9, 80);
        
        assertTrue(v.sub(w).equals(u));
        
    }
    
    @Test
    public void testeMul()
    {
        Geldbetrag x = Geldbetrag.get(20, 0);
        assertEquals(x.mul(10), Geldbetrag.get(200,0));
        
        Geldbetrag z = Geldbetrag.get(0,20);
        assertEquals(z.mul(10), Geldbetrag.get(2,0));
        
        Geldbetrag y = Geldbetrag.get(Integer.MAX_VALUE - 2, 99);
        assertEquals(y.mul(10), Geldbetrag.get(Integer.MAX_VALUE, 99));     
    }
    
    @Test
    public void testeKommaAkzeptanz()
    {
        Geldbetrag x = Geldbetrag.get("10,57");
        Geldbetrag y = Geldbetrag.get(10, 57);
        assertEquals(x, y);
        
        Geldbetrag z = Geldbetrag.get("a0,527");
        assertFalse(z.getzulaessig());
        
    }
    
    @Test
    public void testeGreaterEqualsThan()
    {
        Geldbetrag x = Geldbetrag.get(15, 0);
        Geldbetrag y = Geldbetrag.get(20, 0);
        
        assertFalse(x.greaterEqualsThan(y));
        assertTrue(y.greaterEqualsThan(x));
        
        Geldbetrag z = Geldbetrag.get(15, 0);
        
        assertTrue(x.greaterEqualsThan(z));
    }
    
    @Test
    public void testeToString()
    {
        Geldbetrag x = Geldbetrag.get(15,10);
        assertEquals(x.toString(), "15,10");

        Geldbetrag y = Geldbetrag.get(15,1);
        assertEquals(y.toString(), "15,01");
    }
    
    
}
