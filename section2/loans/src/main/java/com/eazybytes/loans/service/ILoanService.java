package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoanDto;



public interface ILoanService {

    LoanDto fetchLoanDetails(String mobileNumber);

    void createLoan(String mobileNumber);

    boolean updateLoanDetails(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
