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
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.fbergeron.organigram.io.BuildInfo;
import com.fbergeron.organigram.io.XMLOrganigramReader;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.view.OrganigramView;

public class OrganigramApps extends JFrame {

  private static String organigramFile = null;
  private static final long serialVersionUID = 1L;
  private JPanel jContentPane = null;
  private OrganigramView view;

  public void readOrganigram() {
    final URL xmlSourceUrl = OrganigramApps.class.getResource(organigramFile);
    // final URL xmlSourceUrl = OrganigramApps.class.getResource("/data.xml");
    final XMLOrganigramReader xmlHandler = new XMLOrganigramReader();
    final Organigram o = xmlHandler.readOrganigram(xmlSourceUrl);
    o.execute(new BuildInfo(), true);
    view = new OrganigramView(o);
  }

  /**
   * @param args
   */
  public static void main(final String[] args) {
    // TODO Auto-generated method stub
    if (args.length == 0) {
      System.out.println("Usage: ");
      System.out.println("java " + OrganigramApps.class.getName() + " organigram.xml");
      System.exit(1);
    }
    else {
      organigramFile = args[0];
    }

    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        final OrganigramApps thisClass = new OrganigramApps();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisClass.setVisible(true);
      }
    });

  }

  /**
   * This is the default constructor
   */
  public OrganigramApps() {
    super();
    readOrganigram();
    initialize();
  }

  /**
   * This method initializes jContentPane
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(new JScrollPane(view), BorderLayout.CENTER);
    }
    return jContentPane;
  }

  /**
   * This method initializes this
   *
   * @return void
   */
  private void initialize() {
    this.setSize(640, 480);
    setTitle("Organigram Viewer");
    setLayout(new BorderLayout());
    setContentPane(getJContentPane());
  }

} // @jve:decl-index=0:visual-constraint="37,20"
