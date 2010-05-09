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
package com.fbergeron.organigram.io.xml.attr;

import java.util.HashMap;
import java.util.Map;
import com.fbergeron.organigram.io.xml.Attr;

/**
 * The Class AttrEnum.
 * 
 * @param <T> the < t>
 */
public class AttrEnum<T extends Enum<?>> extends Attr<T> {

  /**
   * Instantiates a new tag attr enum.
   * 
   * @param name the name
   */
  public AttrEnum(final String name) {
    super(name);
  }

  /**
   * Instantiates a new tag attr enum.
   * 
   * @param name the name
   * @param def the def
   */
  public AttrEnum(final String name, final T def) {
    super(name, def);
  }

  /** The Constant alignments. */
  protected transient final Map<String, T> values = new HashMap<String, T>();

  /**
   * Write layout tag.
   * 
   * @param val the val
   * @return the string
   */
  @Override
  public String fromVal(final T val) {
    return (val == null ? null : val.toString().toLowerCase());
  }

  /**
   * Read alignment.
   * 
   * @param val the value
   * @return the alignment (int)
   */
  @Override
  public T toVal(final String val) {
    T res = null;
    if (val != null) {
      res = values.get(val.trim().toLowerCase());
    }
    return (res == null) ? def : res;
  }

}
