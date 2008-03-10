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
package com.fbergeron.organigram.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Unit implements Iterable<Unit>, MetaDataCollector {

  private String ID;
  private Unit parent;
  private final Vector<Unit> children = new Vector<Unit>();
  private final Vector<Line> info = new Vector<Line>();
  private BoxLayout boxLayout = null;

  private final HashMap<String, String> meta = new HashMap<String, String>();

  public Unit() {
    //
  }

  public Vector<Line> getInfo() {
    return info;
  }

  public void addInfo(final Line line) {
    info.add(line);
  }

  public Unit getParent() {
    return parent;
  }

  public void addChild(final Unit child) {
    children.addElement(child);
    child.setParent(this);
  }

  public Iterator<Unit> iterator() {
    return children.iterator();
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }

  public int getChildrenCount() {
    return children.size();
  }

  @Override
  public String toString() {
    if (info.size() == 0) { return "null"; }
    return info.elementAt(0).getText();
  }

  private void setParent(final Unit parent) {
    this.parent = parent;
  }

  public void setMeta(final String name, final String val) {
    if (val != null) {
      meta.put(name, val);
    }
    else {
      meta.remove(name);
    }
  }

  public String getMeta(final String name) {
    return meta.get(name);
  }

  public HashMap<String, String> getMeta() {
    return meta;
  }

  public String getID() {
    return ID;
  }

  public void setID(final String id) {
    ID = id;
  }

  public Unit findByID(final String ID, final boolean exact) {
    if (ID.equals(getID())) {
      return this;
    }
    else if (hasChildren()) {
      for (final Unit u : this) {
        final String uID = u.getID();
        if (uID != null) {
          if (ID.startsWith(uID)) { return u.findByID(ID, exact); }
        }
      }
    }
    return (exact ? null : this);
  }

  public void removeID() {
    setID(null);
    for (final Unit u : this) {
      u.removeID();
    }
  }

  public void buildID(final String prefix, final int pos) {
    final String id = prefix + Integer.toString(pos, Character.MAX_RADIX);
    setID(id);
    int ps = 0;
    for (final Unit u : this) {
      ps++;
      u.buildID(id + ".", ps);
    }
  }

  public void execute(final UnitTraversal action, boolean nodeFirst) {
    boolean more = true;
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

  public BoxLayout getBoxLayout() {
    return boxLayout;
  }

  public void setBoxLayout(BoxLayout boxLayout) {
    this.boxLayout = boxLayout;
  }

}
