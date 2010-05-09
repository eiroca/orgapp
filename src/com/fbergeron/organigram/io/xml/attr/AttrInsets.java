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

import java.awt.Insets;
import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.xml.AttrGroup;

/**
 * The Class AttrFont.
 */
public class AttrInsets extends AttrGroup<Insets> {

  /** The Constant ATR_FONT_NAME. */
  public static final String ATR_TOP = "Top";
  /** The Constant ATR_FONT_SIZE. */
  public static final String ATR_LEFT = "Left";
  /** The Constant ATR_FONT_STYLE. */
  public static final String ATR_BOTTOM = "Bottom";
  /** The Constant ATR_FONT_STYLE. */
  public static final String ATR_RIGHT = "Right";

  /** The a top. */
  public transient AttrInt aTop = new AttrInt(AttrInsets.ATR_TOP, null);

  /** The a left. */
  public transient AttrInt aLeft = new AttrInt(AttrInsets.ATR_LEFT, null);

  /** The a bottom. */
  public transient AttrInt aBottom = new AttrInt(AttrInsets.ATR_BOTTOM, null);

  /** The a right. */
  public transient AttrInt aRight = new AttrInt(AttrInsets.ATR_RIGHT, null);

  /**
   * Instantiates a new tag attr font.
   * 
   * @param prefix the prefix
   */
  public AttrInsets(final String prefix) {
    this(prefix, null);
  }

  /**
   * Instantiates a new tag attr font.
   * 
   * @param prefix the prefix
   * @param def the def
   */
  public AttrInsets(final String prefix, final Insets def) {
    super(prefix);
    setDef(def);
    aTop.setDef((def == null ? null : def.top));
    aLeft.setDef((def == null ? null : def.left));
    aRight.setDef((def == null ? null : def.right));
    aBottom.setDef((def == null ? null : def.bottom));
    addAttribute(aTop);
    addAttribute(aLeft);
    addAttribute(aBottom);
    addAttribute(aRight);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.AttrGroup#read(org.xml.sax.Attributes)
   */
  @Override
  public Insets read(final Attributes attribs) {
    super.read(attribs);
    final Integer top = aTop.getLastVal();
    final Integer left = aLeft.getLastVal();
    final Integer right = aRight.getLastVal();
    final Integer bottom = aBottom.getLastVal();
    if ((top == null) || (left == null) || (right == null) || (bottom == null)) {
      setLastVal(null);
    }
    else {
      setLastVal(new Insets(top, left, bottom, right));
    }
    return lastVal;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.xml.AttrGroup#write(java.lang.StringBuffer)
   */
  @Override
  public void write(final StringBuffer buf) {
    final Insets val = getLastVal();
    if (val == null) {
      aTop.setLastVal(null);
      aLeft.setLastVal(null);
      aBottom.setLastVal(null);
      aRight.setLastVal(null);
    }
    else {
      aTop.setLastVal(val.top);
      aLeft.setLastVal(val.left);
      aBottom.setLastVal(val.bottom);
      aRight.setLastVal(val.right);
    }
    super.write(buf);
  }
}
