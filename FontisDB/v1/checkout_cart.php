<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();


//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['cart_userID']) and !empty($_POST['prod_price']) and !empty($_POST['prod_date']) and !empty($_POST['prod_amt']) and !empty($_POST['prod_img']) and !empty($_POST['prod_adminAccepted']) and !empty($_POST['cart_id']) and !empty($_POST['cart_userIDAdmin'])){
        //create an object from the DbOperations class 
        $obj = new DbOperations();

        if($result2 = $obj->checkout($conn, $_POST['cart_userID'], $_POST['prod_price'], $_POST['prod_date'], $_POST['prod_amt'], $_POST['prod_img'], $_POST['prod_adminAccepted'], $_POST['cart_id'], $_POST['cart_userIDAdmin'])){

            //getting prod_id from history
            $stmt = $conn->prepare("SELECT `prod_id` FROM `fontis_userhistory` WHERE `history_userID`=:history_userID AND `prod_adminAccepted`='false'");
            $stmt->bindParam(":history_userID", $_POST['cart_userID']);
            $stmt->execute();
            $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

            foreach($result as $key){
                $stmt = $conn->prepare("SELECT `admin_historyprodID` FROM `fontis_useradminorders` WHERE `admin_historyprodID`=:prod_id");
                $stmt->bindParam(":prod_id", $key['prod_id']);
                $stmt->execute();
                $count = $stmt->fetchColumn(0);
                
                if($count <= 0){
                    //query for inserting userID and prod_id in admin
                    $stmt = $conn->prepare("INSERT INTO `fontis_useradminorders` (`admin_historyprodID`,`admin_userID`) VALUES (:admin_historyprodID,:admin_userID)");
                    $stmt->bindParam(":admin_historyprodID", $key['prod_id']);
                    $stmt->bindParam(":admin_userID", $_POST['cart_userID']);
                    $stmt->execute();
                
                }else{
                    echo "PROD ID ALREADY PRESENT";
                }
            }

            //for adding to useradminbreakdown
            $cart_id = $_POST['cart_id'];
            $cart_id = json_decode($cart_id, TRUE);
        
            $cart_userIDAdmin = $_POST['cart_userIDAdmin'];
            $cart_userIDAdmin = json_decode($cart_userIDAdmin, TRUE);

            //getting prod_id from history
            $stmt = $conn->prepare("SELECT MAX(`prod_id`) as 'max' FROM `fontis_userhistory` WHERE `history_userID`=:history_userID AND `prod_adminAccepted`='false'");
            $stmt->bindParam(":history_userID", $_POST['cart_userID']);
            $stmt->execute();
            $resultUserID = $stmt->fetch(PDO::FETCH_ASSOC);
        
            for($i=0;$i < count($cart_id); $i++){
                $stmt = $conn->prepare("INSERT INTO `fontis_useradminbreakdown`(`admin_historyprodID`,`admin_cartID`,`admin_cartuserID`) VALUES (:prod_id,:cart_id,:cart_userID)");
                $stmt->bindParam(":prod_id", $resultUserID['max']);
                $stmt->bindParam(":cart_id", $cart_id[$i]);
                $stmt->bindParam(":cart_userID", $cart_userIDAdmin[$i]);
                $stmt->execute();
            }


            $stmt = $conn->prepare("SELECT MAX(`prod_id`) as `max` FROM `fontis_userhistory`");
            $stmt->execute();
            $resultGetProdID = $stmt->fetch();

            $stmt = $conn->prepare("SELECT * FROM `fontis_usercarts`");
            $stmt->execute();
            $resultCartDuplicate = $stmt->fetchAll(PDO::FETCH_ASSOC);

            $deliveredStatus = "not delivered";


            foreach($resultCartDuplicate as $key2){
                
                $stmt = $conn->prepare("INSERT INTO `fontis_usercartscheckout` (`admincart_id`,`cart_userID`, `cart_prodTag`, `cart_prodPrice`, `cart_prodName`, `cart_prodDesc`, `cart_prodQty`, `cart_prodImg`, `cart_historyID`, `cart_deliveredStatus`) VALUES (:admincart_id, :cart_userID, :cart_prodTag, :cart_prodPrice, :cart_prodName, :cart_prodDesc, :cart_prodQty, :cart_prodImg, :cart_historyID, :cart_deliveredStatus)");
                $stmt->bindParam(":admincart_id",$key2['cart_id']);
                $stmt->bindParam(":cart_userID",$key2['cart_userID']);
                $stmt->bindParam(":cart_prodTag",$key2['cart_prodTag']);
                $stmt->bindParam(":cart_prodPrice",$key2['cart_prodPrice']);
                $stmt->bindParam(":cart_prodName",$key2['cart_prodName']);
                $stmt->bindParam(":cart_prodDesc",$key2['cart_prodDesc']);
                $stmt->bindParam(":cart_prodQty",$key2['cart_prodQty']);
                $stmt->bindParam(":cart_prodImg", $key2['cart_prodImg']);
                $stmt->bindParam(":cart_historyID", $resultGetProdID['max']);
                $stmt->bindParam(":cart_deliveredStatus", $deliveredStatus);
                $stmt->execute();

            }
            
            //deleting from usercarts
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