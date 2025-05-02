package com.learn.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Response of the server"
)
public class ResponseDto {
    @Schema(
            description = "Response Status Code"
    )
    private String statusCode;
    @Schema(
            description = "Response Message"
    )
    private String statusMessage;
}
