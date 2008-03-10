/**
 * Copyright (C) 2006-2008 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the:
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110-1301
 *   USA
 */
package com.fbergeron.organigram.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;

public class XMLOrganigramWriter implements OrganigramWriter {

  public XMLOrganigramWriter() {
    //
  }

  final static ArrayList<String> order = new ArrayList<String>();
  static {
    XMLOrganigramWriter.order.add("id");
    XMLOrganigramWriter.order.add("firm");
    XMLOrganigramWriter.order.add("name");
    XMLOrganigramWriter.order.add("role");
    XMLOrganigramWriter.order.add("department");
    XMLOrganigramWriter.order.add("date");
  }

  public static void writeMeta(final TAG me, final StringBuffer sb, final HashMap<String, String> meta) {
    final HashSet<String> done = new HashSet<String>();
    for (final String metaName : XMLOrganigramWriter.order) {
      final String val = meta.get(metaName);
      if (val != null) {
        me.writeAttribute(sb, metaName, meta.get(metaName), false);
        done.add(metaName);
      }
    }
    for (final String metaName : meta.keySet()) {
      if (!done.contains(metaName)) {
        me.writeAttribute(sb, metaName, meta.get(metaName), false);
      }
    }
  }

  public boolean writeLines(final StringBuffer sb, final Vector<Line> lines) {
    if (lines == null) { return false; }
    if (lines.size() == 0) { return false; }
    for (final Line l : lines) {
      final TAG me = new TAG(XMLUtil.TAG_INFO);
      me.open(sb, true);
      XMLUtil.writeString(me, sb, XMLUtil.ATR_TYPE, l.getType());
      XMLUtil.writeString(me, sb, XMLUtil.ATR_LINK, l.getLink());
      if (l.getColor() != null) {
        XMLUtil.writeColor(me, sb, XMLUtil.ATR_FONT_COLOR, null, l.getColor());
      }
      XMLUtil.writeFont(me, sb, XMLUtil.ATR_FONT_NAME, XMLUtil.ATR_FONT_SIZE, XMLUtil.ATR_FONT_STYLE, Line.LINE_FONT, l.getFont());
      me.openClose(sb);
      me.writeCData(sb, l.getText());
      me.close(sb, false);
    }
    return true;
  }

