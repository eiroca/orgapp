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

public class OrganigramLayout {

  public static final int ORGMODE_MAX = 0;
  public static final int ORGMODE_MIN = 1;
  public static final int ORGMODE_GROW = 2;
  public static final int ORGLAYOUT_HORIZ = 0;
  public static final int ORGLAYOUT_VERT = 1;
  public static final int ORGCOMPACT_NO = 0;
  public static final int ORGCOMPACT_YES = 1;

  public static final int ORGMODE = ORGMODE_MAX;
  public static final int LAYOUT = ORGLAYOUT_HORIZ;
  public static final int COMPACT = ORGCOMPACT_NO;

  public static final Color COLOR_BACKGROUND = Color.white;
  public static final Color COLOR_LINE = Color.black;
  public static final int MARGIN_BOXRIGHT = 6;
  public static final int MARGIN_BOXLEFT = 6;
  public static final int MARGIN_BOXTOP = 6;
  public static final int MARGIN_BOXBOTTOM = 6;
  public static final boolean ISTOOLTIPENABLED = true;

  private int orgMode = ORGMODE;
  private int orgLayout = LAYOUT;
  private int orgCompact = COMPACT;

  private Color backgroundColor = COLOR_BACKGROUND;
  private Color lineColor = COLOR_LINE;
  private int boxRightMargin = MARGIN_BOXRIGHT;
  private int boxLeftMargin = MARGIN_BOXLEFT;
  private int boxTopMargin = MARGIN_BOXTOP;
  private int boxBottomMargin = MARGIN_BOXBOTTOM;
  private boolean isToolTipEnabled = ISTOOLTIPENABLED;

  public OrganigramLayout() {
    super();
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public int getBoxBottomMargin() {
    return boxBottomMargin;
  }

  public void setBoxBottomMargin(int boxBottomMargin) {
    this.boxBottomMargin = boxBottomMargin;
  }

  public int getBoxLeftMargin() {
    return boxLeftMargin;
  }

  public void setBoxLeftMargin(int boxLeftMargin) {
    this.boxLeftMargin = boxLeftMargin;
  }

  public int getOrgCompact() {
    return orgCompact;
  }

  public void setOrgCompact(int orgCompact) {
    this.orgCompact = orgCompact;
  }

  public int getOrgLayout() {
    return orgLayout;
  }

  public void setOrgLayout(int orgLayout) {
    this.orgLayout = orgLayout;
  }

  public int getOrgMode() {
    return orgMode;
  }

  public void setOrgMode(int orgMode) {
    this.orgMode = orgMode;
  }

  public int getBoxRightMargin() {
    return boxRightMargin;
  }

  public void setBoxRightMargin(int boxRightMargin) {
    this.boxRightMargin = boxRightMargin;
  }

  public int getBoxTopMargin() {
    return boxTopMargin;
  }

  public void setBoxTopMargin(int boxTopMargin) {
    this.boxTopMargin = boxTopMargin;
  }

  public boolean isToolTipEnabled() {
    return isToolTipEnabled;
  }

  public void setToolTipEnabled(boolean isToolTipEnabled) {
    this.isToolTipEnabled = isToolTipEnabled;
  }

  public Color getLineColor() {
    return lineColor;
  }

  public void setLineColor(Color lineColor) {
    this.lineColor = lineColor;
  }

}
