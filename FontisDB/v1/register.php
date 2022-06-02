<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

   
    if(!empty($_POST['username']) and !empty($_POST['password']) and !empty($_POST['first_name']) and !empty($_POST['last_name']) and !empty($_POST['contact_no']) and !empty($_POST['address']) and !empty($_POST['birthdate']) and !empty($_POST['email_address']) and !empty($_POST['employment_status']) and !empty($_POST['marital_status'])){
         $obj = new DbOperations();

         $result = $obj->registerUser($conn, $_POST['username'], $_POST['password'], $_POST['first_name'], $_POST['last_name'], $_POST['contact_no'], $_POST['address'], $_POST['birthdate'], $_POST['email_address'], $_POST['employment_status'], $_POST['marital_status']);

         if($result == 1){
             $response['error'] = false;
             $response['message'] = "User registered successfully.";
         }elseif($result == 2){
             $response['error'] = true;
             $response['message'] = "Registration failed.";
         }elseif($result == 0){
            $response['error'] = true;
            $response['message'] = "That account is already taken.";
         }
    }else{
        $response['error'] = true;
        $response['message'] = "All fields are required.";
    }

}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);
