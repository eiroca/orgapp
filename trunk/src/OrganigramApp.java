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
import java.awt.BorderLayout;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.fbergeron.organigram.OrgUtils;
import com.fbergeron.organigram.io.BuildInfo;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.view.OrganigramView;

/**
 * The Class OrganigramApps.
 */
public class OrganigramApp extends JFrame {

  /** The organigram file. */
  private static String organigramFile = null;

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The content pane. */
  private JPanel jContentPane = null;

  /** The view. */
  private final OrganigramView view;

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(final String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: ");
      System.out.println("java -jar OrgApp.jar organigram.sof");
      System.exit(1);
    }
    else {
      OrganigramApp.organigramFile = args[0];
    }
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        final OrganigramApp thisClass = new OrganigramApp();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisClass.setVisible(true);
      }
    });
  }

  /**
   * This is the default constructor.
   */
  public OrganigramApp() {
    super();
    final URL source = OrgUtils.find(OrganigramApp.organigramFile);
    final Organigram org = OrgUtils.readOrganigram(source);
    if (org == null) {
      System.err.println("Invalid data: " + OrganigramApp.organigramFile);
      System.exit(ImageObserver.ERROR);
    }
    org.execute(new BuildInfo(), true);
    view = new OrganigramView(org, null);
    initialize();
  }

  /**
   * This method initializes jContentPane.
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(view.getView(), BorderLayout.CENTER);
    }
    return jContentPane;
  }

  /**
   * This method initializes this.
   *
   * @return void
   */
  private void initialize() {
    this.setSize(640, 480);
    setTitle("Organigram Viewer");
    setLayout(new BorderLayout());
    setContentPane(getJContentPane());
  }

}