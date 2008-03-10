/**
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
import java.awt.Label;

public class BoxLayout {

  public static final Color COLOR_BOXFRAME = Color.black;
  public static final Color COLOR_BOXBACKGROUND = Color.white;
  public static final Color COLOR_BOXFOREGROUND = Color.black;
  public static final int PADDING_BOXRIGHT = 4;
  public static final int PADDING_BOXLEFT = 4;
  public static final int PADDING_BOXTOP = 4;
  public static final int PADDING_BOXBOTTOM = 4;
  public static final int ALIGN_BOXTEXT = Label.CENTER;

  private Color boxFrameColor = COLOR_BOXFRAME;
  private Color boxBackgroundColor = COLOR_BOXBACKGROUND;
  private Color boxForegroundColor = COLOR_BOXFOREGROUND;
  private int boxRightPadding = PADDING_BOXRIGHT;
  private int boxLeftPadding = PADDING_BOXLEFT;
  private int boxTopPadding = PADDING_BOXTOP;
  private int boxBottomPadding = PADDING_BOXBOTTOM;
  private int boxTextAlignment = ALIGN_BOXTEXT;

  public BoxLayout() {
    super();
  }

  public Color getBoxBackgroundColor() {
    return boxBackgroundColor;
  }

  public void setBoxBackgroundColor(Color boxBackgroundColor) {
    this.boxBackgroundColor = boxBackgroundColor;
  }

  public Color getBoxForegroundColor() {
    return boxForegroundColor;
  }

  public void setBoxForegroundColor(Color boxForegroundColor) {
    this.boxForegroundColor = boxForegroundColor;
  }

  public Color getBoxFrameColor() {
    return boxFrameColor;
  }

  public void setBoxFrameColor(Color boxFrameColor) {
    this.boxFrameColor = boxFrameColor;
  }

  public int getBoxTopPadding() {
    return boxTopPadding;
  }

  public void setBoxTopPadding(int boxTopPadding) {
    this.boxTopPadding = boxTopPadding;
  }

  public int getBoxBottomPadding() {
    return boxBottomPadding;
  }

  public void setBoxBottomPadding(int boxBottomPadding) {
    this.boxBottomPadding = boxBottomPadding;
  }

  public int getBoxLeftPadding() {
    return boxLeftPadding;
  }

  public void setBoxLeftPadding(int boxLeftPadding) {
    this.boxLeftPadding = boxLeftPadding;
  }

  public int getBoxRightPadding() {
    return boxRightPadding;
  }

  public void setBoxRightPadding(int boxRightPadding) {
    this.boxRightPadding = boxRightPadding;
  }

  public int getBoxTextAlignment() {
    return boxTextAlignment;
  }

  public void setBoxTextAlignment(int boxTextAlignment) {
    this.boxTextAlignment = boxTextAlignment;
  }

}
