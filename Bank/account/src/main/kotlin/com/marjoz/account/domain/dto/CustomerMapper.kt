package org.example.com.marjoz.account.domain.dto

import org.example.com.marjoz.account.domain.Customer

internal fun CustomerRequestDto.toCustomerEntity() = Customer(name = this.name,
                                                              email = this.email,
                                                              address = this.address)