/** LGPL > 3.0
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
 */
package com.fbergeron.organigram.io.sitemap;

import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.io.sitemap.tags.TagURL;
import com.fbergeron.organigram.io.sitemap.tags.TagURLSet;

/**
 * The Class SiteMapUtils.
 */
public final class SiteMapXML {

  /** The Constant URLSET. */
  public final transient TAG URLSET = new TagURLSet();

  /** The Constant URL. */
  public final transient TAG URL = new TagURL();

  /** The Constant LOC. */
  public final transient TAG LOC = new TAG("loc");

  /** The Constant LASTMOD. */
  public final transient TAG LASTMOD = new TAG("lastmod");

  /** The Constant CHANGEFREQ. */
  public final transient TAG CHANGEFREQ = new TAG("changefreq");

  /** The Constant PRIORITY. */
  public final transient TAG PRIORITY = new TAG("priority");

  /** The Constant TITLE (extended tag). */
  public final transient TAG TITLE = new TAG("title");

  /** The instance. */
  private static SiteMapXML instance;

  /**
   * Gets the single instance of SiteMapXML.
   * 
   * @return single instance of SiteMapXML
   */
  public static synchronized SiteMapXML getInstance() {
    if (SiteMapXML.instance == null) {
      SiteMapXML.instance = new SiteMapXML();
    }
    return SiteMapXML.instance;
  }

  /**
   * Instantiates a new site map xml.
   */
  private SiteMapXML() {
    super();
  }

}
