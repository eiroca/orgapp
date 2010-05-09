/**
 * LGPL > 3.0 Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
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
package com.fbergeron.organigram.model;

import java.awt.Color;
import java.awt.Insets;
import com.fbergeron.organigram.model.type.Alignment;
import com.fbergeron.organigram.model.type.BoxType;

/**
 * The Layout definition of a Box.
 */
public class BoxLayout implements Cloneable {

  public static final Color DEF_BACKGROUND = Color.white;
  public static final Color DEF_BOXFRAME = Color.black;
  public static final BoxType DEF_BOXTYPE = BoxType.NORMAL;
  public static final Boolean DEF_EXPANDED = Boolean.TRUE;
  public static final Color DEF_FOREGROUND = Color.black;
  public static final Insets DEF_PADDING = new Insets(4, 4, 4, 4);
  public static final Alignment DEF_TEXTALIGN = Alignment.CENTER;

  private BoxLayout parent = null;

  private BoxType type = null;
  private Boolean expanded = null;
  private Alignment textAlignment = null;
  private Color backgroundColor = null;
  private Color foregroundColor = null;
  private Color frameColor = null;
  private Insets padding = null;

  /**
   * Instantiates a new box layout.
   */
  public BoxLayout() {
    type = BoxLayout.DEF_BOXTYPE;
    expanded = BoxLayout.DEF_EXPANDED;
    textAlignment = BoxLayout.DEF_TEXTALIGN;
    backgroundColor = BoxLayout.DEF_BACKGROUND;
    foregroundColor = BoxLayout.DEF_FOREGROUND;
    frameColor = BoxLayout.DEF_BOXFRAME;
    padding = new Insets(BoxLayout.DEF_PADDING.top, BoxLayout.DEF_PADDING.left, BoxLayout.DEF_PADDING.bottom, BoxLayout.DEF_PADDING.right);
  }

  public BoxLayout(final BoxLayout parent) {
    this.parent = parent;
  }

  /**
   * Instantiates a new box layout.
   * 
   * @param source the source
   * 
   *          public BoxLayout(final BoxLayout source, int y) { frameColor = source.frameColor; backgroundColor = source.backgroundColor; foregroundColor = source.foregroundColor; padding = (Insets)
   *          source.padding.clone(); textAlignment = source.textAlignment; expanded = source.expanded; type = source.type; }
   */

  /**
   * Gets the box background color.
   * 
   * @return the box background color
   */
  public Color getBackgroundColor(final boolean inherit) {
    Color res = backgroundColor;
    if ((res == null) && inherit) {
      res = (parent == null ? null : parent.getBackgroundColor(inherit));
    }
    return res;
  }

  /**
   * Gets the box foreground color.
   * 
   * @return the box foreground color
   */
  public Color getForegroundColor(final boolean inherit) {
    Color res = foregroundColor;
    if ((res == null) && inherit) {
      res = (parent == null ? null : parent.getForegroundColor(inherit));
    }
    return res;
  }

  /**
   * Gets the box frame color.
   * 
   * @return the box frame color
   */
  public Color getFrameColor(final boolean inherit) {
    Color res = frameColor;
    if ((res == null) && inherit) {
      res = (parent == null ? null : parent.getFrameColor(inherit));
    }
    return res;
  }

  /**
   * Gets the box top padding.
   * 
   * @return the box top padding
   */
  public Insets getPadding(final boolean inherit) {
    Insets res = padding;
    if ((res == null) && inherit) {
      res = (parent == null ? null : parent.getPadding(inherit));
    }
    return res;
  }

  /**
   * Gets the box text alignment.
   * 
   * @return the box text alignment
   */
  public Alignment getTextAlignment(final boolean inherit) {
    Alignment res = textAlignment;
    if ((res == null) && inherit) {
      res = (parent == null ? null : parent.getTextAlignment(inherit));
    }
    return res;
  }

  /**
   * Returns the box type.
   * @return returns the box type
   */
  public BoxType getType(final boolean inherit) {
    BoxType res = type;
    if ((res == null) && inherit) {
      res = (parent == null ? null : parent.getType(inherit));
    }
    return res;
  }

  /**
   * Get the expanded flag.
   * 
   * @return the expanded flag
   */
  public Boolean isExpanded(final boolean inherit) {
    Boolean res = expanded;
    if ((res == null) && inherit) {
      res = (parent == null ? null : parent.isExpanded(inherit));
    }
    return res;
  }

  /**
   * Sets the box background color.
   * 
   * @param backgroundColor the new box background color
   */
  public void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /**
   * Sets the box expanded flag.
   * 
   * @param expanded the new expanded flag
   */
  public void setExpanded(final Boolean expanded) {
    this.expanded = expanded;
  }

  /**
   * Sets the box foreground color.
   * 
   * @param foregroundColor the new box foreground color
   */
  public void setForegroundColor(final Color foregroundColor) {
    this.foregroundColor = foregroundColor;
  }

  /**
   * Sets the box frame color.
   * 
   * @param frameColor the new box frame color
   */
  public void setFrameColor(final Color frameColor) {
    this.frameColor = frameColor;
  }

  /**
   * Sets the box top padding.
   * 
   * @param topPadding the new box top padding
   */
  public void setPadding(final Insets padding) {
    this.padding = padding;
  }

  /**
   * Sets the box text alignment.
   * 
   * @param textAlignment the new box text alignment
   */
  public void setTextAlignment(final Alignment textAlignment) {
    this.textAlignment = textAlignment;
  }

  /**
   * Sets the box type.
   * 
   * @param type the new type
   */
  public void setType(final BoxType type) {
    this.type = type;
  }

  /**
   * @return the parent
   */
  public BoxLayout getParent() {
    return parent;
  }

  /**
   * @param parent the parent to set
   */
  public void setParent(final BoxLayout parent) {
    this.parent = parent;
  }

}
