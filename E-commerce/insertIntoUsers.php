<?php
   require "index.php";

  if($_SERVER['REQUEST_METHOD']=="POST"){
  	$fullName = $_POST['fullName'];
  	$userName = $_POST['userName'];
  	$phone = $_POST['phone'];
  	$email = $_POST['email'];
  	$password = $_POST['password'];
  	$gender = $_POST['gender'];

  	$sql = "insert into users (fullName,userName,phone,email,password,gender) values ( '".$fullName."' , '".$userName."' , '".$phone."' , '".$email."' , '".$password."' , '".$gender."');";
  	$stmt = $conn->prepare($sql);
  	if($res = $stmt->execute()){
  		echo "SUCCESS";
  	}else{
  		echo "FAILED";
  	}
  }
?>