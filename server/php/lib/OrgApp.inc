<?php
/**
 * OrgApp applet PHP integration library - GPL>=3 - See licence COPYING file
 * @author Enrico Croce & Simona Burzio (staff@eiroca.net)
 * @copyright Copyright (C) 2009-2010 eIrOcA - Enrico Croce & Simona Burzio
 * @license GPL >=3 (http://www.gnu.org/licenses/)
 * @version 1.0.0
 * @link http://www.eiroca.net
 */
if (!defined('DIR_SEP')) define('DIR_SEP',DIRECTORY_SEPARATOR);
if (!defined('ORGAPP_DIR')) define('ORGAPP_DIR',dirname(__FILE__).DIR_SEP);
class OrgApp {
	var $name="Organigram";
	var $code="OrganigramApplet.class";
	var $archive="OrgApplet.jar";
	var $height="50%";
	var $width="50%";
	var $target="_blank";
	function pre() {
		echo '<applet name="'.$this->name.'" code="'.$this->code.'" archive="'.$this->archive.'" width="'.$this->width.'" height="'.$this->height.'">';
		echo '<param name="Target" value="'.$this->target.'" />';
	}
	function post() {
		echo '</applet>';
	}
	function emitRef($dataSource) {
		$this->pre();
		echo '<param name="DataSource" value="'.$dataSource.'" />';
		$this->post();
	}
	function emitFile($data, $dataType) {
		$this->pre();
		$fh = fopen($data, 'r');
		$theData = fread($fh, filesize($data));
		fclose($fh);
		echo '<param name="Data" value="'. htmlspecialchars($theData) .'" />';
		echo '<param name="DataType" value="'. $dataType .'" />';
		$this->post();
	}
	function emitData($data, $dataType) {
		$this->pre();
		echo '<param name="Data" value="'. htmlspecialchars($data) .'" />';
		echo '<param name="DataType" value="'. $dataType .'" />';
		$this->post();
	}
}
?>
