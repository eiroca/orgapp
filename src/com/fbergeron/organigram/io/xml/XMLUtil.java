/** LGPL > 3.0
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
 * Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/
 */
package com.fbergeron.organigram.io.xml;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.BoxLayout;

/**
 * The Class XMLUtil.
 */
public final class XMLUtil {

  /** The Constant ORGANIGRAM. */
  public static final TAG ORGANIGRAM = new OrganigramProcessor();

  /** The Constant UNIT. */
  public static final TAG UNIT = new UnitProcessor();

  /** The Constant CHILDS. */
  public static final TAG CHILDS = new ChildsProcessor();

  /** The Constant INFO. */
  public static final TAG INFO = new InfoProcessor();

  /** The Constant ATR_ISTOOLTIPENABLED. */
  public static final String ATR_TOOLTIPENABLED = "isToolTipEnabled";

  /** The Constant ATR_BACKGROUNDCOLOR. */
  public static final String ATR_BACKGROUNDCOLOR = "backgroundColor";

  /** The Constant ATR_LINECOLOR. */
  public static final String ATR_LINECOLOR = "lineColor";

  /** The Constant ATR_BOX_COLOR_FRAME. */
  public static final String ATR_BOX_COLOR_FRAME = "boxFrameColor";

  /** The Constant ATR_BOX_COLOR_BACKGROUND. */
  public static final String ATR_BOX_COLOR_BACKGROUND = "boxBackgroundColor";

  /** The Constant ATR_BOX_COLOR_FOREGROUND. */
  public static final String ATR_BOX_COLOR_FOREGROUND = "boxForegroundColor";

  /** The Constant ATR_BOX_PADDING_RIGHT. */
  public static final String ATR_BOX_PADDING_RIGHT = "boxRightPadding";

  /** The Constant ATR_BOX_PADDING_LEFT. */
  public static final String ATR_BOX_PADDING_LEFT = "boxLeftPadding";

  /** The Constant ATR_BOX_PADDING_TOP. */
  public static final String ATR_BOX_PADDING_TOP = "boxTopPadding";

  /** The Constant ATR_BOX_PADDING_BOTTOM. */
  public static final String ATR_BOX_PADDING_BOTTOM = "boxBottomPadding";

  /** The Constant ATR_BOX_TEXT_ALIGMENT. */
  public static final String ATR_BOX_TEXT_ALIGMENT = "boxTextAlignment";

  /** The Constant ATR_BOX_EXPENDED. */
  public static final String ATR_BOX_EXPENDED = "boxExpanded";

  /** The Constant ATR_MARGIN_RIGHT. */
  public static final String ATR_MARGIN_RIGHT = "boxRightMargin";

  /** The Constant ATR_MARGIN_LEFT. */
  public static final String ATR_MARGIN_LEFT = "boxLeftMargin";

  /** The Constant ATR_MARGIN_TOP. */
  public static final String ATR_MARGIN_TOP = "boxTopMargin";

  /** The Constant ATR_MARGIN_BOTTOM. */
  public static final String ATR_MARGIN_BOTTOM = "boxBottomMargin";

  /** The Constant ATR_ORG_MODE. */
  public static final String ATR_ORG_MODE = "mode";

  /** The Constant ATR_ORG_LAYOUT. */
  public static final String ATR_ORG_LAYOUT = "layout";

  /** The Constant ATR_ORG_COMPACT. */
  public static final String ATR_ORG_COMPACT = "compact";

  /** The Constant ATR_TYPE. */
  public static final String ATR_TYPE = "type";

  /** The Constant ATR_LINK. */
  public static final String ATR_LINK = "link";

  /** The Constant ATR_WHEN. */
  public static final String ATR_WHEN = "date";

  /** The Constant ATR_ID. */
  public static final String ATR_ID = "id";

  /** The Constant ATR_FONT_COLOR. */
  public static final String ATR_FONT_COLOR = "fontColor";

  /** The Constant ATR_FONT_NAME. */
  public static final String ATR_FONT_NAME = "fontName";

  /** The Constant ATR_FONT_SIZE. */
  public static final String ATR_FONT_SIZE = "fontSize";

  /** The Constant ATR_FONT_STYLE. */
  public static final String ATR_FONT_STYLE = "fontStyle";

  /** The Constant VAL_CENTER. */
  public static final String VAL_CENTER = "center";

  /** The Constant VAL_LEFT. */
  public static final String VAL_LEFT = "left";

  /** The Constant VAL_RIGHT. */
  public static final String VAL_RIGHT = "right";

  /** The Constant VAL_TRUE. */
  public static final String VAL_TRUE = "true";

  /** The Constant VAL_FALSE. */
  public static final String VAL_FALSE = "false";

