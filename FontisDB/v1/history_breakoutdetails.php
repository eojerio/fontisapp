<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    if(!empty($_POST['historyprodID']) and !empty($_POST['userID'])){

        $obj = new DbOperations();
        
        if($result = $obj->populateHistoryBreakdown($conn, $_POST['historyprodID'],$_POST['userID'])){

            foreach($result as $key){
                $stmt = $conn->prepare("SELECT * FROM `fontis_usercartscheckout` WHERE `admincart_id`=:admin_cartID");
                $stmt->bindParam(":admin_cartID", $key['history_cartID']);
                $stmt->execute();

                while ($resultAdminCart = $stmt->fetch(PDO::FETCH_ASSOC)) {
                    /*historyTrue=*/
                    $response[] = $resultAdminCart;
                }
            }
    
        } else {
            $response['error'] = true;
            $response['message'] = "Data cannot be retrieved.";
        }
    }else{
        $response['error'] = true;
        $response['message'] = "Values cannot be empty";
    }
} else {
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

//json packing/encoding
header('Content-Type: application/json; charset=utf-8');
echo json_encode(array("historyBreakdownTrue" => $response));
