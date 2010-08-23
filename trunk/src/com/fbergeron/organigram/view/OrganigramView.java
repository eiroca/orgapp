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
import java.awt.Graphics2D;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.model.type.BoxMode;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.view.render.LineRender;
import com.fbergeron.organigram.view.render.OrganigramRender;
import com.fbergeron.organigram.view.render.box.ClassicBoxRender;
import com.fbergeron.organigram.view.render.box.RoundBoxRender;
import com.fbergeron.organigram.view.render.line.BezierLineRender;
import com.fbergeron.organigram.view.render.line.DirectLineRender;
import com.fbergeron.organigram.view.render.line.GenericLineRender;
import com.fbergeron.organigram.view.render.organigram.HorizontalRender;
import com.fbergeron.organigram.view.render.organigram.VerticalRender;

/**
 * OrganigramView handles the organigram layout disposition.
 */
public class OrganigramView {

  /** The organigram. */
  private Organigram organigram;

  /** The root. */
  public UnitView root;

  /** The render. */
  private transient OrganigramRender organigramRender = null;

  /**
   * Instantiates a new organigram view.
   * 
   * @param organigram the organigram
   */
  public OrganigramView(final Organigram organigram) {
    super();
    this.organigram = organigram;
    root = initUnitTreeRec(organigram.getRoot());
    Layout anchorParent;
    Layout anchorChild;
    Layout anchorCollapsed;
    final OrganigramLayout orgLay = organigram.getOrganigramLayout();
    LineRender lineRender;
    ClassicBoxRender boxRender;
    switch (orgLay.getLayout()) {
      case LEFT:
        anchorParent = Layout.LEFT;
        anchorChild = Layout.RIGHT;
        anchorCollapsed = Layout.RIGHT;
        organigramRender = new VerticalRender(this, orgLay.isCompact(), false, false);
        break;
      case RIGHT:
        anchorParent = Layout.RIGHT;
        anchorChild = Layout.LEFT;
        anchorCollapsed = Layout.LEFT;
        organigramRender = new VerticalRender(this, orgLay.isCompact(), true, false);
        break;
      case BOTTOM:
        anchorParent = Layout.TOP;
        anchorChild = Layout.BOTTOM;
        anchorCollapsed = Layout.TOP;
        organigramRender = new HorizontalRender(this, orgLay.isCompact(), false, true);
        break;
      default: // TOP
        anchorParent = Layout.BOTTOM;
        anchorChild = Layout.TOP;
        anchorCollapsed = Layout.BOTTOM;
        organigramRender = new HorizontalRender(this, orgLay.isCompact(), false, false);
        break;
    }
    switch (orgLay.getLineMode()) {
      case CURVED:
        lineRender = new BezierLineRender(anchorParent, anchorChild);
        break;
      case CONNECTOR:
        lineRender = new GenericLineRender(anchorParent, anchorChild);
        break;
      default:
        lineRender = new DirectLineRender(anchorParent, anchorChild);
    }
    if (orgLay.getBoxMode() == BoxMode.BOX) {
      boxRender = new ClassicBoxRender(anchorCollapsed);
    }
    else {
      boxRender = new RoundBoxRender(anchorCollapsed);
    }
    organigramRender.setLineRender(lineRender);
    organigramRender.setBoxRender(boxRender);
  }

  /**
   * Gets the organigram.
   * 
   * @return the organigram
   */
  public Organigram getOrganigram() {
    return organigram;
  }

  /**
   * Gets the organigram render.
   * 
   * @return the organigramRender
   */
  public OrganigramRender getOrganigramRender() {
    return organigramRender;
  }

  /**
   * Gets the root.
   * 
   * @return the root
   */
  public UnitView getRoot() {
    return root;
  }

  /**
   * Gets the root unit.
   * 
   * @return the root unit
   */
  public Unit getRootUnit() {
    return (root == null ? null : root.getUnit());
  }

  /**
   * Gets the root unit view.
   * 
   * @return the root unit view
   */
  public UnitView getRootUnitView() {
    return root;
  }

  /**
   * Inits the unit tree rec.
   * 
   * @param unit the unit
   * 
   * @return the unit view
   */
  private UnitView initUnitTreeRec(final Unit unit) {
    final UnitView box = new UnitView(unit, this);
    for (final Unit child : unit) {
      box.addChild(initUnitTreeRec(child));
    }
    return box;
  }

  public Dimension doLayout(final Graphics2D graphics) {
    final Dimension size = organigramRender.doLayout(graphics);
    return size;

  }

  public void paint(final Graphics2D graphics, final Dimension size) {
    organigramRender.paint(graphics, size);
  }

  /**
   * Sets the organigram.
   * 
   * @param organigram the new organigram
   */
  public void setOrganigram(final Organigram organigram) {
    this.organigram = organigram;
  }

  /**
   * Sets the organigram render.
   * 
   * @param organigramRender the organigramRender to set
   */
  public void setOrganigramRender(final OrganigramRender organigramRender) {
    this.organigramRender = organigramRender;
  }

  /**
   * Sets the root.
   * 
   * @param root the root to set
   */
  public void setRoot(final UnitView root) {
    this.root = root;
  }

  /**
   * Sets the root unit.
   * 
   * @param rootUnit the new root unit
   */
  public void setRootUnit(final Unit rootUnit) {
    root = initUnitTreeRec(rootUnit);
  }

}
