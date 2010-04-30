/**
 * LGPL > 3.0 Copyright (C) 2005 Frédéric Bergeron
 * (fbergeron@users.sourceforge.net) Copyright (C) 2006-2010 eIrOcA (eNrIcO
 * Croce & sImOnA Burzio)
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
package com.fbergeron.organigram.io.sof;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.io.sof.tags.TagInfo;
import com.fbergeron.organigram.io.sof.tags.TagOrganigram;
import com.fbergeron.organigram.io.sof.tags.TagUnit;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.type.Alignment;
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.model.type.LineMode;
import com.fbergeron.organigram.model.type.OrgMode;
import com.fbergeron.organigram.util.Debug;
import com.fbergeron.organigram.util.OrgUtils;

/**
 * The Class XMLUtil.
 */
public final class SOFXML {

  /** The Constant ORGANIGRAM. */
  public final transient TAG ORGANIGRAM = new TagOrganigram();

  /** The Constant UNIT. */
  public final transient TAG UNIT = new TagUnit();

  /** The Constant INFO. */
  public final transient TAG INFO = new TagInfo();

  /** The Constant ATR_ISTOOLTIPENABLED. */
  public static final String ATR_TOOLTIPENABLED = "isToolTipEnabled";

  /** The Constant ATR_BACKGROUNDCOLOR. */
  public static final String ATR_BACKGROUNDCOLOR = "backgroundColor";

  /** The Constant ATR_LINECOLOR. */
  public static final String ATR_LINECOLOR = "lineColor";

  /** The Constant ATR_LINEMODE. */
  public static final String ATR_LINEMODE = "lineMode";

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

  /** The Constant VAL_TRUE. */
  public static final String VAL_TRUE = "true";

  /** The Constant VAL_FALSE. */
  public static final String VAL_FALSE = "false";

  /** The Constant VAL_BOLD. */
  public static final String VAL_BOLD = "bold";

  /** The Constant VAL_ITALIC. */
  public static final String VAL_ITALIC = "italic";

  /** The Constant boxLayoutAttrs. */
  private static final Set<String> BOXLAYOUTATTRS = new HashSet<String>();

  /** The Constant colors. */
  private static final Map<String, String> COLORS = new HashMap<String, String>();

  /** The Constant layouts. */
  private static final Map<String, Layout> LAYOUTS = new HashMap<String, Layout>();

  /** The Constant orgModes. */
  private static final Map<String, OrgMode> ORGMODES = new HashMap<String, OrgMode>();

  /** The Constant lineModes. */
  private static final Map<String, LineMode> LINEMODES = new HashMap<String, LineMode>();

  /** The Constant bools. */
  private static final Map<String, Boolean> BOOLS = new HashMap<String, Boolean>();

  /** The Constant alignments. */
  private static final Map<String, Alignment> ALIGNMENTS = new HashMap<String, Alignment>();

  /** The instance. */
  private static SOFXML instance;

  /**
   * Adds the enum.
   * 
   * @param set the set
   * @param vals the values
   */
  @SuppressWarnings("unchecked")
  private static void addEnum(final Map set, final Enum vals) {
    final String name = vals.name().toLowerCase();
    set.put(name, vals);
    set.put(name.substring(0, 1), vals);
    set.put(Integer.toString(vals.ordinal()), vals);
  }

