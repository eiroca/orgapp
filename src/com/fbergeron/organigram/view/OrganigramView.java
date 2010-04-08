/**
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
 * 
 */
package com.fbergeron.organigram.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.view.render.LineRender;
import com.fbergeron.organigram.view.render.OrganigramRender;
import com.fbergeron.organigram.view.render.box.ClassicBoxRender;
import com.fbergeron.organigram.view.render.line.HorizLineRender;
import com.fbergeron.organigram.view.render.line.VertLineRender;
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
  private final OrganigramEventManager eventManager;

  /** The preferred size. */
  private final Dimension preferredSize = new Dimension(100, 100);

  /** The owner. */
  private final JScrollPane owner;

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
   * Sets the root unit.
   * 
   * @param rootUnit the new root unit
   */
  public void setRootUnit(final Unit rootUnit) {
    root = initUnitTreeRec(rootUnit);
  }

  /*
   * (non-Javadoc)
   * @see javax.swing.JComponent#getPreferredSize()
   */
  @Override
  public Dimension getPreferredSize() {
    return preferredSize;
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

  /*
   * (non-Javadoc)
   * @see javax.swing.JComponent#getToolTipText(java.awt.event.MouseEvent)
   */
  @Override
  public String getToolTipText(final MouseEvent event) {
    String res = null;
    if (organigram.getOrganigramLayout().isToolTipEnabled()) {
      res = super.getToolTipText(event);
    }
    return res;
  }

  /** The render. */
  OrganigramRender orgRender = null;

  /*
   * (non-Javadoc)
   * @see javax.swing.JComponent#paint(java.awt.Graphics)
   */
  @Override
  public void paint(final Graphics graphics) {
    if (orgRender == null) {
      final OrganigramLayout orgLay = organigram.getOrganigramLayout();
      LineRender lineRender;
      ClassicBoxRender boxRender;
      if (orgLay.getOrgLayout() == OrganigramLayout.ORGLAYOUT_HORIZ) {
        lineRender = new HorizLineRender();
        orgRender = new HorizontalRender(this, (orgLay.getOrgCompact() == OrganigramLayout.ORGCOMPACT_YES));
        boxRender = new ClassicBoxRender(false);
      }
      else {
        lineRender = new VertLineRender();
        orgRender = new VerticalRender(this, (orgLay.getOrgCompact() == OrganigramLayout.ORGCOMPACT_YES));
        boxRender = new ClassicBoxRender(true);
      }
      orgRender.setLineRender(lineRender);
      orgRender.setBoxRender(boxRender);
    }
    orgRender.paint(graphics);
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

  /**
   * Gets the organigram.
   * 
   * @return the organigram
   */
  public Organigram getOrganigram() {
    return organigram;
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
   * Get the view.
   * 
   * @return the owner
   */
  public Component getView() {
    return owner;
  }

}
