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
package com.fbergeron.organigram.io;

import java.io.InputStream;
import java.util.Map;
import com.fbergeron.organigram.model.Organigram;

/**
 * The Interface OrganigramReader.
 */
public interface OrganigramReader {

  /**
   * Read organigram.
   * 
   * @param path the path
   * 
   * @return the organigram
   */
  Organigram readOrganigram(InputStream source);

  /**
   * Returns the organigram.
   * 
   * @return the organigram
   */
  Organigram getOrganigram();

  /**
   * Set the organigram.
   * 
   * @param organigram the organigram
   */
  void setOrganigram(Organigram organigram);

  /**
   * Organigram data.
   * 
   * @return the data
   */
  Map<String, String> getData();

}
