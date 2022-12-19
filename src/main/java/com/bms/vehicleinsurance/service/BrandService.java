package com.bms.vehicleinsurance.service;

import com.bms.vehicleinsurance.dto.BrandDto;
import com.bms.vehicleinsurance.dto.converter.BrandDtoConverter;
import com.bms.vehicleinsurance.exception.brand.BrandAlreadyExistsException;
import com.bms.vehicleinsurance.exception.brand.BrandNotFoundException;
import com.bms.vehicleinsurance.helper.DateHelper;
import com.bms.vehicleinsurance.helper.MessageHelper;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.helper.result.SuccessDataResult;
import com.bms.vehicleinsurance.helper.result.SuccessResult;
import com.bms.vehicleinsurance.model.Brand;
import com.bms.vehicleinsurance.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandDtoConverter converter;

    public BrandService(BrandRepository brandRepository,
                        BrandDtoConverter converter) {
        this.brandRepository = brandRepository;
        this.converter = converter;
    }

    // Create a new brand
    public Result createBrand(String name) {
        existsByName(name);

        Brand brand = Brand.builder()
                .name(name)
                .createdAt(DateHelper.getCurrentDateTime())
                .updatedAt(DateHelper.getCurrentDateTime())
                .build();

        brandRepository.save(brand);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_SAVED);
    }

    // Update an existing brand
    public Result updateBrand(String id, String name) {
        Brand brand = findByBrandId(id);
        existsByName(name);

        brand.setName(name);
        brand.setUpdatedAt(DateHelper.getCurrentDateTime());

        brandRepository.save(brand);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete an existing brand
    public Result deleteBrand(String id) {
        Brand brand = findByBrandId(id);

        brandRepository.delete(brand);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_DELETED);
    }

    // Find a brand by id
    public DataResult<BrandDto> findBrandById(String id) {
        Brand brand = findByBrandId(id);
        return new SuccessDataResult<>(converter.convert(brand), MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // Find all brands
    public DataResult<List<BrandDto>> findAllBrands() {
        List<Brand> brandList = brandRepository.findAll();

        if (brandList.isEmpty()) {
            throw new BrandNotFoundException(MessageHelper.BrandMessage.BRAND_NOT_FOUND);
        }

        return new SuccessDataResult<>(converter.convert(brandList), MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // If brand exists by name exception
    private void existsByName(String name) {
        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new BrandAlreadyExistsException(MessageHelper.BrandMessage.BRAND_ALREADY_EXISTS);
        }
    }

    // Find brand by id and return brand
    protected Brand findByBrandId(String id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(MessageHelper.BrandMessage.BRAND_NOT_FOUND));
    }

}
