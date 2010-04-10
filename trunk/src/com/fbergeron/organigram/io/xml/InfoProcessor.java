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
package com.fbergeron.organigram.io.xml;

import java.awt.Font;
import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.Line;

/**
 * The Class InfoProcessor.
 */
public class InfoProcessor extends TAG {

  /** The new line. */
  Line newLine;

  /**
   * Instantiates a new info processor.
   */
  public InfoProcessor() {
    super("info");
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#start(com.fbergeron.organigram.io.OrganigramReader, org.xml.sax.Attributes)
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    super.start(reader, attribs);
    final String type = attribs.getValue(XMLUtil.ATR_TYPE);
    final String realFontName = attribs.getValue(XMLUtil.ATR_FONT_NAME);
    final int realFontSize = XMLUtil.readInt(attribs.getValue(XMLUtil.ATR_FONT_SIZE), 12);
    final int realFontStyle = XMLUtil.readFontStyle(attribs.getValue(XMLUtil.ATR_FONT_STYLE), Font.PLAIN);
    newLine = new Line();
    newLine.setType(type);
    newLine.setColor(XMLUtil.readColor(attribs.getValue(XMLUtil.ATR_FONT_COLOR), newLine.getColor()));
    newLine.setFont(new Font(realFontName, realFontStyle, realFontSize));
    newLine.setLink(attribs.getValue(XMLUtil.ATR_LINK));
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    final XMLOrganigramReader xor = (XMLOrganigramReader) reader;
    newLine.setText(buf.toString().trim());
    xor.newUnit.addInfo(newLine);
    newLine = null;
  }

}
