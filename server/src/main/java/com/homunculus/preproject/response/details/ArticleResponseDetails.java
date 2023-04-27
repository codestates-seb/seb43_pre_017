package com.homunculus.preproject.response.details;

import com.homunculus.preproject.article.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDetails {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;    // todo : 필드명 주의

    private Article.ArticleStatus status;
    public String getStatus() { return status.getStatus(); }
}