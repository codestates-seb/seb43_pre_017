import { combineReducers } from "@reduxjs/toolkit";

// reducers
import userReducer from "./user";

/** 2023/04/12 - root reducer - by 1-blue */
const rootReducer = combineReducers({
  user: userReducer,
});
/** 2023/04/23 - Action reducer - by 1-blue */
export const LOG_IN = "LOG_IN";
export const LOG_OUT = "LOG_OUT";
export const SIGN_UP = "SIGN_UP";
export const loginAction = (data) => {
  return {
    type: LOG_IN,
    patload: { ...data, isLogin: true },
  };
};
export const logoutAction = () => {
  return {
    type: LOG_IN,
    patload: { isLogin: false },
  };
};
export const signupAction = () => {
  return {
    type: SIGN_UP,
  };
};

export default rootReducer;
