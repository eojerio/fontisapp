<?php   
    include 'DbConnect.php';

    class DbOperations{
        
        /* CRUD -> C -> CREATE */

        //code for register
        function registerUser($conn, $username, $password, $first_name, $last_name, $contact_no, $address, $birthdate, $email_address, $employment_status, $marital_status){
            if($this->userExist($conn, $username, $email_address)){
                return 0;
            }else{
                $password = md5($password);
                $stmt = $conn->prepare("INSERT INTO `fontis_userprofiles` (`username`, `password`, `first_name`, `last_name`, `contact_no`, `address`, `birthdate`, `email_address`, `employment_status`, `marital_status`) VALUES (:username,:password,:first_name,:last_name,:contact_no,:address,:birthdate,:email_address,:employment_status,:marital_status);");
                $stmt->bindParam(":username",$username);
                $stmt->bindParam(":password",$password);
                $stmt->bindParam(":first_name",$first_name);
                $stmt->bindParam(":last_name",$last_name);
                $stmt->bindParam(":contact_no",$contact_no);
                $stmt->bindParam(":address",$address);
                $stmt->bindParam(":birthdate",$birthdate);
                $stmt->bindParam(":email_address",$email_address);
                $stmt->bindParam(":employment_status",$employment_status);
                $stmt->bindParam(":marital_status",$marital_status);
    
                if($stmt->execute()){
                    return 1;
                }else{
                    return 2;
                }
            }

        }

        //code for login
        function userLogin($conn, $username, $password){
            $password = md5($password);
            $stmt = $conn->prepare("SELECT id FROM `fontis_userprofiles` WHERE username=:username AND password=:password");
            $stmt->bindParam(":username", $username);
            $stmt->bindParam(":password", $password);
            $stmt->execute();
            $count = $stmt->fetchColumn(0);

            return $count > 0;
        }

        //code for getting user details from user login to pass to android studio
        function getUser($conn, $username){
            $stmt = $conn->prepare("SELECT * FROM `fontis_userprofiles` WHERE username=:username");
            $stmt->bindParam(":username", $username);
            $stmt->execute();
            $count = $stmt->fetch(PDO::FETCH_ASSOC);

            return $count;
        }

        //code for checking duplicate register
        private function userExist($conn, $username, $email_address){
            $stmt = $conn->prepare("SELECT id FROM `fontis_userprofiles` WHERE username=:username OR email_address=:email_address");
            $stmt->bindParam(":username", $username);
            $stmt->bindParam(":email_address", $email_address);
            $stmt->execute();
            $count = $stmt->fetchColumn(0);
            
            return $count > 0;
        }

        //code for adding to cart
        function addCart($conn, $cart_userID, $cart_prodTag, $cart_prodPrice, $cart_prodName, $cart_prodDesc, $cart_prodQty, $cart_prodImg){
            $stringTest = "test";
            if($this->itemExist($conn, $cart_prodTag)){
                return 0;
            }else{
                $stmt = $conn->prepare("INSERT INTO `fontis_usercarts` (`cart_userID`, `cart_prodTag`, `cart_prodPrice`, `cart_prodName`, `cart_prodDesc`, `cart_prodQty`, `cart_prodImg`) VALUES (:cart_userID, :cart_prodTag, :cart_prodPrice, :cart_prodName, :cart_prodDesc, :cart_prodQty, :cart_prodImg);");
                $stmt->bindParam(":cart_userID",$cart_userID);
                $stmt->bindParam(":cart_prodTag",$cart_prodTag);
                $stmt->bindParam(":cart_prodPrice",$cart_prodPrice);
                $stmt->bindParam(":cart_prodName",$cart_prodName);
                $stmt->bindParam(":cart_prodDesc",$cart_prodDesc);
                $stmt->bindParam(":cart_prodQty",$cart_prodQty);
                $stmt->bindParam(":cart_prodImg", $stringTest);

                if($stmt->execute()){
                    return 1;
                }else{
                    return 2;
                }
            }
        }

        //code for checking duplicate register
        private function itemExist($conn, $cart_prodTag){
            $stmt = $conn->prepare("SELECT cart_id FROM `fontis_usercarts` WHERE `cart_prodTag`=:cart_prodTag");
            $stmt->bindParam(":cart_prodTag", $cart_prodTag);
            $stmt->execute();
            $count = $stmt->fetchColumn(0);
            
            return $count > 0;
        }

        //code for generating list view in cart
        public function populateCart($conn, $cart_userID){
            //query
            $stmt = $conn->prepare("SELECT * FROM `fontis_usercarts` WHERE `cart_userID`=:cart_userID");
            $stmt->bindParam(":cart_userID", $cart_userID);
            $stmt->execute();
            $count = $stmt->fetchAll(PDO::FETCH_ASSOC);
            
            return $count;
        }
    }