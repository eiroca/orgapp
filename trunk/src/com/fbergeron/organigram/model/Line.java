/**
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
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
 * 
 */
package com.fbergeron.organigram.model;

import java.awt.Color;
import java.awt.Font;

/**
 * The Line is a raw of information in a organigram's box.
 */
public class Line {

  /** The Constant LINE_FONT. */
  public static final Font LINE_FONT = new Font(null, Font.PLAIN, 12);

  /** The visible. */
  private boolean visible = true;

  /** The type. */
  private String type;

  /** The text. */
  private String text;

  /** The link. */
  private String link;

  /** The color. */
  private Color color = null;

  /** The font. */
  private Font font = Line.LINE_FONT;

  /**
   * Instantiates a new line.
   */
  public Line() {
    //
  }

  /**
   * Instantiates a new line.
   * 
   * @param text the text
   * @param fontStyle the font style
   * @param fontSize the font size
   */
  public Line(final String text, final int fontStyle, final int fontSize) {
    this.text = text;
    font = new Font(null, fontStyle, fontSize);
  }

  /**
   * Instantiates a new line.
   * 
   * @param text the text
   * @param link the link
   * @param font the font
   * @param color the color
   */
  public Line(final String text, final String link, final Font font, final Color color) {
    this.text = text;
    this.link = link;
    this.color = color;
    this.font = font;
  }

  /**
   * Gets the text.
   * 
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text.
   * 
   * @param text the new text
   */
  public void setText(final String text) {
    this.text = text;
  }

  /**
   * Gets the link.
   * 
   * @return the link
   */
  public String getLink() {
    return link;
  }

  /**
   * Sets the link.
   * 
   * @param link the new link
   */
  public void setLink(final String link) {
    this.link = link;
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
   * @param font the new font
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
   * @param color the new color
   */
  public void setColor(final Color color) {
    this.color = color;
  }

  /**
   * Gets the type.
   * 
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   * 
   * @param type the new type
   */
  public void setType(final String type) {
    this.type = type;
    if (type != null) {
      visible = false;
    }
  }

  /**
   * Checks if is visible.
   * 
   * @return true, if is visible
   */
  public boolean isVisible() {
    return visible;
  }

  /**
   * Sets the visible.
   * 
   * @param visible the new visible
   */
  public void setVisible(final boolean visible) {
    this.visible = visible;
  }

}
