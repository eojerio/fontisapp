<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();


//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['cart_userID']) and !empty($_POST['prod_price']) and !empty($_POST['prod_date']) and !empty($_POST['prod_amt']) and !empty($_POST['prod_img'])){
        //create an object from the DbOperations class 
        $obj = new DbOperations();

        if($result = $obj->checkout($conn, $_POST['cart_userID'], $_POST['prod_price'], $_POST['prod_date'], $_POST['prod_amt'], $_POST['prod_img'])){

            $stmt = $conn->prepare("DELETE FROM `fontis_usercarts` WHERE `cart_userID`=:cart_userID");
            $stmt->bindParam("cart_userID",$_POST['cart_userID']);
            $stmt->execute();

            $response['error'] = false;
            $response['message'] = "Items have been checked out.";
        }else{
            $response['error'] = true;
            $response['message'] = "Checkout unsuccessful";
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