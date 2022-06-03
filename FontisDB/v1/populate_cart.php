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
        if($state = $obj->populateCart($conn, $_POST['cart_userID'])){
            //looping to pass variable
            while($result = $state->fetch(PDO::FETCH_ASSOC)){
                $response[] = $result;
            }

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
echo json_encode(array("cartTrue"=>$response));

/*$response['prodTag'] = $key['cart_prodTag'];
                $response['prodPrice'] = $key['cart_prodPrice'];
                $response['prodName'] = $key['cart_prodName'];
                $response['prodDesc'] = $key['cart_prodDesc'];
                $response['prodQty'] = $key['cart_prodQty']; */