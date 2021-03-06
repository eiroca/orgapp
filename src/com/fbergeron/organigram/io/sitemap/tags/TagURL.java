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
package com.fbergeron.organigram.io.sitemap.tags;

import java.util.Map;
import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.xml.Tag;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class URLProcessor.
 */
public class TagURL extends Tag {

  /**
   * Instantiates a new uRL processor.
   */
  public TagURL() {
    super("url");
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
    reader.getData().clear();
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
    final Map<String, String> info = reader.getData();
    final String loc = info.get("loc");
    final Organigram org = reader.getOrganigram();
    Unit unit = org.findByID(loc, false);
    if (!loc.equals(unit.getId())) {
      final Unit newUnit = new Unit(org);
      unit.addChild(newUnit);
      unit = newUnit;
    }
    unit.setId(loc);
    unit.setMeta("title", info.get("title"));
    unit.setMeta("lastmod", info.get("lastmod"));
    unit.setMeta("changefreq", info.get("changefreq"));
    unit.setMeta("priority", info.get("priority"));
    unit.setMeta("link", loc);
  }

}
