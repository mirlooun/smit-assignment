package com.smit.api.entity;

import com.smit.api.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "procedures")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Procedure extends BaseEntity {
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @NotEmpty
    @Column(nullable = false)
    private String identityCode;

    @NotEmpty
    @Column(nullable = false)
    private String reason;

    @Column
    private boolean emailConfirmationSent = false;

    @Column
    @CreationTimestamp
    private Instant createdAt;

    @Column
    @UpdateTimestamp
    private Instant updatedAt;

    @Override
    public String toString() {
        return "Procedure{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", identityCode='" + identityCode + '\'' +
                ", reason='" + reason + '\'' +
                ", emailConfirmationSent=" + emailConfirmationSent +
                '}';
    }
}
