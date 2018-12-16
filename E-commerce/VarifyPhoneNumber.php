<?php
require "index.php";
if($_SERVER['REQUEST_METHOD']=="POST"){
	$phone = $_POST['phone'];
	$sql = "select id from users where phone = ".$phone;
	$stmt = $conn->prepare($sql);
	if($res = $stmt->execute()){
		$stmt->bind_result($id);
		$count  = 0;
		while ($stmt->fetch()) {
			$count++;
		}

		if($count>0){
			echo "NUMBER EXISTS";
		}else{
			echo "NEW NUMBER";
		}
	}
}

?>