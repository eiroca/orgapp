/**
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
 * Copyright (C) 2006-2008 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the:
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110-1301
 *   USA
 */
package com.fbergeron.organigram.io;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.text.ParseException;
import java.util.StringTokenizer;

public final class XMLUtil {

  public static final String TAG_ORGANIGRAM = "organigram";
  public static final String TAG_UNIT = "unit";
  public static final String TAG_CHILDS = "childs";
  public static final String TAG_INFO = "info";

  public static final String ATR_ISTOOLTIPENABLED = "isToolTipEnabled";
  public static final String ATR_BACKGROUNDCOLOR = "backgroundColor";
  public static final String ATR_LINECOLOR = "lineColor";

  public static final String ATR_BOX_COLOR_FRAME = "boxFrameColor";
  public static final String ATR_BOX_COLOR_BACKGROUND = "boxBackgroundColor";
  public static final String ATR_BOX_COLOR_FOREGROUND = "boxForegroundColor";
  public static final String ATR_BOX_PADDING_RIGHT = "boxRightPadding";
  public static final String ATR_BOX_PADDING_LEFT = "boxLeftPadding";
  public static final String ATR_BOX_PADDING_TOP = "boxTopPadding";
  public static final String ATR_BOX_PADDING_BOTTOM = "boxBottomPadding";
  public static final String ATR_BOX_TEXT_ALIGMENT = "boxTextAlignment";

  public static final String ATR_MARGIN_RIGHT = "boxRightMargin";
  public static final String ATR_MARGIN_LEFT = "boxLeftMargin";
  public static final String ATR_MARGIN_TOP = "boxTopMargin";
  public static final String ATR_MARGIN_BOTTOM = "boxBottomMargin";

  public static final String ATR_ORG_MODE = "mode";
  public static final String ATR_ORG_LAYOUT = "layout";
  public static final String ATR_ORG_COMPACT = "compact";

  public static final String ATR_TYPE = "type";
  public static final String ATR_LINK = "link";
  public static final String ATR_WHEN = "date";
  public static final String ATR_ID = "id";
  public static final String ATR_FONT_COLOR = "fontColor";
  public static final String ATR_FONT_NAME = "fontName";
  public static final String ATR_FONT_SIZE = "fontSize";
  public static final String ATR_FONT_STYLE = "fontStyle";

  public static final String VAL_CENTER = "center";
  public static final String VAL_LEFT = "left";
  public static final String VAL_RIGHT = "right";
  public static final String VAL_TRUE = "true";
  public static final String VAL_FALSE = "false";
  public static final String VAL_BOLD = "bold";
  public static final String VAL_ITALIC = "italic";

  public static String writeColor(final Color c) {
    final StringBuffer sb = new StringBuffer();
    sb.append(c.getRed());
    sb.append(',');
    sb.append(c.getGreen());
    sb.append(',');
    sb.append(c.getBlue());
    return sb.toString();
  }

  public static Color parseColor(final String strColor) throws ParseException {
    final int indexOfFirstComma = strColor.indexOf(',');
    final int indexOfSecondComma = strColor.indexOf(',', indexOfFirstComma + 1);
    if (indexOfFirstComma == -1) { throw (new ParseException("First comma not found.", 0)); }
    if (indexOfSecondComma == -1) { throw (new ParseException("Second comma not found.", indexOfFirstComma)); }
    int red = 0;
    int green = 0;
    int blue = 0;
    try {
      red = Integer.parseInt(strColor.substring(0, indexOfFirstComma));
    }
    catch (final NumberFormatException numberFormatException) {
      throw (new ParseException("Red color is not a number.", 0));
    }
    try {
      green = Integer.parseInt(strColor.substring(indexOfFirstComma + 1, indexOfSecondComma));
    }
    catch (final NumberFormatException numberFormatException) {
      throw (new ParseException("Green color is not a number.", indexOfFirstComma + 1));
    }
    try {
      blue = Integer.parseInt(strColor.substring(indexOfSecondComma + 1));
    }
    catch (final NumberFormatException numberFormatException) {
      throw (new ParseException("Blue color is not a number.", indexOfSecondComma + 1));
    }
    return (new Color(red, green, blue));
  }

  public static Color readColor(final String val, final Color def) {
    if (val != null) {
      try {
        return XMLUtil.parseColor(val);
      }
      catch (final ParseException e) {
        System.err.println(e.toString());
      }
    }
    return def;
  }

