package com.eazybytes.loans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loan",
        description = "Schema to hold Loan information"
)
@Data
public class LoanDto {

    @Schema(
            description = "Mobile number of customer",example = "9876543121"
    )
    @Pattern(regexp = "(^[0-9]{10}$)" ,message = "Mobile  number must be 10 digits")
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    private String mobileNumber;


    @Schema(
            description = "Loan number of customer",example = "487654343121"
    )
    @Pattern(regexp = "(^[0-9]{12}$)" ,message = "Loan  number must be 12 digits")
    @NotEmpty(message = "Loan Number cannot be null or empty")
    private String loanNumber;


    @Schema(
            description = "Type of the Loan",example = "Home Loan"
    )
    @NotEmpty(message = "Loan Type  cannot be null or empty")
    private String loanType;


    @Schema(
            description = "Total loan amount",example = "100000"
    )
    @Positive(message = "Total loan amount must be greater than zero")
    private int  totalLoan;


    @Schema(
            description = "Total loan amount paid",example = "1000"
    )
    @PositiveOrZero(message = "Total loan amount must be greater than zero")
    private int  amountPaid;



    @Schema(
            description = "Total outstanding amount against the loan",example = "99000"
    )
    @PositiveOrZero(message = "Total outstanding amount must be greater than zero")
    private int  outstandingAmount;
}
