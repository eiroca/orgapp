/**
 * LGPL > 3.0 Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
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
package com.fbergeron.organigram.io.sitemap;

import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.model.type.OrgMode;

/**
 * The Class URLSetProcessor.
 */
public class URLSetProcessor extends TAG {

  /**
   * Instantiates a new uRL set processor.
   */
  public URLSetProcessor() {
    super("urlset");
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.io.sitemap.GenericProcessor#start(com.fbergeron
   * .organigram.io.OrganigramReader, org.xml.sax.Attributes)
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    super.start(reader, attribs);
    Organigram org = new Organigram();
    reader.setOrganigram(org);
    final Unit root = new Unit(org);
    reader.getOrganigram().setRoot(root);
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.io.sitemap.GenericProcessor#end(com.fbergeron.
   * organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    super.end(reader);
    final Organigram org = reader.getOrganigram();
    org.removeID();
    org.getOrganigramLayout().setLayout(Layout.LEFT);
    org.getOrganigramLayout().setCompact(true);
    org.getOrganigramLayout().setMode(OrgMode.MIN);
    org.getOrganigramLayout().setToolTipEnabled(true);
  }

}
