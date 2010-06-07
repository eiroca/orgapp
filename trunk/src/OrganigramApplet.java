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
import java.awt.Container;
import java.io.ByteArrayInputStream;
import java.net.URL;
import javax.swing.JApplet;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.util.OrgCharFixUp;
import com.fbergeron.organigram.util.OrgUtils;
import com.fbergeron.organigram.util.Utils;
import com.fbergeron.organigram.util.OrgUtils.OrganigramFormat;
import com.fbergeron.organigram.view.OrganigramView;

/**
 * The Class OrganigramApplet.
 */
public class OrganigramApplet extends JApplet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -90456351900821320L;

  /*
   * (non-Javadoc)
   * @see java.applet.Applet#init()
   */
  @Override
  public void init() {
    final String source = getParameter("DataSource");
    final String target = getParameter("Target");
    Organigram o;
    if (source != null) {
      final URL xmlSourceUrl = Utils.buildURL(getDocumentBase(), source);
      o = OrgUtils.readOrganigram(xmlSourceUrl);
    }
    else {
      final String data = getParameter("Data");
      final String dataType = getParameter("DataType");
      OrganigramFormat type = OrganigramFormat.SOF;
      if (dataType != null) {
        int val;
        try {
          val = Integer.parseInt(dataType);
        }
        catch (final NumberFormatException e) {//
          val = 0;
        }
        switch (val) {
          case 1:
            type = OrganigramFormat.TXT;
            break;
          case 2:
            type = OrganigramFormat.SITEMAP;
            break;
          default:
            type = OrganigramFormat.SOF;
            break;
        }
      }
      final ByteArrayInputStream src = new ByteArrayInputStream(data.getBytes());
      o = OrgUtils.readOrganigram(src, type);
    }
    new OrgCharFixUp().execute(o, true, null);
    o.buidID();
    final OrganigramView view = new OrganigramView(o, target);
    final Container me = getContentPane();
    me.setLayout(new BorderLayout());
    me.add(view.getView(), BorderLayout.CENTER);
  }

}
