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

package com.fbergeron.organigram.view;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JPanel;

import com.fbergeron.organigram.model.Unit;

public class Organigram extends JPanel {

    public Organigram() {
        addMouseMotionListener( linkManager );
        addMouseListener( linkManager );
        layoutManager = new LayoutManager( this );
    }

    public Organigram( Unit rootUnit ) {
        super();
        setRootUnit( rootUnit );
    }

    public Box getRootBox() {
        if( vBox == null )
            return( null );
        return( (Box)vBox.firstElement() );
    }

    public Unit getRootUnit() {
        if( root == null )
            return( null );
        return( root.getUnit() );
    }

    public void setRootUnit( Unit rootUnit ) {
        vBox = new Vector();
        this.root = initUnitTreeRec( rootUnit );
    }

    public Color getBackgroundColor() {
        return( backgroundColor );
    }

    public void setBackgroundColor( Color backgroundColor ) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBoxFrameColor() {
        return( boxFrameColor );
    }

    public void setBoxFrameColor( Color boxFrameColor ) {
        this.boxFrameColor = boxFrameColor;
    }

    public Color getBoxBackgroundColor() {
        return( boxBackgroundColor );
    }

    public void setBoxBackgroundColor( Color boxBackgroundColor ) {
        this.boxBackgroundColor = boxBackgroundColor;
    }

    public Color getBoxForegroundColor() {
        return( boxForegroundColor );
    }

    public void setBoxForegroundColor( Color boxForegroundColor ) {
        this.boxForegroundColor = boxForegroundColor;
    }

    public Color getLineColor() {
        return( lineColor );
    }

    public void setLineColor( Color lineColor ) {
        this.lineColor = lineColor;
    }

    public int getBoxRightMargin() {
        return( boxRightMargin );
    }

    public void setBoxRightMargin( int boxRightMargin ) {
        this.boxRightMargin = boxRightMargin;
    }

    public int getBoxLeftMargin() {
        return( boxLeftMargin );
    }

    public void setBoxLeftMargin( int boxLeftMargin ) {
        this.boxLeftMargin = boxLeftMargin;
    }

    public int getBoxTopMargin() {
        return( boxTopMargin );
    }

    public void setBoxTopMargin( int boxTopMargin ) {
        this.boxTopMargin = boxTopMargin;
    }

    public int getBoxBottomMargin() {
        return( boxBottomMargin );
    }

    public void setBoxBottomMargin( int boxBottomMargin ) {
        this.boxBottomMargin = boxBottomMargin;
    }

    public int getBoxRightPadding() {
        return( boxRightPadding );
    }

    public void setBoxRightPadding( int boxRightPadding ) {
        this.boxRightPadding = boxRightPadding;
    }

    public int getBoxLeftPadding() {
        return( boxLeftPadding );
    }

    public void setBoxLeftPadding( int boxLeftPadding ) {
        this.boxLeftPadding = boxLeftPadding;
    }

    public int getBoxTopPadding() {
        return( boxTopPadding );
    }

    public void setBoxTopPadding( int boxTopPadding ) {
        this.boxTopPadding = boxTopPadding;
    }

    public int getBoxBottomPadding() {
        return( boxBottomPadding );
    }

    public void setBoxBottomPadding( int boxBottomPadding ) {
        this.boxBottomPadding = boxBottomPadding;
    }

    public int getBoxTextAlignment() {
        return( boxTextAlignment );
    }
    
    public void setBoxTextAlignment( int boxTextAlignment ) {
        this.boxTextAlignment = boxTextAlignment;
    }
    
    public Dimension getPreferredSize() {
        return( preferredSize );
    }

    public void setPreferredSize( Dimension preferredSize ) {
        this.preferredSize = preferredSize;
        revalidate();
    }

    public int getBoxPreferredWidth() {
        return( layoutManager.getBoxPreferredWidth() );
    }

