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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
public class OrganigramView extends JPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The organigram. */
  private Organigram organigram;

  /** The root. */
  public UnitView root;

  /** The link manager. */
  private OrganigramEventManager eventManager;

  /** The preferred size. */
  private final Dimension preferredSize = new Dimension(100, 100);

  /** The render. */
  private transient OrganigramRender organigramRender = null;

  /** The owner. */
  private JScrollPane owner;

  /**
   * Instantiates a new organigram view.
   * 
   * @param organigram the organigram
   * @param target the target
   */
  public OrganigramView(final Organigram organigram, final String target) {
    super();
    this.organigram = organigram;
    owner = new JScrollPane(this);
    eventManager = new OrganigramEventManager(this);
    eventManager.setBaseTarget(target);
    root = initUnitTreeRec(organigram.getRoot());
    addMouseMotionListener(eventManager);
    addMouseListener(eventManager);
  }

  /**
   * Gets the event manager.
   * 
   * @return the eventManager
   */
  public OrganigramEventManager getEventManager() {
    return eventManager;
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
   * Gets the owner.
   * 
   * @return the owner
   */
  public JScrollPane getOwner() {
    return owner;
  }

  /*
   * (non-Javadoc)
   * @see javax.swing.JComponent#getPreferredSize()
   */
  @Override
  public Dimension getPreferredSize() {
    return preferredSize;
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

  /*
   * (non-Javadoc)
   * @see javax.swing.JComponent#getToolTipText(java.awt.event.MouseEvent)
   */
  @Override
  public String getToolTipText(final MouseEvent event) {
    return organigram.getOrganigramLayout().isToolTipEnabled() ? super.getToolTipText(event) : null;
  }

  /**
   * Get the view.
   * 
   * @return the owner
   */
  public Component getView() {
    return owner;
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

  /*
   * (non-Javadoc)
   * @see javax.swing.JComponent#paint(java.awt.Graphics)
   */
  @Override
  public void paint(final Graphics graphics) {
    Layout anchorParent;
    Layout anchorChild;
    Layout anchorCollapsed;
    if (organigramRender == null) {
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
    organigramRender.paint((Graphics2D) graphics);
  }

  /**
   * Sets the event manager.
   * 
   * @param eventManager the eventManager to set
   */
  public void setEventManager(final OrganigramEventManager eventManager) {
    this.eventManager = eventManager;
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
   * Sets the owner.
   * 
   * @param owner the owner to set
   */
  public void setOwner(final JScrollPane owner) {
    this.owner = owner;
  }

  /*
   * (non-Javadoc)
   * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
   */
  @Override
  public void setPreferredSize(final Dimension preferredSize) {
    this.preferredSize.setSize(preferredSize);
    revalidate();
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
