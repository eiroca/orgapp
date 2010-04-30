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

import java.awt.Font;
import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.io.sof.SOFReader;
import com.fbergeron.organigram.io.sof.SOFXML;
import com.fbergeron.organigram.model.Line;

/**
 * The Class InfoProcessor.
 */
public class TagInfo extends TAG {

  /** The new line. */
  private Line newLine;

  /**
   * Instantiates a new info processor.
   */
  public TagInfo() {
    super("info");
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#start(com.fbergeron.organigram.io.OrganigramReader, org.xml.sax.Attributes)
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    super.start(reader, attribs);
    final String type = attribs.getValue(SOFXML.ATR_TYPE);
    final String realFontName = attribs.getValue(SOFXML.ATR_FONT_NAME);
    final int realFontSize = SOFXML.readInt(attribs.getValue(SOFXML.ATR_FONT_SIZE), 12);
    final int realFontStyle = SOFXML.readFontStyle(attribs.getValue(SOFXML.ATR_FONT_STYLE), Font.PLAIN);
    newLine = new Line();
    newLine.setType(type);
    newLine.setColor(SOFXML.readColor(attribs.getValue(SOFXML.ATR_FONT_COLOR), newLine.getColor()));
    newLine.setFont(new Font(realFontName, realFontStyle, realFontSize));
    newLine.setLink(attribs.getValue(SOFXML.ATR_LINK));
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    final SOFReader xor = (SOFReader) reader;
    newLine.setText(buf.toString().trim());
    xor.newUnit.addInfo(newLine);
    setNewLine(null);
  }

  /**
   * @return the newLine
   */
  public Line getNewLine() {
    return newLine;
  }

  /**
   * @param newLine the newLine to set
   */
  public void setNewLine(final Line newLine) {
    this.newLine = newLine;
  }

}