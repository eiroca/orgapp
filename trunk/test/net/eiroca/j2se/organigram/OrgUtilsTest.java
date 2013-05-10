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

import junit.framework.Assert;
import junit.framework.TestCase;

import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.util.OrgUtils;
import com.fbergeron.organigram.util.OrgUtils.OrganigramFormat;
import com.fbergeron.organigram.util.Utils;

/**
 * The Class OrgUtilsTest.
 */
public class OrgUtilsTest extends TestCase {

  /**
   * Test autodetect type.
   * 
   * @throws Exception the exception
   */
  public void testAutodetectType() throws Exception {
    Assert.assertTrue(OrgUtils.getType(null) == null);
    Assert.assertTrue(OrgUtils.getType(new URL("http://server/mydata.SoF")) == OrganigramFormat.SOF);
    Assert.assertTrue(OrgUtils.getType(new URL("http://server/mydata.SOF")) == OrganigramFormat.SOF);
    Assert.assertTrue(OrgUtils.getType(new URL("file://sitemap.xml")) == OrganigramFormat.SITEMAP);
    Assert.assertTrue(OrgUtils.getType(new URL("file:mydata.txt")) == OrganigramFormat.TXT);
    Assert.assertTrue(OrgUtils.getType(new URL("file:àààà.tXt")) == OrganigramFormat.TXT);
  }

  /**
   * Test load organigram.
   * 
   * @throws Exception the exception
   */
  public void testLoadOrganigram() throws Exception {
    Assert.assertTrue(OrgUtils.readOrganigram((URL) null, (OrganigramFormat) null) == null);
    Assert.assertTrue(OrgUtils.readOrganigram((URL) null, OrganigramFormat.SOF) == null);
    Assert.assertTrue(OrgUtils.readOrganigram(Utils.findResource("data/test001.in"), null) != null);
    Assert.assertTrue(OrgUtils.readOrganigram(Utils.findResource("data/test001.in"), OrganigramFormat.SOF) != null);
    Assert.assertTrue(OrgUtils.readOrganigram(Utils.findResource("data/test002.in"), OrganigramFormat.SITEMAP) != null);
    Assert.assertTrue(OrgUtils.readOrganigram(Utils.findResource("data/test003.in"), OrganigramFormat.TXT) != null);
  }

  /**
   * Check.
   * 
   * @param base the base
   * @param type the type
   * @throws Exception the exception
   */
  public void check(final String base, final OrganigramFormat type) throws Exception {
    final Organigram org = OrgUtils.readOrganigram(Utils.findResource("data/" + base + ".in"), type);
    final String actual1 = OrgUtils.writeOrganigram(org, OrganigramFormat.SOF, false).trim();
    final String actual2 = OrgUtils.writeOrganigram(org, OrganigramFormat.SITEMAP, false).trim();
    final String actual3 = OrgUtils.writeOrganigram(org, OrganigramFormat.TXT, false).trim();
    final String expected1 = Utils.readAsString(Utils.findResource("data/" + base + "a.out")).trim();
    final String expected2 = Utils.readAsString(Utils.findResource("data/" + base + "b.out")).trim();
    final String expected3 = Utils.readAsString(Utils.findResource("data/" + base + "c.out")).trim();
    Assert.assertEquals(expected1, actual1);
    Assert.assertEquals(expected2, actual2);
    Assert.assertEquals(expected3, actual3);
  }

  /**
   * Test check sof.
   * 
   * @throws Exception the exception
   */
  public void testCheckSOF() throws Exception {
    check("test001", OrganigramFormat.SOF);
  }

  /**
   * Test check site map.
   * 
   * @throws Exception the exception
   */
  public void testCheckSiteMap() throws Exception {
    check("test002", OrganigramFormat.SITEMAP);
  }

  /**
   * Test check txt.
   * 
   * @throws Exception the exception
   */
  public void testCheckTXT() throws Exception {
    check("test003", OrganigramFormat.TXT);
  }

}
