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

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Enumeration;

import com.fbergeron.organigram.model.Line;
import com.fbergeron.organigram.model.Unit;

public class LayoutManager {

    public LayoutManager( Organigram organigram ) {
        this.organigram = organigram;
    }

    public void layoutBoxes( Graphics g ) {
        this.g = g;
        Dimension prefSize = new Dimension( getOrganigramPreferredWidth(), getOrganigramPreferredHeight() );
        organigram.setPreferredSize( prefSize );
        computeBoxLocation();
    }
   
    public int getBoxPreferredWidth() {
        if( boxPreferredWidth > 0 )
            return( boxPreferredWidth );

        int maxLineWidth = 0;
        for( Enumeration e = organigram.getBoxes(); e.hasMoreElements(); ) {
            Box box = (Box)e.nextElement();
            Unit unit = box.getUnit();
            if( unit != null ) {
                Line[] lines = unit.getLines();
                if( lines != null ) {
                    for( int l = 0; l < lines.length; l++ ) {
                        FontMetrics fm = g.getFontMetrics( lines[ l ].getFont() );  
                        int width = fm.stringWidth( lines[ l ].getText() ); 
                        if( width > maxLineWidth )
                            maxLineWidth = width;
                    }
                }
            }
        }
        boxPreferredWidth = organigram.getBoxLeftPadding() + maxLineWidth + organigram.getBoxRightPadding();
        return( boxPreferredWidth );
    }
    
    public int getBoxPreferredHeight() {
        if( boxPreferredHeight > 0 )
            return( boxPreferredHeight );

        int maxHeight = 0;
        for( Enumeration e = organigram.getBoxes(); e.hasMoreElements(); ) {
            Box box = (Box)e.nextElement();
            int totalHeight = 0;
            Unit unit = box.getUnit();
            if( unit != null ) {
                Line[] lines = unit.getLines();
                if( lines != null ) {
                    for( int l = 0; l < lines.length; l++ ) {
                        FontMetrics fm = g.getFontMetrics( lines[ l ].getFont() );  
                        totalHeight += fm.getHeight();
                    }
                }
            }
            if( totalHeight > maxHeight )
                maxHeight = totalHeight;
        }
        boxPreferredHeight = organigram.getBoxTopPadding() + maxHeight + organigram.getBoxBottomPadding();
        return( boxPreferredHeight );
    }

    public int getOrganigramPreferredWidth() {
        if( organigramPreferredWidth > 0 )
            return( organigramPreferredWidth );
        
        organigramPreferredWidth = computeOrganigramPreferredWidthRec( organigram.getRootBox() );
        return( organigramPreferredWidth );
    }

    public int getOrganigramPreferredHeight() {
        if( organigramPreferredHeight > 0 )
            return( organigramPreferredHeight );

        organigramPreferredHeight = computeOrganigramPreferredHeightRec( organigram.getRootBox() );
        return( organigramPreferredHeight );
    }

    private int computeOrganigramPreferredWidthRec( Box box ) {
        if( box == null )
            return( organigram.getBoxLeftMargin() + organigram.getBoxRightMargin() );
            
        if( !box.hasChildren() ) {
            box.setRequiredWidth( organigram.getBoxLeftMargin() + getBoxPreferredWidth() + organigram.getBoxRightMargin() );
            return( box.getRequiredWidth() );
        }

        int preferredWidth = 0;
        for( Enumeration e = box.getChildren(); e.hasMoreElements(); ) {
            Box childBox = (Box)e.nextElement();
            preferredWidth += computeOrganigramPreferredWidthRec( childBox ); 
        }
        box.setRequiredWidth( preferredWidth );
        return( preferredWidth );
    }

    private int computeOrganigramPreferredHeightRec( Box box ) {
        if( box == null )
            return( organigram.getBoxTopMargin() + organigram.getBoxBottomMargin() );
            
        int preferredHeight = organigram.getBoxTopMargin() + getBoxPreferredHeight() + organigram.getBoxBottomMargin();

        int maxChildHeight = 0;
        for( Enumeration e = box.getChildren(); e.hasMoreElements(); ) {
            Box childBox = (Box)e.nextElement();
            int childHeight = computeOrganigramPreferredHeightRec( childBox );
            if( childHeight > maxChildHeight )
                maxChildHeight = childHeight;
        }
        return( preferredHeight + maxChildHeight );
    }

    private void computeBoxLocation() {
        computeBoxLocationRec( organigram.getRootBox(), 
            new Rectangle( 0, 0, organigramPreferredWidth, 
                organigram.getBoxTopMargin() + boxPreferredHeight + organigram.getBoxBottomMargin()) );
    }

    private void computeBoxLocationRec( Box box, Rectangle rect ) {
        if( box == null )
            return;

        Point location = new Point( rect.x + ( rect.width - boxPreferredWidth ) / 2, rect.y + organigram.getBoxTopMargin() );
        box.setLocation( location ); 
        box.setWidth( boxPreferredWidth );
        box.setHeight( boxPreferredHeight );

        int x = rect.x;
        for( Enumeration e = box.getChildren(); e.hasMoreElements(); ) {
            Box childBox = (Box)e.nextElement();
            Rectangle childRect = new Rectangle( x, rect.y + rect.height, childBox.getRequiredWidth(), rect.height ); 
            computeBoxLocationRec( childBox, childRect );
            x += childRect.width;
        }
    }
    
    private Organigram  organigram;

    private Graphics    g;

    private int         boxPreferredWidth           = -1;
    private int         boxPreferredHeight          = -1;

    private int         organigramPreferredWidth    = -1;
    private int         organigramPreferredHeight   = -1;

}
