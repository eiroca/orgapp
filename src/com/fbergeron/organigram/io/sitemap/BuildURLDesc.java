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
package com.fbergeron.organigram.io.sitemap;

import java.awt.Font;
import com.fbergeron.organigram.model.AbstractProcessor;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class BuildInfo.
 */
public class BuildURLDesc extends AbstractProcessor<Object> {

  /** The full. */
  private boolean full;

  /**
   * Instantiates a new builds the url description.
   * 
   * @param full the full
   */
  public BuildURLDesc(final boolean full) {
    super();
    this.full = full;
  }

  /**
   * Adds the.
   * 
   * @param unit the unit
   * @param desc the description
   * @param what the what
   */
  private void add(final Unit unit, final String desc, final String what) {
    final String val = unit.getMeta(what);
    if (val != null) {
      unit.addInfo(new Line(desc + val, Font.PLAIN, 10, null));
    }
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.model.UnitTraversal#process(com.fbergeron.organigram.model.Unit)
   */
  @Override
  public void process(final Unit unit, final int level, final Object context) {
    final String title = unit.getMeta("title");
    if (title == null) {
      final String loc = unit.getMeta("link");
      if (loc != null) {
        unit.addInfo(new Line(loc, Font.BOLD, 12, null));
      }
    }
    else {
      unit.addInfo(new Line(title, Font.BOLD, 12, null));
    }
    if (isFull()) {
      add(unit, "Last Mod : ", "lastmod");
      add(unit, "Frequency: ", "changefreq");
      add(unit, "Priority : ", "priority");
    }
  }

  /**
   * Checks if is full.
   * 
   * @return true, if is full
   */
  public boolean isFull() {
    return full;
  }

  /**
   * Sets the full.
   * 
   * @param full the new full
   */
  public void setFull(final boolean full) {
    this.full = full;
  }
}