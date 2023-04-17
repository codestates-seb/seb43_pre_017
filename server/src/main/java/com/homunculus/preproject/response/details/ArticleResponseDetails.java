package com.homunculus.preproject.response.details;

import java.time.LocalDateTime;

public class ArticleResponseDetails {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;    // todo : 필드명 주의
}