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
import com.fbergeron.organigram.model.type.Layout;
import com.fbergeron.organigram.model.type.LineMode;
import com.fbergeron.organigram.model.type.OrgMode;

/**
 * The Class OrganigramLayout.
 */
public class OrganigramLayout {

  /** The Constant DEF_LAYOUT. */
  public static final Layout DEF_LAYOUT = Layout.TOP;
  
  /** The Constant DEF_ORGMODE. */
  public static final OrgMode DEF_ORGMODE = OrgMode.MAX;
  
  /** The Constant DEF_COMPACT. */
  public static final boolean DEF_COMPACT = Boolean.TRUE;
  
  /** The Constant DEF_BACKGROUND. */
  public static final Color DEF_BACKGROUND = Color.white;
  
  /** The Constant DEF_LINEMODE. */
  public static final LineMode DEF_LINEMODE = LineMode.CONNECTOR;
  
  /** The Constant DEF_LINECOLOR. */
  public static final Color DEF_LINECOLOR = Color.black;
  
  /** The Constant DEF_MARGIN. */
  public static final Insets DEF_MARGIN = new Insets(6, 6, 6, 6);

  /** The Constant DEF_TOOLTIP. */
  public static final boolean DEF_TOOLTIP = true;

  /** The layout. */
  private Layout layout = OrganigramLayout.DEF_LAYOUT;
  
  /** The mode. */
  private OrgMode mode = OrganigramLayout.DEF_ORGMODE;
  
  /** The compact. */
  private boolean compact = OrganigramLayout.DEF_COMPACT;
  
  /** The background color. */
  private Color backgroundColor = OrganigramLayout.DEF_BACKGROUND;
  
  /** The line mode. */
  private LineMode lineMode = OrganigramLayout.DEF_LINEMODE;
  
  /** The line color. */
  private Color lineColor = OrganigramLayout.DEF_LINECOLOR;
  
  /** The margin. */
  private Insets margin = new Insets(OrganigramLayout.DEF_MARGIN.top, OrganigramLayout.DEF_MARGIN.left, OrganigramLayout.DEF_MARGIN.bottom, OrganigramLayout.DEF_MARGIN.right);

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

  /**
   * Gets the line mode.
   * 
   * @return the line mode
   */
  public LineMode getLineMode() {
    return lineMode;
  }

  /**
   * Sets the line mode.
   * 
   * @param lineMode the new line mode
   */
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
  public Insets getMargin() {
    return margin;
  }

  /**
   * Sets the box right margin.
   * 
   * @param margin the new margin
   */
  public void setMargin(final Insets margin) {
    this.margin = margin;
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
