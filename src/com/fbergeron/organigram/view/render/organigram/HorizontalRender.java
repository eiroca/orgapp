/** LGPL > 3.0
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
 */
package com.fbergeron.organigram.view.render.organigram;

import java.awt.Dimension;
import java.awt.Insets;
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
   * @param compact the compact
   * @param flipX the flip x
   * @param flipY the flip y
   */
  public HorizontalRender(final OrganigramView orgView, final boolean compact, final boolean flipX, final boolean flipY) {
    super(orgView, compact, flipX, flipY);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.fbergeron.organigram.view.render.NewRender#layoutBoxes(com.fbergeron
   * .organigram.model.OrganigramLayout, com.fbergeron.organigram.view.UnitView,
   * com.fbergeron.organigram.view.UnitView, int)
   */
  @Override
  public void layoutBoxes(final OrganigramLayout orgLay, final UnitView unit, final UnitView parent, final int level, final Dimension rect) {
    // pB is the first available position of the box in the current level
    // pN is the first available position of the box in the next level
    // Set dimension of the box to the dimension of the box of this level
    final Dimension sizLevB = unit.getSize();
    final Insets margin = orgLay.getMargin();
    Point pntB = getPoint(level, margin.left, margin.top);
    final int width = sizLevB.width + margin.left + margin.right;
    final int height = margin.top + margin.bottom + unit.getHeight();
    final Point pntN = getPoint(level + 1, margin.left, pntB.y + height);
    if (!compact) {
      if (pntB.x < pntN.x) {
        pntB.x = pntN.x;
      }
      if (pntN.x < pntB.x) {
        pntN.x = pntB.x;
      }
    }
    if (unit.hasChildren()) {
      if (orgLay.isFlipped() && unit.isFlippable()) {
        // be sure to remove "compact"
        if (pntN.x < pntB.x) {
          pntN.x = pntB.x;
        }
        if (pntB.x < pntN.x) {
          unit.move(pntN.x - pntB.x, 0, false);
          pntB.x = pntN.x;
        }
        final Point pnt = new Point(pntN);
        pnt.x += width / 2;
        pntB = getPoint(level, 0, 0);
        int curLev = level + 1;
        for (final UnitView child : unit) {
          child.setLocation(pnt);
          final Point p = getPoint(curLev, 0, 0);
          p.x = pnt.x + width;
          p.y = pnt.y;
          pnt.y += child.getSize().height + margin.bottom + margin.top;
          curLev++;
        }
        if (rect.height < pnt.y) {
          rect.height = pnt.y;
        }
      }
      else {
        // Step 1 - layout children
        int xPosB = pntN.x;
        for (final UnitView child : unit) {
          layoutBoxes(orgLay, child, unit, level + 1, rect);
        }
        // Step 2 - center children with parent
        int xPosN = pntN.x;
        final int centerB = pntB.x + (width / 2);
        int centerN = ((xPosN - xPosB) / 2) + xPosB;
        if (centerN < centerB) {
          final int delta = centerB - centerN;
          for (final UnitView child : unit) {
            child.move(delta, 0, true);
          }
          pntN.x = pntN.x + delta;
          xPosB += delta;
          xPosN += delta;
        }
        if (centerB < centerN) {
          // Step 3 - center parent with children
          xPosB = unit.getChildren().get(0).getLocation().x;
          centerN = ((xPosN - xPosB) / 2) + xPosB;
          final int delta = centerN - centerB;
          pntB.x += delta;
          if (pntB.x < xPosB) {
            pntB.x = xPosB;
          }
        }
      }
    }
    // Update box position
    unit.setLocation(pntB);
    pntB.x = pntB.x + width;
    if (rect.width < pntB.x) {
      rect.width = pntB.x;
    }
    if (rect.height < pntB.y) {
      rect.height = pntB.y;
    }
  }

}