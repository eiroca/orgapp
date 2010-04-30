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
package com.fbergeron.organigram.view;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.util.Debug;
import com.fbergeron.organigram.util.Resources;

/**
 * LinkManager handles the user interaction.
 */
public class OrganigramEventManager extends MouseAdapter implements MouseMotionListener {

  /** The organigram. */
  private OrganigramView organigram;

  /** The current link. */
  private String currentLink;

  /** The current target. */
  private String currentTarget = null;

  /** The base target. */
  private String baseTarget = null;

  private final Resources messages = Resources.getInstance();

  /**
   * The Constructor.
   * 
   * @param organigram the organigram
   */
  public OrganigramEventManager(final OrganigramView organigram) {
    super();
    this.organigram = organigram;
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(final MouseEvent event) {
    if (event.getButton() == 1) {
      if (currentLink != null) {
        try {
          showPage(currentLink, currentTarget);
        }
        catch (final MalformedURLException e) {
          Debug.error(e);
        }
      }
    }
    else {
      final Point where = event.getPoint();
      final UnitView unitView = contains(organigram.getRootUnitView(), where);
      if (unitView != null) {
        final BoxLayout layout = unitView.getLayout();
        layout.setExpanded(!layout.isExpanded());
        organigram.getOrganigramRender().invalidate();
        organigram.repaint();
      }
    }
  }

  // @Override
  /*
   * (non-Javadoc)
   * @see
   * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(final MouseEvent event) {
    //
  }

  /**
   * Contains.
   * 
   * @param box the box
   * @param point the p
   * 
   * @return the unit view
   */
  public UnitView contains(final UnitView box, final Point point) {
    if (box == null) { return null; }
    if (box.contains(point)) { return box; }
    if (box.hasChildren()) {
      for (final UnitView child : box) {
        final UnitView unitView = contains(child, point);
        if (unitView != null) { return unitView; }
      }
    }
    return null;
  }

  // @Override
  /*
   * (non-Javadoc)
   * @see
   * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(final MouseEvent event) {
    final Point where = event.getPoint();
    final UnitView unitView = contains(organigram.getRootUnitView(), where);
    setCurrentLink(null);
    String tipText = null;
    if (unitView != null) {
      if (unitView.canExpand()) {
        tipText = messages.getString("RightClick1"); //$NON-NLS-1$
      }
      else if (unitView.canCollapse()) {
        tipText = messages.getString("RightClick2"); //$NON-NLS-1$
      }
      final Unit unit = unitView.getUnit();
      final String link = unit.getMeta("link"); //$NON-NLS-1$
      if (link != null) {
        currentLink = link;
        tipText = MessageFormat.format(messages.getString("GoToLink"), currentLink); //$NON-NLS-1$
        final String linkTarget = unit.getMeta("target"); //$NON-NLS-1$
        if (linkTarget == null) {
          currentTarget = baseTarget;
        }
        else {
          currentTarget = linkTarget;
        }
      }
    }
    if (currentLink == null) {
      organigram.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    else {
      organigram.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    organigram.setToolTipText(tipText);
  }

  /**
   * Show page.
   * 
   * @param pageUrl the page url
   * @param target the target
   * 
   * @throws MalformedURLException the malformed url exception
   */
  public void showPage(final String pageUrl, final String target) throws MalformedURLException {
    final AppletContext appletCtxt = getAppletContext(organigram);
    if (appletCtxt != null) {
      final URL url = new URL(pageUrl);
      if (target == null) {
        appletCtxt.showDocument(url);
      }
      else {
        appletCtxt.showDocument(url, target);
      }
    }
  }

  /**
   * Gets the applet context.
   * 
   * @param organigram the organigram
   * 
   * @return the applet context
   */
  public AppletContext getAppletContext(final Container organigram) {
    Container container = organigram.getParent();
    AppletContext context = null;
    while (container != null) {
      if (container instanceof Applet) {
        final Applet applet = (Applet) container;
        context = applet.getAppletContext();
        break;
      }
      container = container.getParent();
    }
    return context;
  }

  /**
   * Gets the base target.
   * 
   * @return the base target
   */
  public String getBaseTarget() {
    return baseTarget;
  }

  /**
   * Sets the base target.
   * 
   * @param baseTarget the new base target
   */
  public void setBaseTarget(final String baseTarget) {
    this.baseTarget = baseTarget;
  }

  /**
   * Gets the organigram.
   * 
   * @return the organigram
   */
  public OrganigramView getOrganigram() {
    return organigram;
  }

  /**
   * Sets the organigram.
   * 
   * @param organigram the organigram to set
   */
  public void setOrganigram(final OrganigramView organigram) {
    this.organigram = organigram;
  }

  /**
   * Gets the current link.
   * 
   * @return the currentLink
   */
  public String getCurrentLink() {
    return currentLink;
  }

  /**
   * Sets the current link.
   * 
   * @param currentLink the currentLink to set
   */
  public void setCurrentLink(final String currentLink) {
    this.currentLink = currentLink;
  }

  /**
   * Gets the current target.
   * 
   * @return the currentTarget
   */
  public String getCurrentTarget() {
    return currentTarget;
  }

  /**
   * Sets the current target.
   * 
   * @param currentTarget the currentTarget to set
   */
  public void setCurrentTarget(final String currentTarget) {
    this.currentTarget = currentTarget;
  }

}