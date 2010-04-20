<?php 
/**
 * OrgApp applet PHP integration library - GPL>=3 - See licence COPYING file
 * @author Enrico Croce & Simona Burzio (staff@eiroca.net)
 * @copyright Copyright (C) 2009-2010 eIrOcA - Enrico Croce & Simona Burzio
 * @license GPL >=3 (http://www.gnu.org/licenses/)
 * @version 1.0.0
 * @link http://www.eiroca.net
 */
?>
<html>
<body>
<?php
//Import the library
require_once("lib\OrgApp.inc");
//Create the helper object
$OC = new OrgApp();
//Set the dimension of the charts
$OC->width=400;
$OC->height=300;
//Generate a chart, the data is an URL
$OC->emitRef('data.xml');
//Generate a chart, the data is read from a file and embedded in the html page
$OC->emitFile('data.xml', 0);
//Generate a chart with the data given
$OC->emitData('<organigram><unit name="Doe, John" department="CEO" role="H"/></organigram>', 0);
?>
</body>
</html>
