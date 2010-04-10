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
 * The Class DirectHorizLineRender.
 */
public class DirectHorizLineRender extends AbstractLineRender {

  /**
   * Draw horizontal lines
   * 
   * @param box the box
   * @param graphics the graphics
   * @param orgLay the organigram layout
   */
  public void paint(final Graphics graphics, final UnitView box, final OrganigramLayout orgLay) {
    graphics.setColor(orgLay.getLineColor());
    final Point pos = box.getLocation();
    final int x1 = pos.x + box.getWidth() / 2;
    final int y1 = pos.y + box.getHeight();
    int x2;
    final int y2 = y1 + orgLay.getTopMargin() + orgLay.getBottomMargin();
    for (final UnitView child : box) {
      final Point childLocation = child.getLocation();
      x2 = childLocation.x + child.getWidth() / 2;
      graphics.drawLine(x1, y1, x2, y2);
    }
  }

}
