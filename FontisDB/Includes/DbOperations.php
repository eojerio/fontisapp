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
            $stmt = $conn->prepare("SELECT id FROM `fontis_userprofiles` WHERE username=:username OR password=:password");
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
    }