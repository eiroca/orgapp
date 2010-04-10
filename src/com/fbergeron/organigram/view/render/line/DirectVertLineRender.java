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
package com.fbergeron.organigram.view.render.line;

import java.awt.Graphics;
import java.awt.Point;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class DirectVertLineRender.
 */
public class DirectVertLineRender extends AbstractLineRender {

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.view.render.LineRender#paint(java.awt.Graphics, com.fbergeron.organigram.view.UnitView, com.fbergeron.organigram.model.OrganigramLayout)
   */
  public void paint(final Graphics graphics, final UnitView box, final OrganigramLayout orgLay) {
    graphics.setColor(orgLay.getLineColor());
    final Point pos = box.getLocation();
    final int x1 = pos.x + box.getWidth();
    final int y1 = pos.y + box.getHeight() / 2;
    final int x2 = x1 + orgLay.getRightMargin() + orgLay.getLeftMargin();
    int y2;
    for (final UnitView child : box) {
      final Point childLocation = child.getLocation();
      y2 = childLocation.y + child.getHeight() / 2;
      graphics.drawLine(x1, y1, x2, y2);
    }
  }

}
