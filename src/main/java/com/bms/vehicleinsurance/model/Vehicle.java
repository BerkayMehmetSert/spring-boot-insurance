package com.bms.vehicleinsurance.model;

import com.bms.vehicleinsurance.helper.MessageHelper;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "year", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.VEHICLE_YEAR_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.VEHICLE_YEAR_IS_REQUIRED)
    private Integer year;
    @Column(name = "kilometers", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.VEHICLE_KILOMETERS_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.VEHICLE_KILOMETERS_IS_REQUIRED)
    private Integer kilometers;
    @Column(name = "license_plate", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.VEHICLE_LICENSE_PLATE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.VEHICLE_LICENSE_PLATE_IS_REQUIRED)
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.VEHICLE_TYPE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.VEHICLE_TYPE_IS_REQUIRED)
    private VehicleType vehicleType;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne()
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "vehicle")
    private List<VehicleCrash> vehicleCrashList;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Insurance> insuranceList;
}
