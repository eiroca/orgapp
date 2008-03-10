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

import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

public class TXTOrganigramWriter implements OrganigramWriter {

  public TXTOrganigramWriter() {
    //
  }

  private static final String writeStr(final String s) {
    return (s == null ? "" : s);
  }

  public void writeUnit(final StringBuffer sb, final Unit u) {
    if (u != null) {
      final String name = u.getMeta("name");
      if (name != null) {
        sb.append(TXTOrganigramWriter.writeStr(u.getID()));
        sb.append('\t');
        sb.append(TXTOrganigramWriter.writeStr(u.getMeta("date")));
        sb.append('\t');
        sb.append(name);
        sb.append('\t');
        sb.append(TXTOrganigramWriter.writeStr(u.getMeta("role")));
        sb.append('\t');
        sb.append(TXTOrganigramWriter.writeStr(u.getMeta("department")));
        sb.append('\n');
      }
      if (u.hasChildren()) {
        for (final Unit unit : u) {
          writeUnit(sb, unit);
        }
      }
    }
  }

  public String writeOrganigram(final Organigram organigram, final boolean compact) {
    final StringBuffer sb = new StringBuffer(1024);
    final Unit u = organigram.getRoot();
    writeUnit(sb, u);
    return sb.toString();
  }

}
