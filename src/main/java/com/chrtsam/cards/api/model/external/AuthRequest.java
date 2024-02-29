package com.chrtsam.cards.api.model.external;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Chris
 */
@Schema(name = "Authentication Request", description = "Holds the user's email and password for authentication")
public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
