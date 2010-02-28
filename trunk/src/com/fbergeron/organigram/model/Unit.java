/**
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
 * Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 * 
 */
package com.fbergeron.organigram.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Unit if the model of a box in an organigram.
 */
public class Unit implements Iterable<Unit>, MetaDataCollector {

  /** The ID. */
  private String unitID;

  /** The parent. */
  private Unit parent;

  /** The children. */
  private final List<Unit> children = new ArrayList<Unit>();

  /** The info. */
  private final List<Line> info = new ArrayList<Line>();

  /** The box layout. */
  private BoxLayout boxLayout = null;

  /** The meta. */
  private final Map<String, String> meta = new HashMap<String, String>();

  /**
   * Gets the info.
   * 
   * @return the info
   */
  public List<Line> getInfo() {
    return info;
  }

  /**
   * Adds the info.
   * 
   * @param line the line
   */
  public void addInfo(final Line line) {
    info.add(line);
  }

  /**
   * Gets the parent.
   * 
   * @return the parent
   */
  public Unit getParent() {
    return parent;
  }

  /**
   * Adds the child.
   * 
   * @param child the child
   */
  public void addChild(final Unit child) {
    children.add(child);
    child.setParent(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Iterable#iterator()
   */
  public Iterator<Unit> iterator() {
    return children.iterator();
  }

  /**
   * Checks for children.
   * 
   * @return true, if successful
   */
  public boolean hasChildren() {
    return !children.isEmpty();
  }

  /**
   * Gets the children count.
   * 
   * @return the children count
   */
  public int getChildrenCount() {
    return children.size();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    if (info.isEmpty()) { return "null"; }
    return info.toString();
  }

  /**
   * Sets the parent.
   * 
   * @param parent the new parent
   */
  private void setParent(final Unit parent) {
    this.parent = parent;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.model.MetaDataCollector#setMeta(java.lang.String,
   *      java.lang.String)
   */
  public void setMeta(final String name, final String val) {
    if (val == null) {
      meta.remove(name);
    }
    else {
      meta.put(name, val);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.model.MetaDataCollector#getMeta(java.lang.String)
   */
  public String getMeta(final String name) {
    return meta.get(name);
  }

  /**
   * Gets the meta.
   * 
   * @return the meta
   */
  public Map<String, String> getMeta() {
    return meta;
  }

  /**
   * Gets the iD.
   * 
   * @return the iD
   */
  public String getID() {
    return unitID;
  }

  /**
   * Sets the id.
   * 
   * @param aID the new id
   */
  public void setID(final String aID) {
    unitID = aID;
  }

  /**
   * Find by id.
   * 
   * @param aID the iD
   * @param exact the exact
   * 
   * @return the unit
   */
  public Unit findByID(final String aID, final boolean exact) {
    Unit res = (exact ? null : this);
    if (aID.equals(getID())) {
      res = this;
    }
    else if (hasChildren()) {
      String uId;
      for (final Unit unit : this) {
        uId = unit.getID();
        if ((uId != null) && (aID.startsWith(uId))) {
          res = unit.findByID(aID, exact);
          break;
        }
      }
    }
    return res;
  }

  /**
   * Removes the id.
   */
  public void removeID() {
    setID(null);
    for (final Unit u : this) {
      u.removeID();
    }
  }

  /**
   * Builds the id.
   * 
   * @param prefix the prefix
   * @param pos the pos
   */
  public void buildID(final String prefix, final int pos) {
    final String myId = prefix + Integer.toString(pos, Character.MAX_RADIX);
    setID(myId);
    int cnt = 0;
    for (final Unit u : this) {
      cnt++;
      u.buildID(myId + ".", cnt);
    }
  }

  /**
   * Execute.
   * 
   * @param action the action
   * @param nodeFirst the node first
   */
  public void execute(final UnitTraversal action, final boolean nodeFirst) {
    final boolean more = true;
    if (nodeFirst) {
      action.process(this);
    }
    if (!more) { return; }
    for (final Unit u : this) {
      u.execute(action, nodeFirst);
    }
    if (!nodeFirst) {
      action.process(this);
    }
  }

  /**
   * Gets the box layout.
   * 
   * @return the box layout
   */
  public BoxLayout getBoxLayout() {
    return boxLayout;
  }

  /**
   * Sets the box layout.
   * 
   * @param boxLayout the new box layout
   */
  public void setBoxLayout(final BoxLayout boxLayout) {
    this.boxLayout = boxLayout;
  }

}
