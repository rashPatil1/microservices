package com.eazybytes.loans.service;

import com.eazybytes.loans.constants.LoanConstants;
import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loan;
import com.eazybytes.loans.exceptions.LoanAlreadyExistsException;
import com.eazybytes.loans.exceptions.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoanMapper;
import com.eazybytes.loans.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public LoanDto fetchLoanDetails(String mobileNumber) {
        Loan loanEntity = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
        LoanDto loanDto = LoanMapper.mapToLoanDTO(loanEntity, new LoanDto());
       return loanDto;
    }

    public void createLoan(String mobileNumber){
       Optional<Loan> loan = loanRepository.findByMobileNumber(mobileNumber);
       if(loan.isPresent()){
           throw  new LoanAlreadyExistsException("Loan already exist with given mobile number " + mobileNumber);
       }
       loanRepository.save(creatNewLoan(mobileNumber));
    }

    private Loan creatNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        newLoan.setMobileNumber(mobileNumber);
        long randomNumber = 100000000000L + (long ) (new Random().nextDouble() * 900000000000L);
        newLoan.setLoanNumber(Long.toString(randomNumber));
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setAmountPaid(0);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public boolean updateLoanDetails(LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () ->  new ResourceNotFoundException("Loan","LoanNumber", loanDto.getLoanNumber())
        );
        Loan updatedLoan = LoanMapper.mapToLoanEntity(loanDto,loan);
        loanRepository.save(updatedLoan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
             () -> new ResourceNotFoundException("Loan","MobileNumber",mobileNumber));
        loanRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }


}
