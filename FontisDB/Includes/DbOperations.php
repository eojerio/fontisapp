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
            if($this->itemExist($conn, $cart_prodTag, $cart_userID)){
                return 0;
            }else{
                $stmt = $conn->prepare("INSERT INTO `fontis_usercarts` (`cart_userID`, `cart_prodTag`, `cart_prodPrice`, `cart_prodName`, `cart_prodDesc`, `cart_prodQty`, `cart_prodImg`) VALUES (:cart_userID, :cart_prodTag, :cart_prodPrice, :cart_prodName, :cart_prodDesc, :cart_prodQty, :cart_prodImg)");
                $stmt->bindParam(":cart_userID",$cart_userID);
                $stmt->bindParam(":cart_prodTag",$cart_prodTag);
                $stmt->bindParam(":cart_prodPrice",$cart_prodPrice);
                $stmt->bindParam(":cart_prodName",$cart_prodName);
                $stmt->bindParam(":cart_prodDesc",$cart_prodDesc);
                $stmt->bindParam(":cart_prodQty",$cart_prodQty);
                $stmt->bindParam(":cart_prodImg", $cart_prodImg);

                if($stmt->execute()){
                    return 1;
                }else{
                    return 2;
                }
            }
        }

        //code for checking duplicate register
        private function itemExist($conn, $cart_prodTag, $cart_userID){
            $stmt = $conn->prepare("SELECT cart_id FROM `fontis_usercarts` WHERE `cart_prodTag`=:cart_prodTag AND `cart_userID`=:cart_userID");
            $stmt->bindParam(":cart_prodTag", $cart_prodTag);
            $stmt->bindParam(":cart_userID", $cart_userID);
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
            return $stmt;
        }

        //code for incrementing and decreasing values
        function valueQtyUpdate($conn, $cart_userID, $cart_prodTag, $cart_prodQty){
            $stmt = $conn->prepare("UPDATE `fontis_usercarts` SET `cart_prodQty`=:cart_prodQty WHERE `cart_prodTag`=:cart_prodTag AND `cart_userID`=:cart_userID");
            $stmt->bindParam(":cart_userID", $cart_userID);
            $stmt->bindParam(":cart_prodTag", $cart_prodTag);
            $stmt->bindParam(":cart_prodQty", $cart_prodQty);

            return $stmt->execute();
        }

        //code for checking out and inserting values for history database
        function checkout($conn, $cart_userID, $prod_price, $prod_date, $prod_amt, $prod_img, $prod_adminAccepted, $cart_id, $cart_userIDAdmin){
            //query for inserting history cart
            $stmt = $conn->prepare("INSERT INTO `fontis_userhistory`(`history_userID`, `prod_price`, `prod_date`, `prod_amt`, `prod_img`, `prod_adminAccepted`) VALUES (:history_userID,:prod_price,:prod_date,:prod_amt,:prod_img,:prod_adminAccepted)");
            $stmt->bindParam(":history_userID", $cart_userID);
            $stmt->bindParam(":prod_price", $prod_price);
            $stmt->bindParam(":prod_date", $prod_date);
            $stmt->bindParam(":prod_amt", $prod_amt);
            $stmt->bindParam(":prod_img", $prod_img);
            $stmt->bindParam(":prod_adminAccepted", $prod_adminAccepted);   

            if($stmt->execute()){
                return true;
            }else{
                return false;
            }
        }


        //code for generating list view in history
        public function populateHistory($conn, $cart_userID){
            //query
            $stmt = $conn->prepare("SELECT * FROM `fontis_userhistory` WHERE `history_userID`=:cart_userID");
            $stmt->bindParam(":cart_userID", $cart_userID);
            $stmt->execute();
            
            return $stmt;
        }

        //code for deleting specific item
        function deleteItem($conn, $cart_userID, $cart_prodTag){
            $stmt = $conn->prepare("DELETE FROM `fontis_usercarts` WHERE `cart_prodTag`=:cart_prodTag AND `cart_userID`=:cart_userID");
            $stmt->bindParam(":cart_userID", $cart_userID);
            $stmt->bindParam(":cart_prodTag", $cart_prodTag);
        
            return $stmt->execute();
        }

        //code for checking password
        function checkPassword($conn, $id, $password){
            $stmt = $conn->prepare("SELECT `password` FROM `fontis_userprofiles` WHERE `id`=:id");
            $stmt->bindParam(":id", $id);
            $stmt->execute();
            $count = $stmt->fetch();

            if(md5($password) == $count['password']){
                return $count;
            }


        }

        //code for updating user profile with password
        function profileEditPassword($conn, $id, $password, $new_password,$contact_no, $address, $birthdate, $email_address, $employment_status, $marital_status, $user_description){
            if($this->checkPassword($conn, $id, $password)){
                $new_password = md5($new_password);
                $stmt = $conn->prepare("UPDATE `fontis_userprofiles` SET `password`=:password,`contact_no`=:contact_no,`address`=:address,`birthdate`=:birthdate,`email_address`=:email_address,`employment_status`=:employment_status,`marital_status`=:marital_status,`user_description`=:user_description WHERE `id`=:id");
                $stmt->bindParam(":id", $id);
                $stmt->bindParam(":password", $new_password);
                $stmt->bindParam(":contact_no", $contact_no);
                $stmt->bindParam(":address", $address);
                $stmt->bindParam(":birthdate", $birthdate);
                $stmt->bindParam(":email_address", $email_address);
                $stmt->bindParam(":employment_status", $employment_status);
                $stmt->bindParam(":marital_status", $marital_status);
                $stmt->bindParam(":user_description", $user_description);

                if($stmt->execute()){
                    return 1;   
                }else{
                    return 2;
                }
            }else{
                return 0;
            }

        }

        //code for updating user profile without password
        function profileEdit($conn, $id, $contact_no, $address, $birthdate, $email_address, $employment_status, $marital_status, $user_description){
            $stmt = $conn->prepare("UPDATE `fontis_userprofiles` SET `contact_no`=:contact_no,`address`=:address,`birthdate`=:birthdate,`email_address`=:email_address,`employment_status`=:employment_status,`marital_status`=:marital_status,`user_description`=:user_description WHERE `id`=:id");
            $stmt->bindParam(":id", $id);
            $stmt->bindParam(":contact_no", $contact_no);
            $stmt->bindParam(":address", $address);
            $stmt->bindParam(":birthdate", $birthdate);
            $stmt->bindParam(":email_address", $email_address);
            $stmt->bindParam(":employment_status", $employment_status);
            $stmt->bindParam(":marital_status", $marital_status);
            $stmt->bindParam(":user_description", $user_description);

            return $stmt->execute();
        }

        //code for generating list view in cart
        public function populateAdmin($conn){
            //query
            $stmt = $conn->prepare("SELECT * FROM `fontis_useradminorders`");
            $stmt->execute();
            $get = $stmt->fetchAll();

            return $get;
        }

        
        function populateAdminBreakdown($conn, $admin_historyprodID, $admin_cartuserID){
            $stmt = $conn->prepare("SELECT `admin_cartID` FROM `fontis_useradminbreakdown` WHERE `admin_historyprodID`=:admin_historyprodID AND `admin_cartuserID`=:admin_cartuserID");
            $stmt->bindParam(":admin_historyprodID", $admin_historyprodID);
            $stmt->bindParam(":admin_cartuserID", $admin_cartuserID);
            $stmt->execute();
            return $stmt->fetchAll();
        }

    }