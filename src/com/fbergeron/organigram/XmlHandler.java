/* 
 * Copyright (C) 2005  Frédéric Bergeron (fbergeron@users.sourceforge.net)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package com.fbergeron.organigram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.net.URL;
import java.io.IOException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Unit;
import com.fbergeron.organigram.view.Organigram;

public class XmlHandler extends DefaultHandler {

    public XmlHandler( URL xmlSourceUrl ) {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse( new InputSource( xmlSourceUrl.openStream() ), this );        
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
        catch( ParserConfigurationException e ) {
            e.printStackTrace();
        }
        catch( SAXException e ) {
            e.printStackTrace();
        }
    }

    public Organigram getOrganigram() {
        return( organigram );
    }

    public void startElement( String uri, String localName, String qName, Attributes attribs ) throws SAXException {
        tagStack.push( qName );

        if( "organigram".equals( qName ) ) {
            organigram = new Organigram();

            String backgroundColor = attribs.getValue( "backgroundColor" );
            if( backgroundColor != null ) {
                try {
                    Color color = Util.parseColor( backgroundColor );
                    organigram.setBackgroundColor( color );
                }
                catch( ParseException parseException ) {
                    System.err.println( parseException.toString() );
                }
            }
            
            String boxFrameColor = attribs.getValue( "boxFrameColor" );
            if( boxFrameColor != null ) {
                try {
                    Color color = Util.parseColor( boxFrameColor );
                    organigram.setBoxFrameColor( color );
                }
                catch( ParseException parseException ) {
                    System.err.println( parseException.toString() );
                }
            }
            
            String boxBackgroundColor = attribs.getValue( "boxBackgroundColor" );
            if( boxBackgroundColor != null ) {
                try {
                    Color color = Util.parseColor( boxBackgroundColor );
                    organigram.setBoxBackgroundColor( color );
                }
                catch( ParseException parseException ) {
                    System.err.println( parseException.toString() );
                }
            }
            
            String boxForegroundColor = attribs.getValue( "boxForegroundColor" );
            if( boxForegroundColor != null ) {
                try {
                    Color color = Util.parseColor( boxForegroundColor );
                    organigram.setBoxForegroundColor( color );
                }
                catch( ParseException parseException ) {
                    System.err.println( parseException.toString() );
                }
            }
            
            String lineColor = attribs.getValue( "lineColor" );
            if( lineColor != null ) {
                try {
                    Color color = Util.parseColor( lineColor );
                    organigram.setLineColor( color );
                }
                catch( ParseException parseException ) {
                    System.err.println( parseException.toString() );
                }
            }
            
            String boxRightMargin = attribs.getValue( "boxRightMargin" );
            if( boxRightMargin != null ) {
                try {
                    int margin = Integer.parseInt( boxRightMargin );
                    organigram.setBoxRightMargin( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }
            
            String boxLeftMargin = attribs.getValue( "boxLeftMargin" );
            if( boxLeftMargin != null ) {
                try {
                    int margin = Integer.parseInt( boxLeftMargin );
                    organigram.setBoxLeftMargin( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }
            
            String boxTopMargin = attribs.getValue( "boxTopMargin" );
            if( boxTopMargin != null ) {
                try {
                    int margin = Integer.parseInt( boxTopMargin );
                    organigram.setBoxTopMargin( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }
            
            String boxBottomMargin = attribs.getValue( "boxBottomMargin" );
            if( boxBottomMargin != null ) {
                try {
                    int margin = Integer.parseInt( boxBottomMargin );
                    organigram.setBoxBottomMargin( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }

            String boxRightPadding = attribs.getValue( "boxRightPadding" );
            if( boxRightPadding != null ) {
                try {
                    int margin = Integer.parseInt( boxRightPadding );
                    organigram.setBoxRightPadding( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }
            
            String boxLeftPadding = attribs.getValue( "boxLeftPadding" );
            if( boxLeftPadding != null ) {
                try {
                    int margin = Integer.parseInt( boxLeftPadding );
                    organigram.setBoxLeftPadding( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }
            
            String boxTopPadding = attribs.getValue( "boxTopPadding" );
            if( boxTopPadding != null ) {
                try {
                    int margin = Integer.parseInt( boxTopPadding );
                    organigram.setBoxTopPadding( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }
            
            String boxBottomPadding = attribs.getValue( "boxBottomPadding" );
            if( boxBottomPadding != null ) {
                try {
                    int margin = Integer.parseInt( boxBottomPadding );
                    organigram.setBoxBottomPadding( margin );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException.toString() );
                }
            }

            String boxTextAlignment = attribs.getValue( "boxTextAlignment" );
            if( boxTextAlignment != null ) {
                if( "center".equals( boxTextAlignment ) )
                    organigram.setBoxTextAlignment( Label.CENTER );
                else if( "left".equals( boxTextAlignment ) )
                    organigram.setBoxTextAlignment( Label.LEFT );
                else if( "right".equals( boxTextAlignment ) )
                    organigram.setBoxTextAlignment( Label.RIGHT );
                else
                    System.err.println( "Unsupported alignment : " + boxTextAlignment );
            }

            String isToolTipEnabled = attribs.getValue( "isToolTipEnabled" );
            if( "true".equals( isToolTipEnabled ) )
                organigram.setToolTipEnabled( true );
        }
        else if( "unit".equals( qName ) ) {
            newUnit = new Unit();
            if( rootUnit == null )
                rootUnit = newUnit;
            else {
                Unit parentUnit = (Unit)parentUnits.peek();
                parentUnit.addChild( newUnit );
            }
            
            String link = attribs.getValue( "link" );
            if( link != null )
                newUnit.setLink( link );
        }
        else if( "subUnits".equals( qName ) ) {
            parentUnits.push( newUnit );
        }
        else if( "line".equals( qName ) ) {
            newLine = new Line();
            newText = new StringBuffer();
            String textColor = attribs.getValue( "color" );
            if( textColor != null ) {
                try {
                    Color color = Util.parseColor( textColor );
                    newLine.setColor( color );
                }
                catch( ParseException parseException ) {
                    System.err.println( parseException.toString() );
                }
            }
            String fontName = attribs.getValue( "fontName" );
            String fontStyle = attribs.getValue( "fontStyle" );
            String fontSize = attribs.getValue( "fontSize" );

            String realFontName = fontName;
            // We could check if the provided fontName is among
            // available fonts and print an error message if it's not.
            
            int realFontStyle = Font.PLAIN;
            if( fontStyle != null ) {
                if( "Bold".equals( fontStyle ) )
                    realFontStyle = Font.BOLD;    
                else if( "Italic".equals( fontStyle ) )
                    realFontStyle = Font.ITALIC;
                else if( "Bold+Italic".equals( fontStyle ) )
                    realFontStyle = Font.BOLD | Font.ITALIC;
            }

            int realFontSize = 12;
            if( fontSize != null ) {
                try {
                    realFontSize = Integer.parseInt( fontSize );
                }
                catch( NumberFormatException numberFormatException ) {
                    System.err.println( numberFormatException );
                }
            }
                
            newLine.setFont( new Font( realFontName, realFontStyle, realFontSize ) );
            String link = attribs.getValue( "link" );
            if( link != null )
                newLine.setLink( link );
        }
        //System.out.println( getPath() );
    }

    public void characters( char[] ch, int start, int length ) throws SAXException {
        String str = new String( ch, start, length );

        if( "line".equals( tagStack.peek() ) ) {
            newText.append( str.trim() );
        }
    }

    public void endElement( String uri, String localName, String qName ) throws SAXException {
        if( "organigram".equals( qName ) ) 
            organigram.setRootUnit( rootUnit );
        else if( "subUnits".equals( qName ) ) {
            parentUnits.pop();
        }
        else if( "line".equals( qName ) ) {
            newLine.setText( newText.toString() );
            newUnit.addLine( newLine );
            newLine = null;
        }

        tagStack.pop();
    }

    private String getPath() {
        StringBuffer strPath = new StringBuffer();
        for( Enumeration e = tagStack.elements(); e.hasMoreElements(); )
            strPath.append( e.nextElement().toString() ).append( "|" );
        return( strPath.toString() );
    }

    private Organigram      organigram;
    private Stack           parentUnits     = new Stack();

    private Unit            rootUnit;
    private Unit            newUnit;
    private Line            newLine;
    private StringBuffer    newText;

    private Stack           tagStack        = new Stack();
   
}
