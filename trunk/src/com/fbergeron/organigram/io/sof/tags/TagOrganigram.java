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
import com.fbergeron.organigram.io.xml.attr.AttrAlignment;
import com.fbergeron.organigram.io.xml.attr.AttrBoolean;
import com.fbergeron.organigram.io.xml.attr.AttrBoxType;
import com.fbergeron.organigram.io.xml.attr.AttrColor;
import com.fbergeron.organigram.io.xml.attr.AttrInsets;
import com.fbergeron.organigram.io.xml.attr.AttrLayout;
import com.fbergeron.organigram.io.xml.attr.AttrLineMode;
import com.fbergeron.organigram.io.xml.attr.AttrOrgMode;
import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;

/**
 * The Class OrganigramProcessor.
 */
public class TagOrganigram extends Tag {

  public static final String ATR_LAYOUT = "layout";
  public static final String ATR_ORGMODE = "mode";
  public static final String ATR_COMPACT = "compact";
  public static final String ATR_BACKGROUND = "backgroundColor";
  public static final String ATR_LINEMODE = "lineMode";
  public static final String ATR_LINECOLOR = "lineColor";
  public static final String ATR_MARGIN = "margin";
  public static final String ATR_TOOLTIP = "isToolTipEnabled";

  public static final String ATR_BOXTYPE = "boxType";
  public static final String ATR_BOXEXPENDED = "boxExpanded";
  public static final String ATR_BOXTEXTALIGN = "boxTextAlignment";
  public static final String ATR_BOXBACKGROUND = "boxBackgroundColor";
  public static final String ATR_BOXFOREGROUND = "boxForegroundColor";
  public static final String ATR_BOXFRAMECOLOR = "boxFrameColor";
  public static final String ATR_BOXPADDING = "boxPadding";

  public transient AttrLayout aLayout = new AttrLayout(TagOrganigram.ATR_LAYOUT, OrganigramLayout.DEF_LAYOUT);
  public transient AttrOrgMode aOrgMode = new AttrOrgMode(TagOrganigram.ATR_ORGMODE, OrganigramLayout.DEF_ORGMODE);
  public transient AttrBoolean aCompact = new AttrBoolean(TagOrganigram.ATR_COMPACT, OrganigramLayout.DEF_COMPACT);
  public transient AttrColor aBackground = new AttrColor(TagOrganigram.ATR_BACKGROUND, OrganigramLayout.DEF_BACKGROUND);
  public transient AttrLineMode aLineMode = new AttrLineMode(TagOrganigram.ATR_LINEMODE, OrganigramLayout.DEF_LINEMODE);
  public transient AttrColor aLineColor = new AttrColor(TagOrganigram.ATR_LINECOLOR, OrganigramLayout.DEF_LINECOLOR);
  public transient AttrInsets aMargin = new AttrInsets(TagOrganigram.ATR_MARGIN, OrganigramLayout.DEF_MARGIN);
  public transient AttrBoolean aTooltip = new AttrBoolean(TagOrganigram.ATR_TOOLTIP, OrganigramLayout.DEF_TOOLTIP);
  // Default Box Layout
  public transient AttrBoxType aBoxType = new AttrBoxType(TagOrganigram.ATR_BOXTYPE, BoxLayout.DEF_BOXTYPE);
  public transient AttrBoolean aBoxExpanded = new AttrBoolean(TagOrganigram.ATR_BOXEXPENDED, BoxLayout.DEF_EXPANDED);
  public transient AttrAlignment aBoxTextAlignment = new AttrAlignment(TagOrganigram.ATR_BOXTEXTALIGN, BoxLayout.DEF_TEXTALIGN);
  public transient AttrColor aBoxBackground = new AttrColor(TagOrganigram.ATR_BOXBACKGROUND, BoxLayout.DEF_BACKGROUND);
  public transient AttrColor aBoxForeground = new AttrColor(TagOrganigram.ATR_BOXFOREGROUND, BoxLayout.DEF_FOREGROUND);
  public transient AttrColor aBoxFrameColor = new AttrColor(TagOrganigram.ATR_BOXFRAMECOLOR, BoxLayout.DEF_BOXFRAME);
  public transient AttrInsets aBoxPadding = new AttrInsets(TagOrganigram.ATR_BOXPADDING, BoxLayout.DEF_PADDING);

  /**
   * Instantiates a new organigram processor.
   */
  public TagOrganigram() {
    super("organigram");
    addAttribute(aLayout);
    addAttribute(aOrgMode);
    addAttribute(aCompact);
    addAttribute(aBackground);
    addAttribute(aLineMode);
    addAttribute(aLineColor);
    addAttribute(aMargin);
    addAttribute(aTooltip);
    // Default Box Layout
    addAttribute(aBoxType);
    addAttribute(aBoxExpanded);
    addAttribute(aBoxTextAlignment);
    addAttribute(aBoxBackground);
    addAttribute(aBoxForeground);
    addAttribute(aBoxFrameColor);
    addAttribute(aBoxPadding);
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.TAG#end(com.fbergeron.organigram.io.OrganigramReader)
   */
  @Override
  public void end(final OrganigramReader reader) {
    final SOFXML xor = (SOFXML) reader;
    reader.getOrganigram().setRoot(xor.rootUnit);
  }

  /**
   * Process organigram begin.
   * 
   * @param reader the reader
   * @param attribs the attributes
   */
  @Override
  public void start(final OrganigramReader reader, final Attributes attribs) {
    final Organigram organigram = new Organigram();
    reader.setOrganigram(organigram);
    parse(attribs);
    read(organigram, attribs);
  }

  public void read(final Organigram organigram, final Attributes attribs) {
    final OrganigramLayout orgLay = organigram.getOrganigramLayout();
    final BoxLayout boxLay = organigram.getBoxLayout();
    orgLay.setLayout(aLayout.getLastVal());
    orgLay.setMode(aOrgMode.getLastVal());
    orgLay.setCompact(aCompact.getLastVal());
    orgLay.setBackgroundColor(aBackground.getLastVal());
    orgLay.setLineMode(aLineMode.getLastVal());
    orgLay.setLineColor(aLineColor.getLastVal());
    orgLay.setMargin(aMargin.getLastVal());
    orgLay.setToolTipEnabled(aTooltip.getLastVal());
    // Default Box Layout
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
        organigram.setMeta(name, value);
      }
    }
  }

}