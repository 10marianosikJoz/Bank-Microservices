package org.example.com.marjoz.loan.domain

import jakarta.persistence.*

@Entity
@Table(name = "Loans")
internal data class Loan(@Id
                         @GeneratedValue(strategy = GenerationType.IDENTITY)
                         val loanId: Long,
                         @Column(unique = true)
                         val customerEmail: String,
                         @Column(unique = true)
                         val loanNumber: String,
                         @Enumerated(EnumType.STRING)
                         val loanType: LoanType,
                         val totalLoan: Int,
                         val amountPaid: Int) {

    internal constructor(customerEmail: String,
                         loanNumber: String,
                         loanType: LoanType,
                         totalLoan: Int,
                         amountPaid: Int) : this(0, customerEmail, loanNumber, loanType, totalLoan, amountPaid)

    internal enum class LoanType {
        BUSINESS, PERSONAL
    }
}