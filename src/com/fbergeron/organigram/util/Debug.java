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

/**
 * The Class Debug.
 */
public class Debug {

  /**
   * Ignore.
   * 
   * @param e the e
   */
  public static void ignore(final Throwable e) {
    System.out.println("Ignoring: " + e);
  }

  /**
   * Error.
   * 
   * @param e the e
   */
  public static void error(final Throwable e) {
    System.err.println("E=>" + e);
  }

  /**
   * Error.
   * 
   * @param msg the msg
   */
  public static void error(final String msg) {
    System.err.println("E=>" + msg);
  }

}
