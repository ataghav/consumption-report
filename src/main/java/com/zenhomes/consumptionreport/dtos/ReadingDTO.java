package com.zenhomes.consumptionreport.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ReadingDTO {
    @NotNull
    @Digits(integer = 9, fraction = 0)
    @Min(value = 1)
    private Integer counter_id;

    @NotNull
    @Digits(integer = 9, fraction = 3)
    @DecimalMin(value = "0")
    private Double amount;

    public ReadingDTO() {
    }

    public ReadingDTO(@NotEmpty @Digits(integer = 9, fraction = 0) @Min(value = 1) Integer counter_id, @NotEmpty @Digits(integer = 9, fraction = 3) @DecimalMin(value = "0") Double amount) {
        this.counter_id = counter_id;
        this.amount = amount;
    }
}
