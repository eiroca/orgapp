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
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
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
  private Layout collapsedAnchor;

  /**
   * Instantiates a new classic box render.
   * 
   * @param collapsedAnchor the collapsed anchor
   */
  public ClassicBoxRender(final Layout collapsedAnchor) {
    this.collapsedAnchor = collapsedAnchor;
  }

  /**
   * Draw box.
   * 
   * @param graphic the graphic
   * @param box the box
   * @param boxLay the box lay
   */
  public void drawBox(final Graphics2D graphic, final UnitView box, final BoxLayout boxLay) {
    Rectangle2D rect = new Rectangle2D.Double();
    rect.setRect(box.boxRect.x, box.boxRect.y, box.boxRect.width, box.boxRect.height);
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

  /**
   * Left arrow.
   * 
   * @param margin the margin
   * @param box the box
   * @return the polygon
   */
  private final Polygon leftArrow(final Insets margin, final UnitView box) {
    final Polygon pol = new Polygon();
    final int xPos = box.boxRect.x;
    final int yPos = box.boxRect.y + box.boxRect.height / 2;
    final int siz = margin.left / 2;
    pol.addPoint(xPos - siz, yPos);
    pol.addPoint(xPos, yPos - siz);
    pol.addPoint(xPos, yPos + siz);
    pol.addPoint(xPos - siz, yPos);
    return pol;
  }

  /**
   * Left right.
   * 
   * @param margin the margin
   * @param box the box
   * @return the polygon
   */
  private final Polygon leftRight(final Insets margin, final UnitView box) {
    final Polygon pol = new Polygon();
    final int xPos = box.boxRect.x + box.boxRect.width;
    final int yPos = box.boxRect.y + box.boxRect.height / 2;
    final int siz = margin.right / 2;
    pol.addPoint(xPos + siz, yPos);
    pol.addPoint(xPos, yPos - siz);
    pol.addPoint(xPos, yPos + siz);
    pol.addPoint(xPos + siz, yPos);
    return pol;
  }

  /**
   * Left top.
   * 
   * @param margin the margin
   * @param box the box
   * @return the polygon
   */
  private final Polygon leftTop(final Insets margin, final UnitView box) {
    final Polygon pol = new Polygon();
    final int xPos = box.boxRect.x + box.boxRect.width / 2;
    final int yPos = box.boxRect.y;
    final int siz = margin.top / 2;
    pol.addPoint(xPos, yPos - siz);
    pol.addPoint(xPos - siz, yPos);
    pol.addPoint(xPos + siz, yPos);
    pol.addPoint(xPos, yPos - siz);
    return pol;
  }

  /**
   * Left bottom.
   * 
   * @param margin the margin
   * @param box the box
   * @return the polygon
   */
  private final Polygon leftBottom(final Insets margin, final UnitView box) {
    final Polygon pol = new Polygon();
    final int xPos = box.boxRect.x + box.boxRect.width / 2;
    final int yPos = box.boxRect.y + box.boxRect.height;
    final int siz = margin.bottom / 2;
    pol.addPoint(xPos, yPos + siz);
    pol.addPoint(xPos - siz, yPos);
    pol.addPoint(xPos + siz, yPos);
    pol.addPoint(xPos, yPos + siz);
    return pol;
  }

  /**
   * Draws the icon to rappresents that the node has unexpanded children.
   * 
   * @param graphic the graphic
   * @param box the box
   */
  private final void drawBoxExpand(final Graphics graphic, final UnitView box) {
    final Insets margin = box.getOrganigramView().getOrganigram().getOrganigramLayout().getMargin();
    Polygon pol;
    switch (collapsedAnchor) {
      case LEFT:
        pol = leftArrow(margin, box);
        break;
      case RIGHT:
        pol = leftRight(margin, box);
        break;
      case BOTTOM:
        pol = leftBottom(margin, box);
        break;
      default:// TOP
        pol = leftTop(margin, box);
        break;
    }
    graphic.fillPolygon(pol);
  }

  /**
   * Draw text.
   * 
   * @param graphic the graphic
   * @param box the box
   * @param boxLay the box lay
   */
  private final void drawText(final Graphics graphic, final UnitView box, final BoxLayout boxLay) {
    final Insets padding = boxLay.getPadding(true);
    int xPos;
    int yPos = box.boxRect.y + padding.top;
    Dimension min = box.getSmallSize();
    Dimension cur = box.getSize();
    yPos += (cur.height - min.height) / 2;
    final List<Line> lines = box.unit.getInfo();
    for (final Line text : lines) {
      if (text.isVisible()) {
        if (text.getColor() == null) {
          graphic.setColor(boxLay.getForegroundColor(true));
        }
        else {
          graphic.setColor(text.getColor());
        }
        if (text.getFont() != null) {
          graphic.setFont(text.getFont());
        }
        final FontMetrics fontMetr = graphic.getFontMetrics();
        final int textWidth = fontMetr.stringWidth(text.getText());
        if (boxLay.getTextAlignment(true) == Alignment.CENTER) {
          xPos = box.boxRect.x + (box.boxRect.width - textWidth) / 2;
        }
        else if (boxLay.getTextAlignment(true) == Alignment.RIGHT) {
          xPos = box.boxRect.x + box.boxRect.width - textWidth - padding.right;
        }
        else {
          xPos = box.boxRect.x + padding.left;
        }
        graphic.drawString(text.getText(), xPos, yPos + fontMetr.getAscent() + fontMetr.getLeading());
        yPos += fontMetr.getHeight();
      }
    }
  }

  /**
   * Gets the collapsed anchor.
   * 
   * @return the collapsedAnchor
   */
  public Layout getCollapsedAnchor() {
    return collapsedAnchor;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.view.render.BoxRender#paint(java.awt.Graphics,
   * com.fbergeron.organigram.view.UnitView)
   */
  public void paint(final Graphics2D graphic, final UnitView box, final OrganigramLayout orgLay) {
    final BoxLayout boxLay = box.getLayout();
    drawBox(graphic, box, boxLay);
    if (box.canExpand()) {
      drawBoxExpand(graphic, box);
    }
    drawText(graphic, box, boxLay);
  }

  /**
   * Sets the collapsed anchor.
   * 
   * @param collapsedAnchor the collapsedAnchor to set
   */
  public void setCollapsedAnchor(final Layout collapsedAnchor) {
    this.collapsedAnchor = collapsedAnchor;
  }

}
