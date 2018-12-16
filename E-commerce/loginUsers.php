<?php
require "index.php";
if($_SERVER['REQUEST_METHOD']=="POST"){
	$phone = $_POST['phone'];
	$password = $_POST['password'];
	$sql = "select id from users where phone = '".$phone."' and password = '".$password."' ; ";
	$stmt = $conn->prepare($sql);
	if($res = $stmt->execute()){
		$stmt->bind_result($id);
		$count = 0;
		while ($stmt->fetch()) {
		$count++;
		}

		if($count>0){
			echo "SUCCESS";
		}else{
			echo "FAILED";
		}
	}
} 

?>