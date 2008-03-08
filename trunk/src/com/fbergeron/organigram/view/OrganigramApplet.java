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

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import javax.swing.JApplet;
import javax.swing.JScrollPane;

import com.fbergeron.organigram.Util;
import com.fbergeron.organigram.XmlHandler;
import com.fbergeron.organigram.model.Unit;

public class OrganigramApplet extends JApplet {

    public void init() {
        String dataSource = getParameter( "DataSource" );
        
        try {
            URL xmlSourceUrl = null;
            if( dataSource.startsWith( "http" ) )
                xmlSourceUrl = new URL( dataSource );
            else
                xmlSourceUrl = new URL( getDocumentBase(), dataSource );

            XmlHandler xmlHandler = new XmlHandler( xmlSourceUrl );
            
            getContentPane().setLayout( new BorderLayout() );
            Organigram organigram = xmlHandler.getOrganigram();
            JScrollPane pane = new JScrollPane( organigram );
            getContentPane().add( pane, BorderLayout.CENTER );
        }
        catch( MalformedURLException malformedUrlException ) {
            System.err.print( "Invalid data source: " + malformedUrlException );
        }
    }

}

