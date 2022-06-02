<?php

include '../Includes/DbConnect.php';
require_once '../Includes/DbOperations.php';
$response = array();


$stmt = $conn->prepare("SELECT * FROM `fontis_usercarts`");
$stmt->execute();
$count = $stmt->fetchAll();


foreach ($count as $key) {
    $response['price'] = $key['cart_prodPrice'];
    echo $response['price'] . '<br>';
}



echo json_encode(array("cartObject"=>$response));
