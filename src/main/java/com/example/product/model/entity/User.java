package com.example.product.model.entity;

import com.example.product.common.Status;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.product.common.CommonConstant.DELETED_WHERE_CLAUSE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
@DynamicUpdate
@SQLRestriction(value = DELETED_WHERE_CLAUSE)
@SQLDelete(sql = "update users set status = 'DELETED' where user_id=?")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "username", length = 55)
    private String username;

    @Column(name = "email", length = 101, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "status")
    private Status status = Status.ACTIVE;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @CreatedBy
    @JoinColumn(
            name = "`created_by`",
            nullable = false,
            foreignKey = @ForeignKey(name = "`fk_created_user`"),
            updatable = false
    )
    private User createdBy;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @LastModifiedBy
    @JoinColumn(
            name = "`updated_by`",
            nullable = false,
            foreignKey = @ForeignKey(name = "`fk_updated_user`")
    )
    private User updatedBy;
}
