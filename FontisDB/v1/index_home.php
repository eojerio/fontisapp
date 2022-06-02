<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(){
        
    }


}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);