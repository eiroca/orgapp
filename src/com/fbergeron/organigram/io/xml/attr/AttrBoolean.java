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
 * The Class AttrBoolean.
 */
public class AttrBoolean extends Attr<Boolean> {

  /**
   * Instantiates a new tag attr boolean.
   * 
   * @param name the name
   */
  public AttrBoolean(final String name) {
    super(name);
  }

  /**
   * Instantiates a new tag attr boolean.
   * 
   * @param name the name
   * @param def the def
   */
  public AttrBoolean(final String name, final Boolean def) {
    super(name, def);
  }

  /** The Constant VAL_TRUE. */
  public static final String VAL_TRUE = "true";

  /** The Constant VAL_FALSE. */
  public static final String VAL_FALSE = "false";

  /** The Constant bools. */
  public static final Map<String, Boolean> BOOLS = new HashMap<String, Boolean>();

  static {
    AttrBoolean.BOOLS.put("0", Boolean.FALSE);
    AttrBoolean.BOOLS.put("f", Boolean.FALSE);
    AttrBoolean.BOOLS.put("false", Boolean.FALSE);
    AttrBoolean.BOOLS.put("1", Boolean.TRUE);
    AttrBoolean.BOOLS.put("-1", Boolean.TRUE);
    AttrBoolean.BOOLS.put("t", Boolean.TRUE);
    AttrBoolean.BOOLS.put("true", Boolean.TRUE);
  }

  /**
   * Read boolean.
   * 
   * @param val the value
   * @return true, if successful
   */
  @Override
  public Boolean toVal(final String val) {
    Boolean res = def;
    if (val != null) {
      final Boolean bVal = AttrBoolean.BOOLS.get(val.trim().toLowerCase());
      if (bVal != null) {
        res = bVal;
      }
    }
    return res;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TagAttr#fromVal(java.lang.Object)
   */
  @Override
  public String fromVal(final Boolean val) {
    return val ? AttrBoolean.VAL_TRUE : AttrBoolean.VAL_FALSE;
  }

}
