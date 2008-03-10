/**
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
 * Copyright (C) 2006-2008 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the:
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110-1301
 *   USA
 */
package com.fbergeron.organigram.view;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;

public class OrganigramView extends JPanel {

  private static final long serialVersionUID = 1L;

  private Organigram organigram;
  private UnitView root;
  private final LinkManager linkManager = new LinkManager(this);
  private Dimension preferredSize = new Dimension(100, 100);
  private boolean isLayoutNeeded = true;
  private Graphics g;
  private Dimension _boxMaxSize = null;
  private final ArrayList<Dimension> _boxMaxSizeLevel = new ArrayList<Dimension>();

  public OrganigramView(final Organigram organigram) {
    this.organigram = organigram;
    setRootUnit(organigram.getRoot());
    addMouseMotionListener(linkManager);
    addMouseListener(linkManager);
  }

  public Unit getRootUnit() {
    if (root == null) { return (null); }
    return (root.getUnit());
  }

  public UnitView getRootUnitView() {
    return root;
  }

  public void setRootUnit(final Unit rootUnit) {
    root = initUnitTreeRec(rootUnit);
  }

  @Override
  public Dimension getPreferredSize() {
    return preferredSize;
  }

  @Override
  public void setPreferredSize(final Dimension preferredSize) {
    this.preferredSize = preferredSize;
    revalidate();
  }

  @Override
  public String getToolTipText(final MouseEvent event) {
    if (organigram.getOrganigramLayout().isToolTipEnabled()) { return (super.getToolTipText(event)); }
    return null;
  }

  @Override
  public void paint(final Graphics g) {
    final Dimension dim = this.getSize();
    // Draw background.
    g.setColor(organigram.getOrganigramLayout().getBackgroundColor());
    g.fillRect(0, 0, dim.width, dim.height);
    if (isLayoutNeeded) {
      layoutBoxes(g);
      isLayoutNeeded = false;
    }
    root.paint(g);
  }

  private UnitView initUnitTreeRec(final Unit unit) {
    final UnitView box = new UnitView(unit, this);
    for (final Unit child : unit) {
      box.addChild(initUnitTreeRec(child));
    }
    return box;
  }

  public Organigram getOrganigram() {
    return organigram;
  }

  public void setOrganigram(final Organigram organigram) {
    this.organigram = organigram;
  }

  public void layoutBoxes(final Graphics g) {
    this.g = g;
    calcBoxMaximumSize();
    final Dimension organigramMaxSize = calcOrganigramMaxSize();
    calcBoxLocation(organigramMaxSize);
    setPreferredSize(new Dimension(organigramMaxSize));
  }

  private void updateMax(final Dimension x, final Dimension max) {
    if (x.width > max.width) {
      max.width = x.width;
    }
    if (x.height > max.height) {
      max.height = x.height;
    }

  }

  private Dimension calcBoxMaximum(final UnitView box, final Dimension max) {
    int width = 0;
    int height = 0;
    final Unit unit = box.getUnit();
    for (final Line element : unit.getInfo()) {
      if (element.isVisible()) {
        final FontMetrics fm = g.getFontMetrics(element.getFont());
        final int lineWidth = fm.stringWidth(element.getText());
        height += fm.getHeight();
        if (lineWidth > width) {
          width = lineWidth;
        }
      }
    }
    BoxLayout bl = unit.getBoxLayout();
    if (bl == null) {
      bl = organigram.getBoxLayout();
    }
    width += bl.getBoxLeftPadding() + bl.getBoxRightPadding();
    height += bl.getBoxTopPadding() + bl.getBoxBottomPadding();
    return new Dimension(width, height);
  }

  private void calcBoxMaximumSize(final UnitView box, final Dimension max, int level) {
    final Dimension d = calcBoxMaximum(box, max);
    final Dimension l = getBoxSize(level);
    updateMax(d, max);
    updateMax(d, l);
    level++;
    for (final UnitView child : box) {
      calcBoxMaximumSize(child, max, level);
    }
  }

