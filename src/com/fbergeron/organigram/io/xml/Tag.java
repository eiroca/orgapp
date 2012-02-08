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
package com.fbergeron.organigram.io.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.util.Utils;

/**
 * The Class GenericProcessor.
 */
public class Tag implements TagProcessor {

  /** The ATTRS. */
  protected transient List<Attr<?>> attributes = new ArrayList<Attr<?>>();

  /**
   * Adds the attribute.
   * 
   * @param attr the attr
   */
  public void addAttribute(final Attr<?> attr) {
    attributes.add(attr);
  }

  /** The buffer. */
  protected transient StringBuffer buf;

  /** The name. */
  private String name;

  /**
   * Instantiates a new generic processor.
   * 
   * @param name the name
   */
  public Tag(final String name) {
    this.name = name;
    TagFactory.register(this);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#start(com.fbergeron.organigram.io.OrganigramReader, org.xml.sax.Attributes)
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    buf = new StringBuffer(200);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#characters(com.fbergeron.organigram.io.OrganigramReader, char[], int, int)
   */
  @Override
  public void characters(final OrganigramReader reader, final char[] chr, final int start, final int length) {
    if (buf != null) {
      buf.append(new String(chr, start, length));
    }
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    if (buf != null) {
      final Map<String, String> info = reader.getData();
      final String val = buf.toString();
      info.put(name, val.length() == 0 ? null : val);
      buf = null;
    }
  }

  /**
   * Close.
   * 
   * @param buf the buffer
   * @param compact the compact
   */
  public void close(final StringBuffer buf, final boolean compact) {
    if (compact) {
      buf.append("/>");
    }
    else {
      buf.append("</");
      buf.append(name);
      buf.append('>');
    }
  }

  /**
   * Open.
   * 
   * @param buf the buffer
   * @param open the open
   */
  public void open(final StringBuffer buf, final boolean open) {
    buf.append('<');
    buf.append(name);
    if (!open) {
      buf.append('>');
    }
  }

  /**
   * Open close.
   * 
   * @param buf the buffer
   */
  public void openClose(final StringBuffer buf) {
    buf.append('>');
  }

  /**
   * Sets the name.
   * 
   * @param name the new name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Parses the.
   * 
   * @param attribs the attribs
   */
  public void parse(final Attributes attribs) {
    for (final Attr<?> ta : attributes) {
      ta.read(attribs);
    }
  }

  /**
   * Write.
   * 
   * @param buf the buf
   */
  public void write(final StringBuffer buf) {
    for (final Attr<?> ta : attributes) {
      ta.write(buf);
    }
  }

  /**
   * Checks if is attribute.
   * 
   * @param name the name
   * @return true, if is attribute
   */
  public boolean isAttribute(final String name) {
    boolean res = false;
    for (final Attr<?> ta : attributes) {
      res = ta.checkName(name);
      if (res) {
        break;
      }
    }
    return res;
  }

  /**
   * Write c data.
   * 
   * @param buf the buffer
   * @param data the data
   */
  public void writeCData(final StringBuffer buf, final String data) {
    if (data != null) {
      Utils.encodeXMLchars(buf, data);
    }
  }

  /**
   * Write meta.
   * 
   * @param buf the output buffer
   * @param meta the meta
   * @param order the order
   */
  public void writeMeta(final StringBuffer buf, final Map<String, String> meta, final List<String> order) {
    final Set<String> done = new HashSet<String>();
    if (order != null) {
      for (final String metaName : order) {
        final String val = meta.get(metaName);
        if (val != null) {
          Attr.writeAttribute(buf, metaName, meta.get(metaName), false);
          done.add(metaName);
        }
      }
    }
    for (final String metaName : meta.keySet()) {
      if (!done.contains(metaName)) {
        Attr.writeAttribute(buf, metaName, meta.get(metaName), false);
      }
    }
  }

}
