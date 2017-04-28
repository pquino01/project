<%-- 
    Document   : add_passenger
    Created on : 25-feb-2016, 14:28:21
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>World Airlines</title>
    </head>
    <body>
        <div class="container">
            <div class="title"> Add a Passenger </div>

            <fieldset>
                <form action="AddPassenger" method="post">

                    <div class="inputField">
                        <label for="firstname" class="inputLabel"> First Name: </label>
                        <input name="firstname" type="text"> </input>
                    </div>

                    <div class="inputField">
                        <label for="lastname" class="inputLabel"> Last Name: </label>
                        <input name="lastname" type="text"> </input>
                    </div>

                    <div class="inputField">
                        <label for="dob" class="inputLabel"> Date of Birth: </label>
                        <input name="dob" type="text"> </input>
                    </div>

                    <div class="inputField">
                        <label for="gender"> Gender: </label>
                        <select name="gender"> 
                            <option value="Male"> Male </option>
                            <option value="Female"> Female </option>
                        </select>
                    </div>


                </form>
            </fieldset>

            <div class="inputField" id="submitField">
                <input id="submitBtn" type="submit" value="add new passenger"></input>
            </div>
        </div>
    </body>
</html>
