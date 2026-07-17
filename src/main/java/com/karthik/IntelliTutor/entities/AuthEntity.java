package com.karthik.IntelliTutor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="auth_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    @CreationTimestamp
    @Column(unique = true)
    private LocalDateTime createdAt;
}
