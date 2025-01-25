package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "Card",
    description = "Data Transfer Object for Card"
)
@Data
public class CardDto {

    @Schema(description = "Mobile number of customer", example = "9876543121")
    @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digits")
    @NotEmpty(message = "Name cannot be null or empty")
    private String mobileNumber;

    @Schema(description = "Card number of customer", example = "123456789123")
    @Pattern(regexp = "(^[0-9]{12}$)", message = "CardNumber must be 12 digits")
    @NotEmpty(message = "CardNumber cannot be null or empty")
    private String cardNumber;


    @Schema(description = "Card type of customer", example = "Visa")
    @NotEmpty(message = "CardType cannot be null or empty")
    private String cardType;


    @Schema(description = "Total limit of the card", example = "100000")
    @Positive(message = "TotalLimit must be greater than zero")
    private int totalLimit;

    @Schema(description = "Amount used by the card", example = "50000")
    @PositiveOrZero(message = "AmountUsed must be greater than or equal to zero")
    private int amountUsed;

    @Schema(description = "Available limit of the card", example = "50000")
    @PositiveOrZero(message = "AvailableAmount must be greater than or equal to zero")
    private int availableAmount;
}
