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
package com.fbergeron.organigram.model;

import java.awt.Color;
import java.awt.Font;

public class Line {

  public static final Font LINE_FONT = new Font(null, Font.PLAIN, 12);

  private boolean visible = true;
  private String type;
  private String text;
  private String link;
  private Color color = null;
  private Font font = Line.LINE_FONT;

  public Line() {
    //
  }

  public Line(final String text, final int fontStyle, final int fontSize) {
    this.text = text;
    font = new Font(null, fontStyle, fontSize);
  }

  public Line(final String text, final String link, final Font font, final Color color) {
    this.text = text;
    this.link = link;
    this.color = color;
    this.font = font;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public String getLink() {
    return link;
  }

  public void setLink(final String link) {
    this.link = link;
  }

  public Font getFont() {
    return font;
  }

  public void setFont(final Font font) {
    this.font = font;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(final Color color) {
    this.color = color;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
    if (type != null) {
      visible = false;
    }
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(final boolean visible) {
    this.visible = visible;
  }

}
