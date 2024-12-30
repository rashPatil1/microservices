package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDTO(Loan loanEntity, LoanDto loanDto) {
            loanDto.setLoanType(loanEntity.getLoanType());
            loanDto.setLoanNumber(loanEntity.getLoanNumber());
            loanDto.setMobileNumber(loanEntity.getMobileNumber());
            loanDto.setTotalLoan(loanEntity.getTotalLoan());
            loanDto.setAmountPaid(loanEntity.getAmountPaid());
            loanDto.setOutstandingAmount(loanEntity.getOutstandingAmount());
            return loanDto;
    }

    public static Loan mapToLoanEntity(LoanDto loanDto, Loan loan){
        loan.setLoanType(loanDto.getLoanType());
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loan;
    }



}
