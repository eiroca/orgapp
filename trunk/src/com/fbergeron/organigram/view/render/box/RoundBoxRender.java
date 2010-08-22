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
package com.fbergeron.organigram.view.render.box;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class ClassicBoxRender.
 */
public class RoundBoxRender extends ClassicBoxRender {

  /**
   * Instantiates a new classic box render.
   * 
   * @param collapsedAnchor the collapsed anchor
   */
  public RoundBoxRender(final Layout collapsedAnchor) {
    super(collapsedAnchor);
  }

  /**
   * Draw box.
   * 
   * @param graphic the graphic
   * @param box the box
   * @param boxLay the box lay
   */
  public void drawBox(final Graphics2D graphic, final UnitView box, final BoxLayout boxLay) {
    RoundRectangle2D rect = new RoundRectangle2D.Double();
    Insets padding = boxLay.getPadding(true);
    int arcX = (padding.left + padding.right) / 2;
    int arcY = (padding.top + padding.bottom) / 2;
    rect.setRoundRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height, arcX, arcY);
    graphic.setColor(boxLay.getBackgroundColor(true));
    graphic.fill(rect);
    graphic.setColor(boxLay.getFrameColor(true));
    switch (boxLay.getType(true)) {
      case NONE:
        break;
      case HIGHLIGHT:
        Stroke oldStroke = graphic.getStroke();
        BasicStroke stroke = new BasicStroke(2);
        graphic.setStroke(stroke);
        graphic.draw(rect);
        graphic.setStroke(oldStroke);
        break;
      default:
        graphic.draw(rect);
        break;
    }
  }

}
