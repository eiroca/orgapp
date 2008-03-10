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

import java.awt.Font;

import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.model.UnitTraversal;

public class BuildInfo implements UnitTraversal {

  public BuildInfo() {
    //
  }

  public void process(final Unit u) {
    int c = 0;
    for (final Line l : u.getInfo()) {
      if (l.isVisible()) {
        c++;
      }
    }
    if (c == 0) {
      final String dep = u.getMeta("department");
      final String nam = u.getMeta("name");
      if (dep != null) {
        u.addInfo(new Line(dep, Font.BOLD, 12));
      }
      if (nam != null) {
        u.addInfo(new Line(nam, Font.ITALIC, 10));
      }
    }
  }

}
