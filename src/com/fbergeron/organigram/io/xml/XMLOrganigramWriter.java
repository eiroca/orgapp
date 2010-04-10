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
package com.fbergeron.organigram.io.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.io.TAG;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class XMLOrganigramWriter.
 */
public class XMLOrganigramWriter implements OrganigramWriter {

  /** The Constant order. */
  private final static List<String> order = new ArrayList<String>();
  static {
    XMLOrganigramWriter.order.add("id");
    XMLOrganigramWriter.order.add("firm");
    XMLOrganigramWriter.order.add("name");
    XMLOrganigramWriter.order.add("role");
    XMLOrganigramWriter.order.add("department");
    XMLOrganigramWriter.order.add("date");
  }

  /**
   * Write meta.
   * 
   * @param tag the me
   * @param buf the output buffer
   * @param meta the meta
   */
  public static void writeMeta(final TAG tag, final StringBuffer buf, final Map<String, String> meta) {
    final HashSet<String> done = new HashSet<String>();
    for (final String metaName : XMLOrganigramWriter.order) {
      final String val = meta.get(metaName);
      if (val != null) {
        tag.writeAttribute(buf, metaName, meta.get(metaName), false);
        done.add(metaName);
      }
    }
    for (final String metaName : meta.keySet()) {
      if (!done.contains(metaName)) {
        tag.writeAttribute(buf, metaName, meta.get(metaName), false);
      }
    }
  }

  /**
   * Write lines.
   * 
   * @param buf the stringbuffer
   * @param lines the lines
   * 
   * @return true, if successful
   */
  public boolean writeLines(final StringBuffer buf, final List<Line> lines) {
    if (lines == null) { return false; }
    if (lines.isEmpty()) { return false; }
    for (final Line l : lines) {
      XMLUtil.INFO.open(buf, true);
      XMLUtil.writeString(XMLUtil.INFO, buf, XMLUtil.ATR_TYPE, l.getType());
      XMLUtil.writeString(XMLUtil.INFO, buf, XMLUtil.ATR_LINK, l.getLink());
      if (l.getColor() != null) {
        XMLUtil.writeColor(XMLUtil.INFO, buf, XMLUtil.ATR_FONT_COLOR, null, l.getColor());
      }
      XMLUtil.writeFont(XMLUtil.INFO, buf, XMLUtil.ATR_FONT_NAME, XMLUtil.ATR_FONT_SIZE, XMLUtil.ATR_FONT_STYLE, Line.LINE_FONT, l.getFont());
      XMLUtil.INFO.openClose(buf);
      XMLUtil.INFO.writeCData(buf, l.getText());
      XMLUtil.INFO.close(buf, false);
    }
    return true;
  }

  /**
   * Write unit.
   * 
   * @param buf the sb
   * @param unit the root
   * @param writeInfo the write info
   * 
   * @return true, if successful
   */
  public boolean writeUnit(final StringBuffer buf, final Unit unit, final boolean writeInfo) {
    if (unit == null) { return false; }
    XMLUtil.UNIT.open(buf, true);
    XMLUtil.writeString(XMLUtil.UNIT, buf, XMLUtil.ATR_ID, unit.getID());
    final BoxLayout boxLay = unit.getBoxLayout();
    if (boxLay != null) {
      XMLUtil.writeBoxLayoutAtr(XMLUtil.UNIT, buf, boxLay);
    }
    XMLOrganigramWriter.writeMeta(XMLUtil.UNIT, buf, unit.getMeta());
    boolean compact = true;
    if (writeInfo) {
      final List<Line> lines = unit.getInfo();
      if (!lines.isEmpty()) {
        if (compact) {
          XMLUtil.UNIT.openClose(buf);
          compact = false;
        }
        writeLines(buf, lines);
      }
    }
    if (unit.hasChildren()) {
      if (compact) {
        XMLUtil.UNIT.openClose(buf);
        compact = false;
      }
      XMLUtil.CHILDS.open(buf, false);
      for (final Unit u : unit) {
        writeUnit(buf, u, writeInfo);
      }
      XMLUtil.CHILDS.close(buf, false);
    }
    XMLUtil.UNIT.close(buf, compact);
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.io.OrganigramWriter#writeOrganigram(com.fbergeron.organigram.model.Organigram,
   *      boolean)
   */
  public String writeOrganigram(final Organigram organigram, final boolean compact) {
    final StringBuffer buf = new StringBuffer(1024);
    buf.append("<?xml version=\"1.0\"?>");
    XMLUtil.ORGANIGRAM.open(buf, true);
    final OrganigramLayout orgLay = organigram.getOrganigramLayout();
    XMLUtil.writeInt(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_ORG_MODE, OrganigramLayout.ORGMODE, orgLay.getOrgMode());
    XMLUtil.writeInt(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_ORG_LAYOUT, OrganigramLayout.LAYOUT, orgLay.getOrgLayout());
    XMLUtil.writeInt(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_ORG_COMPACT, OrganigramLayout.COMPACT, orgLay.getOrgCompact());
    XMLUtil.writeColor(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_BACKGROUNDCOLOR, OrganigramLayout.COLOR_BACKGROUND, orgLay.getBackgroundColor());
    XMLUtil.writeColor(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_LINECOLOR, OrganigramLayout.COLOR_LINE, orgLay.getLineColor());
    XMLUtil.writeInt(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_MARGIN_TOP, OrganigramLayout.MARGIN_TOP, orgLay.getTopMargin());
    XMLUtil.writeInt(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_MARGIN_LEFT, OrganigramLayout.MARGIN_LEFT, orgLay.getLeftMargin());
    XMLUtil.writeInt(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_MARGIN_RIGHT, OrganigramLayout.MARGIN_RIGHT, orgLay.getRightMargin());
    XMLUtil.writeInt(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_MARGIN_BOTTOM, OrganigramLayout.MARGIN_BOTTOM, orgLay.getBottomMargin());
    XMLUtil.writeBoolean(XMLUtil.ORGANIGRAM, buf, XMLUtil.ATR_TOOLTIPENABLED, OrganigramLayout.TOOLTIP_ENABLED, orgLay.isToolTipEnabled());
    XMLUtil.writeBoxLayoutAtr(XMLUtil.ORGANIGRAM, buf, organigram.getBoxLayout());
    XMLOrganigramWriter.writeMeta(XMLUtil.ORGANIGRAM, buf, organigram.getMeta());
    XMLUtil.ORGANIGRAM.openClose(buf);
    writeUnit(buf, organigram.getRoot(), !compact);
    XMLUtil.ORGANIGRAM.close(buf, false);
    return buf.toString();
  }

}
