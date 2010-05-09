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
package com.fbergeron.organigram.io.sof.tags;

import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.sof.SOFXML;
import com.fbergeron.organigram.io.xml.Tag;
import com.fbergeron.organigram.io.xml.attr.AttrFontInfo;
import com.fbergeron.organigram.io.xml.attr.AttrStr;
import com.fbergeron.organigram.model.Line;

/**
 * The Class InfoProcessor.
 */
public class TagInfo extends Tag {

  public static final String ATR_TYPE = "type";
  public static final String ATR_LINK = "link";
  public static final String ATR_FONT = "font";

  public transient AttrStr aType = new AttrStr(TagInfo.ATR_TYPE, null);
  public transient AttrStr aLink = new AttrStr(TagInfo.ATR_LINK, null);
  public transient AttrFontInfo aFontInfo = new AttrFontInfo(TagInfo.ATR_FONT, null);

  /** The new line. */
  private Line newLine;

  /**
   * Instantiates a new info processor.
   */
  public TagInfo() {
    super("info");
    addAttribute(aType);
    addAttribute(aLink);
    addAttribute(aFontInfo);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    final SOFXML xor = (SOFXML) reader;
    newLine.setText(buf.toString().trim());
    xor.newUnit.addInfo(newLine);
    setNewLine(null);
  }

  /**
   * Gets the new line.
   * 
   * @return the newLine
   */
  public Line getNewLine() {
    return newLine;
  }

  /**
   * Sets the new line.
   * 
   * @param newLine the newLine to set
   */
  public void setNewLine(final Line newLine) {
    this.newLine = newLine;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#start(com.fbergeron.organigram.io.OrganigramReader, org.xml.sax.Attributes)
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    super.start(reader, attribs);
    parse(attribs);
    newLine = new Line();
    newLine.setType(aType.getLastVal());
    newLine.setLink(aLink.getLastVal());
    newLine.setColor(aFontInfo.getLastVal().color);
    newLine.setFont(aFontInfo.getLastVal().font);
  }

}