  private void calcBoxMaximumSize() {
    _boxMaxSize = new Dimension(0, 0);
    _boxMaxSizeLevel.clear();
    calcBoxMaximumSize(root, _boxMaxSize, 0);
    OrganigramLayout ol = organigram.getOrganigramLayout();
    Dimension ms = null;
    for (int i = _boxMaxSizeLevel.size() - 1; i >= 0; i--) {
      final Dimension cs = _boxMaxSizeLevel.get(i);
      switch (ol.getOrgMode()) {
      case OrganigramLayout.ORGMODE_MIN:
        break;
      case OrganigramLayout.ORGMODE_GROW:
        if (ms != null) {
          updateMax(cs, ms);
          cs.setSize(ms);
        }
        else {
          ms = new Dimension(cs);
        }
        break;
      default:
        cs.setSize(_boxMaxSize);
        break;
      }
    }
  }

  private Dimension calcOrganigramMaxSize() {
    BoxSpace c;
    Dimension d;
    if (root != null) {
      c = calcOrganigramMaxRec(root, 0);
      d = new Dimension(c.full);
    }
    else {
      d = new Dimension(organigram.getOrganigramLayout().getBoxLeftMargin() + organigram.getOrganigramLayout().getBoxRightMargin(), organigram.getOrganigramLayout().getBoxTopMargin()
          + organigram.getOrganigramLayout().getBoxBottomMargin());
    }
    return d;
  }

