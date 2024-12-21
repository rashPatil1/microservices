package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardConstants;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.dto.ErrorResponseDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD Rest APIs for Cards in EasyBank",
    description = "CRUD Rest APIs for Cards in EasyBank to create, update, fetch and delete cards"
)
@RequestMapping("/api")
@RestController
@AllArgsConstructor
@Validated
public class CardController {

    private ICardService cardService;


    @Operation(
            summary = "Create Card Rest API",
            description = "REST API to create new Card inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
        responseCode = "409",
        description = "HTTP Status CONFLICT"

    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCardDetails(@RequestParam @Valid
        @Pattern( regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digits") String mobileNumber ){
        cardService.saveCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }


    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Status NOT FOUND"
    )
    @Operation(
            summary = "Fetch Card Details Rest API",
            description = "REST API to fetch Card details based on a mobile number"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam @Valid
    @Pattern( regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digits")                                                String mobileNumber){
        CardDto cardDto = cardService.fetchCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardDto);
    }



    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "HTTP Status NOT FOUND"
    )
    @Operation(
            summary = "Update Card Details Rest API",
            description = "REST API to update Card details based on a mobile number"
    )

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@RequestBody @Valid CardDto cardDto){
        boolean isUpdated = cardService.updateCardDetails(cardDto);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete Card Details Rest API",
            description = "REST API to delete Card details based on a mobile number"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "HTTP Status NOT FOUND"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
    @Pattern( regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digits") String mobileNumber){
        boolean isDeleted = cardService.deleteCardDetails(mobileNumber);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
    }

}
