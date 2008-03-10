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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;

public class UnitView implements Iterable<UnitView> {

  static private final boolean showFullSpace = false;
  static private final boolean showSmallSpace = false;

  private final Unit unit;
  private final OrganigramView organigramView;

  private UnitView parent = null;
  private UnitView left = null;
  private UnitView right = null;

  private UnitView lastChild = null;

  private final Vector<UnitView> children = new Vector<UnitView>();

  public boolean smallChild = false;

  private final Dimension size = new Dimension(0, 0);
  private Rectangle boxRect = new Rectangle(0, 0, 0, 0);
  private Point boxPos;
  private Point boxCorner;
  private boolean spaceUsed = false;

  private final BoxSpace space = new BoxSpace();

  public UnitView(final Unit unit, final OrganigramView organigramView) {
    this.unit = unit;
    this.organigramView = organigramView;
  }

  public Unit getUnit() {
    return unit;
  }

  public boolean contains(final Point p) {
    return boxRect.contains(p);
  }

  public void buildRect() {
    if (boxPos == null) {
      boxRect = new Rectangle(0, 0, 0, 0);
    }
    else {
      boxRect = new Rectangle(boxPos.x, boxPos.y, size.width, size.height);
    }
  }

  public int getHeight() {
    return size.height;
  }

  public int getWidth() {
    return size.width;
  }

  public void setSize(final Dimension size) {
    this.size.setSize(size);
    buildRect();
  }

  public void setLocation(final Point boxPos, Point boxCorner) {
    this.boxPos = boxPos;
    this.boxCorner = boxCorner;
    buildRect();
  }

  public UnitView getParent() {
    return parent;
  }

  public void addChild(final UnitView child) {
    if (lastChild != null) {
      lastChild.right = child;
      child.left = lastChild;
    }
    lastChild = child;
    children.addElement(child);
    child.setParent(this);

  }

  public Iterator<UnitView> iterator() {
    return children.iterator();
  }

  public int countChildren() {
    return children.size();
  }

  private void setParent(final UnitView parent) {
    this.parent = parent;
  }

  public int getFullHeight() {
    return space.full.height;
  }

  public int getSmallHeight() {
    return space.small.height;
  }

  public int getFullWidth() {
    return space.full.width;
  }

  public int getSmallWidth() {
    return space.small.width;
  }

  public void setRequiredSize(final BoxSpace size, final boolean smallChild) {
    space.small.setSize(size.small);
    space.full.setSize(size.full);
    this.smallChild = smallChild;
  }

