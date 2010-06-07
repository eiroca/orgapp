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

  /** The unit ID. */
  private String id;

  /** The Owner. */
  private Organigram organigram;

  /** The children. */
  private List<Unit> children = new ArrayList<Unit>();

  /** The info. */
  private List<Line> info = new ArrayList<Line>();

  /** The box layout. */
  private BoxLayout boxLayout = null;

  /** The meta. */
  private final Map<String, String> meta = new HashMap<String, String>();

  /**
   * Instantiates a new unit.
   * 
   * @param owner the owner
   */
  public Unit(final Organigram owner) {
    setOrganigram(owner);
    boxLayout = new BoxLayout(owner.getBoxLayout());
  }

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
  public Organigram getOrganigram() {
    return organigram;
  }

  /**
   * Adds the child.
   * 
   * @param child the child
   */
  public void addChild(final Unit child) {
    children.add(child);
  }

  /*
   * (non-Javadoc)
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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String res;
    if (info.isEmpty()) {
      res = "null";
    }
    else {
      res = info.toString();
    }
    return res;
  }

  /**
   * Sets the parent.
   * 
   * @param organigram the new organigram
   */
  private void setOrganigram(final Organigram organigram) {
    this.organigram = organigram;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.model.MetaDataCollector#setMeta(java.lang.String,
   * java.lang.String)
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
   * @see
   * com.fbergeron.organigram.model.MetaDataCollector#getMeta(java.lang.String)
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
  public String getId() {
    return id;
  }

  /**
   * Sets the id.
   * 
   * @param aID the new id
   */
  public void setId(final String aID) {
    id = aID;
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
    if (aID.equals(getId())) {
      res = this;
    }
    else if (hasChildren()) {
      String uId;
      for (final Unit unit : this) {
        uId = unit.getId();
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
    setId(null);
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
    setId(myId);
    int cnt = 0;
    for (final Unit u : this) {
      cnt++;
      u.buildID(myId + ".", cnt);
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

  /**
   * Find parent.
   * 
   * @param child the child
   * @return the unit
   */
  private Unit findParent(final Unit child) {
    Unit res = null;
    for (final Unit cld : children) {
      if (cld == child) {
        res = this;
        break;
      }
      final Unit fnd = cld.findParent(child);
      if (fnd != null) {
        res = fnd;
        break;
      }
    }
    return res;
  }

  /**
   * Gets the parent.
   * 
   * @return the parent
   */
  public Unit getParent() {
    final Unit root = getOrganigram().getRoot();
    return root.findParent(this);
  }

  /**
   * Gets the children.
   * 
   * @return the children
   */
  public List<Unit> getChildren() {
    return children;
  }

  /**
   * Sets the children.
   * 
   * @param children the children to set
   */
  public void setChildren(final List<Unit> children) {
    this.children = children;
  }

  /**
   * Sets the info.
   * 
   * @param info the info to set
   */
  public void setInfo(final List<Line> info) {
    this.info = info;
  }

}
