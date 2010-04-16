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
package com.fbergeron.organigram.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The Class Messages.
 */
public class Messages {

  /** The Constant BUNDLE_NAME. */
  private static final String BUNDLE_NAME = "com.fbergeron.organigram.messages"; //$NON-NLS-1$

  /** The Constant RESOURCE_BUNDLE. */
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(Messages.BUNDLE_NAME);

  /**
   * Instantiates a new messages.
   */
  private Messages() {
  }

  /**
   * Gets the string.
   * 
   * @param key the key
   * 
   * @return the string
   */
  public static String getString(final String key) {
    try {
      return Messages.RESOURCE_BUNDLE.getString(key);
    }
    catch (final MissingResourceException e) {
      return '!' + key + '!';
    }
  }
}
