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
package com.fbergeron.organigram.model;

// TODO: Auto-generated Javadoc
/**
 * The Interface UnitTraversal.
 * 
 * @param <T> the < t>
 */
public abstract class AbstractProcessor<T> {

  /**
   * Execute.
   * 
   * @param org the org
   * @param nodeFirst the node first
   * @param context the context
   */
  public void execute(final Organigram org, final boolean nodeFirst, final T context) {
    execute(org.getRoot(), nodeFirst, 0, context);
  }

  /**
   * Execute.
   * 
   * @param unit the unit
   * @param nodeFirst the node first
   * @param level the level
   * @param context the context
   */
  public void execute(final Unit unit, final boolean nodeFirst, final int level, final T context) {
    if (nodeFirst) {
      process(unit, level, context);
    }
    for (final Unit u : unit) {
      execute(u, nodeFirst, level + 1, context);
    }
    if (!nodeFirst) {
      process(unit, level, context);
    }
  }

  /**
   * Process.
   * 
   * @param unit the unit
   * @param level the level
   * @param context the context
   */
  abstract public void process(Unit unit, int level, T context);

}
