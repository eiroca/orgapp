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
  public Unit unit;

  /** The layout. */
  public BoxLayout layout;

  /** The organigram view. */
  public OrganigramView organigramView;

  /** The parent. */
  private UnitView parent = null;

  /** The children. */
  public List<UnitView> children = new ArrayList<UnitView>();

  /** The small child. */
  private boolean smallChild = false;

  /** The box rectangle. */
  public transient final Rectangle boxRect = new Rectangle(0, 0, 0, 0);

  /** The space used. */
  private transient boolean spaceUsed = false;

  /** The minimum size. */
  transient public Dimension small = new Dimension();

  /**
   * Instantiates a new unit view.
   * 
   * @param unit the unit
   * @param organigramView the organigram view
   */
  public UnitView(final Unit unit, final OrganigramView organigramView) {
    this.unit = unit;
    this.organigramView = organigramView;
    layout = unit.getBoxLayout();
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

  /**
   * Node can collapse?
   * 
   * @return true, if yes
   */
  public boolean canCollapse() {
    return (layout.getExpanded(true) && (!children.isEmpty()));
  }

  /**
   * Node can expand?
   * 
   * @return true, if yes
   */
  public boolean canExpand() {
    return (!layout.getExpanded(true) && (!children.isEmpty()));
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
   * Count children.
   * 
   * @return the int
   */
  public int countChildren() {
    return children.size();
  }

  /**
   * Flip x.
   * 
   * @param xFlip the x flip
   * @param recursive the recursive
   */
  public void flipX(final int xFlip, final boolean recursive) {
    boxRect.x = xFlip - boxRect.width - boxRect.x;
    if (recursive && !children.isEmpty()) {
      for (final UnitView child : this) {
        child.flipX(xFlip, recursive);
      }
    }
  }

  /**
   * Flip y.
   * 
   * @param yFlip the y flip
   * @param recursive the recursive
   */
  public void flipY(final int yFlip, final boolean recursive) {
    boxRect.y = yFlip - boxRect.height - boxRect.y;
    if (recursive && !children.isEmpty()) {
      for (final UnitView child : this) {
        child.flipY(yFlip, recursive);
      }
    }
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
   * Gets the height.
   * 
   * @return the height
   */
  public int getHeight() {
    return boxRect.height;
  }

  /**
   * Gets the layout.
   * 
   * @return the layout
   */
  public BoxLayout getLayout() {
    return layout;
  }

  /**
   * Gets the location.
   * 
   * @return the location
   */
  public Point getLocation() {
    return boxRect.getLocation();
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
   * Gets the parent.
   * 
   * @return the parent
   */
  public UnitView getParent() {
    return parent;
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
   * Gets the small height.
   * 
   * @return the small height
   */
  public void setSmallSize(final Dimension d) {
    small.setSize(d);
  }

  /**
   * Gets the space.
   * 
   * @return the space
   */
  public Dimension getSmallSize() {
    return small;
  }

  /**
   * Gets the unit.
   * 
   * @return the unit
   */
  public Unit getUnit() {
    return unit;
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
   * Checks for children.
   * 
   * @return true, if successful
   */
  public boolean hasChildren() {
    return layout.getExpanded(true) ? !children.isEmpty() : false;
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
   * Checks if is space used.
   * 
   * @return true, if is space used
   */
  public boolean isSpaceUsed() {
    return spaceUsed;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  public Iterator<UnitView> iterator() {
    return children.iterator();
  }

  /**
   * Move.
   * 
   * @param deltaX the delta X
   * @param deltaY the delta Y
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

  /**
   * Reset.
   */
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
   * Sets the children.
   * 
   * @param children the children to set
   */
  public void setChildren(final List<UnitView> children) {
    this.children = children;
  }

  /**
   * Sets the layout.
   * 
   * @param layout the layout to set
   */
  public void setLayout(final BoxLayout layout) {
    this.layout = layout;
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

  /**
   * Sets the organigram view.
   * 
   * @param organigramView the organigramView to set
   */
  public void setOrganigramView(final OrganigramView organigramView) {
    this.organigramView = organigramView;
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
   * Sets the size.
   * 
   * @param size the new size
   */
  public void setSize(final Dimension size) {
    boxRect.width = size.width;
    boxRect.height = size.height;
  }

  /**
   * Sets the small child.
   * 
   * @param smallChild the smallChild to set
   */
  public void setSmallChild(final boolean smallChild) {
    this.smallChild = smallChild;
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
   * Sets the unit.
   * 
   * @param unit the unit to set
   */
  public void setUnit(final Unit unit) {
    this.unit = unit;
  }

  /**
   * Update packed.
   */
  final public boolean isPacked() {
    return ((small.width != boxRect.width) || (small.height != boxRect.height));
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final StringBuffer str = new StringBuffer(64);
    str.append('(').append(unit).append(", x=").append(boxRect.x).append(", y=").append(boxRect.y);
    str.append(" [ ");
    for (final UnitView child : this) {
      str.append(child.toString()).append(", ");
    }
    str.append(" ])");
    return str.toString();
  }

}
