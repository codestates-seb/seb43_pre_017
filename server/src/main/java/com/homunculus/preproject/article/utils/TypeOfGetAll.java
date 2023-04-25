package com.homunculus.preproject.article.utils;

import lombok.Getter;

public enum TypeOfGetAll {
    ARTICLE_GET_TYPE_EVALUATION("평가순", "evaluationScore"),
    ARTICLE_GET_TYPE_CREATED_AT("최신순", "createdAt"),
    ARTICLE_GET_TYPE_VIEW_COUNT("조회순", "viewCount"),
    ARTICLE_GET_TYPE_ID("기본", "articleId"),
    ;

    private @Getter String type;
    private @Getter String orderBy;

    TypeOfGetAll(String type, String orderBy) {
        this.type = type;
        this.orderBy = orderBy;
    }
}
