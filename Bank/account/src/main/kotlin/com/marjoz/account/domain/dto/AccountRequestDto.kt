package com.marjoz.account.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

@Schema(name = "Account", description = "Schema to hold request information.")
data class AccountRequestDto(@NotEmpty(message = "Account number can not be null or empty.")
                             @Pattern(regexp = "(^|[0-9]{10})", message = "Account number should have correct format.")
                             @Schema(description = "Account number.", example = "2221113334")
                             val accountNumber: Long,
                            
                             @NotEmpty(message = "Account type can not be null or empty.")
                             @Schema(description = "Account type.", example = "PERSONAL")
                             val accountType: String)