  public void paint(final Graphics g) {
    final Organigram organigram = organigramView.getOrganigram();
    // Draw the box.
    final Point pos = boxPos;
    BoxLayout bl = unit.getBoxLayout();
    if (bl == null) {
      bl = organigram.getBoxLayout();
    }
    OrganigramLayout ol = organigram.getOrganigramLayout();
    g.setColor(bl.getBoxBackgroundColor());
    g.fillRect(pos.x, pos.y, size.width, size.height);
    final String role = unit.getMeta("role");
    g.setColor(bl.getBoxFrameColor());
    if (role != null) {
      if (role.startsWith("H")) {
        g.drawRect(pos.x, pos.y, size.width, size.height);
        g.drawRect(pos.x - 1, pos.y - 1, size.width + 2, size.height + 2);
      }
      else {
        g.drawRect(pos.x, pos.y, size.width, size.height);
      }
    }
    else {
      g.drawRect(pos.x, pos.y, size.width, size.height);
    }
    // Draw the text
    int x;
    int y;
    y = pos.y + bl.getBoxTopPadding();
    final Vector<Line> lines = unit.getInfo();
    FontMetrics fm;
    for (int i = 0; i < lines.size(); i++) {
      final Line line = lines.elementAt(i);
      if (line.isVisible()) {
        if (line.getColor() != null) {
          g.setColor(line.getColor());
        }
        else {
          g.setColor(bl.getBoxForegroundColor());
        }
        if (line.getFont() != null) {
          g.setFont(line.getFont());
          fm = g.getFontMetrics(line.getFont());
        }
        else {
          fm = g.getFontMetrics();
        }
        final int textWidth = fm.stringWidth(line.getText());
        if (bl.getBoxTextAlignment() == Label.CENTER) {
          x = pos.x + (size.width - textWidth) / 2;
        }
        else if (bl.getBoxTextAlignment() == Label.RIGHT) {
          x = pos.x + size.width - textWidth - bl.getBoxRightPadding();
        }
        else {
          x = pos.x + bl.getBoxLeftPadding();
        }
        g.drawString(line.getText(), x, y + fm.getAscent() + fm.getLeading());
        y += fm.getHeight();
      }
    }
    // Draw the lines.
    if (!children.isEmpty()) {
      g.setColor(ol.getLineColor());
      if (ol.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
        int horizLineX1 = -1;
        int horizLineX2 = -1;
        x = pos.x + getWidth() / 2;
        g.drawLine(x, pos.y + getHeight(), x, pos.y + getHeight() + ol.getBoxBottomMargin());
        for (final UnitView child : this) {
          final Point childLocation = child.boxPos;
          x = childLocation.x + child.getWidth() / 2;
          g.drawLine(x, childLocation.y - ol.getBoxTopMargin(), x, childLocation.y);
          if (horizLineX1 == -1) {
            horizLineX1 = x;
          }
          horizLineX2 = x;
        }
        y = pos.y + getHeight() + ol.getBoxBottomMargin();
        g.drawLine(horizLineX1, y, horizLineX2, y);
      }
      else {
        int horizLineY1 = -1;
        int horizLineY2 = -1;
        y = pos.y + getHeight() / 2;
        x = pos.x + getWidth() + ol.getBoxRightMargin();
        g.drawLine(pos.x + getWidth(), y, x, y);
        for (final UnitView child : this) {
          final Point childLocation = child.boxPos;
          y = childLocation.y + child.getHeight() / 2;
          g.drawLine(childLocation.x - ol.getBoxLeftMargin(), y, childLocation.x, y);
          if (horizLineY1 == -1) {
            horizLineY1 = y;
          }
          horizLineY2 = y;
        }
        g.drawLine(x, horizLineY1, x, horizLineY2);
      }
    }
    // Draw the children boxes.
    for (final UnitView child : this) {
      child.paint(g);
    }
    // Debug Frame
    if (showFullSpace) {
      Unit u = getUnit();
      if (u.getID() != null) {
        if (children.size() > 0) {
          int xx = boxCorner.x;
          int yy = boxCorner.y;
          int c1 = (int) (Math.random() * 256);
          int c2 = (int) (Math.random() * 256);
          int c3 = (int) (Math.random() * 256);
          g.setColor(new Color(c1, c2, c3));
          g.drawRect(xx, yy, space.full.width, space.full.height);
        }
      }
    }
    if (showSmallSpace) {
      Unit u = getUnit();
      if (u.getID() != null) {
        int xx = boxCorner.x;
        int yy = boxCorner.y;
        int c1 = (int) (Math.random() * 256);
        int c2 = (int) (Math.random() * 256);
        int c3 = (int) (Math.random() * 256);
        g.setColor(new Color(c1, c2, c3));
        xx += (space.full.width - space.small.width) / 2;
        yy += (space.full.height - space.small.height) / 2;
        if ((space.small.width != space.full.width) | (space.small.height != space.full.height)) {
          g.drawRect(xx, yy, space.full.width, space.full.height);
          g.setColor(new Color(c1 / 2, c2 / 2, c3 / 2));
          g.drawRect(xx, yy, space.small.width, space.small.height);
          g.drawRect(xx - 1, yy - 1, space.small.width + 2, space.small.height + 2);
        }
      }
    }
  }

  @Override
  public String toString() {
    final StringBuffer str = new StringBuffer();
    str.append("(").append(unit).append(", ");
    if (boxPos == null) {
      str.append("x=undefined, y=undefined ");
    }
    else {
      str.append("x=" + boxPos.x + ", y=" + boxPos.y + " ");
    }
    str.append("[ ");
    for (final UnitView child : this) {
      str.append(child.toString()).append(", ");
    }
    str.append(" ] )");
    return (str.toString());
  }

  public UnitView getLeft() {
    return left;
  }

  public UnitView getRight() {
    return right;
  }

  public boolean isSpaceUsed() {
    return spaceUsed;
  }

  public void setSpaceUsed(boolean spaceUsed) {
    this.spaceUsed = spaceUsed;
  }

}
