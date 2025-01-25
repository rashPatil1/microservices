package com.eazybytes.accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",example = "Raj"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=5,max = 30, message = "The length customer should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the customer",example = "Raj@gmail.com"
    )
    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Schema(
            description = "Mobile number of the customer",example = "9876543210"
    )
    @Pattern(regexp = "(^[0-9]{10}$)",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}
