/** LGPL > 3.0
 * Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/
 */
package com.fbergeron.organigram.util;

import java.awt.Font;
import com.fbergeron.organigram.model.AbstractProcessor;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.model.type.BoxType;

/**
 * The Class BuildInfo.
 */
public class OrgCharFixUp extends AbstractProcessor<Object> {

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.model.UnitTraversal#process(com.fbergeron.organigram
   * .model.Unit)
   */
  @Override
  public void process(final Unit unit, final int level, final Object context) {
    int count = 0;
    for (final Line l : unit.getInfo()) {
      if (l.isVisible()) {
        count++;
      }
    }
    if (count == 0) {
      final String dep = unit.getMeta("department");
      final String nam = unit.getMeta("name");
      if (dep != null) {
        unit.addInfo(new Line(dep, Font.BOLD, 12, null));
      }
      if (nam != null) {
        unit.addInfo(new Line(nam, Font.ITALIC, 10, null));
      }
    }
    final String role = unit.getMeta("role");
    if ((role != null) && (role.charAt(0) == 'H')) {
      unit.getBoxLayout().setType(BoxType.HIGHLIGHT);
    }
  }

}
