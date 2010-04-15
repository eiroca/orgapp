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
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.model.type.LineMode;
import com.fbergeron.organigram.model.type.OrgMode;

/**
 * The Class OrganigramLayout.
 */
public class OrganigramLayout {

  /** The Constant ORGMODE. */
  public static final OrgMode DEF_ORGMODE = OrgMode.MAX;

  /** The Constant LAYOUT. */
  public static final Layout DEF_LAYOUT = Layout.TOP;

  /** The Constant COMPACT. */
  public static final boolean DEF_COMPACT = Boolean.TRUE;

  /** The Constant COLOR_BACKGROUND. */
  public static final Color COLOR_BACKGROUND = Color.white;

  /** The Constant COLOR_LINE. */
  public static final Color DEF_LINECOLOR = Color.black;

  public static final LineMode DEF_LINEMODE = LineMode.CONNECTOR;

  /** The Constant MARGIN_BOXRIGHT. */
  public static final int DEF_MARGINRIGHT = 6;

  /** The Constant MARGIN_BOXLEFT. */
  public static final int DEF_MARGINLEFT = 6;

  /** The Constant MARGIN_BOXTOP. */
  public static final int DEF_MARGINTOP = 6;

  /** The Constant MARGIN_BOXBOTTOM. */
  public static final int DEF_MARGINBOTTOM = 6;

  /** The Constant ISTOOLTIPENABLED. */
  public static final boolean DEF_TOOLTIP = true;

  /** The mode. */
  private OrgMode mode = OrganigramLayout.DEF_ORGMODE;

  /** The layout. */
  private Layout layout = OrganigramLayout.DEF_LAYOUT;

  /** The compact. */
  private boolean compact = OrganigramLayout.DEF_COMPACT;

  /** The background color. */
  private Color backgroundColor = OrganigramLayout.COLOR_BACKGROUND;

  /** The line color. */
  private Color lineColor = OrganigramLayout.DEF_LINECOLOR;

  private LineMode lineMode = OrganigramLayout.DEF_LINEMODE;

  /** The box right margin. */
  private int rightMargin = OrganigramLayout.DEF_MARGINRIGHT;

  /** The box left margin. */
  private int leftMargin = OrganigramLayout.DEF_MARGINLEFT;

  /** The box top margin. */
  private int topMargin = OrganigramLayout.DEF_MARGINTOP;

  /** The box bottom margin. */
  private int bottomMargin = OrganigramLayout.DEF_MARGINBOTTOM;

  /** The is tool tip enabled. */
  private boolean toolTipEnabled = OrganigramLayout.DEF_TOOLTIP;

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
  public boolean isCompact() {
    return compact;
  }

  /**
   * Sets the org compact.
   *
   * @param compact the new org compact
   */
  public void setCompact(final boolean compact) {
    this.compact = compact;
  }

  public LineMode getLineMode() {
    return lineMode;
  }

  public void setLineMode(final LineMode lineMode) {
    this.lineMode = lineMode;
  }

  /**
   * Gets the org layout.
   *
   * @return the org layout
   */
  public Layout getLayout() {
    return layout;
  }

  /**
   * Sets the org layout.
   *
   * @param layout the new org layout
   */
  public void setLayout(final Layout layout) {
    this.layout = layout;
  }

  /**
   * Gets the org mode.
   *
   * @return the org mode
   */
  public OrgMode getMode() {
    return mode;
  }

  /**
   * Sets the org mode.
   *
   * @param mode the new org mode
   */
  public void setMode(final OrgMode mode) {
    this.mode = mode;
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
