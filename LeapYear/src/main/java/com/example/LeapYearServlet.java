package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LeapYearServlet")
public class LeapYearServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String yearStr = request.getParameter("year");
        
        out.println("<html><head><title>Leap Year Result</title>");
        out.println("<style>body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println(".result { max-width: 500px; margin: auto; border: 1px solid #ccc; padding: 20px; border-radius: 8px; }");
        out.println("h2 { color: #003366; } .error { color: red; }</style></head><body>");
        out.println("<div class='result'>");
        
        try {
            if (yearStr == null || yearStr.trim().isEmpty()) {
                throw new Exception("Input cannot be empty.");
            }
            
            int year = Integer.parseInt(yearStr.trim());
            
            if (year < 0) {
                throw new Exception("Year cannot be negative.");
            }
            
            boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            
            out.println("<h2>Result for Year: " + year + "</h2>");
            if (isLeap) {
                out.println("<p><strong>" + year + " is a Leap Year!</strong></p>");
            } else {
                out.println("<p><strong>" + year + " is NOT a Leap Year.</strong></p>");
            }
            
            out.println("<h3>Next 5 Leap Years:</h3><ul>");
            int[] nextLeapYears = new int[5];
            int nextYear = year + 1;
            int count = 0;
            while (count < 5) {
                if ((nextYear % 4 == 0 && nextYear % 100 != 0) || (nextYear % 400 == 0)) {
                    nextLeapYears[count] = nextYear;
                    count++;
                }
                nextYear++;
            }
            
            for (int leapYear : nextLeapYears) {
                out.println("<li>" + leapYear + "</li>");
            }
            out.println("</ul>");
            
        } catch (NumberFormatException e) {
            out.println("<h2 class='error'>Invalid Input</h2>");
            out.println("<p class='error'>Please enter a valid numeric year.</p>");
        } catch (Exception e) {
            out.println("<h2 class='error'>Error</h2>");
            out.println("<p class='error'>" + e.getMessage() + "</p>");
        }
        
        out.println("<br><a href='index.html'>Go Back</a>");
        out.println("</div></body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
