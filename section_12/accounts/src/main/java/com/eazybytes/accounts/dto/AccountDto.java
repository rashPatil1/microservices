package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
@Data
public class AccountDto {

    @Schema(
            description = "Account Number of Easy Bank Account", example = "4820424932"
    )
    @NotEmpty(message ="AccountNumber can not be null or empty")
    @Pattern(regexp = "(^[0-9]{10}$)" ,message = "AccountNumber number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type of Easy Bank Account", example = "Savings"
    )
    @NotEmpty(message ="AccountType can not be null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address of Easy Bank Account", example = "123 New york"
    )
    @NotEmpty(message ="BranchAddress can not be null or empty")
    private String branchAddress;
}
