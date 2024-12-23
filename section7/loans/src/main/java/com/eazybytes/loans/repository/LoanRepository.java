package com.eazybytes.loans.repository;

import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByMobileNumber(String mobileNumber);

    Optional<Loan> findByLoanNumber(String loanNumber);

    Optional<Loan> deleteByLoanNumber(String loanNumber);

    @Transactional
    @Modifying
    int  deleteByMobileNumber(String mobileNumber);
}
