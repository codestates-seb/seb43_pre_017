import { useState, useEffect } from "react";
import QuestionsList from "./QuestionList";
import QuestionListHeader from "./QuestionListHeader";
import Pagination from "./Paigination";
import StyledAllcontent, { StyledListContainer } from "./style";
import { toast } from "react-toastify";
import axios from "axios";
import { useStore } from "../../store/reducers";
function Home() {
  // 페이지네이션
  const result = useStore();
  console.log(result);

  const [lists, setLists] = useState([]);
  const [limit, setLimit] = useState(15);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  const [totalQNum, setTotalQNum] = useState(0);
  const [sort, setSort] = useState("userId");
  const token = localStorage.getItem("accessToken");

  useEffect(() => {
    axios(
      `${process.env.REACT_APP_BASE_URL}/api/articles?page=1&size=10&type=조회순`,
      {
        headers: {
          "Content-type": "application/json",
          Authorization: token,
        },
      },
    )
      .then((data) => {
        setLists(data.data.articles);
        setTotalQNum(data.pageInfo.totalElements);
      })
      .catch((error) => {
        toast.error(error);
      });
  }, [sort]);
  console.log(lists);
  return (
    <StyledAllcontent>
      <StyledListContainer>
        <QuestionListHeader
          totalQNum={totalQNum}
          setSort={setSort}
          sort={sort}
        />
        {lists.slice(offset, offset + limit).map((list) => (
          <QuestionsList
            key={list.id}
            id={list.id}
            answerCount={list.count.answers}
            content={list.content}
            votes={list.evaluationScore}
            createdAt={list.createdAt}
            title={list.title}
            user={list.member}
          />
        ))}
        <Pagination
          total={lists.length}
          limit={limit}
          page={page}
          setPage={setPage}
          setLimit={setLimit}
        />
      </StyledListContainer>
      <div></div>
    </StyledAllcontent>
  );
}

export default Home;
