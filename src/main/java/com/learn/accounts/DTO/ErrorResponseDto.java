package com.learn.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error",
        description = "It provide and help to get error"
)
public class ErrorResponseDto {
    @Schema(
            description = "give API error path",example = "http://localhost:8080/api/create"
    )
    private String apiPath;
    @Schema(
            description = "Error Code",example = "500"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error Message",example = "Name already Exist"
    )
    private String errorMessage;
    @Schema(
            description = "time of error Occurred",example = "2025-04-05T19:13:38.8267065"
    )
    private LocalDateTime errorTime;
}
