import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";

// redux
import { articleActions } from "../../store/reducers/article";

/** 2023/04/24 - 토스트 메시지 컴포넌트 - by 1-blue */
const ToastMessage = () => {
  const dispatch = useDispatch();

  const {
    fetchCommentsOfArticleDone,
    fetchCommentsOfAnswerDone,
    createAnswerOfArticleDone,
    updateAnswerOfArticleDone,
    deleteAnswerOfArticleDone,
    createCommentOfArticleDone,
    updateCommentOfArticleDone,
    deleteCommentOfArticleDone,
    createCommentOfAnswerDone,
    updateCommentOfAnswerDone,
    deleteCommentOfAnswerDone,
    uploadEvaluationOfArticleDone,
    uploadEvaluationOfAnswerDone,
  } = useSelector((state) => state.article);

  useEffect(() => {
    toast.success(
      fetchCommentsOfArticleDone ||
        fetchCommentsOfAnswerDone ||
        createAnswerOfArticleDone ||
        updateAnswerOfArticleDone ||
        deleteAnswerOfArticleDone ||
        createCommentOfArticleDone ||
        updateCommentOfArticleDone ||
        deleteCommentOfArticleDone ||
        createCommentOfAnswerDone ||
        updateCommentOfAnswerDone ||
        deleteCommentOfAnswerDone ||
        uploadEvaluationOfArticleDone ||
        uploadEvaluationOfAnswerDone,
    );

    dispatch(articleActions.resetMessage());
  }, [
    fetchCommentsOfArticleDone,
    fetchCommentsOfAnswerDone,
    createAnswerOfArticleDone,
    updateAnswerOfArticleDone,
    deleteAnswerOfArticleDone,
    createCommentOfArticleDone,
    updateCommentOfArticleDone,
    deleteCommentOfArticleDone,
    createCommentOfAnswerDone,
    updateCommentOfAnswerDone,
    deleteCommentOfAnswerDone,
    uploadEvaluationOfArticleDone,
    uploadEvaluationOfAnswerDone,
  ]);

  return <></>;
};

export default ToastMessage;
