/** LGPL > 3.0
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
package com.fbergeron.organigram.view.render.line;

import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.render.LineRender;

/**
 * The Class AbstractLineRender.
 */
public abstract class AbstractLineRender implements LineRender {

  /** The organigram lay. */
  protected OrganigramLayout organigramLayout;

  /** The anchor parent. */
  protected Layout anchorParent;

  /** The anchor child. */
  protected Layout anchorChild;

  public AbstractLineRender(final Layout anchorParent, final Layout anchorChild) {
    super();
    this.anchorParent = anchorParent;
    this.anchorChild = anchorChild;
  }

  /**
   * Gets the organigram layout.
   * 
   * @return the organigram layout
   */
  public OrganigramLayout getOrganigramLayout() {
    return organigramLayout;
  }

  /**
   * Sets the organigram layout.
   * 
   * @param orgLay the new organigram layout
   */
  public void setOrganigramLayout(final OrganigramLayout orgLay) {
    organigramLayout = orgLay;
  }

  /**
   * Gets the anchor parent.
   * 
   * @return the anchor parent
   */
  public Layout getAnchorParent() {
    return anchorParent;
  }

  /**
   * Sets the anchor parent.
   * 
   * @param anchorParent the new anchor parent
   */
  public void setAnchorParent(final Layout anchorParent) {
    this.anchorParent = anchorParent;
  }

  /**
   * Gets the anchor child.
   * 
   * @return the anchor child
   */
  public Layout getAnchorChild() {
    return anchorChild;
  }

  /**
   * Sets the anchor child.
   * 
   * @param anchorChild the new anchor child
   */
  public void setAnchorChild(final Layout anchorChild) {
    this.anchorChild = anchorChild;
  }

}