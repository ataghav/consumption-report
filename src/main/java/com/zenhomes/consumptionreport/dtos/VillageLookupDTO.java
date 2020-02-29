package com.zenhomes.consumptionreport.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class VillageLookupDTO {
    @NotEmpty
    @Min(value = 1)
    private Integer id;

    @NotEmpty
    private String name;

    public VillageLookupDTO() {
    }

    public VillageLookupDTO(@NotEmpty @Min(value = 1) Integer id, @NotEmpty String name) {
        this.id = id;
        this.name = name;
    }
}
