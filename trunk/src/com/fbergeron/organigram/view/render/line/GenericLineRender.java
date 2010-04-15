/**
 * LGPL > 3.0 Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
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
package com.fbergeron.organigram.view.render.line;

import java.awt.Graphics;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class VertLineRender.
 */
public class GenericLineRender extends DirectLineRender {

  public GenericLineRender(final Layout anchorParent, final Layout anchorChild) {
    super(anchorParent, anchorChild);
  }

  private void calcNode(final Layout anchor, final int[] xx, final int[] yy) {
    switch (anchor) {
      case LEFT:
      case RIGHT:
        xx[1] = (xx[0] + xx[3]) / 2;
        xx[2] = xx[1];
        yy[1] = yy[0];
        yy[2] = yy[3];
        break;
      default: // TOP, BOTTOM
        yy[1] = (yy[0] + yy[3]) / 2;
        yy[2] = yy[1];
        xx[1] = xx[0];
        xx[2] = xx[3];
        break;
    }
  }

  /**
   * Draw lines vert.
   * 
   * @param box the box
   * @param graphics the graphics
   * @param orgLay the org lay
   */
  @Override
  public void paint(final Graphics graphics, final UnitView box, final OrganigramLayout orgLay) {
    graphics.setColor(orgLay.getLineColor());
    final int[] xx = new int[4];
    final int[] yy = new int[4];
    setAnchorPos(box, anchorParent, 0, xx, yy);
    for (final UnitView child : box) {
      setAnchorPos(child, anchorChild, 3, xx, yy);
      calcNode(anchorParent, xx, yy);
      graphics.drawPolyline(xx, yy, 4);
    }
  }

}
