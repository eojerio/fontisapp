<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['cart_userID']) and !empty($_POST['cart_prodTag'])){
        $obj = new DbOperations();

        if($obj->deleteItem($conn, $_POST['cart_userID'], $_POST['cart_prodTag'])){
            $response['error'] = false;
            $response['message'] = "Item deleted successfully.";
        }else{
            $response['error'] = true;
            $response['message'] = "Item delete unsuccessful.";
        }
    }else{
        $response['error'] = true;
        $response['message'] = "All fields are required.";
    }
}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

//json packing/encoding
header('Content-Type: application/json; charset=utf-8');
echo json_encode($response);