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
public final class Resources {

  /** The Constant BUNDLE_NAME. */
  private static final String BUNDLE_NAME = "com.fbergeron.organigram.messages"; //$NON-NLS-1$

  /** The Constant RESOURCE_BUNDLE. */
  private final ResourceBundle resources;

  /** The instance. */
  private static Resources instance;

  /**
   * Gets the single instance of Messages.
   * 
   * @return single instance of Messages
   */
  public static synchronized Resources getInstance() {
    if (Resources.instance == null) {
      Resources.instance = new Resources();
    }
    return Resources.instance;
  }

  /**
   * Instantiates a new messages.
   */
  private Resources() {
    resources = ResourceBundle.getBundle(Resources.BUNDLE_NAME);
  }

  /**
   * Gets the string.
   * 
   * @param key the key
   * 
   * @return the string
   */
  public String getString(final String key) {
    String res;
    try {
      res = resources.getString(key);
    }
    catch (final MissingResourceException e) {
      res = '!' + key + '!';
    }
    return res;
  }

}
