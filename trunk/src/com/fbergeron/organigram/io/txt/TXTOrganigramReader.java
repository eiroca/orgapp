/**
 * Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 * 
 */
package com.fbergeron.organigram.io.txt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class TXTOrganigramReader.
 */
public class TXTOrganigramReader implements OrganigramReader {

  /** The organigram. */
  Organigram organigram;

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.io.OrganigramReader#readOrganigram(java.net.URL)
   */
  public Organigram readOrganigram(final InputStream source) {
    organigram = new Organigram();
    final Unit root = new Unit();
    organigram.setRoot(root);
    final BufferedReader reader = new BufferedReader(new InputStreamReader(source));
    read(organigram, reader);
    return organigram;
  }

  /**
   * Gets the.
   * 
   * @param tokenizer the string tokenizer
   * 
   * @return the string
   */
  public String get(final StringTokenizer tokenizer) {
    return (tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null);
  }

  /**
   * Parses the line.
   * 
   * @param organigram the organigram
   * @param line the line
   */
  public void parseLine(final Organigram organigram, final String line) {
    final StringTokenizer tokenizer = new StringTokenizer(line, "\t");
    String unitID = get(tokenizer);
    final String when = get(tokenizer);
    final String name = get(tokenizer);
    final String role = get(tokenizer);
    final String department = get(tokenizer);
    if (unitID == null) {
      unitID = "";
    }
    if (name == null) {
      System.err.println("Missing name " + line);
      return;
    }
    Unit unit = organigram.findByID(unitID, false);
    if (!unitID.equals(unit.getID())) {
      final Unit newUnit = new Unit();
      unit.addChild(newUnit);
      unit = newUnit;
    }
    unit.setID(unitID);
    unit.setMeta("date", when);
    unit.setMeta("name", name);
    unit.setMeta("role", role);
    unit.setMeta("department", department);
  }

  /**
   * Read.
   * 
   * @param organigram the organigram
   * @param reader the reader
   */
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

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.OrganigramReader#getData()
   */
  public Map<String, String> getData() {
    return null;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.OrganigramReader#getOrganigram()
   */
  public Organigram getOrganigram() {
    return organigram;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.OrganigramReader#setOrganigram(com.fbergeron.organigram.model.Organigram)
   */
  public void setOrganigram(final Organigram organigram) {
    this.organigram = organigram;
  }

}
