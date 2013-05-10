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

import junit.framework.Assert;
import junit.framework.TestCase;

import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.util.OrgUtils;
import com.fbergeron.organigram.util.OrgUtils.OrganigramFormat;
import com.fbergeron.organigram.util.Utils;

/**
 * The Class OrganigramTest.
 */
public class OrganigramTest extends TestCase {

  /**
   * Test add ok.
   */
  public void testAddOK() {
    final Organigram org = new Organigram();
    org.add("/", null, "/", null);
    final StringBuffer id = new StringBuffer("");
    for (int i = 0; i < 10; i++) {
      final String parent = "/" + id;
      id.append("a");
      final String child = "/" + id;
      org.add(child, parent, id.toString(), null);
    }
    org.add("/2", "/", "2", null);
    org.add("/3", "/", "3", null);
    org.add("/2/1", "/2", "2.1", null);
    org.add("/2/2", "/2", "2.2", null);
    org.add("/text", "/", "title", null);
    org.add("/text/2", "/text", "title", "message");
    org.add("/text/3", "/text", "title", "message1\nmesage2");
    org.add("/text/4", "/text", "title", "message1\nmesage2\nmessage3");
    final String actual = OrgUtils.writeOrganigram(org, OrganigramFormat.SOF, false).trim();
    final String expected = Utils.readAsString(Utils.findResource("data/org1.out")).trim();
    Assert.assertEquals(expected, actual);
  }

  public void testAddKO() {
    final Organigram org = new Organigram();
    try {
      org.add("1");
      Assert.fail();
    }
    catch (final IllegalArgumentException e) {
    }
    org.add(null).setId("1");
    org.add("1");
    try {
      org.add("2");
      Assert.fail();
    }
    catch (final IllegalArgumentException e) {
    }
  }

}
