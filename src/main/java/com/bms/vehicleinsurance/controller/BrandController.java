package com.bms.vehicleinsurance.controller;

import com.bms.vehicleinsurance.dto.BrandDto;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/brands")
@Tag(name = "Brand", description = "Brand API")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    // Create a new brand with name
    @Operation(summary = "Create brand",
            description = "Create brand with name",
            tags = {"Brand"})
    @PostMapping
    public Result createBrand(String name) {
        return brandService.createBrand(name);
    }

    // Update an existing brand with brand id and name
    @Operation(summary = "Update brand",
            description = "Update brand with brand id and name",
            tags = {"Brand"})
    @PutMapping("/{id}")
    public Result updateBrand(@PathVariable String id, String name) {
        return brandService.updateBrand(id, name);
    }

    // Delete an existing brand with brand id
    @Operation(summary = "Delete brand",
            description = "Delete brand with brand id",
            tags = {"Brand"})
    @DeleteMapping("/{id}")
    public Result deleteBrand(@PathVariable String id) {
        return brandService.deleteBrand(id);
    }

    // Find brand by brand id
    @Operation(summary = "Find brand by id",
            description = "Find brand by brand id",
            tags = {"Brand"})
    @GetMapping("/{id}")
    public DataResult<BrandDto> findBrandById(@PathVariable String id) {
        return brandService.findBrandById(id);
    }

    // Find all brands
    @Operation(summary = "Find all brands",
            description = "Find all brands",
            tags = {"Brand"})
    @GetMapping
    public DataResult<List<BrandDto>> findAllBrands() {
        return brandService.findAllBrands();
    }
}
