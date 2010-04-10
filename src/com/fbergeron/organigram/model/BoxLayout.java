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
package com.fbergeron.organigram.model;

import java.awt.Color;
import java.awt.Label;

/**
 * The Layout definition of a Box.
 */
public class BoxLayout implements Cloneable {

  /** The Constant COLOR_BOXFRAME */
  public static final Color COLOR_BOXFRAME = Color.black;

  /** The Constant COLOR_BOXBACKGROUND */
  public static final Color COLOR_BACKGROUND = Color.white;

  /** The Constant COLOR_BOXFOREGROUND */
  public static final Color COLOR_FOREGROUND = Color.black;

  /** The Constant PADDING_BOXRIGHT */
  public static final int PADDING_RIGHT = 4;

  /** The Constant PADDING_BOXLEFT */
  public static final int PADDING_LEFT = 4;

  /** The Constant PADDING_BOXTOP */
  public static final int PADDING_TOP = 4;

  /** The Constant PADDING_BOXBOTTOM */
  public static final int PADDING_BOTTOM = 4;

  /** The Constant ALIGN_BOXTEXT */
  public static final int TEXT_ALIGN = Label.CENTER;

  /** The box frame color */
  private Color frameColor = BoxLayout.COLOR_BOXFRAME;

  /** The box background color */
  private Color backgroundColor = BoxLayout.COLOR_BACKGROUND;

  /** The box foreground color */
  private Color foregroundColor = BoxLayout.COLOR_FOREGROUND;

  /** The box right padding */
  private int rightPadding = BoxLayout.PADDING_RIGHT;

  /** The box left padding */
  private int leftPadding = BoxLayout.PADDING_LEFT;

  /** The box top padding */
  private int topPadding = BoxLayout.PADDING_TOP;

  /** The box bottom padding */
  private int bottomPadding = BoxLayout.PADDING_BOTTOM;

  /** The box text alignment */
  private int textAlignment = BoxLayout.TEXT_ALIGN;

  /** The children of the box are displayed */
  private boolean expanded = true;

  public BoxLayout() {
  }

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
  public int getTextAlignment() {
    return textAlignment;
  }

  /**
   * Sets the box text alignment.
   * 
   * @param textAlignment the new box text alignment
   */
  public void setTextAlignment(final int textAlignment) {
    this.textAlignment = textAlignment;
  }

  /**
   * Get the expanded flag
   * 
   * @return the expanded flag
   */
  public boolean isExpanded() {
    return expanded;
  }

  /**
   * Sets the box expanded flag
   * @param expanded the new expanded flag
   */
  public void setExpanded(final boolean expanded) {
    this.expanded = expanded;
  }

}
