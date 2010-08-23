/** LGPL > 3.0
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

public class OrganigramPanel extends JPanel {

  /** The serialVersionUID */
  private static final long serialVersionUID = 1L;

  /** The organigram. */
  private final Organigram organigram;

  /** The owner. */
  private JScrollPane owner;

  /** The link manager. */
  private OrganigramEventManager eventManager;

  /** The preferred size. */
  private final Dimension preferredSize = new Dimension(100, 100);

  /** The valid layout. */
  protected transient boolean validLayout = false;

  private OrganigramView organigramView = null;

  public OrganigramPanel(final Organigram organigram, final String target) {
    this.organigram = organigram;
    validLayout = false;
    owner = new JScrollPane(this);
    eventManager = new OrganigramEventManager(this);
    eventManager.setBaseTarget(target);
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
   * @return the organigramView
   */
  public OrganigramView getOrganigramView() {
    return organigramView;
  }

  /**
   * @param organigramView the organigramView to set
   */
  public void setOrganigramView(final OrganigramView organigramView) {
    this.organigramView = organigramView;
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

  @Override
  public void paint(final Graphics graphics) {
    if (organigramView == null) {
      organigramView = new OrganigramView(organigram);
    }
    if (!validLayout) {
      final Dimension size = organigramView.doLayout((Graphics2D) graphics);
      setPreferredSize(size);
      validLayout = true;
    }
    organigramView.paint((Graphics2D) graphics, getSize());
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

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.view.render.OrganigramRender#invalidate()
   */
  @Override
  public void invalidate() {
    super.invalidate();
    validLayout = false;
  }

}
