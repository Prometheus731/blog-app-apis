package com.example.blog.blogappapis.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //get token
        String requestToken=request.getHeader("Authorization");
        log.info("requestToken: "+requestToken);

        //Bearer 2352523sdgsg

        String userName=null;
        String token=null;

        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token=requestToken.substring(7);

            try {
                userName=this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException exception) {
                log.info("Unable to get Jwt token");
            }
            catch(ExpiredJwtException jwtException){
                log.info("JWT token has expired");
            }
            catch (MalformedJwtException exception){
                log.info("Invalid JWT");
            }
        }
        else{
            log.info("JWT token does not begin with bearer");
        }

        //once we get the token now validate

        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(userName);

            if(this.jwtTokenHelper.validateToken(token,userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            else{
                log.info("Invalid JWT token");
            }

        }
        else{
            log.info("username is null or context is not null");
        }

        filterChain.doFilter(request,response);

    }
}
