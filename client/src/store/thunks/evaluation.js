import { createAsyncThunk } from "@reduxjs/toolkit";
import { AxiosError } from "axios";

// api
// import { evaluationApiService } from "../apis";

/** 2023/04/23 - 특정 article의 evaluation 추가 요청 thunk - by 1-blue */
const uploadEvaluationOfArticleThunk = createAsyncThunk(
  // 액션 타입 결정
  "upload/evaluation/article",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // const { data } = await evaluationApiService.apiEvaluationOfArticle(body);

      return body;
    } catch (error) {
      console.error("error >> ", error);

      if (error instanceof AxiosError) {
        return rejectWithValue({ message: error.response?.data.data.message });
      }

      return rejectWithValue({
        message: "알 수 없는 이유로 실패했습니다.",
      });
    }
  },
);

/** 2023/04/23 - 특정 evaluation 패치 요청 thunk - by 1-blue */
const uploadEvaluationOfAnswerThunk = createAsyncThunk(
  // 액션 타입 결정
  "upload/evaluation/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // const { data } = await evaluationApiService.apiEvaluationOfAnswer(body);

      return body;
    } catch (error) {
      console.error("error >> ", error);

      if (error instanceof AxiosError) {
        return rejectWithValue({ message: error.response?.data.data.message });
      }

      return rejectWithValue({
        message: "알 수 없는 이유로 실패했습니다.",
      });
    }
  },
);

/** 2023/04/23 - evaluation thunk 메서드들을 갖는 객체 - by 1-blue */
export const evaluationThunkService = {
  uploadEvaluationOfArticleThunk,
  uploadEvaluationOfAnswerThunk,
};
