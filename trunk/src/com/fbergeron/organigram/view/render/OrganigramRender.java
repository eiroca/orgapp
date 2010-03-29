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
package com.fbergeron.organigram.view.render;

import java.awt.Graphics;

/**
 * The Interface OrganigramRender.
 */
public interface OrganigramRender {

  /**
   * Sets the line render.
   * 
   * @param lineRender the new line render
   */
  void setLineRender(final LineRender lineRender);

  /**
   * Sets the box render.
   * 
   * @param boxRender the new box render
   */
  void setBoxRender(final BoxRender boxRender);

  /**
   * Paint the organigram.
   * 
   * @param graphics the graphics
   */
  void paint(final Graphics graphics);

  void invalidate();

}
