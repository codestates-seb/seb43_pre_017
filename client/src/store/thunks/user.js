import { createAsyncThunk } from "@reduxjs/toolkit";
import { AxiosError } from "axios";

// api
import { userApiService } from "../apis";

/** 2023/04/12 - 유저들 생성 요청 thunk - by 1-blue */
const fetchUsersThunk = createAsyncThunk(
  // 액션 타입 결정
  "create/post",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      const { data } = await userApiService.apiFetchUsers(body);

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

/** 2023/04/12 - 유저 thunk 메서드들을 갖는 객체 - by 1-blue */
export const userThunkService = {
  fetchUsersThunk,
};
