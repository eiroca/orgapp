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
if (!defined('DIR_SEP'))	  define('DIR_SEP', DIRECTORY_SEPARATOR);
if (!defined('ORGAPP_DIR'))	define('ORGAPP_DIR', dirname(__FILE__) . DIR_SEP);
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
