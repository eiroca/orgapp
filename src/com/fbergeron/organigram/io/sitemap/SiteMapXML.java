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

import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.io.sitemap.tags.TagURL;
import com.fbergeron.organigram.io.sitemap.tags.TagURLSet;
import com.fbergeron.organigram.io.xml.Attr;
import com.fbergeron.organigram.io.xml.Tag;
import com.fbergeron.organigram.io.xml.XMLHandler;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class SiteMapUtils.
 */
public final class SiteMapXML extends XMLHandler implements OrganigramReader, OrganigramWriter {

  /** The Constant URLSET. */
  public final transient Tag tUrlSet = new TagURLSet();

  /** The Constant URL. */
  public final transient Tag tUrl = new TagURL();

  /** The Constant LOC. */
  public final transient Tag tLoc = new Tag("loc");

  /** The Constant LASTMOD. */
  public final transient Tag tLastMod = new Tag("lastmod");

  /** The Constant CHANGEFREQ. */
  public final transient Tag tChangeFreq = new Tag("changefreq");

  /** The Constant PRIORITY. */
  public final transient Tag tPriority = new Tag("priority");

  /** The Constant TITLE (extended tag). */
  public final transient Tag tTitle = new Tag("title");

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.io.OrganigramWriter#writeOrganigram(com.fbergeron.organigram.model.Organigram,
   *      boolean)
   */
  @Override
  public String writeOrganigram(final Organigram organigram, final boolean compact) {
    StringBuffer buf;
    buf = new StringBuffer(1024);
    buf.append("<?xml version=\"1.0\"?>");
    tUrlSet.open(buf, true);
    Attr.writeAttribute(buf, "xmlns", "http://www.sitemaps.org/schemas/sitemap/0.9", true);
    write(buf, organigram.getRoot());
    tUrlSet.close(buf, false);
    return buf.toString();
  }

  /**
   * Write a unit in SiteMap format
   * @param buf
   * @param unit
   */
  public void write(final StringBuffer buf, final Unit unit) {
    final String link = unit.getMeta("link");
    if (link != null) {
      tUrl.open(buf, false);
      write(buf, unit, tLoc, "link");
      write(buf, unit, tLastMod);
      write(buf, unit, tChangeFreq);
      write(buf, unit, tPriority);
      tUrl.close(buf, false);
    }
    for (final Unit u : unit) {
      write(buf, u);
    }
  }

  /**
   * Write.
   * 
   * @param unit the unit
   * @param processor the processor
   * @param what the what
   */
  public void write(final StringBuffer buf, final Unit unit, final Tag processor, final String what) {
    final String val = unit.getMeta(what);
    if (val != null) {
      processor.open(buf, false);
      processor.writeCData(buf, val);
      processor.close(buf, false);
    }
  }

  /**
   * Write.
   * 
   * @param unit the unit
   * @param processor the processor
   */
  public void write(final StringBuffer buf, final Unit unit, final Tag processor) {
    write(buf, unit, processor, processor.getName());
  }

}
