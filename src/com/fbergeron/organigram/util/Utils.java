/** LGPL > 3.0 
 * Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * The Class Utils.
 */
/**
 * @author enrico
 * 
 */
public class Utils {

  /**
   * Adds the enum.
   * 
   * @param set the set
   * @param val the value
   */
  @SuppressWarnings("unchecked")
  static public void addEnum(final Map set, final Enum val) {
    if ((set == null) || (val == null)) { return; }
    final String name = val.name().toLowerCase();
    set.put(name, val);
    set.put(name.substring(0, 1), val);
    set.put(Integer.toString(val.ordinal()), val);
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
   * Encode xm lchars.
   * 
   * @param buf the buf
   * @param str the str
   */
  public static void encodeXMLchars(final StringBuffer buf, final String str) {
    Utils.encodeXMLchars(buf, str, true);
  }

  /**
   * Encode.
   * 
   * @param buf the buffer
   * @param str the string
   * @param removeNonASCII the remove non ASCII
   */
  static public void encodeXMLchars(final StringBuffer buf, final String str, final boolean removeNonASCII) {
    if ((buf == null) || (str == null)) { return; }
    for (int i = 0; i < str.length(); i++) {
      final char chr = str.charAt(i);
      if ((removeNonASCII) && (chr < 32)) {
        buf.append(' ');
      }
      else {
        switch (chr) {
          case '&':
            buf.append("&amp;");
            break;
          case '<':
            buf.append("&lt;");
            break;
          case '>':
            buf.append("&gt;");
            break;
          case '"':
            buf.append("&quot;");
            break;
          case '\'':
            buf.append("&#39;");
            break;
          default:
            buf.append(chr);
        }
      }
    }
  }

  /**
   * Find in classpath.
   * 
   * @param filename the filename
   * 
   * @return the uRL
   */
  static public URL findInClasspath(final String filename) {
    URL res = null;
    final String classpath = System.getProperty("java.class.path");
    final String[] paths = classpath.split(File.pathSeparator);
    for (final String path : paths) {
      res = Utils.findInDirectory(path, filename);
      if (res != null) {
        break;
      }
    }
    return res;
  }

  /**
   * Find in directory.
   * 
   * @param path the path
   * @param filename the filename
   * 
   * @return the uRL
   */
  static public URL findInDirectory(final String path, final String filename) {
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
   * Find in resource.
   * 
   * @param name the name
   * 
   * @return the uRL
   */
  static public URL findInResource(final String name) {
    final String path = (name.charAt(0) == '/') ? name : "/" + name;
    return OrgUtils.class.getResource(path);
  }

  /**
   * Find a resuurce in current directory, classpath or internal resources.
   * 
   * @param resName the name of the resource to be found
   * 
   * @return the URL of tyhe resource or null if not found
   */
  static public URL findResource(final String resName) {
    URL res;
    res = Utils.findInDirectory(".", resName);
    if (res == null) {
      res = Utils.findInClasspath(resName);
    }
    if (res == null) {
      res = Utils.findInResource(resName);
    }
    return res;
  }

  private static final int BUF_SIZE = 4 * 1024;

  /**
   * Read as string form a URL
   * 
   * @param sourceURL the source url
   * @return the string
   * @throws IOException Signals that an I/O exception has occurred.
   */
  static public String readAsString(final URL sourceURL) {
    String res = null;
    if (sourceURL != null) {
      BufferedReader reader = null;
      final StringBuffer fileData = new StringBuffer(Utils.BUF_SIZE);
      try {
        reader = new BufferedReader(new InputStreamReader(sourceURL.openStream()));
        final char[] buf = new char[Utils.BUF_SIZE];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
          final String readData = String.valueOf(buf, 0, numRead);
          fileData.append(readData);
        }
        reader.close();
        res = fileData.toString();
      }
      catch (final IOException err) {
        Debug.ignore(err);
        res = null;
        if (reader != null) {
          try {
            reader.close();
          }
          catch (final IOException e) {
            Debug.ignore(e);
          }
        }
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

  /**
   * Write two digits Hex.
   * 
   * @param out the out
   * @param val the val
   */
  static public void writeHH(final StringBuffer out, final int val) {
    if (out != null) {
      if (val < 16) {
        out.append('0');
      }
      out.append(Integer.toString(val, 16));
    }
  }

  public static String getExtension(final File f) {
    String ext = null;
    if (f.isDirectory()) {
      ext = null;
    }
    else {
      final String s = f.getName();
      final int i = s.lastIndexOf('.');
      if ((i > 0) && (i < s.length() - 1)) {
        ext = s.substring(i + 1).toLowerCase();
      }
    }
    return ext;
  }

}
