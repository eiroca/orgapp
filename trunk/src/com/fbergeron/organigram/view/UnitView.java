/**
 * LGPL > 3.0 Copyright (C) 2005 Frédéric Bergeron
 * (fbergeron@users.sourceforge.net) Copyright (C) 2006-2010 eIrOcA (eNrIcO
 * Croce & sImOnA Burzio)
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
package com.fbergeron.organigram.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Unit;

/**
 * UnitView handles the box rendering.
 */
public class UnitView implements Iterable<UnitView> {

  /** The unit. */
  public final Unit unit;
  public final BoxLayout layout;

  /** The organigram view. */
  public final OrganigramView organigramView;

  /** The parent. */
  private UnitView parent = null;

  /** The children. */
  public final List<UnitView> children = new ArrayList<UnitView>();

  /** The small child. */
  private boolean smallChild = false;

  /** The box rect. */
  public final Rectangle boxRect = new Rectangle(0, 0, 0, 0);

  /** The space used. */
  private boolean spaceUsed = false;

  /** The space. */
  public final BoxSpace space = new BoxSpace();

  /**
   * Instantiates a new unit view.
   *
   * @param unit the unit
   * @param organigramView the organigram view
   */
  public UnitView(final Unit unit, final OrganigramView organigramView) {
    this.unit = unit;
    this.organigramView = organigramView;
    final BoxLayout boxLay = unit.getBoxLayout();
    if (boxLay != null) {
      layout = boxLay;
    }
    else {
      layout = new BoxLayout(organigramView.getOrganigram().getBoxLayout());
    }
  }

  /**
   * Gets the unit.
   *
   * @return the unit
   */
  public Unit getUnit() {
    return unit;
  }

  public BoxLayout getLayout() {
    return layout;
  }

  /**
   * Contains.
   *
   * @param point the point
   *
   * @return true, if successful
   */
  public boolean contains(final Point point) {
    return boxRect.contains(point);
  }

  /**
   * Gets the height.
   *
   * @return the height
   */
  public int getHeight() {
    return boxRect.height;
  }

  /**
   * Gets the width.
   *
   * @return the width
   */
  public int getWidth() {
    return boxRect.width;
  }

  /**
   * Sets the size.
   *
   * @param size the new size
   */
  public void setSize(final Dimension size) {
    boxRect.width = size.width;
    boxRect.height = size.height;
  }

  /**
   * Sets the location.
   *
   * @param boxPos the box position
   */
  public void setLocation(final Point boxPos) {
    boxRect.x = boxPos.x;
    boxRect.y = boxPos.y;
  }

  public void reset() {
    boxRect.x = 0;
    boxRect.y = 0;
    boxRect.width = 0;
    boxRect.height = 0;
    for (final UnitView child : this) {
      child.reset();
    }
  }

  /**
   * Gets the parent.
   *
   * @return the parent
   */
  public UnitView getParent() {
    return parent;
  }

  /**
   * Adds the child.
   *
   * @param child the child
   */
  public void addChild(final UnitView child) {
    children.add(child);
    child.setParent(this);
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  public Iterator<UnitView> iterator() {
    return children.iterator();
  }

  public boolean hasChildren() {
    if (layout.isExpanded()) { return (children.size() > 0); }
    return false;
  }

  /**
   * Count children.
   *
   * @return the int
   */
  public int countChildren() {
    return children.size();
  }

  /**
   * Sets the parent.
   *
   * @param parent the new parent
   */
  private void setParent(final UnitView parent) {
    this.parent = parent;
  }

  /**
   * Gets the full height.
   *
   * @return the full height
   */
  public int getFullHeight() {
    return space.full.height;
  }

  /**
   * Gets the small height.
   *
   * @return the small height
   */
  public int getSmallHeight() {
    return space.small.height;
  }

  /**
   * Gets the full width.
   *
   * @return the full width
   */
  public int getFullWidth() {
    return space.full.width;
  }

  /**
   * Gets the small width.
   *
   * @return the small width
   */
  public int getSmallWidth() {
    return space.small.width;
  }

  /**
   * Sets the required size.
   *
   * @param size the size
   * @param smallChild the small child
   */
  public void setRequiredSize(final BoxSpace size, final boolean smallChild) {
    space.small.setSize(size.small);
    space.full.setSize(size.full);
    this.smallChild = smallChild;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final StringBuffer str = new StringBuffer(64);
    str.append('(').append(unit).append(", ").append("x=").append(boxRect.x);
    str.append(", y=").append(boxRect.y).append(" [ ");
    for (final UnitView child : this) {
      str.append(child.toString()).append(", ");
    }
    str.append(" ] )");
    return str.toString();
  }

  /**
   * Gets the box pos.
   *
   * @return the box pos
   */
  public Point getLocation() {
    return boxRect.getLocation();
  }

  /**
   * Gets the box rect.
   *
   * @return the box rect
   */
  public Rectangle getBoxRect() {
    return boxRect;
  }

  /**
   * Gets the children.
   *
   * @return the children
   */
  public List<UnitView> getChildren() {
    return children;
  }

  /**
   * Gets the organigram view.
   *
   * @return the organigram view
   */
  public OrganigramView getOrganigramView() {
    return organigramView;
  }

  /**
   * Gets the size.
   *
   * @return the size
   */
  public Dimension getSize() {
    return boxRect.getSize();
  }

  /**
   * Checks if is small child.
   *
   * @return true, if is small child
   */
  public boolean isSmallChild() {
    return smallChild;
  }

  /**
   * Gets the space.
   *
   * @return the space
   */
  public BoxSpace getSpace() {
    return space;
  }

  /**
   * Checks if is space used.
   *
   * @return true, if is space used
   */
  public boolean isSpaceUsed() {
    return spaceUsed;
  }

  /**
   * Sets the space used.
   *
   * @param spaceUsed the new space used
   */
  public void setSpaceUsed(final boolean spaceUsed) {
    this.spaceUsed = spaceUsed;
  }

  /**
   * Move.
   *
   * @param deltaX the dx
   * @param deltaY the dy
   * @param recursive the recursive
   */
  public void move(final int deltaX, final int deltaY, final boolean recursive) {
    boxRect.x += deltaX;
    boxRect.y += deltaY;
    if (recursive && !children.isEmpty()) {
      for (final UnitView child : this) {
        child.move(deltaX, deltaY, recursive);
      }
    }
  }

  public boolean canExpand() {
    return (!layout.isExpanded() && (countChildren() > 0));
  }

  public void flipX(final int xFlip, final boolean recursive) {
    boxRect.x = xFlip - boxRect.width - boxRect.x;
    if (recursive && !children.isEmpty()) {
      for (final UnitView child : this) {
        child.flipX(xFlip, recursive);
      }
    }
  }

  public void flipY(final int yFlip, final boolean recursive) {
    boxRect.y = yFlip - boxRect.height - boxRect.y;
    if (recursive && !children.isEmpty()) {
      for (final UnitView child : this) {
        child.flipY(yFlip, recursive);
      }
    }
  }

}
