package com.eazybytes.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loan  extends BaseEntity  {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;

    @Column
    private String mobileNumber;

    @Column
    private String loanType;

    @Column
    private String loanNumber;

    @Column
    private int totalLoan;

    @Column
    private int  amountPaid;

    @Column
    private int  outstandingAmount;

}
