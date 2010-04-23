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
package com.fbergeron.organigram.view.render.box;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.type.Alignment;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.UnitView;
import com.fbergeron.organigram.view.render.BoxRender;

/**
 * The Class ClassicBoxRender.
 */
public class ClassicBoxRender implements BoxRender {

  /** The collapsed anchor. */
  private final Layout collapsedAnchor;

  /**
   * Instantiates a new classic box render.
   * 
   * @param collapsedAnchor the collapsed anchor
   */
  public ClassicBoxRender(final Layout collapsedAnchor) {
    this.collapsedAnchor = collapsedAnchor;
  }

  /**
   * Draws the icon to rappresents that the node has unexpanded children.
   * 
   * @param graphic the graphic
   * @param box the box
   * @param boxLay the box lay
   */
  private final void drawBoxExpand(final Graphics graphic, final UnitView box, final BoxLayout boxLay) {
    final Polygon p = new Polygon();
    final int cx;
    final int cy;
    final int sz;
    switch (collapsedAnchor) {
      case LEFT:
        cx = box.boxRect.x;
        cy = box.boxRect.y + box.boxRect.height / 2;
        sz = box.getOrganigramView().getOrganigram().getOrganigramLayout().getLeftMargin() / 2;
        p.addPoint(cx - sz, cy);
        p.addPoint(cx, cy - sz);
        p.addPoint(cx, cy + sz);
        p.addPoint(cx - sz, cy);
        break;
      case RIGHT:
        cx = box.boxRect.x + box.boxRect.width;
        cy = box.boxRect.y + box.boxRect.height / 2;
        sz = box.getOrganigramView().getOrganigram().getOrganigramLayout().getRightMargin() / 2;
        p.addPoint(cx + sz, cy);
        p.addPoint(cx, cy - sz);
        p.addPoint(cx, cy + sz);
        p.addPoint(cx + sz, cy);
        break;
      case BOTTOM:
        cx = box.boxRect.x + box.boxRect.width / 2;
        cy = box.boxRect.y + box.boxRect.height;
        sz = box.getOrganigramView().getOrganigram().getOrganigramLayout().getBottomMargin() / 2;
        p.addPoint(cx, cy + sz);
        p.addPoint(cx - sz, cy);
        p.addPoint(cx + sz, cy);
        p.addPoint(cx, cy + sz);
        break;
      default:// TOP
        cx = box.boxRect.x + box.boxRect.width / 2;
        cy = box.boxRect.y;
        sz = box.getOrganigramView().getOrganigram().getOrganigramLayout().getTopMargin() / 2;
        p.addPoint(cx, cy - sz);
        p.addPoint(cx - sz, cy);
        p.addPoint(cx + sz, cy);
        p.addPoint(cx, cy - sz);
        break;
    }
    graphic.fillPolygon(p);
  }

  /**
   * Draw box.
   * 
   * @param graphic the graphic
   * @param box the box
   * @param boxLay the box lay
   */
  private final void drawBox(final Graphics graphic, final UnitView box, final BoxLayout boxLay) {
    graphic.setColor(boxLay.getBackgroundColor());
    graphic.fillRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height);
    graphic.setColor(boxLay.getFrameColor());
    switch (boxLay.getType()) {
      case HIGHLIGHT:
        graphic.drawRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height);
        graphic.drawRect(box.boxRect.x + 1, box.boxRect.y + 1, box.boxRect.width - 2, box.boxRect.height - 2);
        break;
      default:
        graphic.drawRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height);
        break;
    }
    if (box.canExpand()) {
      drawBoxExpand(graphic, box, boxLay);
    }
  }

  /**
   * Draw text.
   * 
   * @param graphic the graphic
   * @param box the box
   * @param boxLay the box lay
   */
  private final void drawText(final Graphics graphic, final UnitView box, final BoxLayout boxLay) {
    int xPos;
    int yPos;
    yPos = box.boxRect.y + boxLay.getTopPadding();
    final List<Line> lines = box.unit.getInfo();
    FontMetrics fontMetr;
    for (final Line text : lines) {
      if (text.isVisible()) {
        if (text.getColor() == null) {
          graphic.setColor(boxLay.getForegroundColor());
        }
        else {
          graphic.setColor(text.getColor());
        }
        if (text.getFont() != null) {
          graphic.setFont(text.getFont());
        }
        fontMetr = graphic.getFontMetrics();
        final int textWidth = fontMetr.stringWidth(text.getText());
        if (boxLay.getTextAlignment() == Alignment.CENTER) {
          xPos = box.boxRect.x + (box.boxRect.width - textWidth) / 2;
        }
        else if (boxLay.getTextAlignment() == Alignment.RIGHT) {
          xPos = box.boxRect.x + box.boxRect.width - textWidth - boxLay.getRightPadding();
        }
        else {
          xPos = box.boxRect.x + boxLay.getLeftPadding();
        }
        graphic.drawString(text.getText(), xPos, yPos + fontMetr.getAscent() + fontMetr.getLeading());
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
  public void paint(final Graphics graphic, final UnitView box, final OrganigramLayout orgLay) {
    final BoxLayout boxLay = box.getLayout();
    drawBox(graphic, box, boxLay);
    drawText(graphic, box, boxLay);
  }

}
