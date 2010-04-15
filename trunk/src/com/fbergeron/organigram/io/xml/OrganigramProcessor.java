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

import org.xml.sax.Attributes;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;

/**
 * The Class OrganigramProcessor.
 */
public class OrganigramProcessor extends TAG {

  /**
   * Instantiates a new organigram processor.
   */
  public OrganigramProcessor() {
    super("organigram");
  }

  /**
   * Process organigram begin.
   *
   * @param attribs the attributes
   * @param reader the reader
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    final Organigram organigram = new Organigram();
    reader.setOrganigram(organigram);
    final BoxLayout boxLay = organigram.getBoxLayout();
    final OrganigramLayout orgLay = organigram.getOrganigramLayout();
    String name;
    String value;
    for (int i = 0; i < attribs.getLength(); i++) {
      name = attribs.getQName(i);
      value = attribs.getValue(i);
      if (name.equals(XMLUtil.ATR_ORG_MODE)) {
        orgLay.setMode(XMLUtil.readOrgMode(value, orgLay.getMode()));
      }
      else if (name.equals(XMLUtil.ATR_ORG_LAYOUT)) {
        orgLay.setLayout(XMLUtil.readLayout(value, orgLay.getLayout()));
      }
      else if (name.equals(XMLUtil.ATR_ORG_COMPACT)) {
        orgLay.setCompact(XMLUtil.readBoolean(value, orgLay.isCompact()));
      }
      else if (name.equals(XMLUtil.ATR_BACKGROUNDCOLOR)) {
        orgLay.setBackgroundColor(XMLUtil.readColor(value, orgLay.getBackgroundColor()));
      }
      else if (name.equals(XMLUtil.ATR_LINECOLOR)) {
        orgLay.setLineColor(XMLUtil.readColor(value, orgLay.getLineColor()));
      }
      else if (name.equals(XMLUtil.ATR_LINEMODE)) {
        orgLay.setLineMode(XMLUtil.readLineMode(value, orgLay.getLineMode()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_RIGHT)) {
        orgLay.setRightMargin(XMLUtil.readInt(value, orgLay.getRightMargin()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_LEFT)) {
        orgLay.setLeftMargin(XMLUtil.readInt(value, orgLay.getLeftMargin()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_TOP)) {
        orgLay.setTopMargin(XMLUtil.readInt(value, orgLay.getTopMargin()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_BOTTOM)) {
        orgLay.setBottomMargin(XMLUtil.readInt(value, orgLay.getBottomMargin()));
      }
      else if (name.equals(XMLUtil.ATR_TOOLTIPENABLED)) {
        orgLay.setToolTipEnabled(XMLUtil.readBoolean(value, orgLay.isToolTipEnabled()));
      }
      else if (XMLUtil.isBoxLayoutAtr(name, value)) {
        XMLUtil.readBoxLayoutAtr(name, value, boxLay);
      }
      else {
        organigram.setMeta(name, value);
      }
    }
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    final XMLOrganigramReader xor = (XMLOrganigramReader) reader;
    reader.getOrganigram().setRoot(xor.rootUnit);
  }

}
