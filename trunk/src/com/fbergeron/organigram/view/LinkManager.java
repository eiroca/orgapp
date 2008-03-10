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

import com.fbergeron.organigram.model.Unit;

public class LinkManager extends MouseAdapter implements MouseMotionListener {

  private final OrganigramView organigram;

  private String currentLink;

  /**
   * @param organigram
   */
  public LinkManager(final OrganigramView organigram) {
    this.organigram = organigram;
  }

  @Override
  public void mousePressed(final MouseEvent event) {
    if (currentLink != null) {
      showPage(currentLink);
    }
  }

  // @Override
  public void mouseDragged(final MouseEvent event) {
    //
  }

  public UnitView contains(final UnitView box, final Point p) {
    if (box == null) { return null; }
    if (box.contains(p)) { return box; }
    for (final UnitView child : box) {
      final UnitView c = contains(child, p);
      if (c != null) { return c; }
    }
    return null;
  }

  // @Override
  public void mouseMoved(final MouseEvent event) {
    final UnitView c = contains(organigram.getRootUnitView(), event.getPoint());
    currentLink = null;
    if (c != null) {
      final Unit unit = c.getUnit();
      final String link = unit.getMeta("link");
      if (link != null) {
        currentLink = link;
      }
    }
    if (currentLink != null) {
      organigram.setToolTipText(currentLink);
      organigram.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    else {
      organigram.setToolTipText(null);
      organigram.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  public void showPage(final String pageUrl) {
    final AppletContext appletCtxt = getAppletContext(organigram);
    if (appletCtxt != null) {
      try {
        final URL url = new URL(pageUrl);
        appletCtxt.showDocument(url);
      }
      catch (final MalformedURLException malformedUrlException) {
        System.err.println(malformedUrlException);
      }
    }
  }

  public AppletContext getAppletContext(final Container organigram) {
    Container container = organigram.getParent();
    while (container != null) {
      if (container instanceof Applet) {
        final Applet applet = (Applet) container;
        return (applet.getAppletContext());
      }
      container = container.getParent();
    }
    return null;
  }

}