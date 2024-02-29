package com.chrtsam.cards.api.model.external;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Chris
 */
@Schema(name="Error Repsonse", description = "Provides information about specific errors")
public class ErrorResponse {

    @Schema(description = "The http error code")
    private int code;
    @Schema(description = "A detailded message regading the error. When the error code is 500 you can use the id provided for troubleshooting")
    private String message;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
