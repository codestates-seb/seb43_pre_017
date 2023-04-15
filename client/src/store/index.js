import { configureStore } from "@reduxjs/toolkit";

// root reduecer
import rootReducer from "./reducers";

/** 2023/04/15 - store 생성 함수 - by 1-blue */
const createStore = () => {
  const store = configureStore({
    reducer: rootReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat([]),
    devTools: process.env.NODE_ENV === "development",
  });

  return store;
};

/** 2023/04/15 - redux의 store - by 1-blue */
const store = createStore();

export default store;
