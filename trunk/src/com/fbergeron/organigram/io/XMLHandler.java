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
package com.fbergeron.organigram.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.fbergeron.organigram.model.Organigram;

/**
 * The Class XMLHandler.
 */
public class XMLHandler extends DefaultHandler implements OrganigramReader {

  /** The organigram. */
  public Organigram organigram;

  /** The data. */
  public Map<String, String> data = new HashMap<String, String>();

  /** The processors. */
  public Stack<TagProcessor> procs = new Stack<TagProcessor>();

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.OrganigramReader#getOrganigram()
   */
  public Organigram getOrganigram() {
    return organigram;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.OrganigramReader#setOrganigram(com.fbergeron.organigram.model.Organigram)
   */
  public void setOrganigram(final Organigram organigram) {
    this.organigram = organigram;
  }

  /* (non-Javadoc)
   * @see com.fbergeron.organigram.io.OrganigramReader#getData()
   */
  public Map<String, String> getData() {
    return data;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
   *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
   */
  @Override
  public void startElement(final String uri, final String localName, final String qName, final Attributes attribs) throws SAXException {
    final TagProcessor processor = TagFactory.getProcessor(qName);
    if (processor != null) {
      processor.start(this, attribs);
    }
    procs.push(processor);
  }

  /* (non-Javadoc)
   * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void endElement(final String uri, final String localName, final String qName) throws SAXException {
    procs.pop();
    final TagProcessor processor = TagFactory.getProcessor(qName);
    if (processor != null) {
      processor.end(this);
    }
  }

  /* (non-Javadoc)
   * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
   */
  @Override
  public void characters(final char[] chr, final int start, final int length) throws SAXException {
    final TagProcessor processor = procs.peek();
    if (processor != null) {
      processor.characters(this, chr, start, length);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.fbergeron.organigram.io.OrganigramReader#readOrganigram(java.net.URL)
   */
  public Organigram readOrganigram(final InputStream source) {
    organigram = null;
    try {
      final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      final SAXParser parser = parserFactory.newSAXParser();
      parser.parse(source, this);
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

}
