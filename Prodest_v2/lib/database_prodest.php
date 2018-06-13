<?php

class db {
	public static function connect($host, $db_name, $user_name, $user_pass) {
		$conn = @mysql_connect($host, $user_name, $user_pass);
		@mysql_select_db($db_name, $conn);
		mysql_query("SET NAMES 'utf8'");
		mysql_query('SET character_set_connection=utf8');
		mysql_query('SET character_set_client=utf8');
		mysql_query('SET character_set_results=utf8');
	}

	public static function query($sql) {
		return mysql_query($sql);
	}

	public static function find($tipo, $sql) {	
		switch($tipo) {
			case 'all':
				$tempRows = array();
				if(($res = mysql_query($sql)) !== false) {
					while(($row = mysql_fetch_assoc($res)) !== false) {
						$tempRows[] = $row;
					}
				}				
				return $tempRows;
			break;
			case 'first':
				$tempRows = array();
				if(($res = mysql_query($sql)) !== false) {
					while(($row = mysql_fetch_assoc($res)) !== false) {
						$tempRows[] = $row;
					}
					return (count($tempRows)) ? $tempRows[0] : false;
				}				
				return false;
			break;
			case 'count':
				if(($res = mysql_query($sql)) !== false){
					return mysql_num_rows($res);
				}
			break;
		}
	}
}

db::connect('localhost', 'prodest', 'root', '');