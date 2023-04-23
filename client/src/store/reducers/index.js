import { combineReducers } from "@reduxjs/toolkit";

// reducers
import articleReducer from "./article";

/** 2023/04/12 - root reducer - by 1-blue */
const rootReducer = combineReducers({
  article: articleReducer,
});

export default rootReducer;
