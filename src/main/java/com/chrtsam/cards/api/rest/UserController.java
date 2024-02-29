package com.chrtsam.cards.api.rest;

import com.chrtsam.cards.api.auth.JwtTokenUtil;
import com.chrtsam.cards.api.model.external.ErrorResponse;
import com.chrtsam.cards.api.model.external.AuthRequest;
import com.chrtsam.cards.api.model.external.AuthResponse;
import com.chrtsam.cards.api.services.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chris
 */
@Tag(name = "User API", description = "Manage users that case access the api")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserInfoService userDetailsService;

    @Operation(summary = "Attempts to authencticate a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User authenticated. Include the token provided, in the Authorization header,  for subsequent calls to the api",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))}),
        @ApiResponse(responseCode = "401", description = "Unauthorized. Provided credentials not valid or user does not exists",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Unknown error. Use the code provided in the message field for troubleshooting",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public HttpEntity createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (UsernameNotFoundException | DisabledException | BadCredentialsException exc) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception exc) {
            UUID errorTracer = UUID.randomUUID();
            logger.error("Error code: " + errorTracer, exc);
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error code: " + errorTracer));
        }
    }

    private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