  /** The Constant VAL_BOLD. */
  public static final String VAL_BOLD = "bold";

  /** The Constant VAL_ITALIC. */
  public static final String VAL_ITALIC = "italic";

  private static final HashSet<String> boxLayoutAttrs = new HashSet<String>();
  private static final HashMap<String, String> colors = new HashMap<String, String>();

  static {
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_PADDING_RIGHT);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_PADDING_LEFT);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_PADDING_TOP);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_PADDING_BOTTOM);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_COLOR_FRAME);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_COLOR_BACKGROUND);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_COLOR_FOREGROUND);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_TEXT_ALIGMENT);
    XMLUtil.boxLayoutAttrs.add(XMLUtil.ATR_BOX_EXPENDED);
    XMLUtil.colors.put("aqua", "#00ffff");
    XMLUtil.colors.put("cyan", "#00ffff");
    XMLUtil.colors.put("gray", "#808080");
    XMLUtil.colors.put("navy", "#000080");
    XMLUtil.colors.put("silver", "#c0c0c0");
    XMLUtil.colors.put("black", "#000000");
    XMLUtil.colors.put("green", "#008000");
    XMLUtil.colors.put("olive", "#808000");
    XMLUtil.colors.put("teal", "#008080");
    XMLUtil.colors.put("blue", "#0000ff");
    XMLUtil.colors.put("lime", "#00ff00");
    XMLUtil.colors.put("purple", "#800080");
    XMLUtil.colors.put("white", "#ffffff");
    XMLUtil.colors.put("fuchsia", "#ff00ff");
    XMLUtil.colors.put("magenta", "#ff00ff");
    XMLUtil.colors.put("maroon", "#800000");
    XMLUtil.colors.put("red", "#ff0000");
    XMLUtil.colors.put("yellow", "#ffff00");
  }

  /**
   * Instantiates a new XML utility
   */
  private XMLUtil() {
    super();
  }

  /**
   * Write color.
   * 
   * @param col the color
   * 
   * @return the string
   */
  public static String writeColor(final Color col) {
    final StringBuffer buf = new StringBuffer();
    buf.append(col.getRed());
    buf.append(',');
    buf.append(col.getGreen());
    buf.append(',');
    buf.append(col.getBlue());
    return buf.toString();
  }

  /**
   * Parses the color.
   * 
   * @param strColor the string color
   * 
   * @return the color
   * 
   * @throws ParseException the parse exception
   */
  public static Color parseColor(String strColor) throws ParseException {
    int rd = 0;
    int gr = 0;
    int bl = 0;
    if (strColor != null) {
      try {
        strColor = strColor.toLowerCase();
        if (XMLUtil.colors.containsKey(strColor)) {
          strColor = XMLUtil.colors.get(strColor);
        }
        if (strColor.startsWith("#")) {
          if (strColor.length() == 4) {
            final StringBuffer sb = new StringBuffer();
            sb.append('#').append(strColor.charAt(1)).append(strColor.charAt(1)).append(strColor.charAt(2)).append(strColor.charAt(2)).append(strColor.charAt(3)).append(strColor.charAt(3));
            strColor = sb.toString();
          }
          if (strColor.length() == 7) {
            rd = Integer.parseInt(strColor.substring(1, 3), 16);
            gr = Integer.parseInt(strColor.substring(3, 5), 16);
            bl = Integer.parseInt(strColor.substring(5, 7), 16);
          }
          else {
            throw (new ParseException(strColor + " has an invalid length.", 0));
          }
        }
        else {
          final int indexOfFirstComma = strColor.indexOf(',');
          final int indexOfSecondComma = strColor.indexOf(',', indexOfFirstComma + 1);
          if (indexOfFirstComma == -1) { throw (new ParseException("First comma not found.", 0)); }
          if (indexOfSecondComma == -1) { throw (new ParseException("Second comma not found.", indexOfFirstComma)); }
          rd = Integer.parseInt(strColor.substring(0, indexOfFirstComma));
          gr = Integer.parseInt(strColor.substring(indexOfFirstComma + 1, indexOfSecondComma));
          bl = Integer.parseInt(strColor.substring(indexOfSecondComma + 1));
        }
      }
      catch (final NumberFormatException numberFormatException) {
        throw (new ParseException(strColor + " is an invalid color", 0));
      }
    }
    return new Color(rd, gr, bl);
  }

  /**
   * Read color.
   * 
   * @param val the value
   * @param def the default
   * 
   * @return the color
   */
  public static Color readColor(final String val, final Color def) {
    Color res = def;
    if (val != null) {
      try {
        res = XMLUtil.parseColor(val);
      }
      catch (final ParseException e) {
        System.err.println(e.toString());
      }
    }
    return res;
  }

  /**
   * Read integer
   * 
   * @param val the value
   * @param def the default
   * 
   * @return the integer
   */
  public static int readInt(final String val, final int def) {
    int res = def;
    if (val != null) {
      try {
        res = Integer.parseInt(val);
      }
      catch (final NumberFormatException e) {
        System.err.println(e.toString());
      }
    }
    return res;
  }

  /**
   * Read alignment.
   * 
   * @param val the value
   * @param def the default
   * 
   * @return the alignment (int)
   */
  public static int readAligment(final String val, final int def) {
    int res = def;
    if (val != null) {
      if (XMLUtil.VAL_CENTER.equalsIgnoreCase(val)) {
        res = Label.CENTER;
      }
      else if (XMLUtil.VAL_LEFT.equalsIgnoreCase(val)) {
        res = Label.LEFT;
      }
      else if (XMLUtil.VAL_RIGHT.equalsIgnoreCase(val)) {
        res = Label.RIGHT;
      }
    }
    return res;
  }

  /**
   * Read boolean.
   * 
   * @param val the value
   * @param def the default
   * 
   * @return true, if successful
   */
  public static boolean readBoolean(final String val, final boolean def) {
    boolean res = def;
    if (val != null) {
      if (XMLUtil.VAL_TRUE.equals(val)) {
        res = true;
      }
      else if (XMLUtil.VAL_FALSE.equals(val)) {
        res = false;
      }
    }
    return res;
  }

  /**
   * Read font style.
   * 
   * @param val the value
   * @param def the default
   * 
   * @return the font style (int)
   */
  public static int readFontStyle(final String val, final int def) {
    int res = def;
    if (val != null) {
      int style = Font.PLAIN;
      final StringTokenizer styleStr = new StringTokenizer(val, "+");
      String styleAtr;
      while (styleStr.hasMoreTokens()) {
        styleAtr = styleStr.nextToken();
        if (XMLUtil.VAL_BOLD.equalsIgnoreCase(styleAtr)) {
          style |= Font.BOLD;
        }
        else if (XMLUtil.VAL_ITALIC.equalsIgnoreCase(styleAtr)) {
          style |= Font.ITALIC;
        }
      }
      res = style;
    }
    return res;
  }

  /**
   * Write color.
   * 
   * @param tag the me
   * @param buf the stringbuffer
   * @param atr the attribute
   * @param defVal the default value
   * @param val the value
   */
  public static void writeColor(final TAG tag, final StringBuffer buf, final String atr, final Color defVal, final Color val) {
    if (!val.equals(defVal)) {
      tag.writeAttribute(buf, atr, XMLUtil.writeColor(val), false);
    }
  }

  /**
   * Write integer.
   * 
   * @param tag the me
   * @param buf the stringbuffer
   * @param atr the attribute
   * @param defVal the default value
   * @param val the value
   */
  public static void writeInt(final TAG tag, final StringBuffer buf, final String atr, final int defVal, final int val) {
    if (val != defVal) {
      tag.writeAttribute(buf, atr, Integer.toString(val), false);
    }
  }

  /**
   * Write font.
   * 
   * @param tag the me
   * @param buf the stringbuffer
   * @param atrName1 the attribute name1
   * @param atrName2 the attribute name2
   * @param atrName3 the attribute name3
   * @param defVal the default value
   * @param val the value
   */
  public static void writeFont(final TAG tag, final StringBuffer buf, final String atrName1, final String atrName2, final String atrName3, final Font defVal, final Font val) {
    final String name = val.getFamily();
    final String name2 = defVal.getFamily();
    if (!name.equals(name2)) {
      tag.writeAttribute(buf, atrName1, name, false);
    }
    if (val.getSize() != defVal.getSize()) {
      tag.writeAttribute(buf, atrName2, Integer.toString(val.getSize()), false);
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
      tag.writeAttribute(buf, atrName3, styleBuf.toString(), false);
    }
  }

  /**
   * Write boolean.
   * 
   * @param tag the me
   * @param buf the stringbuffer
   * @param atr the attribute
   * @param defVal the default value
   * @param val the value
   */
  public static void writeBoolean(final TAG tag, final StringBuffer buf, final String atr, final boolean defVal, final boolean val) {
    if (val != defVal) {
      if (val) {
        tag.writeAttribute(buf, atr, XMLUtil.VAL_TRUE, false);
      }
      else {
        tag.writeAttribute(buf, atr, XMLUtil.VAL_FALSE, false);
      }
    }
  }

  /**
   * Write align.
   * 
   * @param tag the me
   * @param buf the sb
   * @param atr the atr
   * @param defVal the def val
   * @param val the val
   */
  public static void writeAlign(final TAG tag, final StringBuffer buf, final String atr, final int defVal, final int val) {
    if (val != defVal) {
      switch (val) {
        case Label.CENTER:
          tag.writeAttribute(buf, atr, XMLUtil.VAL_CENTER, false);
          break;
        case Label.LEFT:
          tag.writeAttribute(buf, atr, XMLUtil.VAL_LEFT, false);
          break;
        case Label.RIGHT:
          tag.writeAttribute(buf, atr, XMLUtil.VAL_RIGHT, false);
          break;
        default:
          break;
      }
    }
  }

  /**
   * Write string.
   * 
   * @param tag the me
   * @param buf the sb
   * @param atr the atr
   * @param val the val
   */
  public static void writeString(final TAG tag, final StringBuffer buf, final String atr, final String val) {
    if (val != null) {
      tag.writeAttribute(buf, atr, val, false);
    }
  }

  /**
   * Checks if is box layout atr.
   * 
   * @param name the name
   * @param value the value
   * 
   * @return true, if is box layout atr
   */
  public static boolean isBoxLayoutAtr(final String name, final String value) {
    final boolean res = XMLUtil.boxLayoutAttrs.contains(name);
    return res;
  }

  /**
   * Read box layout atr.
   * 
   * @param name the name
   * @param value the value
   * @param boxLay the box lay
   */
  public static void readBoxLayoutAtr(final String name, final String value, final BoxLayout boxLay) {
    if (name.equals(XMLUtil.ATR_BOX_PADDING_RIGHT)) {
      boxLay.setRightPadding(XMLUtil.readInt(value, boxLay.getRightPadding()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_PADDING_LEFT)) {
      boxLay.setLeftPadding(XMLUtil.readInt(value, boxLay.getLeftPadding()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_PADDING_TOP)) {
      boxLay.setTopPadding(XMLUtil.readInt(value, boxLay.getTopPadding()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_PADDING_BOTTOM)) {
      boxLay.setBottomPadding(XMLUtil.readInt(value, boxLay.getBottomPadding()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_COLOR_FRAME)) {
      boxLay.setFrameColor(XMLUtil.readColor(value, boxLay.getFrameColor()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_COLOR_BACKGROUND)) {
      boxLay.setBackgroundColor(XMLUtil.readColor(value, boxLay.getBackgroundColor()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_COLOR_FOREGROUND)) {
      boxLay.setForegroundColor(XMLUtil.readColor(value, boxLay.getForegroundColor()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_TEXT_ALIGMENT)) {
      boxLay.setTextAlignment(XMLUtil.readAligment(value, boxLay.getTextAlignment()));
    }
    else if (name.equals(XMLUtil.ATR_BOX_EXPENDED)) {
      boxLay.setExpanded(XMLUtil.readBoolean(value, boxLay.isExpanded()));
    }
  }

  /**
   * Write box layout atr.
   * 
   * @param tag the tag
   * @param buf the buf
   * @param boxLay the box lay
   */
  public static void writeBoxLayoutAtr(final TAG tag, final StringBuffer buf, final BoxLayout boxLay) {
    XMLUtil.writeColor(tag, buf, XMLUtil.ATR_BOX_COLOR_FRAME, BoxLayout.COLOR_BOXFRAME, boxLay.getFrameColor());
    XMLUtil.writeColor(tag, buf, XMLUtil.ATR_BOX_COLOR_BACKGROUND, BoxLayout.COLOR_BACKGROUND, boxLay.getBackgroundColor());
    XMLUtil.writeColor(tag, buf, XMLUtil.ATR_BOX_COLOR_FOREGROUND, BoxLayout.COLOR_FOREGROUND, boxLay.getForegroundColor());
    XMLUtil.writeInt(tag, buf, XMLUtil.ATR_BOX_PADDING_TOP, BoxLayout.PADDING_TOP, boxLay.getTopPadding());
    XMLUtil.writeInt(tag, buf, XMLUtil.ATR_BOX_PADDING_LEFT, BoxLayout.PADDING_LEFT, boxLay.getLeftPadding());
    XMLUtil.writeInt(tag, buf, XMLUtil.ATR_BOX_PADDING_RIGHT, BoxLayout.PADDING_RIGHT, boxLay.getRightPadding());
    XMLUtil.writeInt(tag, buf, XMLUtil.ATR_BOX_PADDING_BOTTOM, BoxLayout.PADDING_BOTTOM, boxLay.getBottomPadding());
    XMLUtil.writeAlign(tag, buf, XMLUtil.ATR_BOX_TEXT_ALIGMENT, BoxLayout.TEXT_ALIGN, boxLay.getTextAlignment());
  }

}
