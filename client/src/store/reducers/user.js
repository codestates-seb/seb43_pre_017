import { createSlice } from "@reduxjs/toolkit";

// thunk
import { userThunkService } from "../thunks";

const initialState = {
  users: [],

  fetchUsersLoading: false,
  fetchUsersDone: null,
  fetchUsersError: null,
};

/** 2023/04/12 - user 관련 슬라이스 - by 1-blue */
const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    resetMessage(state) {
      state.fetchUsersLoading = false;
      state.fetchUsersDone = null;
      state.fetchUsersError = null;
    },
    reset(state) {
      state.users = [];
    },
  },

  // "thunk"가 적용된 액션 ( 비동기 액션 )
  extraReducers(builder) {
    // 게시글 생성
    builder.addCase(userThunkService.fetchUsersThunk.pending, (state) => {
      state.fetchUsersLoading = true;
    });
    builder.addCase(
      userThunkService.fetchUsersThunk.fulfilled,
      (state, action) => {
        state.fetchUsersLoading = false;
        state.users = action.payload;
      },
    );
    builder.addCase(
      userThunkService.fetchUsersThunk.rejected,
      (state, action) => {
        state.fetchUsersLoading = false;
        if (action.payload?.message) {
          state.fetchUsersError = action.payload.message;
        }

        console.error("fetchUsersThunk >> ", action);
      },
    );
  },
});

/** 2023/04/12 - user 관련 액션 크리에이터 - by 1-blue */
export const userActions = userSlice.actions;

export default userSlice.reducer;
