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
package com.fbergeron.organigram.model;

import java.awt.Color;

/**
 * The Class OrganigramLayout.
 */
public class OrganigramLayout {

  /** The Constant ORGMODE_MAX. */
  public static final int ORGMODE_MAX = 0;

  /** The Constant ORGMODE_MIN. */
  public static final int ORGMODE_MIN = 1;

  /** The Constant ORGMODE_GROW. */
  public static final int ORGMODE_GROW = 2;

  /** The Constant ORGLAYOUT_HORIZ. */
  public static final int ORGLAYOUT_HORIZ = 0;

  /** The Constant ORGLAYOUT_VERT. */
  public static final int ORGLAYOUT_VERT = 1;

  /** The Constant ORGCOMPACT_NO. */
  public static final int ORGCOMPACT_NO = 0;

  /** The Constant ORGCOMPACT_YES. */
  public static final int ORGCOMPACT_YES = 1;

  /** The Constant ORGMODE. */
  public static final int ORGMODE = OrganigramLayout.ORGMODE_MAX;

  /** The Constant LAYOUT. */
  public static final int LAYOUT = OrganigramLayout.ORGLAYOUT_HORIZ;

  /** The Constant COMPACT. */
  public static final int COMPACT = OrganigramLayout.ORGCOMPACT_NO;

  /** The Constant COLOR_BACKGROUND. */
  public static final Color COLOR_BACKGROUND = Color.white;

  /** The Constant COLOR_LINE. */
  public static final Color COLOR_LINE = Color.black;

  /** The Constant MARGIN_BOXRIGHT. */
  public static final int MARGIN_RIGHT = 6;

  /** The Constant MARGIN_BOXLEFT. */
  public static final int MARGIN_LEFT = 6;

  /** The Constant MARGIN_BOXTOP. */
  public static final int MARGIN_TOP = 6;

  /** The Constant MARGIN_BOXBOTTOM. */
  public static final int MARGIN_BOTTOM = 6;

  /** The Constant ISTOOLTIPENABLED. */
  public static final boolean TOOLTIP_ENABLED = true;

  /** The org mode. */
  private int orgMode = OrganigramLayout.ORGMODE;

  /** The org layout. */
  private int orgLayout = OrganigramLayout.LAYOUT;

  /** The org compact. */
  private int orgCompact = OrganigramLayout.COMPACT;

  /** The background color. */
  private Color backgroundColor = OrganigramLayout.COLOR_BACKGROUND;

  /** The line color. */
  private Color lineColor = OrganigramLayout.COLOR_LINE;

  /** The box right margin. */
  private int rightMargin = OrganigramLayout.MARGIN_RIGHT;

  /** The box left margin. */
  private int leftMargin = OrganigramLayout.MARGIN_LEFT;

  /** The box top margin. */
  private int topMargin = OrganigramLayout.MARGIN_TOP;

  /** The box bottom margin. */
  private int bottomMargin = OrganigramLayout.MARGIN_BOTTOM;

  /** The is tool tip enabled. */
  private boolean toolTipEnabled = OrganigramLayout.TOOLTIP_ENABLED;

  /**
   * Gets the background color.
   * 
   * @return the background color
   */
  public Color getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Sets the background color.
   * 
   * @param backgroundColor the new background color
   */
  public void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /**
   * Gets the org compact.
   * 
   * @return the org compact
   */
  public int getOrgCompact() {
    return orgCompact;
  }

  /**
   * Sets the org compact.
   * 
   * @param orgCompact the new org compact
   */
  public void setOrgCompact(final int orgCompact) {
    this.orgCompact = orgCompact;
  }

  /**
   * Gets the org layout.
   * 
   * @return the org layout
   */
  public int getOrgLayout() {
    return orgLayout;
  }

  /**
   * Sets the org layout.
   * 
   * @param orgLayout the new org layout
   */
  public void setOrgLayout(final int orgLayout) {
    this.orgLayout = orgLayout;
  }

  /**
   * Gets the org mode.
   * 
   * @return the org mode
   */
  public int getOrgMode() {
    return orgMode;
  }

  /**
   * Sets the org mode.
   * 
   * @param orgMode the new org mode
   */
  public void setOrgMode(final int orgMode) {
    this.orgMode = orgMode;
  }

  /**
   * Gets the box right margin.
   * 
   * @return the box right margin
   */
  public int getRightMargin() {
    return rightMargin;
  }

  /**
   * Sets the box right margin.
   * 
   * @param rightMargin the new box right margin
   */
  public void setRightMargin(final int rightMargin) {
    this.rightMargin = rightMargin;
  }

  /**
   * Gets the box top margin.
   * 
   * @return the box top margin
   */
  public int getTopMargin() {
    return topMargin;
  }

  /**
   * Sets the box top margin.
   * 
   * @param topMargin the new box top margin
   */
  public void setTopMargin(final int topMargin) {
    this.topMargin = topMargin;
  }

  /**
   * Gets the box bottom margin.
   * 
   * @return the box bottom margin
   */
  public int getBottomMargin() {
    return bottomMargin;
  }

  /**
   * Sets the box bottom margin.
   * 
   * @param bottomMargin the new box bottom margin
   */
  public void setBottomMargin(final int bottomMargin) {
    this.bottomMargin = bottomMargin;
  }

  /**
   * Gets the box left margin.
   * 
   * @return the box left margin
   */
  public int getLeftMargin() {
    return leftMargin;
  }

  /**
   * Sets the box left margin.
   * 
   * @param leftMargin the new box left margin
   */
  public void setLeftMargin(final int leftMargin) {
    this.leftMargin = leftMargin;
  }

  /**
   * Checks if is tool tip enabled.
   * 
   * @return true, if is tool tip enabled
   */
  public boolean isToolTipEnabled() {
    return toolTipEnabled;
  }

  /**
   * Sets the tool tip enabled.
   * 
   * @param toolTipEnabled the new tool tip enabled
   */
  public void setToolTipEnabled(final boolean toolTipEnabled) {
    this.toolTipEnabled = toolTipEnabled;
  }

  /**
   * Gets the line color.
   * 
   * @return the line color
   */
  public Color getLineColor() {
    return lineColor;
  }

  /**
   * Sets the line color.
   * 
   * @param lineColor the new line color
   */
  public void setLineColor(final Color lineColor) {
    this.lineColor = lineColor;
  }

}
