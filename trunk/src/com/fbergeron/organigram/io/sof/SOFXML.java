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
package com.fbergeron.organigram.io.sof;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.fbergeron.organigram.io.OrganigramReader;
import com.fbergeron.organigram.io.OrganigramWriter;
import com.fbergeron.organigram.io.sof.tags.TagInfo;
import com.fbergeron.organigram.io.sof.tags.TagOrganigram;
import com.fbergeron.organigram.io.sof.tags.TagUnit;
import com.fbergeron.organigram.io.xml.XMLHandler;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;

/**
 * The Class XMLUtil.
 */
public final class SOFXML extends XMLHandler implements OrganigramReader, OrganigramWriter {

  /** The organigram Tag. */
  public final transient TagOrganigram tOrganigram = new TagOrganigram();
  
  /** The unit Tag. */
  public final transient TagUnit tUnit = new TagUnit();
  
  /** The info Tag. */
  public final transient TagInfo tInfo = new TagInfo();

  /** The parent units. */
  public final Stack<Unit> parentUnits = new Stack<Unit>();

  /** The root unit. */
  public transient Unit rootUnit;

  /** The new unit. */
  public transient Unit newUnit;

  /** The new line. */
  public transient Line newLine;

  /** The Constant order. */
  private final static List<String> ORDER = new ArrayList<String>();

  static {
    SOFXML.ORDER.add("id");
    SOFXML.ORDER.add("firm");
    SOFXML.ORDER.add("name");
    SOFXML.ORDER.add("role");
    SOFXML.ORDER.add("department");
    SOFXML.ORDER.add("date");
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
    boolean res;
    if ((lines == null) || (lines.isEmpty())) {
      res = false;
    }
    else {
      res = true;
      for (final Line l : lines) {
        tInfo.open(buf, true);
        tInfo.aType.setLastVal(l.getType());
        tInfo.aLink.setLastVal(l.getLink());
        tInfo.aFontInfo.setLastVal(l.getFontInfo());
        tInfo.write(buf);
        tInfo.openClose(buf);
        tInfo.writeCData(buf, l.getText());
        tInfo.close(buf, false);
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
      tUnit.open(buf, true);
      tUnit.aId.setLastVal(unit.getId());
      final BoxLayout boxLay = unit.getBoxLayout();
      tUnit.aBoxType.setLastVal(boxLay.getType(false));
      tUnit.aBoxExpanded.setLastVal(boxLay.getExpanded(false));
      tUnit.aBoxTextAlignment.setLastVal(boxLay.getTextAlignment(false));
      tUnit.aBoxBackground.setLastVal(boxLay.getBackgroundColor(false));
      tUnit.aBoxForeground.setLastVal(boxLay.getForegroundColor(false));
      tUnit.aBoxFrameColor.setLastVal(boxLay.getFrameColor(false));
      tUnit.aBoxPadding.setLastVal(boxLay.getPadding(false));
      tUnit.write(buf);
      tUnit.writeMeta(buf, unit.getMeta(), SOFXML.ORDER);
      boolean compact = true;
      if (writeInfo) {
        final List<Line> lines = unit.getInfo();
        if (!lines.isEmpty()) {
          if (compact) {
            tUnit.openClose(buf);
            compact = false;
          }
          writeLines(buf, lines);
        }
      }
      if (unit.hasChildren()) {
        if (compact) {
          tUnit.openClose(buf);
          compact = false;
        }
        for (final Unit u : unit) {
          writeUnit(buf, u, writeInfo);
        }
      }
      tUnit.close(buf, compact);
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
  @Override
  public String writeOrganigram(final Organigram organigram, final boolean compact) {
    final StringBuffer buf = new StringBuffer(1024);
    buf.append("<?xml version=\"1.0\"?>");
    tOrganigram.open(buf, true);
    final OrganigramLayout orgLay = organigram.getOrganigramLayout();
    final BoxLayout boxLay = organigram.getBoxLayout();
    tOrganigram.aLayout.setLastVal(orgLay.getLayout());
    tOrganigram.aOrgMode.setLastVal(orgLay.getMode());
    tOrganigram.aCompact.setLastVal(orgLay.isCompact());
    tOrganigram.aFlipped.setLastVal(orgLay.isFlipped());
    tOrganigram.aBackground.setLastVal(orgLay.getBackgroundColor());
    tOrganigram.aLineMode.setLastVal(orgLay.getLineMode());
    tOrganigram.aLineColor.setLastVal(orgLay.getLineColor());
    tOrganigram.aMargin.setLastVal(orgLay.getMargin());
    tOrganigram.aTooltip.setLastVal(orgLay.isToolTipEnabled());
    tOrganigram.aBoxType.setLastVal(boxLay.getType(false));
    tOrganigram.aBoxExpanded.setLastVal(boxLay.getExpanded(false));
    tOrganigram.aBoxTextAlignment.setLastVal(boxLay.getTextAlignment(false));
    tOrganigram.aBoxBackground.setLastVal(boxLay.getBackgroundColor(false));
    tOrganigram.aBoxForeground.setLastVal(boxLay.getForegroundColor(false));
    tOrganigram.aBoxFrameColor.setLastVal(boxLay.getFrameColor(false));
    tOrganigram.aBoxPadding.setLastVal(boxLay.getPadding(false));
    tOrganigram.write(buf);
    tOrganigram.writeMeta(buf, organigram.getMeta(), SOFXML.ORDER);
    tOrganigram.openClose(buf);
    writeUnit(buf, organigram.getRoot(), !compact);
    tOrganigram.close(buf, false);
    return buf.toString();
  }

}
