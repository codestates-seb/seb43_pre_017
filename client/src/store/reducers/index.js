import { combineReducers } from "@reduxjs/toolkit";

// reducers
import userReducer from "./user";

/** 2023/04/12 - root reducer - by 1-blue */
const rootReducer = combineReducers({
  user: userReducer,
});

export default rootReducer;
