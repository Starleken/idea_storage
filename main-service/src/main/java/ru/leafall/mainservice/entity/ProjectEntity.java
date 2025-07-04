package ru.leafall.mainservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "idea", nullable = false)
    private String idea;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "reason_purchase")
    private String reasonForPurchase;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_at")
    @CreatedDate
    private Long createdAt;

    @Column(name = "expired_at")
    private Long expiredAt;

    @Column(name = "finished_at")
    private Long finishedAt;

    @Column(name = "is_visible")
    private Boolean isVisible;
}
