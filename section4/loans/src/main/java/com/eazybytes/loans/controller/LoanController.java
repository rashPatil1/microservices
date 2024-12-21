package com.eazybytes.loans.controller;

import com.eazybytes.loans.constants.LoanConstants;
import com.eazybytes.loans.dto.ErrorResponseDto;
import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.entity.Loan;
import com.eazybytes.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name= "CRUD Rest APIs for Loans in EasyBank",
        description = "CRUD Rest APIs for Loans in EasyBank to create, update, fetch and delete loans"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoanController {

    private final ILoanService iLoanService;

    public LoanController(ILoanService iLoanService) {
        this.iLoanService = iLoanService;
    }

    @Operation(
            summary = "Create Loan Rest API",
            description = "REST API to create new Loan inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam  @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile  number must be 10 digits") String mobileNumber){
        iLoanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }


    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch Loan details based on a mobile number"
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam
                  @Pattern(regexp = "(^[0-9]{10}$)" ,message = "Mobile number must be 10 digits") String mobileNumber){
        LoanDto loanDto = iLoanService.fetchLoanDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDto);
    }


    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update Loan details based on a Loan number"
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
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@RequestBody @Valid LoanDto loanDto){
        boolean isUpdated = iLoanService.updateLoanDetails(loanDto);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }


    }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
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
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam
              @Pattern(regexp = "(^[0-9]{10}$)",message = "Mobile  number must be 10 digits")   String mobileNumber){
        boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }



}
