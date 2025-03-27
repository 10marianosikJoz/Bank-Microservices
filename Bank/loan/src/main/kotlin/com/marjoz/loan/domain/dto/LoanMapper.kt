package org.example.com.marjoz.loan.domain.dto

import org.example.com.marjoz.loan.domain.Loan

internal fun LoanRequestDto.toLoanEntity() = Loan(customerEmail = this.customerEmail,
                                                  loanNumber = this.loanNumber,
                                                  loanType = Loan.LoanType.valueOf(this.loanType),
                                                  totalLoan = this.totalLoan,
                                                  amountPaid = this.amountPaid)

internal fun Loan.toLoanResponseDto() = LoanResponseDto(customerEmail = this.customerEmail,
                                                        loanNumber = this.loanNumber,
                                                        loanType = this.loanType.name,
                                                        totalLoan = this.totalLoan)