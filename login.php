<?php

    // Connect to MySQL database
    $conn = mysqli_connect("localhost", "root", "", "dbtimeline+");

    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    // Get user input
    $email = $_POST["email"];
    $password = $_POST["password"];

    // Check if username and password match
    $sql = "SELECT * FROM user WHERE email='$email' AND password='$password'";
    $result = mysqli_query($conn, $sql);

    if (mysqli_num_rows($result) > 0) {
        // Login successful
        echo "success";
    } else {
        // Login failed
        echo "error";
    }

    mysqli_close($conn);

?>