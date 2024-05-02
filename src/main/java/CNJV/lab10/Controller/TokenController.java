//package CNJV.lab10.Controller;
//
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/save-token")
//public class TokenServlet extends HttpServlet {
//
//    String token = "Bearer your_access_token_here";
//
//    HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", token);
//    HttpEntity<String> entity = new HttpEntity<>(headers);
//
//    RestTemplate restTemplate = new RestTemplate();
//    ResponseEntity<String> response = restTemplate.exchange(
//            "http://example.com/api/resource", HttpMethod.GET, entity, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//        System.out.println("Request successful. Response body: " + response.getBody());
//    } else {
//        System.out.println("Request failed. Status code: " + response.getStatusCode());
//    }
//}
