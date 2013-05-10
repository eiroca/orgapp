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

import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.type.LineMode;
import com.fbergeron.organigram.model.type.OrgMode;
import com.fbergeron.organigram.view.OrganigramImage;

/**
 * The Class OrgUtilsTest.
 */
public class DemoTest extends TestCase {

  /** Path where to store output image */
  private static final String OUT_PATH = "demo1.png";

  /**
   * Test demo1.
   * 
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void testDemo1() throws IOException {
    final Organigram org = new Organigram();
    org.add("1", null, "/", "", 10, 20, null);
    org.add("2", "1", "bin", "nothing");
    org.add("3", "1", "boot", "nothing");
    org.add("4", "1", "dev", "nothing");
    org.add("5", "1", "etc", null, 50, 20, null);
    org.add("6", "1", "home", "nothing");
    org.add("7", "1", "lib", "nothing");
    org.add("8", "1", "lost+found", "nothing", 100, 20, null);
    org.add("9", "1", "mnt", "nothing");
    org.add("10", "1", "proc", "nothing");
    org.add("11", "1", "root", "nothing");
    org.add("12", "1", "sbin", "nothing");
    org.add("13", "1", "tmp", "nothing");
    org.add("14", "1", "usr", null, 50, 20, null);
    org.add("15", "1", "var", null, 50, 20, null);
    org.add("16", "5", "rc.d", "nothing");
    org.add("17", "5", "skel", "nothing");
    org.add("18", "5", "X11", "nothing");
    org.add("19", "14", "bin", "nothing");
    org.add("20", "14", "local", "nothing");
    org.add("21", "14", "include", "nothing");
    org.add("22", "14", "lib", "nothing");
    org.add("23", "14", "man", "nothing");
    org.add("24", "14", "sbin", "nothing");
    org.add("25", "14", "src", "nothing");
    org.add("26", "14", "X11 R6", "nothing");
    org.add("27", "15", "tmp", "nothing");
    org.add("28", "15", "spool", "", 50, 20, null);
    org.add("29", "20", "bin", "nothing");
    org.add("30", "20", "sbin", "nothing");
    org.add("31", "25", "linux", "nothing");
    org.add("32", "28", "lpd", "nothing");
    org.add("33", "28", "mail", "nothing");
    org.add("34", "28", "uucp", "nothing");
    org.add("35", "28", "cron", "nothing");
    final OrganigramLayout layout = org.getOrganigramLayout();
    layout.setLineMode(LineMode.CONNECTOR);
    layout.setCompact(true);
    layout.setMode(OrgMode.MIN);
    layout.setMargin(new Insets(10, 10, 10, 10));
    final OrganigramImage img = new OrganigramImage(org);
    ImageIO.write(img.getImage(), "PNG", new File(DemoTest.OUT_PATH));
  }
}
