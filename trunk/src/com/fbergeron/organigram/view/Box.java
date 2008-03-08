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

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Vector;

import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Unit;

public class Box {

    public Box( Unit unit, Organigram organigram ) {
        this.unit = unit;
        this.organigram = organigram;
    }

    public Unit getUnit() {
        return( unit );
    }

    public boolean contains( Point p ) {
        if( getLocation() == null )
            return( false );

        Rectangle rect = new Rectangle( getLocation().x, getLocation().y, getWidth(), getHeight() );
        return( rect.contains( p ) );
    }

    public int getWidth() {
        return( width );
    }

    public void setWidth( int width ) {
        this.width = width;
    }

    public int getHeight() {
        return( height );
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public Box getParent() {
        return( parent );
    }

    public Box getPrevSibling() {
        if( parent == null )
            return( null );
        
        Box prevSiblingBox = null;
        for( Enumeration e = parent.getChildren(); e.hasMoreElements(); ) {
            Box sibling = (Box)e.nextElement();
            if( sibling.equals( this ) )
                return( prevSiblingBox );
            else
                prevSiblingBox = sibling;
        }

        return( null );
    }
        
    public void addChild( Box child ) {
        children.addElement( child );
        child.setParent( this );
    }

    public Enumeration getChildren() {
        return( children.elements() );
    }

    public boolean hasChildren() {
        return( !children.isEmpty() );
    }

    private void setParent( Box parent ) {
        this.parent = parent;
    }

    public int getRequiredWidth() {
        return( requiredWidth );
    }

    public void setRequiredWidth( int requiredWidth ) {
        this.requiredWidth = requiredWidth;
    }

    public Point getLocation() {
        return( location );
    }

    public void setLocation( Point location ) {
        this.location = location;
    }

    public void paint( Graphics g ) {
        // Draw the box.
        Point pos = getLocation();
        
        g.setColor( organigram.getBoxBackgroundColor() );
        g.fillRect( pos.x, pos.y, width, height );

        int y = pos.y + organigram.getBoxTopPadding();
        for( int i = 0; i < unit.getLines().length; i++ ) {
            FontMetrics fm = g.getFontMetrics();
            Line line = unit.getLines()[ i ];
            g.setColor( organigram.getBoxForegroundColor() );
            if( line.getColor() != null )
                g.setColor( line.getColor() );
            if( line.getFont() != null ) {
                g.setFont( line.getFont() );
                fm = g.getFontMetrics( line.getFont() );
            }
            int textWidth = fm.stringWidth( unit.getLines()[ i ].getText() ); 
            int x = pos.x + organigram.getBoxLeftPadding();
            if( organigram.getBoxTextAlignment() == Label.CENTER ) 
                x = pos.x + ( organigram.getBoxPreferredWidth() - textWidth ) / 2;
            else if( organigram.getBoxTextAlignment() == Label.RIGHT )
                x = pos.x + organigram.getBoxPreferredWidth() - textWidth;
            g.drawString( unit.getLines()[ i ].getText(), x, y + fm.getAscent() + fm.getLeading() );
            y += fm.getHeight();
        }

        g.setColor( organigram.getBoxFrameColor() );
        g.drawRect( pos.x, pos.y, width, height );

        // Draw the lines.
        if( hasChildren() ) {
            int horizLineX1 = -1;
            int horizLineX2 = -1;
            
            g.setColor( organigram.getLineColor() );
            int x = pos.x + getWidth() / 2;
            g.drawLine( x, pos.y + getHeight(), x, pos.y + getHeight() + organigram.getBoxBottomMargin() );
            
            for( Enumeration e = getChildren(); e.hasMoreElements(); ) {
                Box child = (Box)e.nextElement();
                Point childLocation = child.getLocation();
                x = childLocation.x + child.getWidth() / 2;
                g.drawLine( x, childLocation.y - organigram.getBoxTopMargin(), x, childLocation.y );
                
                if( horizLineX1 == -1 )
                    horizLineX1 = x;
                horizLineX2 = x;
            }
            
            y = pos.y + getHeight() + organigram.getBoxBottomMargin();
            g.drawLine( horizLineX1, y, horizLineX2, y );
        }
        
        // Draw the children boxes.
        for( Enumeration e = getChildren(); e.hasMoreElements(); ) {
            Box child = (Box)e.nextElement();
            child.paint( g );
        }
    }

    public String toString() {
        Point loc = getLocation();
        StringBuffer str = new StringBuffer();
        str.append( "(" ).append( unit ).append( ", " );
        str.append( getRequiredWidth() ).append( ", " );
        if( loc == null )
            str.append( "x=undefined, y=undefined " );
        else
            str.append( "x=" + loc.x + ", y=" + loc.y + " " );
        str.append( "[ " );
        for( Enumeration e = getChildren(); e.hasMoreElements(); ) {
            Box child = (Box)e.nextElement();
            str.append( child.toString() ).append( ", " );
        }
        str.append( " ] )" );
        return( str.toString() );
    }
        
    private Unit        unit;
    private Organigram  organigram;

    private Box         parent;
    private Vector      children = new Vector();
    
    private int         requiredWidth;
    
    private int         width;
    private int         height;

    private Point       location;

}
