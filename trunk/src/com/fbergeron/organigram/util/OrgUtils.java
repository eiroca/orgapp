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
package com.fbergeron.organigram.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.io.sitemap.SiteMapXML;
import com.fbergeron.organigram.io.sof.SOFXML;
import com.fbergeron.organigram.io.txt.TXTOrganigramReader;
import com.fbergeron.organigram.io.txt.TXTOrganigramWriter;
import com.fbergeron.organigram.model.Organigram;

/**
 * The Class Util.
 */
public class OrgUtils {

  public enum OrganigramFormat {
    SOF, TXT, SITEMAP
  }

  /**
   * Write organigram.
   * 
   * @param org the org
   * @param type the type
   * @param compact the compact
   * 
   * @return the string
   */
  static public String writeOrganigram(final Organigram org, final OrganigramFormat type, final boolean compact) {
    OrganigramWriter writer;
    switch (type) {
      case TXT:
        writer = new TXTOrganigramWriter();
        break;
      case SITEMAP:
        writer = new SiteMapXML();
        break;
      default:
        writer = new SOFXML();
        break;
    }
    return writer.writeOrganigram(org, compact);
  }

  /**
   * Read organigram.
   * 
   * @param sourceUrl the source url
   * 
   * @return the organigram
   */
  static public Organigram readOrganigram(final URL sourceUrl) {
    return (sourceUrl == null ? null : OrgUtils.readOrganigram(sourceUrl, OrgUtils.getType(sourceUrl)));
  }

  /**
   * Gets the type.
   * 
   * @param sourceUrl the source url
   * 
   * @return the type
   */
  static public OrganigramFormat getType(final URL sourceUrl) {
    final String path = sourceUrl.toExternalForm();
    OrganigramFormat type;
    if (path.endsWith(".txt")) {
      type = OrganigramFormat.TXT;
    }
    else if (path.endsWith("sitemap.xml")) {
      type = OrganigramFormat.SITEMAP;
    }
    else {
      type = OrganigramFormat.SOF;
    }
    return type;
  }

  /**
   * Read organigram.
   * 
   * @param sourceUrl the source url
   * @param type the type
   * 
   * @return the organigram
   */
  static public Organigram readOrganigram(final URL sourceUrl, final OrganigramFormat type) {
    Organigram org = null;
    if (sourceUrl != null) {
      InputStream source;
      try {
        source = sourceUrl.openStream();
        org = OrgUtils.readOrganigram(source, type);
      }
      catch (final IOException e) {
        Debug.ignore(e);
      }
    }
    return org;
  }

  /**
   * Read organigram.
   * 
   * @param source the source
   * @param type the type
   * @return the organigram
   */
  static public Organigram readOrganigram(final InputStream source, final OrganigramFormat type) {
    OrganigramReader handler;
    switch (type) {
      case TXT:
        handler = new TXTOrganigramReader();
        break;
      case SITEMAP:
        handler = new SiteMapXML();
        break;
      default:
        handler = new SOFXML();
        break;
    }
    return handler.readOrganigram(source);
  }

}