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
package com.fbergeron.organigram.io.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for creating Tag objects.
 */
public final class TagFactory {

  /** The registry. */
  private static final Map<String, TagProcessor> REGISTRY = new HashMap<String, TagProcessor>();

  /**
   * Instantiates a new tag factory.
   */
  private TagFactory() {
    super();
  }

  /**
   * Register.
   * 
   * @param processor the processor
   */
  public static void register(final TagProcessor processor) {
    TagFactory.REGISTRY.put(processor.getName(), processor);
  }

  /**
   * Gets the processor.
   * 
   * @param tagName the tag name
   * 
   * @return the processor
   */
  public static TagProcessor getProcessor(final String tagName) {
    return TagFactory.REGISTRY.get(tagName);
  }

}
