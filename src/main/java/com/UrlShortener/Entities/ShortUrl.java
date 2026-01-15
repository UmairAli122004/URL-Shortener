package com.UrlShortener.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name="short_urls")
public class ShortUrl{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_key", nullable = false, unique = true, length = 10)
    private String shortKey;

    @Column(nullable = false)
    private String originalUrl;

    @Column(name="is_private")
    private Boolean isPrivate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name="click_count")
    private Long clickCount;

}
