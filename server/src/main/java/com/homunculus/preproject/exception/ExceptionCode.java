package com.homunculus.preproject.exception;

import lombok.Getter;

public enum ExceptionCode {
    INVALID_MEMBER(400, "Member is null. Something Wrong !"),
    MEMBER_NOT_ALLOWED(403, "Member not authenticated"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_FIELD_NOT_FOUND(500, "The object does not have a member field"),

    ARTICLE_MEMBER_NOT_ALLOWED(403, "Article is not authored by the corresponding Member"),
    ARTICLE_NOT_FOUND(404, "Article not found"),
    ARTICLE_NOT_MATCHED(406, "Article is not linked to Answer. Something wrong !"),

    ANSWER_MEMBER_NOT_ALLOWED(403, "Answer is not authored by the corresponding Member"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ANSWER_NOT_MATCHED(406, "Answer is not linked to Article. Something wrong !"),
    ANSWER_NOT_ACCEPTABLE(406, "Answer is already true. This is working only parameter -false- of accepted field !"),

    COMMENT_MEMBER_NOT_ALLOWED(403, "Comment is not authored by the corresponding Member"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    COMMENT_ARTICLE_NOT_MATCHED(406, "Comment is not linked to Article. Something wrong !"),
    COMMENT_ANSWER_NOT_MATCHED(406, "Comment is not linked to Answer. Something wrong !"),

    EVALUATION_MEMBER_NOT_ALLOWED(403, "Evaluation is not authored by the corresponding Member"),

    NOT_IMPLEMENTATION(501, "Not Implementation"),
    ;

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
