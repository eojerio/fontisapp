<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['cart_userID'])){
        //creating an object
        $obj = new DbOperations();

        //calling userLogin method as an instance of obj variable and assigning user variable to save the value of obj
        if($result = $obj->populateCart($conn, $_POST['cart_userID'])){
            //looping to pass variable
            foreach ($result as $key) {
                $response[] = $key;
            }
            $response['error'] = false;
        }else{
            $response['error'] = true;
            $response['message'] = "Data cannot be retrieved.";
        }
    }else{
        $response['error'] = true;
        $response['message'] = "Values cannot be empty";
    }
}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}


//json packing/encoding
header('Content-Type: application/json; charset=utf-8');
echo json_encode(array("cartTrue"=>array($response)));