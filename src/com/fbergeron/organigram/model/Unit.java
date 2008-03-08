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

package com.fbergeron.organigram.model;

import java.util.Enumeration;
import java.util.Vector;

public class Unit {

    public Unit() {
    }

    public Unit( Line[] lines ) {
        this.lines = lines;
    }

    public Line[] getLines() {
        return( lines );
    }

    public void setLines( Line[] lines ) {
        this.lines = lines;
    }

    public void addLine( Line line ) {
        if( lines == null )
            lines = new Line[] { line };
        else {
            Line[] newLines = new Line[ lines.length + 1 ];
            int i = 0;
            for( ; i < lines.length; i++ )
                newLines[ i ] = lines[ i ];
            newLines[ i ] = line;
            lines = newLines;
        }
    }

    public String getLink() {
        return( link );
    }

    public void setLink( String link ) {
        this.link = link;
    }

    public Unit getParent() {
        return( parent );
    }
        
    public void addChild( Unit child ) {
        children.addElement( child );
        child.setParent( this );
    }

    public Enumeration getChildren() {
        return( children.elements() );
    }

    public boolean hasChildren() {
        return( !children.isEmpty() );
    }

    public int getChildrenCount() {
        return( children.size() );
    }

    public String toString() {
        if( lines == null || lines.length == 0 )
            return( "null" );
        return( lines[ 0 ].getText() );
    }

    private void setParent( Unit parent ) {
        this.parent = parent;
    }

    private Unit        parent;
    private Vector      children = new Vector();

    private Line[]      lines;
    private String      link;

}
