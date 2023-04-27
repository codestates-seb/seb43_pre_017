import { createAsyncThunk } from "@reduxjs/toolkit";
import { AxiosError } from "axios";

// api
import { answerApiService } from "../apis";

/** 2023/04/20 - 특정 article에 answer 생성 요청 thunk - by 1-blue */
const createAnswerThunk = createAsyncThunk(
  "create/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      const { data } = await answerApiService.apiCreateAnswerOfArticle(body);

      return data;
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

/** 2023/04/20 - 특정 article에 answer 수정 요청 thunk - by 1-blue */
const updateAnswerThunk = createAsyncThunk(
  "update/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      const { data } = await answerApiService.apiUpdateAnswerOfArticle(body);

      return data;
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

/** 2023/04/20 - 특정 article에 answer 제거 요청 thunk - by 1-blue */
const deleteAnswerThunk = createAsyncThunk(
  "delete/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      const { data } = await answerApiService.apiDeleteAnswerOfArticle(body);

      return data;
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

/** 2023/04/20 - answer thunk 메서드들을 갖는 객체 - by 1-blue */
export const answerThunkService = {
  createAnswerThunk,
  updateAnswerThunk,
  deleteAnswerThunk,
};
