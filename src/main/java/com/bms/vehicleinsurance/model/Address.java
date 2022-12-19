package com.bms.vehicleinsurance.model;

import com.bms.vehicleinsurance.helper.MessageHelper;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "street", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.STREET_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.STREET_IS_REQUIRED)
    @Size(min = 3, message = MessageHelper.GlobalValidationMessage.STREET_MIN_LENGTH)
    private String street;
    @Column(name = "city", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.CITY_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.CITY_IS_REQUIRED)
    @Size(min = 3, message = MessageHelper.GlobalValidationMessage.CITY_MIN_LENGTH)
    private String city;
    @Column(name = "state", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.STATE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.STATE_IS_REQUIRED)
    @Size(min = 3, message = MessageHelper.GlobalValidationMessage.STATE_MIN_LENGTH)
    private String state;
    @Column(name = "zip", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.ZIP_CODE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.ZIP_CODE_IS_REQUIRED)
    @Size(min = 3, message = MessageHelper.GlobalValidationMessage.ZIP_CODE_MIN_LENGTH)
    private String zipCode;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
