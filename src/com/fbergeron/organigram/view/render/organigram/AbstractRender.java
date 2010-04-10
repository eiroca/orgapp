/** LGPL > 3.0
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
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
package com.fbergeron.organigram.view.render.organigram;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.view.OrganigramView;
import com.fbergeron.organigram.view.UnitView;
import com.fbergeron.organigram.view.render.BoxRender;
import com.fbergeron.organigram.view.render.LineRender;
import com.fbergeron.organigram.view.render.OrganigramRender;

/**
 * The Class GenericRender.
 */
abstract public class AbstractRender implements OrganigramRender {

  /** The org view. */
  protected OrganigramView orgView;

  /** The _box max size level. */
  private final List<Dimension> boxMaxSizeLevel = new ArrayList<Dimension>();

  /** The line render. */
  protected LineRender lineRender;

  /** The box render. */
  protected BoxRender boxRender;

  /** The point level. */
  private final List<Point> pointLevel = new ArrayList<Point>();

  /** The compact. */
  protected boolean compact;

  protected boolean validLayout = false;

  /**
   * Instantiates a new new render.
   * 
   * @param orgView the org view
   * @param graphic the graphic
   * @param compact the compact
   */
  public AbstractRender(final OrganigramView orgView, final boolean compact) {
    this.orgView = orgView;
    this.compact = compact;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.view.render.OrganigramRender#setLineRender(com
   * .fbergeron.organigram.view.render.LineRender)
   */
  public void setLineRender(final LineRender lineRender) {
    this.lineRender = lineRender;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.view.render.OrganigramRender#setBoxRender(com.
   * fbergeron.organigram.view.render.BoxRender)
   */
  public void setBoxRender(final BoxRender boxRender) {
    this.boxRender = boxRender;
  }

  /**
   * Paint the organigram box.
   * 
   * @param box the box
   * @param graphics the graphics
   * @param orgLay the org lay
   */
  private void paintBox(final UnitView box, final Graphics graphics, final OrganigramLayout orgLay) {
    boxRender.paint(graphics, box, orgLay);
    if (box.hasChildren()) {
      lineRender.paint(graphics, box, orgLay);
      // Draw the children boxes.
      for (final UnitView child : box) {
        paintBox(child, graphics, orgLay);
      }
    }
  }

  /**
   * Paint.
   * 
   * @param graphics the graphics
   */
  public synchronized void paint(final Graphics graphics) {
    if (!validLayout) {
      layoutBoxes(graphics);
    }
    final Dimension dim = orgView.getSize();
    final OrganigramLayout orgLay = orgView.getOrganigram().getOrganigramLayout();
    // Draw background.
    graphics.setColor(orgLay.getBackgroundColor());
    graphics.fillRect(0, 0, dim.width, dim.height);
    if (orgView.root != null) {
      paintBox(orgView.root, graphics, orgLay);
    }
  }

  /**
   * Layout boxes.
   * 
   * @param graphic the graphic
   */
  public void layoutBoxes(final Graphics graphic) {
    final Dimension organigramMaxSize = new Dimension();
    if (orgView.root == null) {
      final OrganigramLayout orgLay = orgView.getOrganigram().getOrganigramLayout();
      organigramMaxSize.setSize(orgLay.getLeftMargin() + orgLay.getRightMargin(), orgLay.getTopMargin() + orgLay.getBottomMargin());
    }
    else {
      pointLevel.clear();
      calcBoxMaximumSize(graphic);
      organigramMaxSize.setSize(layoutBoxes());
    }
    orgView.setPreferredSize(organigramMaxSize);
  }

  /**
   * Update max.
   * 
   * @param dim the dim
   * @param max the max
   */
  private void updateMax(final Dimension dim, final Dimension max) {
    if (dim.width > max.width) {
      max.width = dim.width;
    }
    if (dim.height > max.height) {
      max.height = dim.height;
    }
  }

