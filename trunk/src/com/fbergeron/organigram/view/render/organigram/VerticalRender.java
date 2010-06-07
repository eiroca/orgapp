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
package com.fbergeron.organigram.view.render.organigram;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.view.OrganigramView;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class CompactVertRender.
 */
public class VerticalRender extends AbstractRender {

  /**
   * Instantiates a new compact vert render.
   * 
   * @param orgView the org view
   * @param compact the compact
   * @param flipX the flip x
   * @param flipY the flip y
   */
  public VerticalRender(final OrganigramView orgView, final boolean compact, final boolean flipX, final boolean flipY) {
    super(orgView, compact, flipX, flipY);
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.view.render.NewRender#layoutBoxes(com.fbergeron
   * .organigram.model.OrganigramLayout, com.fbergeron.organigram.view.UnitView,
   * com.fbergeron.organigram.view.UnitView, int)
   */
  @Override
  public void layoutBoxes(final OrganigramLayout orgLay, final UnitView unit, final UnitView parent, final int level) {
    // pB is the first available position of the box in the current level
    // pN is the first available position of the box in the next level
    validLayout = true;
    // Set dimension of the box to the dimension of the box of this level
    final Dimension sizLevB = getBoxSize(level);
    unit.setSize(sizLevB);
    final Insets margin = orgLay.getMargin();
    final Point pntB = getPoint(level, margin.left, margin.top);
    final int height = sizLevB.height + margin.top + margin.bottom;
    final int width = margin.left + margin.right + unit.getWidth();
    final Point pntN = getPoint(level + 1, pntB.x + width, margin.top);
    if (!compact) {
      if (pntB.y < pntN.y) {
        pntB.y = pntN.y;
      }
      if (pntN.y < pntB.y) {
        pntN.y = pntB.y;
      }
    }
    if (unit.hasChildren()) {
      // Step 1 - layout children
      int yPosB = pntN.y;
      for (final UnitView child : unit) {
        layoutBoxes(orgLay, child, unit, level + 1);
      }
      // Step 2 - center children with parent
      int yPosN = pntN.y;
      final int centerB = pntB.y + height / 2;
      final int centerN = (yPosN - yPosB) / 2 + yPosB;
      int delta;
      if (centerN < centerB) {
        delta = centerB - centerN;
      }
      else {
        delta = 0;
      }
      if (delta > 0) {
        for (final UnitView child : unit) {
          child.move(0, delta, true);
        }
        pntN.y = pntN.y + delta;
        yPosB += delta;
        yPosN += delta;
      }
      else {
        // Step 3 - center parent with children
        delta = (yPosN - yPosB - height);
        if (delta > 0) {
          delta = ((delta + 1) / 2) + yPosB;
          if (delta > pntB.y) {
            pntB.y = delta;
          }
        }
        else if (yPosB > pntB.y) {
          // Adjust parent to be aligned with children
          pntB.y = yPosB;
        }
      }
    }
    // Update box position
    unit.setLocation(pntB);
    pntB.y = pntB.y + height;
  }

}
