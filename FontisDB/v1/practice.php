<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();


//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){



$stmt = $conn->prepare("SELECT `first_name` FROM `fontis_userprofiles` WHERE `id`=:id");
$stmt->bindParam(":id", $_POST['id']);
$stmt->execute();
$count = $stmt->fetch();

echo $count['first_name'];

}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

