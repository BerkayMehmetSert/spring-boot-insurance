package com.bms.vehicleinsurance.model;

import com.bms.vehicleinsurance.helper.MessageHelper;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_crashes")
public class VehicleCrash {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "description", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.VEHICLE_CRASH_DESCRIPTION_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.VEHICLE_CRASH_DESCRIPTION_IS_REQUIRED)
    private String description;
    @Column(name = "crash_date", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.VEHICLE_CRASH_DATE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.VEHICLE_CRASH_DATE_IS_REQUIRED)
    private String crashDate;
    @Column(name = "crash_valuation", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.VEHICLE_CRASH_VALUATION_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.VEHICLE_CRASH_VALUATION_IS_REQUIRED)
    private Double crashValuation;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne()
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
}