  static {
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_PADDING_RIGHT);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_PADDING_LEFT);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_PADDING_TOP);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_PADDING_BOTTOM);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_COLOR_FRAME);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_COLOR_BACKGROUND);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_COLOR_FOREGROUND);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_TEXT_ALIGMENT);
    SOFXML.BOXLAYOUTATTRS.add(SOFXML.ATR_BOX_EXPENDED);
    SOFXML.COLORS.put("aqua", "#00ffff");
    SOFXML.COLORS.put("cyan", "#00ffff");
    SOFXML.COLORS.put("gray", "#808080");
    SOFXML.COLORS.put("navy", "#000080");
    SOFXML.COLORS.put("silver", "#c0c0c0");
    SOFXML.COLORS.put("black", "#000000");
    SOFXML.COLORS.put("green", "#008000");
    SOFXML.COLORS.put("olive", "#808000");
    SOFXML.COLORS.put("teal", "#008080");
    SOFXML.COLORS.put("blue", "#0000ff");
    SOFXML.COLORS.put("lime", "#00ff00");
    SOFXML.COLORS.put("purple", "#800080");
    SOFXML.COLORS.put("white", "#ffffff");
    SOFXML.COLORS.put("fuchsia", "#ff00ff");
    SOFXML.COLORS.put("magenta", "#ff00ff");
    SOFXML.COLORS.put("maroon", "#800000");
    SOFXML.COLORS.put("red", "#ff0000");
    SOFXML.COLORS.put("yellow", "#ffff00");
    SOFXML.BOOLS.put("0", Boolean.FALSE);
    SOFXML.BOOLS.put("f", Boolean.FALSE);
    SOFXML.BOOLS.put("false", Boolean.FALSE);
    SOFXML.BOOLS.put("1", Boolean.TRUE);
    SOFXML.BOOLS.put("-1", Boolean.TRUE);
    SOFXML.BOOLS.put("t", Boolean.TRUE);
    SOFXML.BOOLS.put("true", Boolean.TRUE);
    for (final Layout x : Layout.values()) {
      SOFXML.addEnum(SOFXML.LAYOUTS, x);
    }
    for (final OrgMode x : OrgMode.values()) {
      SOFXML.addEnum(SOFXML.ORGMODES, x);
    }
    for (final LineMode x : LineMode.values()) {
      SOFXML.addEnum(SOFXML.LINEMODES, x);
    }
    for (final Alignment x : Alignment.values()) {
      SOFXML.addEnum(SOFXML.ALIGNMENTS, x);
    }
  }

  /**
   * Gets the single instance of SiteMapXML.
   * 
   * @return single instance of SiteMapXML
   */
  public static synchronized SOFXML getInstance() {
    if (SOFXML.instance == null) {
      SOFXML.instance = new SOFXML();
    }
    return SOFXML.instance;
  }

  /**
   * Instantiates a new XML utility.
   */
  private SOFXML() {
    super();
  }

  /**
   * Write two digits Hex.
   * 
   * @param out the out
   * @param val the val
   */
  public static void writeHH(final StringBuffer out, final int val) {
    if (val < 16) {
      out.append('0');
    }
    out.append(Integer.toString(val, 16));
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
    buf.append('#');
    SOFXML.writeHH(buf, col.getRed());
    SOFXML.writeHH(buf, col.getGreen());
    SOFXML.writeHH(buf, col.getBlue());
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
    int cRd;
    int cGr;
    int cBl;
    if (strColor == null) {
      cRd = 0;
      cGr = 0;
      cBl = 0;
    }
    else {
      strColor = strColor.trim().toLowerCase();
      if (SOFXML.COLORS.containsKey(strColor)) {
        strColor = SOFXML.COLORS.get(strColor);
      }
      if (strColor.charAt(0) == '#') {
        if (strColor.length() == 4) {
          final StringBuffer buf = new StringBuffer();
          buf.append('#').append(strColor.charAt(1)).append(strColor.charAt(1)).append(strColor.charAt(2)).append(strColor.charAt(2)).append(strColor.charAt(3)).append(strColor.charAt(3));
          strColor = buf.toString();
        }
        if (strColor.length() == 7) {
          cRd = Integer.parseInt(strColor.substring(1, 3), 16);
          cGr = Integer.parseInt(strColor.substring(3, 5), 16);
          cBl = Integer.parseInt(strColor.substring(5, 7), 16);
        }
        else {
          throw new ParseException(strColor + " has an invalid length.", 0);
        }
      }
      else {
        if (strColor.startsWith("rgb")) {
          int endIndex;
          if (strColor.endsWith(")")) {
            endIndex = strColor.length() - 1;
          }
          else {
            endIndex = strColor.length();
          }
          strColor = strColor.substring(4, endIndex);
        }
        final int indexOfFirstComma = strColor.indexOf(',');
        if (indexOfFirstComma == -1) { throw new ParseException("First comma not found.", 0); }
        final int indexOfSecondComma = strColor.indexOf(',', indexOfFirstComma + 1);
        if (indexOfSecondComma == -1) { throw new ParseException("Second comma not found.", indexOfFirstComma); }
        cRd = OrgUtils.val(strColor.substring(0, indexOfFirstComma), -1);
        cGr = OrgUtils.val(strColor.substring(indexOfFirstComma + 1, indexOfSecondComma), -1);
        cBl = OrgUtils.val(strColor.substring(indexOfSecondComma + 1), -1);
        if ((cRd == -1) || (cGr == -1) || (cBl == -1)) { throw new ParseException(strColor + " has an invalid definition.", 0); }
      }
    }
    return new Color(cRd, cGr, cBl);
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
        res = SOFXML.parseColor(val);
      }
      catch (final ParseException e) {
        Debug.error(e.toString());
      }
    }
    return res;
  }

  /**
   * Read integer.
   * 
   * @param val the value
   * @param def the default
   * @return the integer
   */
  public static int readInt(final String val, final int def) {
    int res = def;
    if (val != null) {
      try {
        res = Integer.parseInt(val);
      }
      catch (final NumberFormatException e) {
        Debug.error(e.toString());
      }
    }
    return res;
  }

  /**
   * Read layout tag, mapping in done using "layouts".
   * 
   * @param val the val
   * @param def the def
   * @return the layout
   */
  public static Layout readLayout(final String val, final Layout def) {
    Layout res = null;
    if (val != null) {
      res = SOFXML.LAYOUTS.get(val.trim().toLowerCase());
    }
    return (res == null) ? def : res;
  }

  /**
   * Read org mode.
   * 
   * @param val the val
   * @param def the def
   * @return the org mode
   */
  public static OrgMode readOrgMode(final String val, final OrgMode def) {
    OrgMode res = null;
    if (val != null) {
      res = SOFXML.ORGMODES.get(val.trim().toLowerCase());
    }
    return (res == null) ? def : res;
  }

  /**
   * Read line mode.
   * 
   * @param val the val
   * @param def the def
   * @return the line mode
   */
  public static LineMode readLineMode(final String val, final LineMode def) {
    LineMode res = null;
    if (val != null) {
      res = SOFXML.LINEMODES.get(val.trim().toLowerCase());
    }
    return (res == null) ? def : res;
  }

  /**
   * Read alignment.
   * 
   * @param val the value
   * @param def the default
   * 
   * @return the alignment (int)
   */
  public static Alignment readAligment(final String val, final Alignment def) {
    Alignment res = null;
    if (val != null) {
      res = SOFXML.ALIGNMENTS.get(val.trim().toLowerCase());
    }
    return (res == null) ? def : res;
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
      final Boolean bVal = SOFXML.BOOLS.get(val.trim().toLowerCase());
      if (bVal != null) {
        res = bVal;
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
        if (SOFXML.VAL_BOLD.equalsIgnoreCase(styleAtr)) {
          style |= Font.BOLD;
        }
        else if (SOFXML.VAL_ITALIC.equalsIgnoreCase(styleAtr)) {
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
      tag.writeAttribute(buf, atr, SOFXML.writeColor(val), false);
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
   * Write layout tag.
   * 
   * @param tag the tag
   * @param buf the buf
   * @param atr the atr
   * @param defVal the def val
   * @param val the val
   */
  @SuppressWarnings("unchecked")
  public static void writeEnum(final TAG tag, final StringBuffer buf, final String atr, final Enum defVal, final Enum val) {
    if (!val.equals(defVal)) {
      tag.writeAttribute(buf, atr, val.toString().toLowerCase(), false);
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
        styleBuf.append(SOFXML.VAL_ITALIC);
        first = false;
      }
      if ((style & Font.BOLD) == Font.BOLD) {
        if (!first) {
          styleBuf.append('+');
        }
        styleBuf.append(SOFXML.VAL_BOLD);
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
        tag.writeAttribute(buf, atr, SOFXML.VAL_TRUE, false);
      }
      else {
        tag.writeAttribute(buf, atr, SOFXML.VAL_FALSE, false);
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
    return SOFXML.BOXLAYOUTATTRS.contains(name);
  }

  /**
   * Read box layout atr.
   * 
   * @param name the name
   * @param value the value
   * @param boxLay the box lay
   */
  public static void readBoxLayoutAtr(final String name, final String value, final BoxLayout boxLay) {
    if (name.equals(SOFXML.ATR_BOX_PADDING_RIGHT)) {
      boxLay.setRightPadding(SOFXML.readInt(value, boxLay.getRightPadding()));
    }
    else if (name.equals(SOFXML.ATR_BOX_PADDING_LEFT)) {
      boxLay.setLeftPadding(SOFXML.readInt(value, boxLay.getLeftPadding()));
    }
    else if (name.equals(SOFXML.ATR_BOX_PADDING_TOP)) {
      boxLay.setTopPadding(SOFXML.readInt(value, boxLay.getTopPadding()));
    }
    else if (name.equals(SOFXML.ATR_BOX_PADDING_BOTTOM)) {
      boxLay.setBottomPadding(SOFXML.readInt(value, boxLay.getBottomPadding()));
    }
    else if (name.equals(SOFXML.ATR_BOX_COLOR_FRAME)) {
      boxLay.setFrameColor(SOFXML.readColor(value, boxLay.getFrameColor()));
    }
    else if (name.equals(SOFXML.ATR_BOX_COLOR_BACKGROUND)) {
      boxLay.setBackgroundColor(SOFXML.readColor(value, boxLay.getBackgroundColor()));
    }
    else if (name.equals(SOFXML.ATR_BOX_COLOR_FOREGROUND)) {
      boxLay.setForegroundColor(SOFXML.readColor(value, boxLay.getForegroundColor()));
    }
    else if (name.equals(SOFXML.ATR_BOX_TEXT_ALIGMENT)) {
      boxLay.setTextAlignment(SOFXML.readAligment(value, boxLay.getTextAlignment()));
    }
    else if (name.equals(SOFXML.ATR_BOX_EXPENDED)) {
      boxLay.setExpanded(SOFXML.readBoolean(value, boxLay.isExpanded()));
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
    SOFXML.writeColor(tag, buf, SOFXML.ATR_BOX_COLOR_FRAME, BoxLayout.DEF_BOXFRAMECOLOR, boxLay.getFrameColor());
    SOFXML.writeColor(tag, buf, SOFXML.ATR_BOX_COLOR_BACKGROUND, BoxLayout.DEF_BACKGROUNDCOLOR, boxLay.getBackgroundColor());
    SOFXML.writeColor(tag, buf, SOFXML.ATR_BOX_COLOR_FOREGROUND, BoxLayout.DEF_FOREGROUNDCOLOR, boxLay.getForegroundColor());
    SOFXML.writeInt(tag, buf, SOFXML.ATR_BOX_PADDING_TOP, BoxLayout.DEF_PADDINGTOP, boxLay.getTopPadding());
    SOFXML.writeInt(tag, buf, SOFXML.ATR_BOX_PADDING_LEFT, BoxLayout.DEF_PADDINGLEFT, boxLay.getLeftPadding());
    SOFXML.writeInt(tag, buf, SOFXML.ATR_BOX_PADDING_RIGHT, BoxLayout.DEF_PADDINGRIGHT, boxLay.getRightPadding());
    SOFXML.writeInt(tag, buf, SOFXML.ATR_BOX_PADDING_BOTTOM, BoxLayout.DEF_PADDINGBOTTOM, boxLay.getBottomPadding());
    SOFXML.writeEnum(tag, buf, SOFXML.ATR_BOX_TEXT_ALIGMENT, BoxLayout.DEF_TEXTALIGN, boxLay.getTextAlignment());
  }

}
