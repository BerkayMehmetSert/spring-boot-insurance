package com.bms.vehicleinsurance.model;

import com.bms.vehicleinsurance.helper.MessageHelper;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.CUSTOMER_FIRST_NAME_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.CUSTOMER_FIRST_NAME_IS_REQUIRED)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name", nullable = false)

    @NotEmpty(message = MessageHelper.GlobalValidationMessage.CUSTOMER_LAST_NAME_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.CUSTOMER_LAST_NAME_IS_REQUIRED)
    private String lastName;
    @Column(name = "email", nullable = false)

    @NotEmpty(message = MessageHelper.GlobalValidationMessage.CUSTOMER_EMAIL_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.CUSTOMER_EMAIL_IS_REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = MessageHelper.GlobalValidationMessage.CUSTOMER_EMAIL_IS_INVALID)
    private String email;
    @Column(name = "phone", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.CUSTOMER_PHONE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.CUSTOMER_PHONE_IS_REQUIRED)
    @Pattern(regexp = "^[0-9]{10}$",
            message = MessageHelper.GlobalValidationMessage.CUSTOMER_PHONE_IS_INVALID)
    private String phone;
    @Column(name = "national_id", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.CUSTOMER_NATIONAL_ID_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.CUSTOMER_NATIONAL_ID_IS_REQUIRED)
    @Size(min = 11, max = 14, message = MessageHelper.GlobalValidationMessage.CUSTOMER_NATIONAL_ID_IS_INVALID)
    @Pattern(regexp = "^[0-9]{11}$",
            message = MessageHelper.GlobalValidationMessage.CUSTOMER_NATIONAL_ID_IS_INVALID)
    private String nationalId;
    @Column(name = "birth_date", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.CUSTOMER_BIRTH_DATE_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.CUSTOMER_BIRTH_DATE_IS_REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addressList;
}
