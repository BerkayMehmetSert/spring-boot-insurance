package com.bms.vehicleinsurance.model;

import com.bms.vehicleinsurance.helper.MessageHelper;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurances")
public class Insurance {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "insurance_type", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.INSURANCE_TYPE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.INSURANCE_TYPE_IS_REQUIRED)
    @Enumerated(EnumType.STRING)
    private InsuranceType insuranceType;
    @Column(name = "description", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.INSURANCE_DESCRIPTION_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.INSURANCE_DESCRIPTION_IS_REQUIRED)
    private String description;
    @Column(name = "insurance_start_date", nullable = false)
    private LocalDate insuranceStartDate;
    @Column(name = "insurance_end_date", nullable = false)
    private LocalDate insuranceEndDate;
    @Column(name = "insurance_price", nullable = false)
    private Double price;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne()
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
}
