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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.io.sitemap.SiteMapReader;
import com.fbergeron.organigram.io.sitemap.SiteMapWriter;
import com.fbergeron.organigram.io.sof.XMLOrganigramReader;
import com.fbergeron.organigram.io.sof.XMLOrganigramWriter;
import com.fbergeron.organigram.io.txt.TXTOrganigramReader;
import com.fbergeron.organigram.io.txt.TXTOrganigramWriter;
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
    int type;
    if (path.endsWith(".txt")) {
      type = 1;
    }
    else if (path.endsWith("sitemap.xml")) {
      type = 2;
    }
    else {
      type = 0;
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
  static public Organigram readOrganigram(final InputStream source, final int type) {
    OrganigramReader handler;
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
    return handler.readOrganigram(source);
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
    URL res = null;
    final File file = new File(path, filename);
    if (file.exists() && !file.isDirectory()) {
      try {
        res = file.toURI().toURL();
      }
      catch (final MalformedURLException e) {
        Debug.ignore(e);
      }
    }
    return res;
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
      if (res != null) {
        break;
      }
    }
    return res;
  }

  /**
   * Find in resource.
   * 
   * @param name the name
   * 
   * @return the uRL
   */
  public static URL findInResource(final String name) {
    final String path = (name.charAt(0) == '/') ? name : "/" + name;
    return OrgUtils.class.getResource(path);
  }

  /**
   * Find.
   * 
   * @param name the name
   * 
   * @return the uRL
   */
  static public URL find(final String name) {
    URL res = OrgUtils.findInClasspath(name);
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
    URL res = null;
    if (dataSource != null) {
      try {
        if (documentBase == null) {
          res = new URL(dataSource);
        }
        else {
          res = new URL(documentBase, dataSource);
        }
      }
      catch (final MalformedURLException e) {
        Debug.ignore(e);
      }
    }
    return res;
  }

  /**
   * Val.
   * 
   * @param val the val
   * @param def the def
   * @return the int
   */
  static public int val(final String val, final int def) {
    int res;
    try {
      res = Integer.parseInt(val);
    }
    catch (final NumberFormatException err) {
      res = def;
    }
    return res;
  }

}