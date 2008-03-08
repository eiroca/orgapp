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

import java.awt.Color;
import java.awt.Font;

public class Line {

    public Line() {
    }

    public Line( String text ) {
        this.text = text;
    }

    public Line( String text, String link, Font font, Color color ) {
        this.text = text;
        this.link = link;
        this.color = color;
        this.font = font;
    }

    public String getText() {
        return( text );
    }

    public void setText( String text ) {
        this.text = text;
    }

    public String getLink() {
        return( link );
    }

    public void setLink( String link ) {
        this.link = link;
    }

    public Font getFont() {
        return( font );
    }

    public void setFont( Font font ) {
        this.font = font;
    }

    public Color getColor() {
        return( color );
    }

    public void setColor( Color color ) {
        this.color = color;
    }

    private String  text;
    private String  link;
    private Color   color;
    private Font    font;

}

