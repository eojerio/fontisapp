<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();

//checks if server method is post : else, Invalid request
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['id']) and !empty($_POST['contact_no']) and !empty($_POST['address']) and !empty($_POST['birthdate']) and !empty($_POST['email_address']) and !empty($_POST['employment_status']) and !empty($_POST['marital_status']) and !empty($_POST['user_description'])){
        $obj = new DbOperations();

        if($result = $obj->profileEdit($conn, $_POST['id'],$_POST['contact_no'], $_POST['address'], $_POST['birthdate'], $_POST['email_address'], $_POST['employment_status'], $_POST['marital_status'], $_POST['user_description'])){

            $stmt = $conn->prepare("SELECT * FROM `fontis_userprofiles` WHERE `id` = :id");
            $stmt->bindParam(":id", $_POST['id']);
            $stmt->execute();
            $key = $stmt->fetch(PDO::FETCH_ASSOC);


                $response['contact_no'] = $key['contact_no'];
                $response['address'] = $key['address'] ;
                $response['birthdate'] = $key['birthdate'];
                $response['email_address'] = $key['email_address'];
                $response['employment_status'] = $key['employment_status'];
                $response['marital_status'] = $key['marital_status']; 
                $response['user_description'] = $key['user_description']; 


            $response['error'] = false;
            $response['message'] = "Update Successful!"; 
        }else{
            $response['error'] = true;
            $response['message'] = "Update Failed"; 
        }

    }else{
        $response['error'] = true;
        $response['message'] = "Values cannot be empty"; 
    }

}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);