package com.homunculus.preproject.article.dto;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.user.entity.User;

public class ArticleResponseDto extends Auditable {
    private Long articleId;
    private String title;
    private String content;
    private Long viewCount;
    // private List<Answer> answers;  fixme : Answer 추가 후 수정해야함
    private User user;
}
