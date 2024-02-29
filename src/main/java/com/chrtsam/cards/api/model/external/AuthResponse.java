package com.chrtsam.cards.api.model.external;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Chris
 */
@Schema(name = "Authentication Response", description = "Response indicating a successful authentication. Use the token provided for subsequent requests")
public class AuthResponse {

    private final String jwttoken;

    public AuthResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
