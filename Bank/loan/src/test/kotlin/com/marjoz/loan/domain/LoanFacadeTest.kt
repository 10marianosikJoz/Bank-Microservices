package com.marjoz.loan.domain

import org.example.com.marjoz.loan.domain.Loan
import org.example.com.marjoz.loan.domain.dto.toLoanEntity
import org.junit.jupiter.api.AfterEach
import kotlin.test.Test

internal class LoanFacadeTest {

    private val loanModuleTestConfiguration = LoanModuleTestConfiguration()
    private val inMemoryLoanRepository = loanModuleTestConfiguration.inMemoryLoanRepository()
    private val loanFacade = loanModuleTestConfiguration.loanFacade(inMemoryLoanRepository)
    private val loanDataProvider = LoanDataProvider()

    @AfterEach
    internal fun tearDown() {
        inMemoryLoanRepository.truncate()
    }

    @Test
    internal fun `should create loan`() {
        //given
        val initialLoan = loanDataProvider.loanData("PERSONAL")

        //when
        val createdLoan = loanFacade.createLoan(initialLoan)

        //then
        assert(inMemoryLoanRepository.findByCustomerEmail(createdLoan.customerEmail) != null)
    }

    @Test
    internal fun `should fetch loan`() {
        //given
        val initialLoan = loanDataProvider.loanData("PERSONAL").toLoanEntity()
        inMemoryLoanRepository.save(initialLoan)

        //when
        val expectedLoan = loanFacade.fetchLoanDetails(initialLoan.customerEmail)

        //then
        assert(expectedLoan.customerEmail == initialLoan.customerEmail)
    }

    @Test
    internal fun `should update loan`() {
        //given
        val updatedLoan = loanDataProvider.loanData("BUSINESS")
        val initialLoan = loanDataProvider.loanData("PERSONAL").toLoanEntity()
        inMemoryLoanRepository.save(initialLoan)

        //when
        loanFacade.updateLoanDetails(updatedLoan)

        //then
        assert(inMemoryLoanRepository.findByLoanNumber(initialLoan.loanNumber)?.loanType == Loan.LoanType.BUSINESS)
    }

    @Test
    internal fun `should delete loan`() {
        //given
        val initialLoan = loanDataProvider.loanData("PERSONAL").toLoanEntity()
        inMemoryLoanRepository.save(initialLoan)

        //when
        loanFacade.deleteLoan(initialLoan.customerEmail)

        //then
        assert(inMemoryLoanRepository.findByCustomerEmail(initialLoan.customerEmail) == null)
    }
}
