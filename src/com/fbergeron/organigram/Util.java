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
import java.text.ParseException;

public class Util {

    public static Color parseColor( String strColor ) throws ParseException {
        int indexOfFirstComma = strColor.indexOf( ',' );
        int indexOfSecondComma = strColor.indexOf( ',', indexOfFirstComma + 1 );
        if( indexOfFirstComma == -1 )
            throw( new ParseException( "First comma not found.", 0 ) );
        if( indexOfSecondComma == -1 )
            throw( new ParseException( "Second comma not found.", indexOfFirstComma ) );

        int red = 0;
        int green = 0;
        int blue = 0;

        try {
            red = Integer.parseInt( strColor.substring( 0, indexOfFirstComma ) );
        }
        catch( NumberFormatException numberFormatException ) {
            throw( new ParseException( "Red color is not a number.", 0 ) );
        }
                
        try {
            green = Integer.parseInt( strColor.substring( indexOfFirstComma + 1, indexOfSecondComma ) );
        }
        catch( NumberFormatException numberFormatException ) {
            throw( new ParseException( "Green color is not a number.", indexOfFirstComma + 1 ) );
        }
                
        try {
            blue = Integer.parseInt( strColor.substring( indexOfSecondComma + 1 ) );
        }
        catch( NumberFormatException numberFormatException ) {
            throw( new ParseException( "Blue color is not a number.", indexOfSecondComma + 1 ) );
        }
                
        return( new Color( red, green, blue ) ); 
    }

}
