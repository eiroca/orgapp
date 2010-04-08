/**
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
 * 
 */
package com.fbergeron.organigram.view.render.organigram;

import java.awt.Dimension;
import java.awt.Point;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.view.OrganigramView;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Class CompactHorizRender.
 */
public class HorizontalRender extends AbstractRender {

  /**
   * Instantiates a new compact horizontal render.
   * 
   * @param orgView the organigram view
   * @param graphic the graphic
   * @param compact the compact
   */
  public HorizontalRender(final OrganigramView orgView, final boolean compact) {
    super(orgView, compact);
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
    final Point pB = getPoint(level, orgLay.getLeftMargin(), orgLay.getTopMargin());
    final int wB = sizLevB.width + orgLay.getLeftMargin() + orgLay.getRightMargin();
    final int h = orgLay.getTopMargin() + orgLay.getBottomMargin() + unit.getHeight();
    final Point pN = getPoint(level + 1, orgLay.getLeftMargin(), pB.y + h);
    if (!compact) {
      if (pB.x < pN.x) {
        pB.x = pN.x;
      }
      if (pN.x < pB.x) {
        pN.x = pB.x;
      }
    }
    if (unit.hasChildren()) {
      // Step 1 - layout childs
      int xB = pN.x;
      for (final UnitView child : unit) {
        layoutBoxes(orgLay, child, unit, level + 1);
      }
      // Step 2 - center childs with parent
      int xN = pN.x;
      final int cB = pB.x + wB / 2;
      final int cN = (xN - xB) / 2 + xB;
      int d = 0;
      if (cN < cB) {
        d = cB - cN;
      }
      if (d > 0) {
        for (final UnitView child : unit) {
          child.move(d, 0, true);
        }
        pN.x = pN.x + d;
        xB += d;
        xN += d;
      }
      else {
        // Step 3 - center parent with childs
        d = (xN - xB - wB);
        if (d > 0) {
          d = ((d + 1) / 2) + xB;
          if (d > pB.x) {
            pB.x = d;
          }
        }
        else if (xB > pB.x) {
          // Adjust parent to be aligned with childs
          pB.x = xB;
        }
      }
    }
    // Update box position
    unit.setLocation(pB);
    pB.x = pB.x + wB;
  }

}
