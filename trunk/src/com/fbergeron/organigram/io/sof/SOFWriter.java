/**
 * LGPL > 3.0 Copyright (C) 2006-2010 eIrOcA (eNrIcO Croce & sImOnA Burzio)
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
package com.fbergeron.organigram.io.sof;

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
public class SOFWriter implements OrganigramWriter {

  private final static SOFXML SOF = SOFXML.getInstance();

  /** The Constant order. */
  private final static List<String> ORDER = new ArrayList<String>();
  static {
    SOFWriter.ORDER.add("id");
    SOFWriter.ORDER.add("firm");
    SOFWriter.ORDER.add("name");
    SOFWriter.ORDER.add("role");
    SOFWriter.ORDER.add("department");
    SOFWriter.ORDER.add("date");
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
    for (final String metaName : SOFWriter.ORDER) {
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
    boolean res = false;
    if ((lines != null) && (!lines.isEmpty())) {
      res = true;
      for (final Line l : lines) {
        SOFWriter.SOF.INFO.open(buf, true);
        SOFXML.writeString(SOFWriter.SOF.INFO, buf, SOFXML.ATR_TYPE, l.getType());
        SOFXML.writeString(SOFWriter.SOF.INFO, buf, SOFXML.ATR_LINK, l.getLink());
        if (l.getColor() != null) {
          SOFXML.writeColor(SOFWriter.SOF.INFO, buf, SOFXML.ATR_FONT_COLOR, null, l.getColor());
        }
        SOFXML.writeFont(SOFWriter.SOF.INFO, buf, SOFXML.ATR_FONT_NAME, SOFXML.ATR_FONT_SIZE, SOFXML.ATR_FONT_STYLE, Line.LINE_FONT, l.getFont());
        SOFWriter.SOF.INFO.openClose(buf);
        SOFWriter.SOF.INFO.writeCData(buf, l.getText());
        SOFWriter.SOF.INFO.close(buf, false);
      }
    }
    return res;
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
    boolean res;
    if (unit == null) {
      res = false;
    }
    else {
      SOFWriter.SOF.UNIT.open(buf, true);
      SOFXML.writeString(SOFWriter.SOF.UNIT, buf, SOFXML.ATR_ID, unit.getId());
      final BoxLayout boxLay = unit.getBoxLayout();
      if (boxLay != null) {
        SOFXML.writeBoxLayoutAtr(SOFWriter.SOF.UNIT, buf, boxLay);
      }
      SOFWriter.writeMeta(SOFWriter.SOF.UNIT, buf, unit.getMeta());
      boolean compact = true;
      if (writeInfo) {
        final List<Line> lines = unit.getInfo();
        if (!lines.isEmpty()) {
          if (compact) {
            SOFWriter.SOF.UNIT.openClose(buf);
            compact = false;
          }
          writeLines(buf, lines);
        }
      }
      if (unit.hasChildren()) {
        if (compact) {
          SOFWriter.SOF.UNIT.openClose(buf);
          compact = false;
        }
        for (final Unit u : unit) {
          writeUnit(buf, u, writeInfo);
        }
      }
      SOFWriter.SOF.UNIT.close(buf, compact);
      res = true;
    }
    return res;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.fbergeron.organigram.io.OrganigramWriter#writeOrganigram(com.fbergeron
   * .organigram.model.Organigram, boolean)
   */
  public String writeOrganigram(final Organigram organigram, final boolean compact) {
    final StringBuffer buf = new StringBuffer(1024);
    buf.append("<?xml version=\"1.0\"?>");
    SOFWriter.SOF.ORGANIGRAM.open(buf, true);
    final OrganigramLayout orgLay = organigram.getOrganigramLayout();
    SOFXML.writeEnum(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_ORG_LAYOUT, OrganigramLayout.DEF_LAYOUT, orgLay.getLayout());
    SOFXML.writeEnum(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_ORG_MODE, OrganigramLayout.DEF_ORGMODE, orgLay.getMode());
    SOFXML.writeBoolean(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_ORG_COMPACT, OrganigramLayout.DEF_COMPACT, orgLay.isCompact());
    SOFXML.writeColor(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_BACKGROUNDCOLOR, OrganigramLayout.COLOR_BACKGROUND, orgLay.getBackgroundColor());
    SOFXML.writeColor(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_LINECOLOR, OrganigramLayout.DEF_LINECOLOR, orgLay.getLineColor());
    SOFXML.writeEnum(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_LINEMODE, OrganigramLayout.DEF_LINEMODE, orgLay.getLineMode());
    SOFXML.writeInt(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_MARGIN_TOP, OrganigramLayout.DEF_MARGINTOP, orgLay.getTopMargin());
    SOFXML.writeInt(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_MARGIN_LEFT, OrganigramLayout.DEF_MARGINLEFT, orgLay.getLeftMargin());
    SOFXML.writeInt(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_MARGIN_RIGHT, OrganigramLayout.DEF_MARGINRIGHT, orgLay.getRightMargin());
    SOFXML.writeInt(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_MARGIN_BOTTOM, OrganigramLayout.DEF_MARGINBOTTOM, orgLay.getBottomMargin());
    SOFXML.writeBoolean(SOFWriter.SOF.ORGANIGRAM, buf, SOFXML.ATR_TOOLTIPENABLED, OrganigramLayout.DEF_TOOLTIP, orgLay.isToolTipEnabled());
    SOFXML.writeBoxLayoutAtr(SOFWriter.SOF.ORGANIGRAM, buf, organigram.getBoxLayout());
    SOFWriter.writeMeta(SOFWriter.SOF.ORGANIGRAM, buf, organigram.getMeta());
    SOFWriter.SOF.ORGANIGRAM.openClose(buf);
    writeUnit(buf, organigram.getRoot(), !compact);
    SOFWriter.SOF.ORGANIGRAM.close(buf, false);
    return buf.toString();
  }

}
