package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Pattern;

public class Geldbetrag
{
    /**
     * Der Euroanteil des Geldbetrags
     */
    private final int _betragEuro;

    /**
     * Der Centanteil des Geldbetrags
     */
    private final int _betragCent;

    /**
     * Ist der Geldbetrag ein zulässiger Wert
     */
    private boolean _zulaessig;

    /**
     * Getter Methode für das erzeugen eines neuen Geldgetrags mit getrennten Euro und Cent Beträgen
     * 
     * @param euro Eurobetrag des Geldbetrags
     * @param cent Centbetrag des Geldbetrags
     * @return das neu erzeugte Object von Geldbetrag
     * 
     * @require cent < 100
     * @ensure get(euro, cent) != null
     */
    public static Geldbetrag get(int euro, int cent)
    {
        assert cent < 100 : "Vorbedingung verletzt: cent < 100";

        return new Geldbetrag(euro, cent);
    }

    /**
     * Getter Methode für das erzeugen eines neuen Geldbetrags mit geminsamen Eurocentbetrag als String
     * 
     * @param euroKommaCent String für den Eurocentbetrag
     * @return das neu erzeugte Object von Geldbetrag
     * 
     * @require euroKommaCent != null
     * @ensure get(eurocent) != null
     */
    public static Geldbetrag get(String euroKommaCent)
    {
        assert euroKommaCent != null : "Vorbedingung verletzt: euroKommaCent != null";

        if (euroKommaCent.matches("\\d{2,5}[,]\\d{2}"))
        {
            Pattern p = Pattern.compile(",");

            String[] ec = p.split(euroKommaCent);

            return new Geldbetrag(Integer.parseInt(ec[0]),
                    Integer.parseInt(ec[1]));
        }
        else
        {
            return new Geldbetrag(0, 0, false);
        }
    }

    /**
     * Getter Methode für das erzeugen eines neuen Geldbetrags mit gemeinsamen Eurocentbetrag als String
     * 
     * @param eurocent Integer für den Eurocentbetrag
     * @return das neu erzeugte Object von Geldbetrag
     * 
     * @ensure get(eurocent) != null
     */
    public static Geldbetrag get(int eurocent)
    {
        int euro = eurocent / 100;
        int cent = eurocent - 100 * euro;

        return new Geldbetrag(euro, cent);
    }

    /**
     * privater Konstruktor der Klasse Geldbetrag 
     * @param euro Eurobetrag des Geldbetrags
     * @param cent Centbetrag des Geldbetrags
     */
    private Geldbetrag(int euro, int cent)
    {
        _betragEuro = euro;
        _betragCent = cent;
        _zulaessig = true;
    }

    private Geldbetrag(int euro, int cent, boolean b)
    {
        _betragEuro = euro;
        _betragCent = cent;
        _zulaessig = b;
    }

    /**
     * Wandelt den Geldbetrag in einen String um
     * 
     * @return den Geldbetrag als String
     * 
     * @ensure toString() != null
     */
    public String toString()
    {
        if (_betragCent < 10)
        {
            return _betragEuro + "," + "0" + _betragCent;
        }
        else
        {
            return _betragEuro + "," + _betragCent;
        }
    }

    /**
     * Addiert einen Geldbetrag mit einem anderen. 
     * 
     * @param x der zweite Geldbetrag der addiert werden soll
     * @return ein neu erzeugter Geldbetrag mit dem Wert der beiden addierten
     * 
     * @require x != null
     * @ensure add(x) != null
     */
    public Geldbetrag add(Geldbetrag x)
    {
        assert x != null : "Vorbedingung verletzt: x != null";

        int sum = toInt() + x.toInt();
        if (sum < toInt())
        {
            return get(Integer.MAX_VALUE, 99);
        }
        else
        {
            return get(sum);
        }
    }

    /**
     * Subtrahiert den zweiten Geldbetrag vom ersten
     * Falls der zweite Geldbetrag größer dem ersten sein sollte wird der absolute Wert des negativen Wertes genommen
     * 
     * @param x der zu subtrahierende Geldbetrag
     * @return ein neu erzeugter Geldbetrag mit dem Wert der beiden verrechneten
     * 
     * @require x != null
     * @ensure sub(x) != null
     */
    public Geldbetrag sub(Geldbetrag x)
    {
        assert x != null : "Vorbedingung verletzt: x != null";

        return get(Math.abs(toInt() - x.toInt()));
    }

    /**
     * Multipliziert den Geldbetrag mit einer Integerzahl
     * 
     * @param x die Integerzahl mit der multipliziert werden soll
     * @return ein neu erzeugter Geldbetrag mit dem Ergebnis der Rechnung
     * 
     * @require x > 0     
     * @ensure mul(x) != null
     */
    public Geldbetrag mul(int x)
    {
        assert x > 0 : "Vorbedingung verletzt: x > 0";

        int product = toInt() * x;
        if (product < toInt())
        {
            return get(Integer.MAX_VALUE, 99);
        }
        else
        {
            return get(product);
        }
    }

    /**
     * überprüft auf Gleichheit mit einem Objekt.
     * 
     * @param o ein Object mit dem verglichen werden soll
     * @return boolean-Wert, wenn die Objecte gleich sind, ist er true
     * 
     * @require o != null
     */
    public boolean equals(Object o)
    {
        assert o != null : "Vorbedingung verletzt: o != null";

        return (o instanceof Geldbetrag) && equals((Geldbetrag) o);
    }

    /**
     * überprüft zwei Geldbeträge auf Gleichheit
     * 
     * @param andererGeldbetrag der zu vergleichende Geldbetrag
     * @return boolean-Wert, wenn die Geldbeträge gleich sind, ist er true
     * 
     * @require andererGeldbetrag != null
     */
    public boolean equals(Geldbetrag andererGeldbetrag)
    {
        assert andererGeldbetrag != null : "Vorbedingung verletzt: andererGeldbetrag != null";

        return toInt() == andererGeldbetrag.toInt();
    }

    /**
     * erzeugt einen hashcode des Geldbetrags
     * @return den hashcode
     */
    public int hashcode()
    {
        return Integer.MAX_VALUE - toInt();
    }

    /**
     * überprüft ob der ersteBetrag größer-gleich dem zweiten ist
     * @param x der Geldbetrag mit dem verglichen werden soll
     * @return boolean-Wert, wenn der erste größer-gleich dem ersten ist, ist er true
     * 
     * @require x != null
     */
    public boolean greaterEqualsThan(Geldbetrag x)
    {
        assert x != null : "Vorbedingung verletzt: x != null";

        return toInt() >= x.toInt();
    }

    /**
     * erzeugt einen Integer-Wert des Geldbetrags im EuroCent-Muster
     * @return den Geldbetrag als Integer
     */
    public int toInt()
    {
        if (_betragEuro >= (Integer.MAX_VALUE - 100) / 100)
        {
            return Integer.MAX_VALUE;
        }
        else
        {
            return _betragCent + 100 * _betragEuro;
        }
    }

    public boolean getzulaessig()
    {
        return _zulaessig;
    }
}
