/** LGPL > 3.0
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
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
package com.fbergeron.organigram.io.sof;

import java.util.Stack;
import com.fbergeron.organigram.io.TagProcessor;
import com.fbergeron.organigram.io.XMLHandler;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class XMLOrganigramReader.
 */
public class XMLOrganigramReader extends XMLHandler {

  // Only to initialize XMLUtil
  /** The tag. */
  public final TagProcessor tag = XMLUtil.ORGANIGRAM;

  /** The parent units. */
  public final Stack<Unit> parentUnits = new Stack<Unit>();

  /** The root unit. */
  public Unit rootUnit;

  /** The new unit. */
  public Unit newUnit;

  /** The new line. */
  public Line newLine;

}