  private BoxSpace calcOrganigramMaxRec(final UnitView box, int level) {
    final Dimension d = getBoxSize(level);
    boolean bidir = false;
    OrganigramLayout ol = organigram.getOrganigramLayout();
    int boxHeight = ol.getBoxTopMargin() + d.height + ol.getBoxBottomMargin();
    int boxWidth = ol.getBoxLeftMargin() + d.width + ol.getBoxRightMargin();
    int childHeight = 0;
    int childWidth = 0;
    int bonusWidth = 0;
    int bonusHeight = 0;
    int bonusSibillingX = 0;
    int bonusSibillingY = 0;
    boolean smallChild = false;
    Dimension dimFull = new Dimension(boxWidth, boxHeight);
    Dimension dimSmall = new Dimension(boxWidth, boxHeight);
    if (box.countChildren() > 0) {
      level++;
      if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
        BoxSpace c = null;
        int h;
        for (final UnitView childBox : box) {
          c = calcOrganigramMaxRec(childBox, level);
          childWidth += c.small.width;
          h = c.small.height;
          if (h > childHeight) {
            childHeight = h;
          }
        }
        if (boxWidth > childWidth) {
          bonusWidth = childWidth;
          smallChild = true;
        }
        else {
          bonusWidth = boxWidth;
        }
      }
      else {
        int w;
        for (final UnitView childBox : box) {
          final BoxSpace c = calcOrganigramMaxRec(childBox, level);
          childHeight += c.small.height;
          w = c.small.width;
          if (w > childWidth) {
            childWidth = w;
          }
        }
        if (boxHeight > childHeight) {
          bonusHeight = childHeight;
          smallChild = true;
        }
        else {
          bonusHeight = boxHeight;
        }
      }
      if ((ol.getOrgCompact() == OrganigramLayout.ORGCOMPACT_YES) && (!smallChild && (box.countChildren() > 1))) {
        // Experimental
        // does not work correctly
        UnitView sx = box.getLeft();
        UnitView dx = box.getRight();
        boolean ok1;
        boolean ok2;
        while (true) {
          boolean sxSpace = (sx != null) && (sx.countChildren() == 0) && (!sx.isSpaceUsed());
          boolean dxSpace = (dx != null) && (dx.countChildren() == 0) && (!dx.isSpaceUsed());
          ok1 = false;
          ok2 = false;
          if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
            if (sxSpace && (dxSpace | (dx == null))) {
              bonusSibillingX += boxWidth;
              sx.setSpaceUsed(true);
              ok1 = true;
            }
            if (dxSpace && (sxSpace | (sx == null))) {
              if (bidir) {
                bonusSibillingX += boxWidth;
                dx.setSpaceUsed(true);
              }
              ok2 = true;
            }
          }
          else {
            if (sxSpace && (dxSpace | (dx == null))) {
              bonusSibillingY += boxHeight;
              sx.setSpaceUsed(true);
              ok1 = true;
            }
            if (dxSpace && (sxSpace | (sx == null))) {
              if (bidir) {
                bonusSibillingY += boxHeight;
                dx.setSpaceUsed(true);
              }
              ok2 = true;
            }
          }
          if (!ok1 & !ok2) {
            break;
          }
          sx = (sx != null ? sx.getLeft() : null);
          dx = (dx != null ? dx.getRight() : null);
        }
      }
      dimFull = new Dimension(boxWidth + childWidth - bonusWidth, boxHeight + childHeight - bonusHeight);
      dimSmall = new Dimension(Math.max(dimFull.width - bonusSibillingX, boxWidth), Math.max(dimFull.height - bonusSibillingY, boxHeight));
    }
    BoxSpace size = new BoxSpace(dimSmall, dimFull);
    box.setRequiredSize(size, smallChild);
    return size;
  }

  private void calcBoxLocation(final Dimension organigramMaxSize) {
    int w;
    int h;
    OrganigramLayout ol = organigram.getOrganigramLayout();
    final Dimension d = getBoxSize(0);
    if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
      w = organigramMaxSize.width;
      h = ol.getBoxTopMargin() + d.height + ol.getBoxBottomMargin();
    }
    else {
      w = ol.getBoxLeftMargin() + d.width + ol.getBoxRightMargin();
      h = organigramMaxSize.height;
    }
    if (root != null) {
      calcBoxLocationRec(root, new Rectangle(0, 0, w, h), 0);
    }
  }

  private void calcBoxLocationRec(final UnitView box, final Rectangle rect, int level) {
    Point location;
    OrganigramLayout ol = organigram.getOrganigramLayout();
    final Dimension d = getBoxSize(level);
    final int w = ol.getBoxLeftMargin() + d.width + ol.getBoxRightMargin();
    final int h = ol.getBoxTopMargin() + d.height + ol.getBoxBottomMargin();
    if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
      location = new Point(rect.x + (rect.width - d.width) / 2, rect.y + ol.getBoxTopMargin());
    }
    else {
      location = new Point(rect.x + ol.getBoxLeftMargin(), rect.y + (rect.height - d.height) / 2);
    }
    box.setLocation(location, new Point(rect.x, rect.y));
    box.setSize(d);
    int x = rect.x;
    int y = rect.y;
    level++;
    int offset = 0;
    if (box.smallChild) {
      final Dimension dc = getBoxSize(level);
      final int nc = box.countChildren();
      if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
        final int wc = ol.getBoxLeftMargin() + dc.width + ol.getBoxRightMargin();
        offset = (box.getFullWidth() - wc * nc) / (nc + 1);
      }
      else {
        final int hc = ol.getBoxTopMargin() + dc.height + ol.getBoxBottomMargin();
        offset = (box.getFullHeight() - hc * nc) / (nc + 1);
      }
    }
    for (final UnitView childBox : box) {
      final Rectangle childRec;
      if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
        x -= (childBox.getFullWidth() - childBox.getSmallWidth()) / 2;
      }
      else {
        y -= (childBox.getFullHeight() - childBox.getSmallHeight()) / 2;
      }
      if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
        childRec = new Rectangle(x + offset, rect.y + h, childBox.getFullWidth(), rect.height);
      }
      else {
        childRec = new Rectangle(x + w, y + offset, rect.width, childBox.getFullHeight());
      }
      calcBoxLocationRec(childBox, childRec, level);
      if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
        x += childBox.getSmallWidth() + offset;
      }
      else {
        y += childBox.getSmallHeight() + offset;
      }
    }
  }

  public Dimension getBoxSize(final int level) {
    while (_boxMaxSizeLevel.size() <= level) {
      _boxMaxSizeLevel.add(new Dimension(0, 0));
    }
    return _boxMaxSizeLevel.get(level);
  }

}
