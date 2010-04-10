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
package com.fbergeron.organigram.view;

import java.awt.Dimension;

/**
 * BoxSpace keeps the minimum and the maximum size of a box.
 */
public class BoxSpace {

  /** packed flag == false if minimum and maximum size are equals. */
  transient public boolean packed;

  /** The minimum size. */
  transient public Dimension small;

  /** The maximum size. */
  transient public Dimension full;

  /**
   * Instantiates a new box space.
   */
  public BoxSpace() {
    small = new Dimension(0, 0);
    full = new Dimension(0, 0);
    packed = false;
  }

  /**
   * Instantiates a new box space.
   * 
   * @param small the small
   * @param full the full
   */
  public BoxSpace(final Dimension small, final Dimension full) {
    this.small = small;
    this.full = full;
    updatePacked();
  }

  /**
   * Update packed.
   */
  final public void updatePacked() {
    if ((small.width != full.width) || (small.height != full.height)) {
      packed = true;
    }
    else {
      packed = false;
    }
  }

}
