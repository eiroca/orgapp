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
import com.fbergeron.organigram.util.OrgUtils;
import com.fbergeron.organigram.util.OrgUtils.OrganigramFormat;

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
    Assert.assertTrue(OrgUtils.getType(new URL("http://server/mydata.SoF")) == OrganigramFormat.SOF);
    Assert.assertTrue(OrgUtils.getType(new URL("http://server/mydata.SOF")) == OrganigramFormat.SOF);
    Assert.assertTrue(OrgUtils.getType(new URL("file://sitemap.xml")) == OrganigramFormat.SITEMAP);
    Assert.assertTrue(OrgUtils.getType(new URL("file:mydata.txt")) == OrganigramFormat.TXT);
    Assert.assertTrue(OrgUtils.getType(new URL("file:àààà.tXt")) == OrganigramFormat.TXT);
  }

}
