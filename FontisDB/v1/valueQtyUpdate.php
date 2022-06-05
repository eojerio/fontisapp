<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['cart_userID']) and !empty($_POST['cart_prodTag']) and !empty($_POST['cart_prodQty'])){

        $obj = new DbOperations();

        if($result = $obj->valueQtyUpdate($conn, $_POST['cart_userID'], $_POST['cart_prodTag'], $_POST['cart_prodQty'])){

            $stmt = $conn->prepare("SELECT `cart_prodQty` FROM `fontis_usercarts` WHERE `cart_userID` = :cart_userID AND `cart_prodTag` = :cart_prodTag");
            $stmt->bindParam(":cart_userID", $_POST['cart_userID']);
            $stmt->bindParam(":cart_prodTag", $_POST['cart_prodTag']);
            $stmt->execute();
            $key = $stmt->fetch(PDO::FETCH_ASSOC);
               
            $response['cart_prodQty'] = $key['cart_prodQty'];

            $response['error'] = false;
            $response['message'] = "Update Successful!"; 
        }else{
            $response['error'] = true;
            $response['message'] = "Update Failed"; 
        }
    }else{
        $response['error'] = true;
        $response['message'] = "Item quantity cannot be less than 1"; 
    }

}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);
    