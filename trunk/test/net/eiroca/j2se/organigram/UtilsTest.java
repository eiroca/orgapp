/** GPL >= 3.0
 *
 * Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 */
package net.eiroca.j2se.organigram;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;
import com.fbergeron.organigram.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilsTest.
 */
public class UtilsTest extends TestCase {

  /**
   * The Enum EIROCA.
   */
  enum EIROCA {
    Enrico, Simona
  }

  /**
   * Test add enum.
   * 
   * @throws Exception the exception
   */
  public void testAddEnum() throws Exception {
    final Map<String, EIROCA> set = new HashMap<String, EIROCA>();
    Utils.addEnum(null, null);
    Utils.addEnum(null, EIROCA.Enrico);
    Utils.addEnum(set, EIROCA.Enrico);
    Utils.addEnum(set, EIROCA.Simona);
    Assert.assertTrue(set.get("enrico") == EIROCA.Enrico);
    Assert.assertTrue(set.get("0") == EIROCA.Enrico);
    Assert.assertTrue(set.get("e") == EIROCA.Enrico);
    Assert.assertTrue(set.get("simona") == EIROCA.Simona);
    Assert.assertTrue(set.get("1") == EIROCA.Simona);
    Assert.assertTrue(set.get("s") == EIROCA.Simona);
    Assert.assertTrue(set.get("2") == null);
  }

  /**
   * Test build url.
   * 
   * @throws Exception the exception
   */
  public void testBuildURL() throws Exception {
    final String url1 = "http://www.eiroca.net";
    final String url2 = "mobile";
    Assert.assertEquals(null, Utils.buildURL(null, null));
    Assert.assertEquals(url1, Utils.buildURL(null, url1).toString());
    Assert.assertEquals(url1 + "/" + url2, Utils.buildURL(new URL(url1), url2).toString());
  }

  /**
   * Test encode XML.
   * 
   * @throws Exception the exception
   */
  public void testEncodeXML() throws Exception {
    StringBuffer buf = new StringBuffer(256);
    Utils.encodeXMLchars(null, null, false);
    Utils.encodeXMLchars(null, "A", false);
    Utils.encodeXMLchars(buf, null, false);
    Utils.encodeXMLchars(buf, "B", false);
    buf = new StringBuffer(256);
    Utils.encodeXMLchars(buf, "\teIrOcA", false);
    Utils.encodeXMLchars(buf, "\teIrOcA", true);
    Utils.encodeXMLchars(buf, " \"\'<>&amp;", true);
    Assert.assertEquals("\teIrOcA eIrOcA &quot;&#39;&lt;&gt;&amp;amp;", buf.toString());
  };

  /**
   * Test find resource.
   * 
   * @throws Exception the exception
   */
  public void testFindResource() throws Exception {
    Assert.assertEquals(null, Utils.findResource("xyz123abc"));
    URL res = Utils.findResource("data/Utils.txt");
    Assert.assertTrue(res != null);
    Assert.assertTrue(res.toString().startsWith("file:"));
    Assert.assertTrue(res.toString().endsWith("Utils.txt"));
    res = Utils.findResource("AllTests.class");
    Assert.assertTrue(res != null);
  }

  public void testReadAsString() throws Exception {
    final StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 7000; i++) {
      buf.append('0');
    }

    Assert.assertTrue(Utils.readAsString(null) == null);
    Assert.assertTrue(Utils.readAsString(Utils.findResource("dsfasdf")) == null);
    Assert.assertEquals(buf.toString(), Utils.readAsString(Utils.findResource("data/Utils.txt")));
  }

  /**
   * Test val.
   * 
   * @throws Exception the exception
   */
  public void testVal() throws Exception {
    final int def = 1;
    Assert.assertEquals(def, Utils.val(null, def));
    Assert.assertEquals(def, Utils.val(Integer.toString(def), def));
    Assert.assertEquals(def, Utils.val("2.3", def));
    Assert.assertEquals(def, Utils.val("a", def));
    Assert.assertTrue(def != Utils.val(Integer.toString(def + 1), def));
  }

  /**
   * Test write 2 Hex chars minimum number.
   * 
   * @throws Exception the exception
   */
  public void testWriteHH() throws Exception {
    final StringBuffer buf = new StringBuffer(256);
    Utils.writeHH(null, 0);
    Utils.writeHH(buf, 0);
    Utils.writeHH(buf, 1);
    Utils.writeHH(buf, 10);
    Utils.writeHH(buf, 16);
    Utils.writeHH(buf, 255);
    Utils.writeHH(buf, 256);
    Assert.assertEquals("00010a10ff100", buf.toString());
  }

}
