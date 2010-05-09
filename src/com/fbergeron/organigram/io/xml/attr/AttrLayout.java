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

import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.util.Utils;

/**
 * The Class AttrLayout.
 */
public class AttrLayout extends AttrEnum<Layout> {

  /**
   * Instantiates a new tag attr layout.
   * 
   * @param name the name
   */
  public AttrLayout(final String name) {
    this(name, null);
  }

  /**
   * Instantiates a new tag attr layout.
   * 
   * @param name the name
   * @param def the def
   */
  public AttrLayout(final String name, final Layout def) {
    super(name, def);
    for (final Layout x : Layout.values()) {
      Utils.addEnum(values, x);
    }
  }

}
