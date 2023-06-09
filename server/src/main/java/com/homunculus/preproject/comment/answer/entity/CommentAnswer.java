package com.homunculus.preproject.comment.answer.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CommentAnswer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentAnswerId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
    private CommentAnswerStatus commentAnswerStatus = CommentAnswerStatus.COMMENT_ANSWER_REGISTRY;

    public enum CommentAnswerStatus {
        COMMENT_ANSWER_REGISTRY("등록상태"),
        COMMENT_ANSWER_DELETE("삭제상태");

        private @Getter String status;

        CommentAnswerStatus(String status) {
            this.status = status;
        }
    }
}
