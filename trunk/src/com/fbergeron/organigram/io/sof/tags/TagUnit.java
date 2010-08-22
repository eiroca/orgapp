/** LGPL > 3.0
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
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
package com.fbergeron.organigram.io.sof.tags;

import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.sof.SOFXML;
import com.fbergeron.organigram.io.xml.Tag;
import com.fbergeron.organigram.io.xml.attr.AttrAlignment;
import com.fbergeron.organigram.io.xml.attr.AttrBoolean;
import com.fbergeron.organigram.io.xml.attr.AttrBoxType;
import com.fbergeron.organigram.io.xml.attr.AttrColor;
import com.fbergeron.organigram.io.xml.attr.AttrInsets;
import com.fbergeron.organigram.io.xml.attr.AttrStr;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class UnitProcessor.
 */
public class TagUnit extends Tag {

  public static final String ATR_ID = "id";
  public static final String ATR_TYPE = "type";
  public static final String ATR_EXPENDED = "expanded";
  public static final String ATR_TEXTALIGMENT = "textAlignment";
  public static final String ATR_BACKGROUND = "backgroundColor";
  public static final String ATR_FOREGROUND = "foregroundColor";
  public static final String ATR_FRAMECOLOR = "frameColor";
  public static final String ATR_PADDING = "padding";

  public transient AttrStr aId = new AttrStr(TagUnit.ATR_ID, null);
  public transient AttrBoxType aBoxType = new AttrBoxType(TagUnit.ATR_TYPE, null);
  public transient AttrBoolean aBoxExpanded = new AttrBoolean(TagUnit.ATR_EXPENDED, null);
  public transient AttrAlignment aBoxTextAlignment = new AttrAlignment(TagUnit.ATR_TEXTALIGMENT, null);
  public transient AttrColor aBoxBackground = new AttrColor(TagUnit.ATR_BACKGROUND, null);
  public transient AttrColor aBoxForeground = new AttrColor(TagUnit.ATR_FOREGROUND, null);
  public transient AttrColor aBoxFrameColor = new AttrColor(TagUnit.ATR_FRAMECOLOR, null);
  public transient AttrInsets aBoxPadding = new AttrInsets(TagUnit.ATR_PADDING, null);

  /**
   * Instantiates a new unit processor.
   */
  public TagUnit() {
    super("unit");
    addAttribute(aId);
    addAttribute(aBoxType);
    addAttribute(aBoxExpanded);
    addAttribute(aBoxTextAlignment);
    addAttribute(aBoxBackground);
    addAttribute(aBoxForeground);
    addAttribute(aBoxFrameColor);
    addAttribute(aBoxPadding);
  }

  /**
   * Process unit begin.
   * 
   * @param reader the reader
   * @param attribs the attributes
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    final SOFXML xor = (SOFXML) reader;
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
    parse(attribs);
    final BoxLayout boxLay = xor.newUnit.getBoxLayout();
    xor.newUnit.setId(aId.getLastVal());
    boxLay.setType(aBoxType.getLastVal());
    boxLay.setExpanded(aBoxExpanded.getLastVal());
    boxLay.setTextAlignment(aBoxTextAlignment.getLastVal());
    boxLay.setBackgroundColor(aBoxBackground.getLastVal());
    boxLay.setForegroundColor(aBoxForeground.getLastVal());
    boxLay.setFrameColor(aBoxFrameColor.getLastVal());
    boxLay.setPadding(aBoxPadding.getLastVal());
    for (int i = 0; i < attribs.getLength(); i++) {
      final String name = attribs.getQName(i);
      if (!isAttribute(name)) {
        final String value = attribs.getValue(i);
        xor.newUnit.setMeta(name, value);
      }
    }
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    final SOFXML xor = (SOFXML) reader;
    xor.newUnit = xor.parentUnits.pop();
  }

}