  public static int readInt(final String val, final int def) {
    if (val != null) {
      try {
        return Integer.parseInt(val);
      }
      catch (final NumberFormatException e) {
        System.err.println(e.toString());
      }
    }
    return def;
  }

  public static int readAligment(final String val, final int def) {
    if (val != null) {
      if (XMLUtil.VAL_CENTER.equalsIgnoreCase(val)) {
        return Label.CENTER;
      }
      else if (XMLUtil.VAL_LEFT.equalsIgnoreCase(val)) {
        return Label.LEFT;
      }
      else if (XMLUtil.VAL_RIGHT.equalsIgnoreCase(val)) { return Label.RIGHT; }
    }
    return def;
  }

  public static boolean readBoolean(final String val, final boolean def) {
    if (val != null) {
      if (XMLUtil.VAL_TRUE.equals(val)) { return true; }
      if (XMLUtil.VAL_FALSE.equals(val)) { return false; }
    }
    return def;
  }

  public static int readFontStyle(final String val, final int def) {
    if (val != null) {
      int style = Font.PLAIN;
      final StringTokenizer st = new StringTokenizer(val, "+");
      String vl;
      while (st.hasMoreTokens()) {
        vl = st.nextToken();
        if (XMLUtil.VAL_BOLD.equalsIgnoreCase(vl)) {
          style |= Font.BOLD;
        }
        else if (XMLUtil.VAL_ITALIC.equalsIgnoreCase(vl)) {
          style |= Font.ITALIC;
        }
      }
      return style;
    }
    return def;
  }

  public static void writeColor(final TAG me, final StringBuffer sb, final String atr, final Color defVal, final Color val) {
    if (val != defVal) {
      me.writeAttribute(sb, atr, XMLUtil.writeColor(val), false);
    }
  }

  public static void writeInt(final TAG me, final StringBuffer sb, final String atr, final int defVal, final int val) {
    if (val != defVal) {
      me.writeAttribute(sb, atr, Integer.toString(val), false);
    }
  }

  public static void writeFont(final TAG me, final StringBuffer sb, final String atrName1, final String atrName2, final String atrName3, final Font defVal, final Font val) {
    final String name = val.getFamily();
    final String name2 = defVal.getFamily();
    if (!name.equals(name2)) {
      me.writeAttribute(sb, atrName1, name, false);
    }
    if (val.getSize() != defVal.getSize()) {
      me.writeAttribute(sb, atrName2, Integer.toString(val.getSize()), false);
    }
    final int style = val.getStyle();
    if (style != defVal.getStyle()) {
      boolean first = true;
      final StringBuffer styleBuf = new StringBuffer();
      if ((style & Font.ITALIC) == Font.ITALIC) {
        styleBuf.append(XMLUtil.VAL_ITALIC);
        first = false;
      }
      if ((style & Font.BOLD) == Font.BOLD) {
        if (!first) {
          styleBuf.append('+');
        }
        styleBuf.append(XMLUtil.VAL_BOLD);
      }
      me.writeAttribute(sb, atrName3, styleBuf.toString(), false);
    }
  }

  public static void writeBoolean(final TAG me, final StringBuffer sb, final String atr, final boolean defVal, final boolean val) {
    if (val != defVal) {
      if (val) {
        me.writeAttribute(sb, atr, XMLUtil.VAL_TRUE, false);
      }
      else {
        me.writeAttribute(sb, atr, XMLUtil.VAL_FALSE, false);
      }
    }
  }

  public static void writeAlign(final TAG me, final StringBuffer sb, final String atr, final int defVal, final int val) {
    if (val != defVal) {
      switch (val) {
      case Label.CENTER:
        me.writeAttribute(sb, atr, XMLUtil.VAL_CENTER, false);
        break;
      case Label.LEFT:
        me.writeAttribute(sb, atr, XMLUtil.VAL_LEFT, false);
        break;
      case Label.RIGHT:
        me.writeAttribute(sb, atr, XMLUtil.VAL_RIGHT, false);
        break;
      }
    }
  }

  public static void writeString(final TAG me, final StringBuffer sb, final String atr, final String val) {
    if (val != null) {
      me.writeAttribute(sb, atr, val, false);
    }
  }

}
