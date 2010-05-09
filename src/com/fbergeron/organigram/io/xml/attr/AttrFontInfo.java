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
import java.awt.Font;
import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.xml.AttrGroup;
import com.fbergeron.organigram.util.FontInfo;

/**
 * The Class AttrFont.
 */
public class AttrFontInfo extends AttrGroup<FontInfo> {

  /** The Constant ATR_FONT_NAME. */
  public static final String ATR_FONT_NAME = "Name";
  /** The Constant ATR_FONT_SIZE. */
  public static final String ATR_FONT_SIZE = "Size";
  /** The Constant ATR_FONT_STYLE. */
  public static final String ATR_FONT_STYLE = "Style";
  /** The Constant ATR_FONT_STYLE. */
  public static final String ATR_FONT_COLOR = "Color";

  /** The a font name. */
  public transient AttrStr aFontName = new AttrStr(AttrFontInfo.ATR_FONT_NAME, null);

  /** The a font size. */
  public transient AttrInt aFontSize = new AttrInt(AttrFontInfo.ATR_FONT_SIZE, 12);

  /** The a font style. */
  public transient AttrStyle aFontStyle = new AttrStyle(AttrFontInfo.ATR_FONT_STYLE, Font.PLAIN);

  /** The a font color. */
  public transient AttrColor aFontColor = new AttrColor(AttrFontInfo.ATR_FONT_COLOR, null);

  /**
   * Instantiates a new tag attr font.
   * 
   * @param prefix the prefix
   */
  public AttrFontInfo(final String prefix) {
    this(prefix, null);
  }

  /**
   * Instantiates a new tag attr font.
   * 
   * @param prefix the prefix
   * @param def the def
   */
  public AttrFontInfo(final String prefix, final FontInfo def) {
    super(prefix);
    setDef(def);
    addAttribute(aFontName);
    addAttribute(aFontSize);
    addAttribute(aFontStyle);
    addAttribute(aFontColor);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.AttrGroup#read(org.xml.sax.Attributes)
   */
  @Override
  public FontInfo read(final Attributes attribs) {
    super.read(attribs);
    final String fontName = aFontName.getLastVal();
    final Integer fontSize = aFontSize.getLastVal();
    final Integer fontStyle = aFontStyle.getLastVal();
    final Color fontColor = aFontColor.getLastVal();
    if ((fontName == null) && (fontSize == null) && (fontStyle == null) && (fontColor == null)) {
      setLastVal(null);
    }
    else {
      setLastVal(new FontInfo(aFontName.getLastVal(), aFontStyle.getLastVal(), aFontSize.getLastVal(), aFontColor.getLastVal()));
    }
    return lastVal;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.AttrGroup#write(java.lang.StringBuffer)
   */
  @Override
  public void write(final StringBuffer buf) {
    final FontInfo fontInfo = getLastVal();
    final String fontName = fontInfo.font.getName();
    if ("Default".equals(fontName)) {
      aFontName.setLastVal(null);
    }
    else {
      aFontName.setLastVal(fontName);
    }
    aFontSize.setLastVal(fontInfo.font.getSize());
    aFontStyle.setLastVal(fontInfo.font.getStyle());
    aFontColor.setLastVal(fontInfo.color);
    super.write(buf);
  }
}
