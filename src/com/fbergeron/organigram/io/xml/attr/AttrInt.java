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

import com.fbergeron.organigram.io.xml.Attr;
import com.fbergeron.organigram.util.Debug;

/**
 * The Class AttrInt.
 */
public class AttrInt extends Attr<Integer> {

  /**
   * Instantiates a new tag attr int.
   * 
   * @param name the name
   */
  public AttrInt(final String name) {
    super(name);
  }

  /**
   * Instantiates a new tag attr int.
   * 
   * @param name the name
   * @param def the def
   */
  public AttrInt(final String name, final Integer def) {
    super(name, def);
  }

  /**
   * Read integer.
   * 
   * @param val the value
   * @return the integer
   */
  @Override
  public Integer toVal(final String val) {
    Integer res = def;
    if (val != null) {
      try {
        res = Integer.valueOf(val);
      }
      catch (final NumberFormatException e) {
        Debug.error(e.toString());
      }
    }
    return res;
  }

}
