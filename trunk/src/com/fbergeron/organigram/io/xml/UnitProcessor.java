/**
 * LGPL > 3.0 Copyright (C) 2005 Frédéric Bergeron
 * (fbergeron@users.sourceforge.net) Copyright (C) 2006-2010 eIrOcA (eNrIcO
 * Croce & sImOnA Burzio)
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
package com.fbergeron.organigram.io.xml;

import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class UnitProcessor.
 */
public class UnitProcessor extends TAG {

  /**
   * Instantiates a new unit processor.
   */
  public UnitProcessor() {
    super("unit");
  }

  /**
   * Process unit begin.
   * 
   * @param reader the reader
   * @param attribs the attributes
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    final XMLOrganigramReader xor = (XMLOrganigramReader) reader;
    final Organigram organigram = xor.getOrganigram();
    xor.newUnit = new Unit(organigram);
    if (xor.rootUnit == null) {
      xor.rootUnit = xor.newUnit;
    }
    else {
      final Unit parentUnit = xor.parentUnits.peek();
      parentUnit.addChild(xor.newUnit);
    }
    xor.parentUnits.push(xor.newUnit);
    String name;
    String value;
    for (int i = 0; i < attribs.getLength(); i++) {
      name = attribs.getQName(i);
      value = attribs.getValue(i);
      if (name.equals(XMLUtil.ATR_ID)) {
        xor.newUnit.setID(value);
      }
      else if (XMLUtil.isBoxLayoutAtr(name, value)) {
        XMLUtil.readBoxLayoutAtr(name, value, xor.newUnit.getBoxLayout(true));
      }
      else {
        xor.newUnit.setMeta(name, value);
      }
    }
  }

  @Override
  public void end(final OrganigramReader reader) {
    final XMLOrganigramReader xor = (XMLOrganigramReader) reader;
    xor.newUnit = xor.parentUnits.pop();
  }

}