  public boolean writeUnit(final StringBuffer sb, final Unit root, final boolean writeInfo) {
    if (root == null) { return false; }
    final TAG me = new TAG(XMLUtil.TAG_UNIT);
    me.open(sb, true);
    XMLUtil.writeString(me, sb, XMLUtil.ATR_ID, root.getID());
    BoxLayout bl = root.getBoxLayout();
    if (bl != null) {
      XMLUtil.writeColor(me, sb, XMLUtil.ATR_BOX_COLOR_FRAME, BoxLayout.COLOR_BOXFRAME, bl.getBoxFrameColor());
      XMLUtil.writeColor(me, sb, XMLUtil.ATR_BOX_COLOR_BACKGROUND, BoxLayout.COLOR_BOXBACKGROUND, bl.getBoxBackgroundColor());
      XMLUtil.writeColor(me, sb, XMLUtil.ATR_BOX_COLOR_FOREGROUND, BoxLayout.COLOR_BOXFOREGROUND, bl.getBoxForegroundColor());
      XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_TOP, BoxLayout.PADDING_BOXTOP, bl.getBoxTopPadding());
      XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_LEFT, BoxLayout.PADDING_BOXLEFT, bl.getBoxLeftPadding());
      XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_RIGHT, BoxLayout.PADDING_BOXRIGHT, bl.getBoxRightPadding());
      XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_BOTTOM, BoxLayout.PADDING_BOXBOTTOM, bl.getBoxBottomPadding());
      XMLUtil.writeAlign(me, sb, XMLUtil.ATR_BOX_TEXT_ALIGMENT, BoxLayout.ALIGN_BOXTEXT, bl.getBoxTextAlignment());
    }
    XMLOrganigramWriter.writeMeta(me, sb, root.getMeta());
    boolean compact = true;
    if (writeInfo) {
      final Vector<Line> lines = root.getInfo();
      if (lines.size() > 0) {
        if (compact) {
          me.openClose(sb);
          compact = false;
        }
        writeLines(sb, lines);
      }
    }
    if (root.hasChildren()) {
      if (compact) {
        me.openClose(sb);
        compact = false;
      }
      final TAG childs = new TAG(XMLUtil.TAG_CHILDS);
      childs.open(sb, false);
      for (final Unit u : root) {
        writeUnit(sb, u, writeInfo);
      }
      childs.close(sb, false);
    }
    me.close(sb, compact);
    return true;
  }

  public String writeOrganigram(final Organigram organigram, boolean compact) {
    final StringBuffer sb = new StringBuffer(1024);
    sb.append("<?xml version=\"1.0\"?>");
    final TAG me = new TAG(XMLUtil.TAG_ORGANIGRAM);
    me.open(sb, true);
    BoxLayout bl = organigram.getBoxLayout();
    OrganigramLayout ol = organigram.getOrganigramLayout();
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_ORG_MODE, OrganigramLayout.ORGMODE, ol.getOrgMode());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_ORG_LAYOUT, OrganigramLayout.LAYOUT, ol.getOrgLayout());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_ORG_COMPACT, OrganigramLayout.COMPACT, ol.getOrgCompact());
    XMLUtil.writeColor(me, sb, XMLUtil.ATR_BACKGROUNDCOLOR, OrganigramLayout.COLOR_BACKGROUND, ol.getBackgroundColor());
    XMLUtil.writeColor(me, sb, XMLUtil.ATR_LINECOLOR, OrganigramLayout.COLOR_LINE, ol.getLineColor());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_MARGIN_TOP, OrganigramLayout.MARGIN_BOXTOP, ol.getBoxTopMargin());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_MARGIN_LEFT, OrganigramLayout.MARGIN_BOXLEFT, ol.getBoxLeftMargin());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_MARGIN_RIGHT, OrganigramLayout.MARGIN_BOXRIGHT, ol.getBoxRightMargin());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_MARGIN_BOTTOM, OrganigramLayout.MARGIN_BOXBOTTOM, ol.getBoxBottomMargin());
    XMLUtil.writeBoolean(me, sb, XMLUtil.ATR_ISTOOLTIPENABLED, OrganigramLayout.ISTOOLTIPENABLED, ol.isToolTipEnabled());
    XMLUtil.writeColor(me, sb, XMLUtil.ATR_BOX_COLOR_FRAME, BoxLayout.COLOR_BOXFRAME, bl.getBoxFrameColor());
    XMLUtil.writeColor(me, sb, XMLUtil.ATR_BOX_COLOR_BACKGROUND, BoxLayout.COLOR_BOXBACKGROUND, bl.getBoxBackgroundColor());
    XMLUtil.writeColor(me, sb, XMLUtil.ATR_BOX_COLOR_FOREGROUND, BoxLayout.COLOR_BOXFOREGROUND, bl.getBoxForegroundColor());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_TOP, BoxLayout.PADDING_BOXTOP, bl.getBoxTopPadding());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_LEFT, BoxLayout.PADDING_BOXLEFT, bl.getBoxLeftPadding());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_RIGHT, BoxLayout.PADDING_BOXRIGHT, bl.getBoxRightPadding());
    XMLUtil.writeInt(me, sb, XMLUtil.ATR_BOX_PADDING_BOTTOM, BoxLayout.PADDING_BOXBOTTOM, bl.getBoxBottomPadding());
    XMLUtil.writeAlign(me, sb, XMLUtil.ATR_BOX_TEXT_ALIGMENT, BoxLayout.ALIGN_BOXTEXT, bl.getBoxTextAlignment());
    XMLOrganigramWriter.writeMeta(me, sb, organigram.getMeta());
    me.openClose(sb);
    writeUnit(sb, organigram.getRoot(), !compact);
    me.close(sb, false);
    return sb.toString();
  }

}
