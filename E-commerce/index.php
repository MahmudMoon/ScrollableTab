<?php
$server="localhost";
$database = "Ecommerce";
$user_name = "root";
$user_pass = "";

$conn = new mysqli($server,$user_name,$user_pass,$database);
if($conn->error){
	die('Failed to connect '.mysqli_connect_error());
}

?>