/**
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
 * 
 */
package com.fbergeron.organigram.model;

/**
 * Action needs to collect metadata.
 */
public interface MetaDataCollector {

  /**
   * Sets the meta.
   * 
   * @param name the name
   * @param value the value
   */
  void setMeta(String name, String value);

  /**
   * Gets the meta.
   * 
   * @param name the name
   * 
   * @return the meta
   */
  String getMeta(String name);

}
