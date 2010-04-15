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
import java.awt.Point;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class VertLineRender.
 */
public class DirectLineRender extends AbstractLineRender {

  protected Layout anchorParent;
  protected Layout anchorChild;

  public DirectLineRender(final Layout anchorParent, final Layout anchorChild) {
    this.anchorParent = anchorParent;
    this.anchorChild = anchorChild;
  }

  final protected void setAnchorPos(final UnitView box, final Layout anchor, final int idx, final int[] xx, final int[] yy) {
    final Point pos = box.getLocation();
    switch (anchor) {
      case LEFT:
        xx[idx] = pos.x + box.getWidth() + 1;
        yy[idx] = pos.y + box.getHeight() / 2;
        break;
      case RIGHT:
        xx[idx] = pos.x - 1;
        yy[idx] = pos.y + box.getHeight() / 2;
        break;
      case BOTTOM:
        xx[idx] = pos.x + box.getWidth() / 2;
        yy[idx] = pos.y + box.getHeight() + 1;
        break;
      default: // TOP
        xx[idx] = pos.x + box.getWidth() / 2;
        yy[idx] = pos.y - 1;
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
  public void paint(final Graphics graphics, final UnitView box, final OrganigramLayout orgLay) {
    graphics.setColor(orgLay.getLineColor());
    final int[] xx = new int[2];
    final int[] yy = new int[2];
    setAnchorPos(box, anchorParent, 0, xx, yy);
    for (final UnitView child : box) {
      setAnchorPos(child, anchorChild, 1, xx, yy);
      graphics.drawPolyline(xx, yy, 2);
    }
  }

}
