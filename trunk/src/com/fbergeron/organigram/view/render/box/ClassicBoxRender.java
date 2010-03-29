/**
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
 * 
 */
package com.fbergeron.organigram.view.render.box;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Polygon;
import java.util.List;

import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.view.UnitView;
import com.fbergeron.organigram.view.render.BoxRender;

/**
 * The Class ClassicBoxRender.
 */
public class ClassicBoxRender implements BoxRender {

  /**
   * Instantiates a new classic box render.
   */
  public ClassicBoxRender() {
    //
  }

  /**
   * Draw box.
   * 
   * @param graphic the graphic
   * @param boxLay the box lay
   * @param box the box
   */
  private final void drawBox(final Graphics graphic, final UnitView box, final BoxLayout boxLay) {
    graphic.setColor(boxLay.getBackgroundColor());
    graphic.fillRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height);
    final String role = box.unit.getMeta("role");
    graphic.setColor(boxLay.getFrameColor());
    char roleType = ' ';
    if (role != null) {
      roleType = role.charAt(0);
    }
    switch (roleType) {
      case 'H':
        graphic.drawRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height);
        graphic.drawRect(box.boxRect.x - 1, box.boxRect.y - 1, box.boxRect.width + 2, box.boxRect.height + 2);
        break;
      default:
        graphic.drawRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height);
        break;
    }
    if (!boxLay.isExpanded()) {
      int cx = box.boxRect.x + box.boxRect.width / 2;
      int cy = box.boxRect.y + box.boxRect.height;
      Polygon p = new Polygon();
      p.addPoint(cx + 0, cy + 5);
      p.addPoint(cx - 5, cy);
      p.addPoint(cx + 5, cy);
      p.addPoint(cx + 0, cy + 5);
      graphic.fillPolygon(p);
    }
  }

  /**
   * Draw text.
   * 
   * @param graphic the graphic
   * @param boxLay the box lay
   * @param box the box
   */
  private final void drawText(final Graphics graphic, final UnitView box, final BoxLayout boxLay) {
    int xPos;
    int yPos;
    yPos = box.boxRect.y + boxLay.getTopPadding();
    final List<Line> lines = box.unit.getInfo();
    FontMetrics fontMetr;
    for (final Line line : lines) {
      if (line.isVisible()) {
        if (line.getColor() == null) {
          graphic.setColor(boxLay.getForegroundColor());
        }
        else {
          graphic.setColor(line.getColor());
        }
        if (line.getFont() != null) {
          graphic.setFont(line.getFont());
        }
        fontMetr = graphic.getFontMetrics();
        final int textWidth = fontMetr.stringWidth(line.getText());
        if (boxLay.getTextAlignment() == Label.CENTER) {
          xPos = box.boxRect.x + (box.boxRect.width - textWidth) / 2;
        }
        else if (boxLay.getTextAlignment() == Label.RIGHT) {
          xPos = box.boxRect.x + box.boxRect.width - textWidth - boxLay.getRightPadding();
        }
        else {
          xPos = box.boxRect.x + boxLay.getLeftPadding();
        }
        graphic.drawString(line.getText(), xPos, yPos + fontMetr.getAscent() + fontMetr.getLeading());
        yPos += fontMetr.getHeight();
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.view.render.BoxRender#paint(java.awt.Graphics,
   * com.fbergeron.organigram.view.UnitView)
   */
  public void paint(final Graphics graphic, final UnitView box) {
    BoxLayout boxLay = box.getLayout();
    drawBox(graphic, box, boxLay);
    drawText(graphic, box, boxLay);
  }

}
