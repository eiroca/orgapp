<html>
<body>
<?php
require_once("lib\OrgApp.inc");
$OC = new OrgApp();
$OC->width=400;
$OC->height=300;
$OC->emitRef('data.xml'); echo "\n";
$OC->emitFile('data.xml', 0); echo "\n";
$OC->emitData('<organigram><unit name="Doe, John" department="CEO" role="H"/></organigram>', 0); echo "\n";
?>
</body>
</html>
