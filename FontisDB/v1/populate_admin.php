<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $obj = new DbOperations();

    //calling userLogin method as an instance of obj variable and assigning user variable to save the value of obj
    if ($result = $obj->populateAdmin($conn)) {
        //looping to pass variable

        $i = 0;

        foreach ($result as $key) {
            $stmt = $conn->prepare("SELECT * FROM `fontis_userhistory` WHERE `prod_id`=:prod_id AND `history_userID`=:history_userID");
            $stmt->bindParam("prod_id", $key['admin_historyprodID']);
            $stmt->bindParam("history_userID", $key['admin_userID']);
            $stmt->execute();

            $stmt1 = $conn->prepare("SELECT `first_name`,`last_name`,`address` FROM `fontis_userprofiles` WHERE `id`=:history_userID");
            $stmt1->bindParam("history_userID", $key['admin_userID']);
            $stmt1->execute();
            $resultGet = $stmt1->fetch();

            while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {
                /*historyTrue=*/
                $response[] = $result;
                $response[$i]['first_name'] = $resultGet['first_name'];
                $response[$i]['last_name'] = $resultGet['last_name'];
                $response[$i]['address'] = $resultGet['address'];
                $response[$i]['admin_historyprodID'] = $key['admin_historyprodID'];
                $response[$i]['admin_userID'] = $key['admin_userID'];
                $i++;
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
echo json_encode(array("adminTrue" => $response));
