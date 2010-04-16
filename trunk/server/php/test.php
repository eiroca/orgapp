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
