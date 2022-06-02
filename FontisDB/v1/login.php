<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();


if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['username']) and !empty($_POST['password'])){
        $obj = new DbOperations();

        if($obj->userLogin($conn, $_POST['username'], $_POST['password'])){
            $user = $obj->getUser($conn, $_POST['username']);
            
            $response['error'] = false;

            $response['id'] = $user['id'];
            $response['username'] = $user['username'];
            $response['first_name'] = $user['first_name'];
            $response['last_name'] = $user['last_name'];
            $response['contact_no'] = $user['contact_no'];
            $response['address'] = $user['address'] ;
            $response['birthdate'] = $user['birthdate'];
            $response['email_address'] = $user['email_address'];
            $response['employment_status'] = $user['employment_status'];
            $response['marital_status'] = $user['marital_status']; 

            $response['message'] = "Login Successful.";
        }else{
            $response['error'] = true;
            $response['message'] = "Invalid username or password.";
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