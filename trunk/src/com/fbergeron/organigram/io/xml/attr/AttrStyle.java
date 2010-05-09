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

import java.awt.Font;
import java.util.StringTokenizer;
import com.fbergeron.organigram.io.xml.Attr;

/**
 * The Class AttrInt.
 */
public class AttrStyle extends Attr<Integer> {

  /** The Constant VAL_BOLD. */
  public static final String VAL_BOLD = "bold";
  /** The Constant VAL_ITALIC. */
  public static final String VAL_ITALIC = "italic";

  /**
   * Instantiates a new tag attr int.
   * 
   * @param name the name
   */
  public AttrStyle(final String name) {
    super(name);
  }

  /**
   * Instantiates a new tag attr int.
   * 
   * @param name the name
   * @param def the def
   */
  public AttrStyle(final String name, final Integer def) {
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
    final Integer res = def;
    if (val != null) {
      int style = Font.PLAIN;
      final StringTokenizer styleStr = new StringTokenizer(val, "+");
      String styleAtr;
      while (styleStr.hasMoreTokens()) {
        styleAtr = styleStr.nextToken();
        if (AttrStyle.VAL_BOLD.equalsIgnoreCase(styleAtr)) {
          style |= Font.BOLD;
        }
        else if (AttrStyle.VAL_ITALIC.equalsIgnoreCase(styleAtr)) {
          style |= Font.ITALIC;
        }
      }
    }
    return res;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.Attr#fromVal(java.lang.Object)
   */
  @Override
  public String fromVal(final Integer val) {
    boolean first = true;
    final int style = val.intValue();
    final StringBuffer styleBuf = new StringBuffer();
    if ((style & Font.ITALIC) == Font.ITALIC) {
      styleBuf.append(AttrStyle.VAL_ITALIC);
      first = false;
    }
    if ((style & Font.BOLD) == Font.BOLD) {
      if (!first) {
        styleBuf.append('+');
      }
      styleBuf.append(AttrStyle.VAL_BOLD);
    }
    return styleBuf.toString();
  }

}