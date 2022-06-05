<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    //checks if variables are empty : else, All fields are required.
    if(!empty($_POST['cart_prodTag']) and !empty($_POST['cart_prodPrice']) and !empty($_POST['cart_prodName']) and !empty($_POST['cart_prodDesc']) and !empty($_POST['cart_prodQty'])){
        //create an object from the DbOperations class 
        $obj = new DbOperations();
        
        //saves the value of obj from addCart method
        $result = $obj->addCart($conn, $_POST['cart_userID'],$_POST['cart_prodTag'], $_POST['cart_prodPrice'], $_POST['cart_prodName'], $_POST['cart_prodDesc'], $_POST['cart_prodQty'], $_POST['cart_prodImg']);
        
        //checks the result value
        if($result == 1){
            $response['error'] = false;
            $response['message'] = "Item added to cart.";
        }elseif($result == 2){
            $response['error'] = true;
            $response['message'] = "Unsuccessful transaction.";
        }elseif($result == 0){
           $response['error'] = true;
           $response['message'] = "Item already added to cart";
        }
    }else{
        $response['error'] = true;
        $response['message'] = "Values cannot be empty.";
    }
}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);