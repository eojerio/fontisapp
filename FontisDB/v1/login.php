<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    //checks if variables are empty : else, All fields are required.
    if(!empty($_POST['username']) and !empty($_POST['password'])){
        $obj = new DbOperations();

        //calling userLogin method as an instance of obj variable and assigning user variable to save the value of obj
        if($obj->userLogin($conn, $_POST['username'], $_POST['password'])){
            $user = $obj->getUser($conn, $_POST['username']);
            
            $response['error'] = false;

            $response['id'] = $user['id'];
            $response['username'] = $user['username'];
            $response['password'] = md5($user['password']);
            $response['first_name'] = $user['first_name'];
            $response['last_name'] = $user['last_name'];
            $response['contact_no'] = $user['contact_no'];
            $response['address'] = $user['address'] ;
            $response['birthdate'] = $user['birthdate'];
            $response['email_address'] = $user['email_address'];
            $response['employment_status'] = $user['employment_status'];
            $response['marital_status'] = $user['marital_status'];
            $response['user_description'] = $user['user_description'];  

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