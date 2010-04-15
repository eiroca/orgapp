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
package com.fbergeron.organigram;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.io.sitemap.SiteMapReader;
import com.fbergeron.organigram.io.sitemap.SiteMapWriter;
import com.fbergeron.organigram.io.txt.TXTOrganigramReader;
import com.fbergeron.organigram.io.txt.TXTOrganigramWriter;
import com.fbergeron.organigram.io.xml.XMLOrganigramReader;
import com.fbergeron.organigram.io.xml.XMLOrganigramWriter;
import com.fbergeron.organigram.model.Organigram;

/**
 * The Class Util.
 */
public class OrgUtils {

  /**
   * Write organigram.
   *
   * @param org the org
   * @param type the type
   * @param compact the compact
   *
   * @return the string
   */
  static public String writeOrganigram(final Organigram org, final int type, final boolean compact) {
    OrganigramWriter writer;
    switch (type) {
      case 1:
        writer = new TXTOrganigramWriter();
        break;
      case 2:
        writer = new SiteMapWriter();
        break;
      default:
        writer = new XMLOrganigramWriter();
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
  static public int getType(final URL sourceUrl) {
    final String path = sourceUrl.toExternalForm();
    int type = 0;
    if (path.endsWith(".txt")) {
      type = 1;
    }
    else if (path.endsWith("sitemap.xml")) {
      type = 2;
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
  static public Organigram readOrganigram(final URL sourceUrl, final int type) {
    Organigram org = null;
    if (sourceUrl != null) {
      InputStream source;
      try {
        source = sourceUrl.openStream();
        org = OrgUtils.readOrganigram(source, type);
      }
      catch (final IOException e) {
        //
      }
    }
    return org;
  }

  /**
   * Read organigram.
   *
   * @param source
   * @param type
   * @return
   */
  static public Organigram readOrganigram(final InputStream source, final int type) {
    Organigram org = null;
    OrganigramReader handler = null;
    switch (type) {
      case 1:
        handler = new TXTOrganigramReader();
        break;
      case 2:
        handler = new SiteMapReader();
        break;
      default:
        handler = new XMLOrganigramReader();
        break;
    }
    org = handler.readOrganigram(source);
    return org;
  }

  /**
   * Find in directory.
   *
   * @param path the path
   * @param filename the filename
   *
   * @return the uRL
   */
  public static URL findInDirectory(final String path, final String filename) {
    final File file = new File(path, filename);
    if (file.exists() && !file.isDirectory()) {
      try {
        return file.toURI().toURL();
      }
      catch (final MalformedURLException e) {
      }
    }
    return null;
  }

  /**
   * Find in classpath.
   *
   * @param filename the filename
   *
   * @return the uRL
   */
  public static URL findInClasspath(final String filename) {
    URL res = null;
    final String classpath = System.getProperty("java.class.path");
    final String[] paths = classpath.split(File.pathSeparator);
    for (final String path : paths) {
      res = OrgUtils.findInDirectory(path, filename);
      if (res != null) { return res; }
    }
    return null;
  }

  /**
   * Find in resource.
   *
   * @param name the name
   *
   * @return the uRL
   */
  public static URL findInResource(String name) {
    if (!name.startsWith("/")) {
      name = "/" + name;
    }
    final URL res = OrgUtils.class.getResource(name);
    return res;
  }

  /**
   * Find.
   *
   * @param name the name
   *
   * @return the uRL
   */
  static public URL find(final String name) {
    URL res = null;
    res = OrgUtils.findInClasspath(name);
    if (res != null) {
      res = OrgUtils.findInResource(name);
    }
    return res;
  }

  /**
   * Builds the url.
   *
   * @param documentBase the document base
   * @param dataSource the data source
   *
   * @return the uRL
   */
  static public URL buildURL(final URL documentBase, final String dataSource) {
    URL sourceUrl = null;
    if (dataSource != null) {
      try {
        if (documentBase != null) {
          sourceUrl = new URL(documentBase, dataSource);
        }
        else {
          sourceUrl = new URL(dataSource);
        }
      }
      catch (final MalformedURLException malformedUrlException) {
      }
    }
    return sourceUrl;
  }

}
