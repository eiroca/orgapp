/** LGPL > 3.0
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
package com.fbergeron.organigram.util;

import java.awt.Color;
import java.awt.Font;

/**
 * The Class FontInfo.
 */
public class FontInfo {

  /** The font. */
  public Font font;

  /** The color. */
  public Color color;

  /**
   * Instantiates a new font info.
   */
  public FontInfo() {
    font = new Font(null, Font.PLAIN, 12);
    color = new Color(0);
  }

  /**
   * Instantiates a new font info.
   * 
   * @param name the name
   * @param style the style
   * @param size the size
   * @param color the color
   */
  public FontInfo(final String name, final int style, final int size, final Color color) {
    font = new Font(name, style, size);
    this.color = color;
  }

  /**
   * Instantiates a new font info.
   * 
   * @param font the font
   * @param color the color
   */
  public FontInfo(final Font font, final Color color) {
    this.font = font;
    this.color = color;
  }

  /**
   * Gets the font.
   * 
   * @return the font
   */
  public Font getFont() {
    return font;
  }

  /**
   * Sets the font.
   * 
   * @param font the font to set
   */
  public void setFont(final Font font) {
    this.font = font;
  }

  /**
   * Gets the color.
   * 
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Sets the color.
   * 
   * @param color the color to set
   */
  public void setColor(final Color color) {
    this.color = color;
  }

}
