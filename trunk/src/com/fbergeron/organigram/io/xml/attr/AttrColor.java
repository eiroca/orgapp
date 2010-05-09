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

import java.awt.Color;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import com.fbergeron.organigram.io.xml.Attr;
import com.fbergeron.organigram.util.Debug;
import com.fbergeron.organigram.util.Utils;

/**
 * The Class AttrColor.
 */
public class AttrColor extends Attr<Color> {

  /**
   * Instantiates a new tag attr color.
   * 
   * @param name the name
   */
  public AttrColor(final String name) {
    super(name);
  }

  /**
   * Instantiates a new tag attr color.
   * 
   * @param name the name
   * @param def the def
   */
  public AttrColor(final String name, final Color def) {
    super(name, def);
  }

  /** The Constant colors. */
  public static final Map<String, String> COLORS = new HashMap<String, String>();

  static {
    AttrColor.COLORS.put("aqua", "#00ffff");
    AttrColor.COLORS.put("cyan", "#00ffff");
    AttrColor.COLORS.put("gray", "#808080");
    AttrColor.COLORS.put("navy", "#000080");
    AttrColor.COLORS.put("silver", "#c0c0c0");
    AttrColor.COLORS.put("black", "#000000");
    AttrColor.COLORS.put("green", "#008000");
    AttrColor.COLORS.put("olive", "#808000");
    AttrColor.COLORS.put("teal", "#008080");
    AttrColor.COLORS.put("blue", "#0000ff");
    AttrColor.COLORS.put("lime", "#00ff00");
    AttrColor.COLORS.put("purple", "#800080");
    AttrColor.COLORS.put("white", "#ffffff");
    AttrColor.COLORS.put("fuchsia", "#ff00ff");
    AttrColor.COLORS.put("magenta", "#ff00ff");
    AttrColor.COLORS.put("maroon", "#800000");
    AttrColor.COLORS.put("red", "#ff0000");
    AttrColor.COLORS.put("yellow", "#ffff00");
  }

  /**
   * Expand.
   * 
   * @param val the val
   * @return the int
   */
  private static int expand(final int val) {
    return val * 16 + val;
  }

  /**
   * Parses the html color.
   * 
   * @param strColor the str color
   * @return the color
   * @throws ParseException the parse exception
   */
  private static Color parseHtmlColor(final String strColor) throws ParseException {
    int cRd;
    int cGr;
    int cBl;
    if (strColor.length() == 4) {
      cRd = AttrColor.expand(Integer.parseInt(strColor.substring(1, 2), 16));
      cGr = AttrColor.expand(Integer.parseInt(strColor.substring(2, 3), 16));
      cBl = AttrColor.expand(Integer.parseInt(strColor.substring(3, 4), 16));
    }
    else if (strColor.length() == 7) {
      cRd = Integer.parseInt(strColor.substring(1, 3), 16);
      cGr = Integer.parseInt(strColor.substring(3, 5), 16);
      cBl = Integer.parseInt(strColor.substring(5, 7), 16);
    }
    else {
      throw new ParseException(strColor + " has an invalid length.", 0);
    }
    return new Color(cRd, cGr, cBl);
  }

  /**
   * Parses the rgb color.
   * 
   * @param strColor the str color
   * @return the color
   * @throws ParseException the parse exception
   */
  private static Color parseRGBColor(final String strColor) throws ParseException {
    int cRd;
    int cGr;
    int cBl;
    int startIndex = 0;
    int endIndex = strColor.length();
    if (strColor.startsWith("rgb")) {
      startIndex = 4;
      if (strColor.endsWith(")")) {
        endIndex = strColor.length() - 1;
      }
    }
    final int firstComma = strColor.indexOf(',', startIndex);
    if (firstComma == -1) { throw new ParseException("First comma not found.", 0); }
    final int secondComma = strColor.indexOf(',', firstComma + 1);
    if (secondComma == -1) { throw new ParseException("Second comma not found.", firstComma); }
    cRd = Utils.val(strColor.substring(startIndex, firstComma).trim(), -1);
    cGr = Utils.val(strColor.substring(firstComma + 1, secondComma).trim(), -1);
    cBl = Utils.val(strColor.substring(secondComma + 1, endIndex).trim(), -1);
    if ((cRd == -1) || (cGr == -1) || (cBl == -1)) { throw new ParseException(strColor + " has an invalid definition.", 0); }
    return new Color(cRd, cGr, cBl);
  }

  /**
   * Parses the color.
   * 
   * @param strColor the str color
   * @return the color
   * @throws ParseException the parse exception
   */
  public static Color parseColor(final String strColor) throws ParseException {
    Color res;
    if (strColor == null) {
      res = new Color(0);
    }
    else {
      String strCol = strColor.trim().toLowerCase();
      if (AttrColor.COLORS.containsKey(strCol)) {
        strCol = AttrColor.COLORS.get(strCol);
      }
      if (strCol.charAt(0) == '#') {
        res = AttrColor.parseHtmlColor(strCol);
      }
      else {
        res = AttrColor.parseRGBColor(strCol);
      }
    }
    return res;
  }

  /**
   * Read color.
   * 
   * @param val the value
   * @return the color
   */
  @Override
  public Color toVal(final String val) {
    Color res = def;
    if (val != null) {
      try {
        res = AttrColor.parseColor(val);
      }
      catch (final ParseException e) {
        Debug.error(e.toString());
      }
    }
    return res;
  }

  /**
   * Write color.
   * 
   * @param col the color
   * 
   * @return the string
   */
  @Override
  public String fromVal(final Color col) {
    final StringBuffer buf = new StringBuffer();
    buf.append('#');
    Utils.writeHH(buf, col.getRed());
    Utils.writeHH(buf, col.getGreen());
    Utils.writeHH(buf, col.getBlue());
    return buf.toString();
  }

}
