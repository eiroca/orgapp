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
import com.fbergeron.organigram.model.type.Alignment;
import com.fbergeron.organigram.model.type.BoxType;

/**
 * The Layout definition of a Box.
 */
public class BoxLayout implements Cloneable {

  /** The default frame color. */
  public static final Color DEF_BOXFRAMECOLOR = Color.black;

  /** The default background color. */
  public static final Color DEF_BACKGROUNDCOLOR = Color.white;

  /** The default foreground color. */
  public static final Color DEF_FOREGROUNDCOLOR = Color.black;

  /** The default right padding. */
  public static final int DEF_PADDINGRIGHT = 4;

  /** The default left padding. */
  public static final int DEF_PADDINGLEFT = 4;

  /** The default top padding. */
  public static final int DEF_PADDINGTOP = 4;

  /** The default bottom padding. */
  public static final int DEF_PADDINGBOTTOM = 4;

  /** The default text alignment. */
  public static final Alignment DEF_TEXTALIGN = Alignment.CENTER;

  /** The default box type. */
  public static final BoxType DEF_BOXTYPE = BoxType.NORMAL;

  /** The box frame color. */
  private Color frameColor = BoxLayout.DEF_BOXFRAMECOLOR;

  /** The box background color. */
  private Color backgroundColor = BoxLayout.DEF_BACKGROUNDCOLOR;

  /** The box foreground color. */
  private Color foregroundColor = BoxLayout.DEF_FOREGROUNDCOLOR;

  /** The box right padding. */
  private int rightPadding = BoxLayout.DEF_PADDINGRIGHT;

  /** The box left padding. */
  private int leftPadding = BoxLayout.DEF_PADDINGLEFT;

  /** The box top padding. */
  private int topPadding = BoxLayout.DEF_PADDINGTOP;

  /** The box bottom padding. */
  private int bottomPadding = BoxLayout.DEF_PADDINGBOTTOM;

  /** The box text alignment. */
  private Alignment textAlignment = BoxLayout.DEF_TEXTALIGN;

  /** The box type. */
  private BoxType type = BoxLayout.DEF_BOXTYPE;

  /** The children of the box are displayed. */
  private boolean expanded = true;

  /**
   * Instantiates a new box layout.
   */
  public BoxLayout() {
  }

  /**
   * Instantiates a new box layout.
   * 
   * @param source the source
   */
  public BoxLayout(final BoxLayout source) {
    frameColor = source.frameColor;
    backgroundColor = source.backgroundColor;
    foregroundColor = source.foregroundColor;
    rightPadding = source.rightPadding;
    leftPadding = source.leftPadding;
    topPadding = source.topPadding;
    bottomPadding = source.bottomPadding;
    textAlignment = source.textAlignment;
    expanded = source.expanded;
    type = source.type;
  }

  /**
   * Gets the box background color.
   * 
   * @return the box background color
   */
  public Color getBackgroundColor() {
    return backgroundColor;
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
   * Gets the box foreground color.
   * 
   * @return the box foreground color
   */
  public Color getForegroundColor() {
    return foregroundColor;
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
   * Gets the box frame color.
   * 
   * @return the box frame color
   */
  public Color getFrameColor() {
    return frameColor;
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
   * Gets the box top padding.
   * 
   * @return the box top padding
   */
  public int getTopPadding() {
    return topPadding;
  }

  /**
   * Sets the box top padding.
   * 
   * @param topPadding the new box top padding
   */
  public void setTopPadding(final int topPadding) {
    this.topPadding = topPadding;
  }

  /**
   * Gets the box bottom padding.
   * 
   * @return the box bottom padding
   */
  public int getBottomPadding() {
    return bottomPadding;
  }

  /**
   * Sets the box bottom padding.
   * 
   * @param bottomPadding the new box bottom padding
   */
  public void setBottomPadding(final int bottomPadding) {
    this.bottomPadding = bottomPadding;
  }

  /**
   * Gets the box left padding.
   * 
   * @return the box left padding
   */
  public int getLeftPadding() {
    return leftPadding;
  }

  /**
   * Sets the box left padding.
   * 
   * @param leftPadding the new box left padding
   */
  public void setLeftPadding(final int leftPadding) {
    this.leftPadding = leftPadding;
  }

  /**
   * Gets the box right padding.
   * 
   * @return the box right padding
   */
  public int getRightPadding() {
    return rightPadding;
  }

  /**
   * Sets the box right padding.
   * 
   * @param rightPadding the new box right padding
   */
  public void setRightPadding(final int rightPadding) {
    this.rightPadding = rightPadding;
  }

  /**
   * Gets the box text alignment.
   * 
   * @return the box text alignment
   */
  public Alignment getTextAlignment() {
    return textAlignment;
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
   * Get the expanded flag.
   * 
   * @return the expanded flag
   */
  public boolean isExpanded() {
    return expanded;
  }

  /**
   * Sets the box expanded flag.
   * 
   * @param expanded the new expanded flag
   */
  public void setExpanded(final boolean expanded) {
    this.expanded = expanded;
  }

  /**
   * Returns the box type.
   * @return
   */
  public BoxType getType() {
    return type;
  }

  /**
   * Sets the box type.
   * 
   * @param type
   */
  public void setType(BoxType type) {
    this.type = type;
  }

}
