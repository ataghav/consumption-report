package com.zenhomes.consumptionreport.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class VillageInfoDTO {
    @NotEmpty
    @Min(value = 1)
    private Integer id;

    @NotEmpty
    private VillageLookupDTO village;

    public VillageInfoDTO() {
    }

    public VillageInfoDTO(@NotEmpty @Min(value = 1) Integer id, @NotEmpty VillageLookupDTO village) {
        this.id = id;
        this.village = village;
    }
}
