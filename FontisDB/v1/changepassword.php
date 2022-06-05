<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['id']) and !empty($_POST['password']) and !empty($_POST['new_password'])){
        $obj = new DbOperations();
        
        echo  $_POST['id'] . " " .  $_POST['password'];
        $result = $obj->changePassword($conn, $_POST['id'], $_POST['password'], $_POST['new_password']);

                 //checks the result value
         if($result == 1){
             $response['error'] = false;
             $response['message'] = "Password updated Successfully";
         }elseif($result == 2){
             $response['error'] = true;
             $response['message'] = "Password change unsuccessful.";
         }elseif($result == 0){
            $response['error'] = true;
            $response['message'] = "Invalid password";
         }
    }else{
        $response['error'] = true;
        $response['message'] = "Values cannot be empty"; 
    }
}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);