  /**
   * Calc box maximum.
   * 
   * @param box the box
   * @param graphic the graphic
   * 
   * @return the dimension
   */
  private Dimension calcBoxMaximum(final Graphics graphic, final UnitView box) {
    final Dimension size = new Dimension();
    final Unit unit = box.getUnit();
    for (final Line element : unit.getInfo()) {
      if (element.isVisible()) {
        final FontMetrics metric = graphic.getFontMetrics(element.getFont());
        final int lineWidth = metric.stringWidth(element.getText());
        size.height += metric.getHeight();
        if (lineWidth > size.width) {
          size.width = lineWidth;
        }
      }
    }
    final BoxLayout boxLay = box.getLayout();
    size.width += boxLay.getLeftPadding() + boxLay.getRightPadding();
    size.height += boxLay.getTopPadding() + boxLay.getBottomPadding();
    return size;
  }

  /**
   * Calc box maximum size.
   * 
   * @param box the box
   * @param max the max
   * @param level the level
   * @param graphic the graphic
   */
  private void calcBoxMaximumSize(final Graphics graphic, final UnitView box, final Dimension max, final int level) {
    final Dimension boxMax = calcBoxMaximum(graphic, box);
    final Dimension levSiz = getBoxSize(level);
    updateMax(boxMax, max);
    updateMax(boxMax, levSiz);
    final int newLevel = level + 1;
    for (final UnitView child : box) {
      calcBoxMaximumSize(graphic, child, max, newLevel);
    }
  }

  /**
   * Calc box maximum size.
   * 
   * @param graphic the graphic
   */
  private void calcBoxMaximumSize(final Graphics graphic) {
    final Dimension boxMaxSize = new Dimension(0, 0);
    boxMaxSizeLevel.clear();
    calcBoxMaximumSize(graphic, orgView.root, boxMaxSize, 0);
    final OrganigramLayout orgLay = orgView.getOrganigram().getOrganigramLayout();
    Dimension mySiz = null;
    Dimension levSiz;
    for (int i = boxMaxSizeLevel.size() - 1; i >= 0; i--) {
      levSiz = boxMaxSizeLevel.get(i);
      switch (orgLay.getOrgMode()) {
        case OrganigramLayout.ORGMODE_MIN:
          break;
        case OrganigramLayout.ORGMODE_GROW:
          if (mySiz == null) {
            mySiz = new Dimension(levSiz);
          }
          else {
            updateMax(levSiz, mySiz);
            levSiz.setSize(mySiz);
          }
          break;
        default:
          levSiz.setSize(boxMaxSize);
          break;
      }
    }
  }

  /**
   * Gets the box size.
   * 
   * @param level the level
   * 
   * @return the box size
   */
  public Dimension getBoxSize(final int level) {
    while (boxMaxSizeLevel.size() <= level) {
      boxMaxSizeLevel.add(new Dimension(0, 0));
    }
    return boxMaxSizeLevel.get(level);
  }

  /**
   * Gets the box size.
   * 
   * @param level the level
   * @param xPos the x
   * @param yPos the y
   * 
   * @return the box size
   */
  public Point getPoint(final int level, final int xPos, final int yPos) {
    while (pointLevel.size() <= level) {
      pointLevel.add(new Point(xPos, yPos));
    }
    return pointLevel.get(level);
  }

  /*
   * (non-Javadoc)
   * @see com.fbergeron.organigram.view.render.GenericRender#layoutBoxes()
   */
  /**
   * Layout boxes.
   * 
   * @return the dimension
   */
  public Dimension layoutBoxes() {
    final Dimension rect = new Dimension();
    final UnitView unit = orgView.root;
    final OrganigramLayout orgLay = orgView.getOrganigram().getOrganigramLayout();
    layoutBoxes(orgLay, unit, null, 0);
    for (int i = 0; i < pointLevel.size(); i++) {
      final Point pos = getPoint(i, 0, 0);
      if (rect.width < pos.x) {
        rect.width = pos.x;
      }
      if (rect.height < pos.y) {
        rect.height = pos.y;
      }
    }
    return rect;
  }

  /**
   * Layout boxes.
   * 
   * @param orgLay the org lay
   * @param unit the unit
   * @param parent the parent
   * @param level the level
   */
  public void layoutBoxes(final OrganigramLayout orgLay, final UnitView unit, final UnitView parent, final int level) {
    validLayout = true;
  }

  public void invalidate() {
    validLayout = false;
  }

}
