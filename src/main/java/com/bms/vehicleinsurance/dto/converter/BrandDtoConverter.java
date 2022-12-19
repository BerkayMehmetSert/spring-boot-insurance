package com.bms.vehicleinsurance.dto.converter;

import com.bms.vehicleinsurance.dto.BrandDto;
import com.bms.vehicleinsurance.model.Brand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandDtoConverter {
    public BrandDto convert(Brand from) {
        return new BrandDto(
                from.getId(),
                from.getName()
        );
    }

    public List<BrandDto> convert(List<Brand> from) {
        return from.stream().map(this::convert).toList();
    }
}
