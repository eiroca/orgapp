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
import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JScrollPane;

import com.fbergeron.organigram.io.BuildInfo;
import com.fbergeron.organigram.io.XMLOrganigramReader;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.view.OrganigramView;

public class OrganigramApplet extends JApplet {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public URL getURL(final String dataSource, final String defPath) {
    URL xmlSourceUrl = null;
    try {
      if (dataSource != null) {
        if (dataSource.startsWith("http")) {
          xmlSourceUrl = new URL(dataSource);
        }
        else {
          xmlSourceUrl = new URL(getDocumentBase(), dataSource);
        }
      }
    }
    catch (final MalformedURLException malformedUrlException) {
      System.err.print("Invalid data source: " + malformedUrlException);
    }
    if (xmlSourceUrl == null) {
      xmlSourceUrl = getClass().getResource(defPath);
    }
    return xmlSourceUrl;
  }

  public static void addOrganigramView(final Container me, final URL xmlSourceUrl) {
    final XMLOrganigramReader xmlHandler = new XMLOrganigramReader();
    final Organigram o = xmlHandler.readOrganigram(xmlSourceUrl);
    o.execute(new BuildInfo(), true);
    final OrganigramView view = new OrganigramView(o);
    me.setLayout(new BorderLayout());
    me.add(new JScrollPane(view), BorderLayout.CENTER);
  }

  @Override
  public void init() {
    final URL xmlSourceUrl = getURL(getParameter("DataSource"), "/data.xml");
    OrganigramApplet.addOrganigramView(getContentPane(), xmlSourceUrl);
  }

}
