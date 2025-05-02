package com.learn.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",description = "Details of account"
)
public class AccountDto {
    @Schema(
            description = "account number of customer",example = "1234567891"
    )
    @NotEmpty(message = "Account Number can't be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number should be 10 digit number")
    private Long accountNumber;

    @Schema(
            description = "Account type",example = "Saving"
    )
    @NotEmpty(message = "Account type can't be null")
    private String accountType;

    @Schema(
            description = "Branch Address of Account",example = "Gunupur"
    )
    @NotEmpty(message = "Branch Address can't be null")
    private String branchAddress;
}
