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

import org.xml.sax.Attributes;
import com.fbergeron.organigram.util.Utils;

/**
 * The Class Attr.
 * 
 * @param <T> the < t>
 */
public class Attr<T> {

  /**
   * Write attribute.
   * 
   * @param buf the buffer
   * @param name the name
   * @param value the value
   * @param last the last
   */
  public static void writeAttribute(final StringBuffer buf, final String name, final String value, final boolean last) {
    buf.append(' ');
    buf.append(name);
    buf.append("=\"");
    Utils.encodeXMLchars(buf, value);
    buf.append('"');
    if (last) {
      buf.append('>');
    }
  }

  /** The def. */
  protected T def = null;

  /** The last val. */
  protected T lastVal = null;

  /** The name. */
  protected String name;

  /**
   * Instantiates a new tag attr.
   * 
   * @param name the name
   */
  public Attr(final String name) {
    super();
    this.name = name;
  }

  /**
   * Instantiates a new tag attr.
   * 
   * @param name the name
   * @param def the def
   */
  public Attr(final String name, final T def) {
    super();
    this.name = name;
    this.def = def;
  }

  /**
   * From val.
   * 
   * @param val the val
   * @return the string
   */
  public String fromVal(final T val) {
    return val.toString();
  }

  /**
   * Gets the def.
   * 
   * @return the def
   */
  public T getDef() {
    return def;
  }

  /**
   * Gets the last val.
   * 
   * @return the lastVal
   */
  public T getLastVal() {
    return lastVal;
  }

  /**
   * Gets the name.
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Checks if is.
   * 
   * @param name the name
   * @return true, if successful
   */
  public boolean checkName(final String name) {
    return this.name.equals(name);
  }

  /**
   * Read.
   * 
   * @param attribs the attribs
   * @return the t
   */
  public T read(final Attributes attribs) {
    lastVal = toVal(attribs.getValue(name));
    return lastVal;
  }

  /**
   * Sets the def.
   * 
   * @param def the def to set
   */
  public void setDef(final T def) {
    this.def = def;
  }

  /**
   * Sets the name.
   * 
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Read.
   * 
   * @param value the value
   * @return the t
   */
  public T toVal(final String value) {
    return null;
  }

  /**
   * Write.
   * 
   * @param buf the buf
   */
  public void write(final StringBuffer buf) {
    if ((lastVal != null) && (!lastVal.equals(def))) {
      Attr.writeAttribute(buf, name, fromVal(lastVal), false);
    }
  }

  /**
   * Sets the last val.
   * 
   * @param lastVal the lastVal to set
   */
  public void setLastVal(final T lastVal) {
    this.lastVal = lastVal;
  }

}
