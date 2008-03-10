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

public class Organigram implements MetaDataCollector {

  private BoxLayout boxLayout = new BoxLayout();
  private OrganigramLayout orgLayout = new OrganigramLayout();
  private Unit root;

  private final HashMap<String, String> meta = new HashMap<String, String>();

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public Organigram() {
    //
  }

  public BoxLayout getBoxLayout() {
    return boxLayout;
  }

  public OrganigramLayout getOrganigramLayout() {
    return orgLayout;
  }

  public void setMeta(final String name, final String value) {
    meta.put(name, value);
  }

  public String getMeta(final String name) {
    return meta.get(name);
  }

  public HashMap<String, String> getMeta() {
    return meta;
  }

  public Unit getRoot() {
    return root;
  }

  public void setRoot(final Unit root) {
    this.root = root;
  }

  public Unit findByID(final String ID, final boolean exact) {
    final Unit u = root.findByID(ID, exact);
    return (u != null ? u : root);
  }

  public void removeID() {
    root.removeID();
  }

  public void buidID() {
    root.buildID("", 0);
  }

  public void execute(final UnitTraversal action, final boolean nodeFirst) {
    root.execute(action, nodeFirst);
  }

}
