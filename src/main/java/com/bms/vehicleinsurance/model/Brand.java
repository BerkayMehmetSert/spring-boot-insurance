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
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = MessageHelper.GlobalValidationMessage.BRAND_NAME_IS_REQUIRED)
    @NotNull(message = MessageHelper.GlobalValidationMessage.BRAND_NAME_IS_REQUIRED)
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList;
}
