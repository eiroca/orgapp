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
package com.fbergeron.organigram.view.render.line;

import java.awt.Graphics2D;
import java.awt.Point;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class VertLineRender.
 */
public class DirectLineRender extends AbstractLineRender {

  /**
   * Instantiates a new direct line render.
   * 
   * @param anchorParent
   * @param anchorChildNormal
   * @param anchorChildFlipped
   */
  public DirectLineRender(final Layout anchorParent, final Layout anchorChildNormal, final Layout anchorChildFlipped) {
    super(anchorParent, anchorChildNormal, anchorChildFlipped);
  }

  /**
   * Sets the anchor pos.
   * 
   * @param box the box
   * @param anchor the anchor
   * @param idx the idx
   * @param xPos the xx
   * @param yPos the yy
   */
  final protected void setAnchorPos(final UnitView box, final Layout anchor, final int idx, final int[] xPos, final int[] yPos) {
    final Point pos = box.getLocation();
    switch (anchor) {
      case LEFT:
        xPos[idx] = pos.x + box.getWidth() + 1;
        yPos[idx] = pos.y + (box.getHeight() / 2);
        break;
      case RIGHT:
        xPos[idx] = pos.x - 1;
        yPos[idx] = pos.y + (box.getHeight() / 2);
        break;
      case BOTTOM:
        xPos[idx] = pos.x + (box.getWidth() / 2);
        yPos[idx] = pos.y + box.getHeight() + 1;
        break;
      default: // TOP
        xPos[idx] = pos.x + (box.getWidth() / 2);
        yPos[idx] = pos.y - 1;
        break;
    }
  }

  /**
   * Draw lines vert.
   * 
   * @param graphics the graphics
   * @param box the box
   * @param orgLay the org lay
   */
  @Override
  public void paint(final Graphics2D graphics, final UnitView box, final OrganigramLayout orgLay) {
    final boolean flipped = orgLay.isFlipped() && box.isFlippable();
    graphics.setColor(orgLay.getLineColor());
    final int[] xPos = new int[2];
    final int[] yPos = new int[2];
    setAnchorPos(box, anchorParent, 0, xPos, yPos);
    for (final UnitView child : box) {
      setAnchorPos(child, getAnchorChild(flipped), 1, xPos, yPos);
      graphics.drawPolyline(xPos, yPos, 2);
    }
  }

}
