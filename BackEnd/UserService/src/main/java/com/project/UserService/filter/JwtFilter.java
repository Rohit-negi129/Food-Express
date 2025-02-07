//package com.project.UserService.filter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//public class JwtFilter extends GenericFilter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        ServletOutputStream pw = response.getOutputStream();
//        //expects the token to come from the header
//        // Parse and validate the token and set the user id from claims in the request header as an attribute.
//        final String authHeader = request.getHeader("Authorization");
//        if(request.getMethod().equals("OPTIONS")){
//            //if the method is options the request can pass through not validation of token is required
//            response.setStatus(HttpServletResponse.SC_OK);
//            filterChain.doFilter(request,response);
//        }
//        else if(authHeader == null || !authHeader.startsWith("Bearer "))
//        {
//            throw new ServletException("Missing or Invalid Token");
//        }
//        String token = authHeader.substring(7);
//        Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token).getBody();
//        request.setAttribute("claims",claims);
//        filterChain.doFilter(request, response);
//    }
//}
