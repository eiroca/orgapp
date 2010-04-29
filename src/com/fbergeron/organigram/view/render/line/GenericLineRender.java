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

  /**
   * Instantiates a new generic line render.
   * 
   * @param anchorParent the anchor parent
   * @param anchorChild the anchor child
   */
  public GenericLineRender(final Layout anchorParent, final Layout anchorChild) {
    super(anchorParent, anchorChild);
  }

  /**
   * Calc node.
   * 
   * @param anchor the anchor
   * @param xPos the xx
   * @param yPos the yy
   */
  private void calcNode(final Layout anchor, final int[] xPos, final int[] yPos) {
    switch (anchor) {
      case LEFT:
      case RIGHT:
        xPos[1] = (xPos[0] + xPos[3]) / 2;
        xPos[2] = xPos[1];
        yPos[1] = yPos[0];
        yPos[2] = yPos[3];
        break;
      default: // TOP, BOTTOM
        yPos[1] = (yPos[0] + yPos[3]) / 2;
        yPos[2] = yPos[1];
        xPos[1] = xPos[0];
        xPos[2] = xPos[3];
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
  public void paint(final Graphics graphics, final UnitView box, final OrganigramLayout orgLay) {
    graphics.setColor(orgLay.getLineColor());
    final int[] xPos = new int[4];
    final int[] yPos = new int[4];
    setAnchorPos(box, anchorParent, 0, xPos, yPos);
    for (final UnitView child : box) {
      setAnchorPos(child, anchorChild, 3, xPos, yPos);
      calcNode(anchorParent, xPos, yPos);
      graphics.drawPolyline(xPos, yPos, 4);
    }
  }

}
