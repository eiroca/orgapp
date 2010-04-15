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
package com.fbergeron.organigram.io;

import java.util.Map;
import org.xml.sax.Attributes;

/**
 * The Class GenericProcessor.
 */
public class TAG implements TagProcessor {

  /** The name. */
  private String tagName;

  /** The buffer. */
  protected StringBuffer buf;

  /**
   * Instantiates a new generic processor.
   *
   * @param name the name
   */
  public TAG(final String name) {
    tagName = name;
    TagFactory.register(this);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#getName()
   */
  public String getName() {
    return tagName;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(final String name) {
    tagName = name;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#start(com.fbergeron.organigram.io.OrganigramReader, org.xml.sax.Attributes)
   */
  public void start(final OrganigramReader reader, final Attributes attribs) {
    buf = new StringBuffer(200);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#characters(com.fbergeron.organigram.io.OrganigramReader, char[], int, int)
   */
  public void characters(final OrganigramReader reader, final char[] chr, final int start, final int length) {
    final String str = new String(chr, start, length);
    if (buf != null) {
      buf.append(str);
    }
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.sitemap.TagProcessor#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  public void end(final OrganigramReader reader) {
    if (buf != null) {
      final Map<String, String> info = reader.getData();
      final String val = buf.toString();
      info.put(tagName, val.length() == 0 ? null : val);
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
    buf.append(tagName);
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
   * Write c data.
   *
   * @param buf the buffer
   * @param data the data
   */
  public void writeCData(final StringBuffer buf, final String data) {
    if (data != null) {
      TAG.encode(buf, data);
    }
  }

  /**
   * Encode.
   *
   * @param buf the buffer
   * @param str the string
   */
  public static void encode(final StringBuffer buf, final String str) {
    for (int i = 0; i < str.length(); i++) {
      final char c = str.charAt(i);
      if (c < 32) {
        buf.append(' ');
      }
      else {
        switch (c) {
          case '&':
            buf.append("&amp;");
            break;
          default:
            buf.append(c);
            break;
        }
      }
    }
  }

  /**
   * Write attribute.
   *
   * @param buf the buffer
   * @param name the name
   * @param value the value
   * @param last the last
   */
  public void writeAttribute(final StringBuffer buf, final String name, final String value, final boolean last) {
    buf.append(' ');
    buf.append(name);
    buf.append("=\"");
    TAG.encode(buf, value);
    buf.append('"');
    if (last) {
      buf.append('>');
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
      buf.append(tagName);
      buf.append('>');
    }
  }

}
