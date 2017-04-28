/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.controllers;

import com.airline.models.Gender;
import com.airline.models.Passenger;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo
 */
//SERVLET WILL SERVE AS A CONTROLLER TO ROUTE TO THE DIFFERENT VIEWS (JSP.. LOCATED IN WEB-INF)!!!!
//WEB-INF cannot be accessed by brwser, so we will use controller to route
public class AddPassenger extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //(se llama este metodo al usar barra URL del browser y se pasa por ahi a traves index)
            RequestDispatcher view;
            view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
            view.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Passenger p = new Passenger();
        
            String firstName = request.getParameter("firstname");
            p.setFirstName(firstName);
            
            String lastName = request.getParameter("lastname");
            p.setLastName(lastName);
            
            String dob = request.getParameter("dob");
            String dobArray[]=dob.split("\\/");
            String day = dobArray[0];
            String month = dobArray[1];
            String year = dobArray[2];
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,Integer.parseInt(year));
            cal.set(Calendar.MONTH,Integer.parseInt(month));
            cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day));
            Date dateofbirth = cal.getTime();
            p.setDob(dateofbirth);
            
            String gender = request.getParameter("gender");
            p.setGender(Gender.valueOf(gender));
            
            
            System.out.println (firstName+lastName+dateofbirth+gender);
    }


}
