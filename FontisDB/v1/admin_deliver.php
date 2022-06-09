<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['admin_historyprodID']) and !empty($_POST['admin_userID']) and !empty($_POST['admin_prodDate'])){
        $obj = new DbOperations();

        $result = $obj->adminDeliver($conn, $_POST['admin_historyprodID'], $_POST['admin_userID'], $_POST['admin_prodDate']);

        //checks the result value
        if($result == 1){
            //query
            $stmt = $conn->prepare("SELECT * FROM `fontis_useradminhistory` WHERE `admin_historyprodID`=:admin_historyprodID");
            $stmt->bindParam(":admin_historyprodID", $_POST['admin_historyprodID']);
            $stmt->execute();
            $result = $stmt->fetchAll();

            foreach($result as $key){
                $stmt = $conn->prepare("SELECT * FROM `fontis_userhistory` WHERE `prod_id`=:prod_id AND `history_userID`=:history_userID");
                $stmt->bindParam("prod_id", $key['admin_historyprodID']);
                $stmt->bindParam("history_userID", $key['admin_userID']);
                $stmt->execute();
                $resultGet1 = $stmt->fetch();

                $stmt1 = $conn->prepare("SELECT `first_name`,`last_name`,`address` FROM `fontis_userprofiles` WHERE `id`=:history_userID");
                $stmt1->bindParam("history_userID", $key['admin_userID']);
                $stmt1->execute();
                $resultGet2 = $stmt1->fetch();    

                $response['prod_price'] = $resultGet1['prod_price'];
                $response['prod_date'] = $resultGet1['prod_date'];
                $response['prod_amt'] = $resultGet1['prod_amt'];
                $response['first_name'] = $resultGet2['first_name'];
                $response['last_name'] = $resultGet2['last_name'];
                $response['address'] = $resultGet2['address'];  
            }

            $stmt = $conn->prepare("DELETE FROM `fontis_useradminorders` WHERE `admin_historyprodID`=:admin_historyprodID AND `admin_userID`=:admin_userID");
            $stmt->bindParam(":admin_historyprodID", $_POST['admin_historyprodID']);
            $stmt->bindParam(":admin_userID", $_POST['admin_userID']);
            $stmt->execute();

            $prodAdminAccepted = "true";

            $stmt = $conn->prepare("UPDATE `fontis_userhistory` SET `prod_adminAccepted`=:prod_adminAccepted WHERE `prod_id`=:prod_id");
            $stmt->bindParam(":prod_adminAccepted", $prodAdminAccepted);
            $stmt->bindParam(":prod_id", $_POST['admin_historyprodID']);
            $stmt->execute();

            $response['error'] = false;
            $response['message'] = "Item has been checked out.";
        }elseif($result == 2){
            $response['error'] = true;
            $response['message'] = "Unsuccessful delivery.";
        }elseif($result == 0){
           $response['error'] = true;
           $response['message'] = "history id already exists";
        }
    } else {
        $response['error'] = true;
        $response['message'] = "Values cannot be null.";
    }
}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}


//json packing/encoding
header('Content-Type: application/json; charset=utf-8');
echo json_encode($response);