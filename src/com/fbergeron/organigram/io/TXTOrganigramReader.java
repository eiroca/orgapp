/**
 * Copyright (C) 2006-2008 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the:
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110-1301
 *   USA
 */
package com.fbergeron.organigram.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

public class TXTOrganigramReader implements OrganigramReader {

  public TXTOrganigramReader() {
    //
  }

  public Organigram readOrganigram(final URL xmlSourceUrl) {
    Organigram organigram;
    organigram = new Organigram();
    final Unit root = new Unit();
    organigram.setRoot(root);
    try {
      final BufferedReader reader = new BufferedReader(new InputStreamReader(xmlSourceUrl.openStream()));
      read(organigram, reader);
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
    return organigram;
  }

  public String get(final StringTokenizer st) {
    if (st.hasMoreTokens()) { return st.nextToken(); }
    return null;
  }

  public void parseLine(final Organigram organigram, final String line) {
    final StringTokenizer st = new StringTokenizer(line, "\t");
    String ID = get(st);
    final String when = get(st);
    final String name = get(st);
    final String role = get(st);
    final String department = get(st);
    if (ID == null) {
      ID = "";
    }
    if (name != null) {
      Unit u = organigram.findByID(ID, false);
      if (!ID.equals(u.getID())) {
        final Unit n = new Unit();
        u.addChild(n);
        u = n;
      }
      u.setID(ID);
      u.setMeta("date", when);
      u.setMeta("name", name);
      u.setMeta("role", role);
      u.setMeta("department", department);
    }
    else {
      System.err.println("Missing name " + line);
    }

  }

  public void read(final Organigram organigram, final BufferedReader reader) {
    String line;
    try {
      line = reader.readLine();
      while (line != null) {
        line = line.trim();
        if (line.length() > 0) {
          parseLine(organigram, line);
        }
        line = reader.readLine();
      }
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
  }

}
