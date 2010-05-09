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
import java.util.List;
import org.xml.sax.Attributes;

/**
 * The Class AttrGroup.
 * 
 * @param <T> the < t>
 */
public class AttrGroup<T> extends Attr<T> {

  /** The ATTRS. */
  protected transient List<Attr<?>> attributes = new ArrayList<Attr<?>>();

  /** The prefix. */
  private String prefix;

  /**
   * Instantiates a new attr group.
   * 
   * @param prefix the prefix
   */
  public AttrGroup(final String prefix) {
    super(null);
    if (prefix == null) {
      this.prefix = "";
    }
    else {
      this.prefix = prefix;
    }
  }

  /**
   * Adds the attribute.
   * 
   * @param attr the attr
   */
  public void addAttribute(final Attr<?> attr) {
    final String name = attr.getName();
    attr.setName(prefix + name);
    attributes.add(attr);
  }

  /**
   * Gets the prefix.
   * 
   * @return the prefix
   */
  public String getPrefix() {
    return prefix;
  }

  /**
   * Sets the prefix.
   * 
   * @param prefix the prefix to set
   */
  public void setPrefix(final String prefix) {
    this.prefix = prefix;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.Attr#read(org.xml.sax.Attributes)
   */
  @Override
  public T read(final Attributes attribs) {
    for (final Attr<?> ta : attributes) {
      ta.read(attribs);
    }
    return null;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.Attr#checkName(java.lang.String)
   */
  @Override
  public boolean checkName(final String name) {
    boolean res = false;
    for (final Attr<?> ta : attributes) {
      res = ta.checkName(name);
      if (res) {
        break;
      }
    }
    return res;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.Attr#write(java.lang.StringBuffer)
   */
  @Override
  public void write(final StringBuffer buf) {
    for (final Attr<?> ta : attributes) {
      ta.write(buf);
    }
  }

}
