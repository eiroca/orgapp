/**
 * Copyright (C) 2005 Frédéric Bergeron (fbergeron@users.sourceforge.net)
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

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fbergeron.organigram.model.BoxLayout;
import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Organigram;
import com.fbergeron.organigram.model.OrganigramLayout;
import com.fbergeron.organigram.model.Unit;

public class XMLOrganigramReader extends DefaultHandler implements OrganigramReader {

  private Organigram organigram;
  private final Stack<Unit> parentUnits = new Stack<Unit>();
  private Unit rootUnit;
  private Unit newUnit;
  private Line newLine;
  private StringBuffer newText;
  private final Stack<String> tagStack = new Stack<String>();

  public XMLOrganigramReader() {
    //
  }

  public Organigram readOrganigram(final URL path) {
    organigram = null;
    try {
      final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      final SAXParser parser = parserFactory.newSAXParser();
      parser.parse(new InputSource(path.openStream()), this);
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
    catch (final ParserConfigurationException e) {
      e.printStackTrace();
    }
    catch (final SAXException e) {
      e.printStackTrace();
    }
    return organigram;
  }

  public void processOrganigramBegin(final Attributes attribs) {
    organigram = new Organigram();
    BoxLayout bl = organigram.getBoxLayout();
    OrganigramLayout ol = organigram.getOrganigramLayout();
    String name;
    String value;
    for (int i = 0; i < attribs.getLength(); i++) {
      name = attribs.getQName(i);
      value = attribs.getValue(i);
      if (name.equals(XMLUtil.ATR_ORG_MODE)) {
        ol.setOrgMode(XMLUtil.readInt(value, ol.getOrgMode()));
      }
      else if (name.equals(XMLUtil.ATR_ORG_LAYOUT)) {
        ol.setOrgLayout(XMLUtil.readInt(value, ol.getOrgLayout()));
      }
      else if (name.equals(XMLUtil.ATR_ORG_COMPACT)) {
        ol.setOrgCompact(XMLUtil.readInt(value, ol.getOrgCompact()));
      }
      else if (name.equals(XMLUtil.ATR_BACKGROUNDCOLOR)) {
        ol.setBackgroundColor(XMLUtil.readColor(value, ol.getBackgroundColor()));
      }
      else if (name.equals(XMLUtil.ATR_LINECOLOR)) {
        ol.setLineColor(XMLUtil.readColor(value, ol.getLineColor()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_RIGHT)) {
        ol.setBoxRightMargin(XMLUtil.readInt(value, ol.getBoxRightMargin()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_LEFT)) {
        ol.setBoxLeftMargin(XMLUtil.readInt(value, ol.getBoxLeftMargin()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_TOP)) {
        ol.setBoxTopMargin(XMLUtil.readInt(value, ol.getBoxTopMargin()));
      }
      else if (name.equals(XMLUtil.ATR_MARGIN_BOTTOM)) {
        ol.setBoxBottomMargin(XMLUtil.readInt(value, ol.getBoxBottomMargin()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_RIGHT)) {
        bl.setBoxRightPadding(XMLUtil.readInt(value, bl.getBoxRightPadding()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_LEFT)) {
        bl.setBoxLeftPadding(XMLUtil.readInt(value, bl.getBoxLeftPadding()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_TOP)) {
        bl.setBoxTopPadding(XMLUtil.readInt(value, bl.getBoxTopPadding()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_BOTTOM)) {
        bl.setBoxBottomPadding(XMLUtil.readInt(value, bl.getBoxBottomPadding()));
      }
      else if (name.equals(XMLUtil.ATR_ISTOOLTIPENABLED)) {
        ol.setToolTipEnabled(XMLUtil.readBoolean(value, ol.isToolTipEnabled()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_COLOR_FRAME)) {
        bl.setBoxFrameColor(XMLUtil.readColor(value, bl.getBoxFrameColor()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_COLOR_BACKGROUND)) {
        bl.setBoxBackgroundColor(XMLUtil.readColor(value, bl.getBoxBackgroundColor()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_COLOR_FOREGROUND)) {
        bl.setBoxForegroundColor(XMLUtil.readColor(value, bl.getBoxForegroundColor()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_TEXT_ALIGMENT)) {
        bl.setBoxTextAlignment(XMLUtil.readAligment(value, bl.getBoxTextAlignment()));
      }
      else {
        organigram.setMeta(name, value);
      }
    }
  }

  public BoxLayout getBoxLayout(Unit u) {
    BoxLayout bl = u.getBoxLayout();
    if (bl == null) {
      BoxLayout obl = organigram.getBoxLayout();
      bl = new BoxLayout();
      bl.setBoxBackgroundColor(obl.getBoxBackgroundColor());
      bl.setBoxForegroundColor(obl.getBoxForegroundColor());
      bl.setBoxFrameColor(obl.getBoxFrameColor());
      bl.setBoxTextAlignment(obl.getBoxTextAlignment());
      bl.setBoxLeftPadding(obl.getBoxLeftPadding());
      bl.setBoxRightPadding(obl.getBoxRightPadding());
      bl.setBoxTopPadding(obl.getBoxTopPadding());
      bl.setBoxBottomPadding(obl.getBoxBottomPadding());

      u.setBoxLayout(bl);
    }
    return bl;
  }

  public void processUnitBegin(final Attributes attribs) {
    newUnit = new Unit();
    BoxLayout bl;
    if (rootUnit == null) {
      rootUnit = newUnit;
    }
    else {
      final Unit parentUnit = parentUnits.peek();
      parentUnit.addChild(newUnit);
    }
    String name;
    String value;
    for (int i = 0; i < attribs.getLength(); i++) {
      name = attribs.getQName(i);
      value = attribs.getValue(i);
      if (name.equals(XMLUtil.ATR_ID)) {
        newUnit.setID(value);
      }
      else if (name.equals(XMLUtil.ATR_BOX_COLOR_FRAME)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxFrameColor(XMLUtil.readColor(value, bl.getBoxFrameColor()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_COLOR_BACKGROUND)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxBackgroundColor(XMLUtil.readColor(value, bl.getBoxBackgroundColor()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_COLOR_FOREGROUND)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxForegroundColor(XMLUtil.readColor(value, bl.getBoxForegroundColor()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_RIGHT)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxRightPadding(XMLUtil.readInt(value, bl.getBoxRightPadding()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_LEFT)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxLeftPadding(XMLUtil.readInt(value, bl.getBoxLeftPadding()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_TOP)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxTopPadding(XMLUtil.readInt(value, bl.getBoxTopPadding()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_PADDING_BOTTOM)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxBottomPadding(XMLUtil.readInt(value, bl.getBoxBottomPadding()));
      }
      else if (name.equals(XMLUtil.ATR_BOX_TEXT_ALIGMENT)) {
        bl = getBoxLayout(newUnit);
        bl.setBoxTextAlignment(XMLUtil.readAligment(value, bl.getBoxTextAlignment()));
      }
      else {
        newUnit.setMeta(name, value);
      }
    }
  }

  public void processLineBegin(final Attributes attribs) {
    final String type = attribs.getValue(XMLUtil.ATR_TYPE);
    final String realFontName = attribs.getValue(XMLUtil.ATR_FONT_NAME);
    final int realFontSize = XMLUtil.readInt(attribs.getValue(XMLUtil.ATR_FONT_SIZE), 12);
    final int realFontStyle = XMLUtil.readFontStyle(attribs.getValue(XMLUtil.ATR_FONT_STYLE), Font.PLAIN);
    newLine = new Line();
    newText = new StringBuffer(99);
    newLine.setType(type);
    newLine.setColor(XMLUtil.readColor(attribs.getValue(XMLUtil.ATR_FONT_COLOR), newLine.getColor()));
    newLine.setFont(new Font(realFontName, realFontStyle, realFontSize));
    newLine.setLink(attribs.getValue(XMLUtil.ATR_LINK));
  }

  @Override
  public void characters(final char[] ch, final int start, final int length) throws SAXException {
    final String str = new String(ch, start, length);
    if (XMLUtil.TAG_INFO.equals(tagStack.peek())) {
      newText.append(str);
    }
  }

  @Override
  public void startElement(final String uri, final String localName, final String qName, final Attributes attribs) throws SAXException {
    tagStack.push(qName);
    if (XMLUtil.TAG_ORGANIGRAM.equals(qName)) {
      processOrganigramBegin(attribs);
    }
    else if (XMLUtil.TAG_UNIT.equals(qName)) {
      processUnitBegin(attribs);
    }
    else if (XMLUtil.TAG_CHILDS.equals(qName)) {
      parentUnits.push(newUnit);
    }
    else if (XMLUtil.TAG_INFO.equals(qName)) {
      processLineBegin(attribs);
    }
  }

  @Override
  public void endElement(final String uri, final String localName, final String qName) throws SAXException {
    if (XMLUtil.TAG_ORGANIGRAM.equals(qName)) {
      organigram.setRoot(rootUnit);
    }
    else if (XMLUtil.TAG_CHILDS.equals(qName)) {
      newUnit = parentUnits.pop();
    }
    else if (XMLUtil.TAG_UNIT.equals(qName)) {
      //
    }
    else if (XMLUtil.TAG_INFO.equals(qName)) {
      newLine.setText(newText.toString().trim());
      newUnit.addInfo(newLine);
      newLine = null;
    }
    tagStack.pop();
  }

}
