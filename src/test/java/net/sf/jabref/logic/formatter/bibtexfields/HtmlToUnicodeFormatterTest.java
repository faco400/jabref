package net.sf.jabref.logic.formatter.bibtexfields;

import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlToUnicodeFormatterTest {

    private final HtmlToUnicodeFormatter formatter = new HtmlToUnicodeFormatter();

    @Before
    public void setUp() throws Exception {
        Globals.prefs = JabRefPreferences.getInstance();
    }

    @Test
    public void formatWithoutHtmlCharactersReturnsSameString() {
        assertEquals("abc", formatter.format("abc"));
    }

    @Test
    public void formatMultipleHtmlCharacters() {
        assertEquals("åäö", formatter.format("&aring;&auml;&ouml;"));
    }

    @Test
    public void formatCombinedAccent() {
        assertEquals("í", formatter.format("i&#x301;"));
    }

    @Test
    public void testBasic() {
        assertEquals("aaa", formatter.format("aaa"));
    }

    @Test
    public void testHTML() {
        assertEquals("ä", formatter.format("&auml;"));
        assertEquals("ä", formatter.format("&#228;"));
        assertEquals("ä", formatter.format("&#xe4;"));
        assertEquals("Ε", formatter.format("&Epsilon;"));
    }

    @Test
    public void testHTMLRemoveTags() {
        assertEquals("aaa", formatter.format("<p>aaa</p>"));
    }

    @Test
    public void formatExample() {
        assertEquals("bread & butter", formatter.format(formatter.getExampleInput()));
    }
}


