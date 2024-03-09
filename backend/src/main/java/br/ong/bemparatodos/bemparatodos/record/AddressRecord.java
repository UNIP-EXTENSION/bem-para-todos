package br.ong.bemparatodos.bemparatodos.record;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddressRecord(

        @NotNull(message = "The Id Address be null")
        UUID id,

        @NotBlank(message = "zipcode cannot be empty")
        @Min(value = 1, message = "zipcode must be greater than 1")
        @Max(value = 15, message = "the maximum of the zip code is 15")
        String zipcode,

        @NotBlank(message = "street cannot be empty")
        String street,

        @NotBlank(message = "neighborhood cannot be empty")
        String neighborhood,

        @NotBlank(message = "state cannot be empty")
        @Min(value = 1, message = "state must be greater than 1")
        @Max(value = 2, message = "state maximum of the state 2")
        String state
) {}
