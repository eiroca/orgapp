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
package com.fbergeron.organigram.view.render;

import java.awt.Graphics2D;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.view.UnitView;

/**
 * The Interface LineRender.
 */
public interface LineRender {

  /**
   * Draw lines.
   * 
   * @param graphics the graphics
   * @param box the box
   * @param orgLay the org lay
   */
  void paint(final Graphics2D graphics, final UnitView box, final OrganigramLayout orgLay);

}
