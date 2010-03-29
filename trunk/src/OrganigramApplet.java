/**
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
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
 * 
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.net.URL;
import javax.swing.JApplet;
import com.fbergeron.organigram.OrgUtils;
import com.fbergeron.organigram.io.BuildInfo;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.view.OrganigramView;

/**
 * The Class OrganigramApplet.
 */
public class OrganigramApplet extends JApplet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -90456351900821320L;

  /*
   * (non-Javadoc)
   *
   * @see java.applet.Applet#init()
   */
  @Override
  public void init() {
    final String source = getParameter("DataSource");
    final String target = getParameter("Target");
    final URL xmlSourceUrl = OrgUtils.buildURL(getDocumentBase(), source);
    final Container me = getContentPane();
    final Organigram o = OrgUtils.readOrganigram(xmlSourceUrl);
    o.execute(new BuildInfo(), true);
    final OrganigramView view = new OrganigramView(o, target);
    me.setLayout(new BorderLayout());
    me.add(view.getView(), BorderLayout.CENTER);
  }

}
