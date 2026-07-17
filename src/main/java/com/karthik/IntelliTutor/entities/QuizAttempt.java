package com.karthik.IntelliTutor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private String difficulty;

    private Integer totalQuestions;

    private Integer totalScore;

    private String strength;

    @Column(length = 2000)
    private String weakPoint;

    @Column(length = 2000)
    private String nextTopic;

    private String grade;

    private Double percentage;

    private LocalDateTime attemptedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AuthEntity user;
}