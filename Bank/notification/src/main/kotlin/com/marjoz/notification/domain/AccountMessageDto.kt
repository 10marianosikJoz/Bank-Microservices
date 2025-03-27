package com.marjoz.notification.domain

internal data class AccountMessageDto(val accountNumber : Long,
                                      val name : String,
                                      val email : String)