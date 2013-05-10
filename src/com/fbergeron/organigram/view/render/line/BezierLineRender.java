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
import java.awt.geom.CubicCurve2D;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class VertLineRender.
 */
public class BezierLineRender extends GenericLineRender {

  /**
   * Instantiates a new Bezier line render.
   * 
   * @param anchorParent the anchor parent
   * @param anchorChild the anchor child
   */
  public BezierLineRender(final Layout anchorParent, final Layout anchorChildNormal, final Layout anchorChildFlipped) {
    super(anchorParent, anchorChildNormal, anchorChildFlipped);
  }

  /**
   * Draw Bezier lines
   * 
   * @param graphics the graphics
   * @param box the box
   * @param orgLay the org lay
   */
  @Override
  public void paint(final Graphics2D graphics, final UnitView box, final OrganigramLayout orgLay) {
    final boolean flipped = orgLay.isFlipped() && box.isFlippable();
    final CubicCurve2D line = new CubicCurve2D.Double();
    graphics.setColor(orgLay.getLineColor());
    final int[] xPos = new int[4];
    final int[] yPos = new int[4];
    setAnchorPos(box, anchorParent, 0, xPos, yPos);
    for (final UnitView child : box) {
      setAnchorPos(child, getAnchorChild(flipped), 3, xPos, yPos);
      calcNode(anchorParent, xPos, yPos, flipped);
      line.setCurve(xPos[0], yPos[0], xPos[1], yPos[1], xPos[2], yPos[2], xPos[3], yPos[3]);
      graphics.draw(line);
    }
  }

}
