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
 * The Class CompactVertRender.
 */
public class VerticalRender extends AbstractRender {

  /**
   * Instantiates a new compact vert render.
   * 
   * @param orgView the org view
   * @param graphic the graphic
   * @param compact the compact
   */
  public VerticalRender(final OrganigramView orgView,  final boolean compact) {
    super(orgView,  compact);
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
    validLayout=true;
    // Set dimension of the box to the dimension of the box of this level
    final Dimension sizLevB = getBoxSize(level);
    unit.setSize(sizLevB);
    // pB is the first available position of the box in the current level
    // pN is the first available position of the box in the next level
    final Point pB = getPoint(level, orgLay.getLeftMargin(), orgLay.getTopMargin());
    final int hB = sizLevB.height + orgLay.getTopMargin() + orgLay.getBottomMargin();
    final int w = orgLay.getLeftMargin() + orgLay.getRightMargin() + unit.getWidth();
    final Point pN = getPoint(level + 1, pB.x + w, orgLay.getTopMargin());
    if (!compact) {
      if (pB.y < pN.y) {
        pB.y = pN.y;
      }
      if (pN.y < pB.y) {
        pN.y = pB.y;
      }
    }
    if (unit.hasChildren()) {
      // Step 1 - layout childs
      int h1 = pN.y;
      for (final UnitView child : unit) {
        layoutBoxes(orgLay, child, unit, level + 1);
      }
      // Step 2 - center childs with parent
      int d;
      int h2 = pN.y;
      final int cB = pB.y + hB / 2;
      final int cN = (h2 - h1) / 2 + h1;
      if (cN < cB) {
        d = cB - cN;
        for (final UnitView child : unit) {
          child.move(0, d, true);
        }
        pN.y = pN.y + d;
        h1 += d;
        h2 += d;
      }
      // Step 3 - center parent with childs
      d = (h2 - h1 - hB);
      if (d > 0) {
        d = ((d + 1) / 2) + h1;
        if (d > pB.y) {
          pB.y = d;
        }
      }
      else if (h1 > pB.y) {
        // Adjust parent to be aligned with childs
        pB.y = h1;
      }
    }
    // Update box position
    unit.setLocation(pB);
    pB.y = pB.y + hB;
  }

}