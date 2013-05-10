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
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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
  protected OrganigramView organigramView;

  /** The line render. */
  protected LineRender lineRender;

  /** The box render. */
  protected BoxRender boxRender;

  /** The point level. */
  private final transient List<Point> pointLevel = new ArrayList<Point>();

  /** The compact. */
  protected transient boolean compact;

  /** The flip x. */
  private transient final boolean flipX;

  /** The flip y. */
  private transient final boolean flipY;

  /**
   * Instantiates a new new render.
   * 
   * @param orgView the org view
   * @param compact the compact
   * @param flipX the flip x
   * @param flipY the flip y
   */
  public AbstractRender(final OrganigramView orgView, final boolean compact, final boolean flipX, final boolean flipY) {
    organigramView = orgView;
    this.compact = compact;
    this.flipX = flipX;
    this.flipY = flipY;

  }

  /**
   * Calc box maximum.
   * 
   * @param graphic the graphic
   * @param box the box
   * @return the dimension
   */
  private Dimension calcBoxSize(final Graphics graphic, final UnitView box) {
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
    final Insets padding = box.getLayout().getPadding(true);
    size.width += padding.left + padding.right;
    size.height += padding.top + padding.bottom;
    return size;
  }

  /**
   * Calc box maximum size.
   * 
   * @param graphic the graphic
   */
  private void updateBoxSize(final Graphics graphic) {
    final List<Dimension> boxMaxSizeLevel = new ArrayList<Dimension>();
    final Dimension boxMaxSize = new Dimension(0, 0);
    boxMaxSizeLevel.clear();
    calcBoxMaximumSize(graphic, organigramView.root, boxMaxSize, 0, boxMaxSizeLevel);
    final OrganigramLayout orgLay = organigramView.getOrganigram().getOrganigramLayout();
    Dimension mySiz = null;
    Dimension levSiz;
    for (int i = boxMaxSizeLevel.size() - 1; i >= 0; i--) {
      levSiz = boxMaxSizeLevel.get(i);
      switch (orgLay.getMode()) {
        case MIN:
          break;
        case GROW:
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
    final int level = 0;
    if (organigramView.root != null) {
      updateSize(organigramView.root, level, boxMaxSizeLevel);
    }
  }

  /**
   * Update size.
   *
   * @param view the view
   * @param level the level
   * @param boxMaxSizeLevel the box max size level
   */
  private void updateSize(final UnitView view, final int level, final List<Dimension> boxMaxSizeLevel) {
    view.setSize(boxMaxSizeLevel.get(level));
    if (view.hasChildren()) {
      final int newLevel = level + 1;
      for (final UnitView child : view.children) {
        updateSize(child, newLevel, boxMaxSizeLevel);
      }
    }
  }

  /**
   * Calc box maximum size.
   *
   * @param graphic the graphic
   * @param box the box
   * @param max the max
   * @param level the level
   * @param boxMaxSizeLevel the box max size level
   */
  private void calcBoxMaximumSize(final Graphics graphic, final UnitView box, final Dimension max, final int level, final List<Dimension> boxMaxSizeLevel) {
    final Dimension boxSize = calcBoxSize(graphic, box);
    box.setSmallSize(boxSize);
    while (boxMaxSizeLevel.size() <= level) {
      boxMaxSizeLevel.add(new Dimension(0, 0));
    }
    final Dimension levSiz = boxMaxSizeLevel.get(level);
    updateMax(boxSize, max);
    updateMax(boxSize, levSiz);
    final int newLevel = level + 1;
    for (final UnitView child : box) {
      calcBoxMaximumSize(graphic, child, max, newLevel, boxMaxSizeLevel);
    }
  }

  /**
   * Gets the box render.
   * 
   * @return the boxRender
   */
  public BoxRender getBoxRender() {
    return boxRender;
  }

  /**
   * Gets the line render.
   * 
   * @return the lineRender
   */
  public LineRender getLineRender() {
    return lineRender;
  }

  /**
   * Gets the organigram view.
   * 
   * @return the organigramView
   */
  public OrganigramView getOrganigramView() {
    return organigramView;
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

  /**
   * Layout boxes.
   *
   * @param orgLay the org lay
   * @param unit the unit
   * @param parent the parent
   * @param level the level
   * @param rect the rect
   */
  abstract public void layoutBoxes(final OrganigramLayout orgLay, final UnitView unit, final UnitView parent, final int level, Dimension rect);

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.view.render.GenericRender#layoutBoxes()
   */
  /**
   * Layout boxes.
   * 
   * @return the dimension
   */
  public Dimension layoutBoxes() {
    final Dimension rect = new Dimension(0, 0);
    final UnitView unit = organigramView.root;
    final OrganigramLayout orgLay = organigramView.getOrganigram().getOrganigramLayout();
    layoutBoxes(orgLay, unit, null, 0, rect);
    if (flipX) {
      unit.flipX(rect.width, true);
    }
    if (flipY) {
      unit.flipY(rect.height, true);
    }
    return rect;
  }

  /**
   * Layout boxes.
   * 
   * @param graphic the graphic
   * @return the dimension
   */
  private Dimension layoutBoxes(final Graphics graphic) {
    final Dimension organigramMaxSize = new Dimension();
    if (organigramView.root == null) {
      final OrganigramLayout orgLay = organigramView.getOrganigram().getOrganigramLayout();
      final Insets margin = orgLay.getMargin();
      organigramMaxSize.setSize(margin.left + margin.right, margin.top + margin.bottom);
    }
    else {
      pointLevel.clear();
      updateBoxSize(graphic);
      final Dimension size = layoutBoxes();
      organigramMaxSize.setSize(size);
    }
    return organigramMaxSize;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.fbergeron.organigram.view.render.OrganigramRender#doLayout(java.awt
   * .Graphics2D)
   */
  @Override
  public Dimension doLayout(final Graphics2D graphics) {
    final Dimension size = layoutBoxes(graphics);
    return size;
  }

  /**
   * Paint.
   * 
   * @param graphics the graphics
   * @param size the size
   */
  @Override
  public void paint(final Graphics2D graphics, final Dimension size) {
    final OrganigramLayout orgLay = organigramView.getOrganigram().getOrganigramLayout();
    // Draw background.
    graphics.setColor(orgLay.getBackgroundColor());
    graphics.fillRect(0, 0, size.width, size.height);
    if (organigramView.root != null) {
      paintBox(organigramView.root, graphics, orgLay);
    }
  }

  /**
   * Paint the organigram box.
   * 
   * @param box the box
   * @param graphics the graphics
   * @param orgLay the org lay
   */
  private void paintBox(final UnitView box, final Graphics2D graphics, final OrganigramLayout orgLay) {
    boxRender.paint(graphics, box, orgLay);
    if (box.hasChildren()) {
      lineRender.paint(graphics, box, orgLay);
      // Draw the children boxes.
      for (final UnitView child : box) {
        paintBox(child, graphics, orgLay);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.fbergeron.organigram.view.render.OrganigramRender#setBoxRender(com.
   * fbergeron.organigram.view.render.BoxRender)
   */
  @Override
  public void setBoxRender(final BoxRender boxRender) {
    this.boxRender = boxRender;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.fbergeron.organigram.view.render.OrganigramRender#setLineRender(com
   * .fbergeron.organigram.view.render.LineRender)
   */
  @Override
  public void setLineRender(final LineRender lineRender) {
    this.lineRender = lineRender;
  }

  /**
   * Sets the organigram view.
   * 
   * @param organigramView the organigramView to set
   */
  public void setOrganigramView(final OrganigramView organigramView) {
    this.organigramView = organigramView;
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

}
