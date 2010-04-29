/** LGPL > 3.0
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
 */
package com.fbergeron.organigram.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The Organigram model.
 */
public class Organigram implements MetaDataCollector {

  /** The box layout. */
  private BoxLayout boxLayout = new BoxLayout();

  /** The org layout. */
  private OrganigramLayout organigramLayout = new OrganigramLayout();

  /** The root. */
  private Unit root;

  /** The meta. */
  private final Map<String, String> meta = new HashMap<String, String>();

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Gets the box layout.
   * 
   * @return the box layout
   */
  public BoxLayout getBoxLayout() {
    return boxLayout;
  }

  /**
   * Gets the organigram layout.
   * 
   * @return the organigram layout
   */
  public OrganigramLayout getOrganigramLayout() {
    return organigramLayout;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.model.MetaDataCollector#setMeta(java.lang.String,
   *      java.lang.String)
   */
  public void setMeta(final String name, final String value) {
    meta.put(name, value);
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
   * Gets the root.
   * 
   * @return the root
   */
  public Unit getRoot() {
    return root;
  }

  /**
   * Sets the root.
   * 
   * @param root the new root
   */
  public void setRoot(final Unit root) {
    this.root = root;
  }

  /**
   * Find by id.
   * 
   * @param theID the iD
   * @param exact the exact
   * 
   * @return the unit
   */
  public Unit findByID(final String theID, final boolean exact) {
    final Unit unit = root.findByID(theID, exact);
    return (unit == null ? root : unit);
  }

  /**
   * Removes the id.
   */
  public void removeID() {
    root.removeID();
  }

  /**
   * Buid id.
   */
  public void buidID() {
    root.buildID("", 0);
  }

  /**
   * Execute.
   * 
   * @param action the action
   * @param nodeFirst the node first
   */
  public void execute(final UnitTraversal action, final boolean nodeFirst) {
    root.execute(action, nodeFirst, 0);
  }

  /**
   * @param boxLayout the boxLayout to set
   */
  public void setBoxLayout(final BoxLayout boxLayout) {
    this.boxLayout = boxLayout;
  }

  /**
   * @param organigramLayout the organigramLayout to set
   */
  public void setOrganigramLayout(final OrganigramLayout organigramLayout) {
    this.organigramLayout = organigramLayout;
  }

}
