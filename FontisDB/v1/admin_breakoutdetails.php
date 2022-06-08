<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $obj = new DbOperations();

    if($result = $obj->populateAdminBreakdown($conn)){
        
        foreach($result as $key){
            $stmt = $conn->prepare("SELECT * FROM `fontis_usercarts` WHERE `cart_id`=:admin_cartID AND `cart_userID`=:admin_cartuserID");
            $stmt->bindParam(":admin_cartID", $key['admin_cartID']);
            $stmt->bindParam(":admin_cartuserID", $key['admin_cartuserID']);
            $stmt->execute();

            while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {
                /*historyTrue=*/
                $response[] = $result;
            }
        }

    } else {
        $response['error'] = true;
        $response['message'] = "Data cannot be retrieved.";
    }

} else {
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

//json packing/encoding
header('Content-Type: application/json; charset=utf-8');
echo json_encode(array("adminBreakdownTrue" => $response));
