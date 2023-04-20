package com.homunculus.preproject.comment.answer.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.user.entity.User;
import com.homunculus.preproject.user.entity.UserDetails;
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
    private Long commentId;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "USERDETAILS_ID")
    private UserDetails userDetails;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
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
