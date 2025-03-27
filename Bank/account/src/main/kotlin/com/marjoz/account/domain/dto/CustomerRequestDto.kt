package org.example.com.marjoz.account.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Schema(name = "CustomerRequestDto", description = "Schema to hold request information.")
data class CustomerRequestDto(@NotEmpty(message = "Name can not be null or empty.")
                              @Size(min = 5, max = 15, message = "The length of the customer name should be between 5 and 15.")
                              @Schema(description = "Name of the customer.", example = "Marcin")
                              val name: String,

                              @NotEmpty(message = "Email address can not be null or empty.")
                              @Email(message = "Email should have correct format.")
                              @Schema(description = "Email address of the customer.", example = "marcin@gmail.com")
                              val email: String,

                              @NotEmpty(message = "Address can not be null or empty.")
                              @Schema(description = "Address of the customer.", example = "New York, Modern Street 1")
                              val address: String)