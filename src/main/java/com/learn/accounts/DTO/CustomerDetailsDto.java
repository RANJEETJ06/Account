package com.learn.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer,Account,Card and Loan details"
)
public class CustomerDetailsDto {
    @Schema(
            description = "Name of Customer",example = "Ranjeet Jena"
    )
    @NotEmpty(message = "Name can't be empty")
    @Size(max = 30,min = 5,message = "Name should be 5-30 size")
    private String name;

    @Schema(
            description = "Email of Customer",example = "ranjeetjena@gmail.com"
    )
    @NotEmpty(message = "Mail should be provided")
    @Email(message = "valid Email should be provided")
    private String email;

    @Schema(
            description = "Mobile number of Customer",example = "1234567890"
    )
    @NotEmpty(message = "Mobile number should be provided")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be 10 digit number")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountDto accountDto;

    @Schema(
            description = "Card details of the customer"
    )
    private CardsDto cardsDto;

    @Schema(
            description = "Loan details of the customer"
    )
    private LoansDto loansDto;
}
