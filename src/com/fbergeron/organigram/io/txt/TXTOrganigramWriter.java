/** LGPL > 3.0
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
 */
package com.fbergeron.organigram.io.txt;

import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class TXTOrganigramWriter.
 */
public class TXTOrganigramWriter implements OrganigramWriter {

  /**
   * Write string
   *
   * @param str the string
   *
   * @return the string
   */
  private static final String writeStr(final String str) {
    return (str == null ? "" : str);
  }

  /**
   * Write unit.
   *
   * @param buf the stringbuffer
   * @param unit the u
   */
  public void writeUnit(final StringBuffer buf, final Unit unit) {
    if (unit != null) {
      final String name = unit.getMeta("name");
      if (name != null) {
        buf.append(TXTOrganigramWriter.writeStr(unit.getID()));
        buf.append('\t');
        buf.append(TXTOrganigramWriter.writeStr(unit.getMeta("date")));
        buf.append('\t');
        buf.append(name);
        buf.append('\t');
        buf.append(TXTOrganigramWriter.writeStr(unit.getMeta("role")));
        buf.append('\t');
        buf.append(TXTOrganigramWriter.writeStr(unit.getMeta("department")));
        buf.append('\n');
      }
      if (unit.hasChildren()) {
        for (final Unit child : unit) {
          writeUnit(buf, child);
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.io.OrganigramWriter#writeOrganigram(com.fbergeron.organigram.model.Organigram,
   *      boolean)
   */
  public String writeOrganigram(final Organigram organigram, final boolean compact) {
    final StringBuffer buf = new StringBuffer(1024);
    final Unit unit = organigram.getRoot();
    writeUnit(buf, unit);
    return buf.toString();
  }

}
