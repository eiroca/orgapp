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
package com.fbergeron.organigram.view.render.line;

import java.awt.Graphics;
import java.awt.Point;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class HorizLineRender.
 */
public class HorizLineRender extends AbstractLineRender {

  /**
   * Draw lines horiz.
   * 
   * @param box the box
   * @param graphics the graphics
   * @param orgLay the org lay
   */
  public void paint(final Graphics graphics, final UnitView box, final OrganigramLayout orgLay) {
    graphics.setColor(orgLay.getLineColor());
    final int[] xx = new int[4];
    final int[] yy = new int[4];
    final Point pos = box.getLocation();
    xx[0] = pos.x + box.getWidth() / 2;
    yy[0] = pos.y + box.getHeight();
    xx[1] = xx[0];
    yy[1] = yy[0] + orgLay.getBottomMargin();
    yy[2] = yy[1];
    yy[3] = yy[1] + orgLay.getTopMargin();
    for (final UnitView child : box) {
      final Point childLocation = child.getLocation();
      xx[2] = childLocation.x + child.getWidth() / 2;
      xx[3] = xx[2];
      graphics.drawPolyline(xx, yy, 4);
    }
  }

}
