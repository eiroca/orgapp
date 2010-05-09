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

import com.fbergeron.organigram.model.type.OrgMode;
import com.fbergeron.organigram.util.Utils;

/**
 * The Class AttrOrgMode.
 */
public class AttrOrgMode extends AttrEnum<OrgMode> {

  /**
   * Instantiates a new tag attr org mode.
   * 
   * @param name the name
   */
  public AttrOrgMode(final String name) {
    super(name, null);
  }

  /**
   * Instantiates a new tag attr org mode.
   * 
   * @param name the name
   * @param def the def
   */
  public AttrOrgMode(final String name, final OrgMode def) {
    super(name, def);
    for (final OrgMode x : OrgMode.values()) {
      Utils.addEnum(values, x);
    }
  }

}
