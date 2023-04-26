import { createAsyncThunk } from "@reduxjs/toolkit";
import { AxiosError } from "axios";

// api
// import { answersApiService } from "../apis";
import { fetchDummyAnswersOfArticle } from "../../_dummy";

/** 2023/04/20 - 특정 answer 패치 요청 thunk - by 1-blue */
const fetchAnswersThunk = createAsyncThunk(
  // 액션 타입 결정
  "fetch/answers",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // FIXME: 가짜 데이터
      // const { data } = await answersApiService.apiFetchAnswersOfArticle(body);
      const data = await fetchDummyAnswersOfArticle();

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
export const answersThunkService = {
  fetchAnswersThunk,
};