    public boolean isToolTipEnabled() {
        return( isToolTipEnabled );
    }

    public void setToolTipEnabled( boolean isToolTipEnabled ) {
        this.isToolTipEnabled = isToolTipEnabled;
    }

    public String getToolTipText( MouseEvent event ) {
        if( isToolTipEnabled )
            return( super.getToolTipText( event ) );
        return( null );
    }

    public void paint( Graphics g ) {
        Dimension dim = this.getSize();
       
        // Draw background.
        g.setColor( getBackgroundColor() );
        g.fillRect( 0, 0, dim.width, dim.height );

        if( isLayoutNeeded ) {
            layoutManager.layoutBoxes( g );
            isLayoutNeeded = false;
        }

        root.paint( g );
    }

    public String toString() {
        if( root == null )
            return( "" );
        else
            return( root.toString() );
    }

    public void showPage( String pageUrl ) {
        AppletContext appletCtxt = getAppletContext();
        if( appletCtxt != null ) {
            try {
                URL url = new URL( pageUrl );
                appletCtxt.showDocument( url );
            }
            catch( MalformedURLException malformedUrlException ) {
                System.err.println( malformedUrlException );
            }
        }
    }
   
    public Enumeration getBoxes() {
        if( vBox == null )
            return( null );
        else
            return( vBox.elements() );
    }
    
    private Box initUnitTreeRec( Unit unit ) {
        Box box = new Box( unit, this );
        vBox.addElement( box );
        for( Enumeration e = unit.getChildren(); e.hasMoreElements(); ) {
            Unit child = (Unit)e.nextElement();
            box.addChild( initUnitTreeRec( child ) );
        }
        return( box );
    }
        
    private AppletContext getAppletContext() {
        Container container = getParent();
        while( container != null ) {
            if( container instanceof Applet ) {
                Applet applet = (Applet)container;
                return( applet.getAppletContext() );
            }
            container = container.getParent();
        }
        return( null );
    }

    private Box             root;

    private int             boxWidth;
    private int             boxHeight;

    private Color           backgroundColor     = Color.white;
    private Color           boxFrameColor       = Color.black;
    private Color           boxBackgroundColor  = Color.lightGray;
    private Color           boxForegroundColor  = Color.blue;
    private Color           lineColor           = Color.black;

    private int             boxRightMargin      = 6;
    private int             boxLeftMargin       = 6;
    private int             boxTopMargin        = 6;
    private int             boxBottomMargin     = 6;
    
    private int             boxRightPadding     = 4;
    private int             boxLeftPadding      = 4;
    private int             boxTopPadding       = 4;
    private int             boxBottomPadding    = 4;

    private int             boxTextAlignment    = Label.CENTER;

    private boolean         isToolTipEnabled;

    private Dimension       preferredSize       = new Dimension( 100, 100 );

    private Vector          vBox;

    private boolean         isLayoutNeeded      = true;
    private LayoutManager   layoutManager;

    private LinkManager     linkManager         = new LinkManager();

    class LinkManager extends MouseAdapter implements MouseMotionListener {

        public void mousePressed( MouseEvent event ) {
            if( currentLink != null )
                showPage( currentLink );
        }

        public void mouseDragged( MouseEvent event ) {
        }

        public void mouseMoved( MouseEvent event ) {
            if( vBox != null ) {
                for( Enumeration e = vBox.elements(); e.hasMoreElements(); ) {
                    Box box = (Box)e.nextElement();
                    if( box.contains( event.getPoint() ) ) {
                        Unit unit = box.getUnit();
                        String link = unit.getLink();
                        if( link != null ) {
                            currentLink = link;
                            Organigram.this.setToolTipText( currentLink );
                            Organigram.this.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
                            return;
                        }
                    }
                }
                currentLink = null;
                Organigram.this.setToolTipText( null );
                Organigram.this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) ); 
            }
        }

        private String currentLink;

    }

}
