/**
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
package com.fbergeron.organigram.io.sitemap;

import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.model.UnitTraversal;

/**
 * The Class SiteMapWriter.
 */
public class SiteMapWriter implements OrganigramWriter, UnitTraversal {

  /** The buffer. */
  StringBuffer buf;

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.io.OrganigramWriter#writeOrganigram(com.fbergeron.organigram.model.Organigram,
   *      boolean)
   */
  public String writeOrganigram(final Organigram organigram, final boolean compact) {
    buf = new StringBuffer(1024);
    buf.append("<?xml version=\"1.0\"?>");
    SiteMapUtils.URLSET.open(buf, true);
    SiteMapUtils.URLSET.writeAttribute(buf, "xmlns", "http://www.sitemaps.org/schemas/sitemap/0.9", true);
    organigram.execute(this, true);
    SiteMapUtils.URLSET.close(buf, false);
    return buf.toString();
  }

  /**
   * Write.
   * 
   * @param unit the unit
   * @param processor the processor
   * @param what the what
   */
  public void write(final Unit unit, final TAG processor, final String what) {
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
  public void write(final Unit unit, final TAG processor) {
    write(unit, processor, processor.getName());
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.model.UnitTraversal#process(com.fbergeron.organigram.model.Unit)
   */
  public void process(final Unit unit, int level) {
    final String link = unit.getMeta("link");
    if (link != null) {
      SiteMapUtils.URL.open(buf, false);
      write(unit, SiteMapUtils.LOC, "link");
      write(unit, SiteMapUtils.LASTMOD);
      write(unit, SiteMapUtils.CHANGEFREQ);
      write(unit, SiteMapUtils.PRIORITY);
      SiteMapUtils.URL.close(buf, false);
    }
  }

}
