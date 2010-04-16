<?php 
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
?>
<html>
<body>
<?php
//Import the library
require_once("lib\OrgApp.inc");
//Create the helper object
$OC = new OrgApp();
//Set the dimension of the chats
$OC->width=400;
$OC->height=300;
//Generate a chart, the data is a URL
$OC->emitRef('data.xml'); 
//Generate a chart, the data is read from a file and embedded in the html page
$OC->emitFile('data.xml', 0); 
//Generate a chart with the data given
$OC->emitData('<organigram><unit name="Doe, John" department="CEO" role="H"/></organigram>', 0); 
?>
</body>
</html>
