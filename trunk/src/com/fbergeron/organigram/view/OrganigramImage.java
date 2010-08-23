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
package com.fbergeron.organigram.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.fbergeron.organigram.model.Organigram;

/**
 * The Class OrganigramImage.
 */
public class OrganigramImage {

  /** The organigram. */
  private final Organigram organigram;

  /** The image. */
  BufferedImage image = null;

  /**
   * Instantiates a new organigram image.
   * 
   * @param organigram the organigram
   */
  public OrganigramImage(final Organigram organigram) {
    this.organigram = organigram;
  }

  /**
   * Generate graphic.
   * 
   * @param size the size
   */
  public void generateGraphic(final Dimension size) {
    final OrganigramView view = new OrganigramView(organigram);
    image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    Graphics2D canvas = image.createGraphics();
    Dimension aSize = view.doLayout(canvas);
    if (size != null) {
      aSize = size;
    }
    image = new BufferedImage(aSize.width, aSize.height, BufferedImage.TYPE_INT_ARGB);
    canvas = image.createGraphics();
    view.paint(canvas, aSize);
  }

  /**
   * Gets the image.
   * 
   * @return the image
   */
  public BufferedImage getImage() {
    if (image == null) {
      generateGraphic(null);
    }
    return image;
  }

}
