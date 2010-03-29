/**
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
 * 
 */
package com.fbergeron.organigram.io.xml;

import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.BoxLayout;
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
   * Gets the box layout.
   * 
   * @param unit the u
   * @param organigram the organigram
   * 
   * @return the box layout
   */
  public BoxLayout getBoxLayout(final Organigram organigram, final Unit unit) {
    BoxLayout boxLay = unit.getBoxLayout();
    if (boxLay == null) {
      final BoxLayout obl = organigram.getBoxLayout();
      boxLay = new BoxLayout();
      boxLay.setBackgroundColor(obl.getBackgroundColor());
      boxLay.setForegroundColor(obl.getForegroundColor());
      boxLay.setFrameColor(obl.getFrameColor());
      boxLay.setTextAlignment(obl.getTextAlignment());
      boxLay.setLeftPadding(obl.getLeftPadding());
      boxLay.setRightPadding(obl.getRightPadding());
      boxLay.setTopPadding(obl.getTopPadding());
      boxLay.setBottomPadding(obl.getBottomPadding());
      unit.setBoxLayout(boxLay);
    }
    return boxLay;
  }

  /**
   * Process unit begin.
   * 
   * @param attribs the attributes
   * @param reader the reader
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    final XMLOrganigramReader xor = (XMLOrganigramReader) reader;
    final Organigram organigram = xor.getOrganigram();
    xor.newUnit = new Unit();
    if (xor.rootUnit == null) {
      xor.rootUnit = xor.newUnit;
    }
    else {
      final Unit parentUnit = xor.parentUnits.peek();
      parentUnit.addChild(xor.newUnit);
    }
    String name;
    String value;
    for (int i = 0; i < attribs.getLength(); i++) {
      name = attribs.getQName(i);
      value = attribs.getValue(i);
      if (name.equals(XMLUtil.ATR_ID)) {
        xor.newUnit.setID(value);
      }
      else if (XMLUtil.isBoxLayoutAtr(name, value)) {
        XMLUtil.readBoxLayoutAtr(name, value, getBoxLayout(organigram, xor.newUnit));
      }
      else {
        xor.newUnit.setMeta(name, value);
      }
    }
  }

}