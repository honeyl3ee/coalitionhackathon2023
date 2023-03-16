import { Routes, Route } from "react-router-dom";
import Home from "./pages/index";
import Party from "./pages/party";
import Mypage from "./pages/mypage";

const MainRoute = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/party" element={<Party />} />
        <Route path="/mypage" element={<Mypage />} />
      </Routes>
    </>
  );
};

export default MainRoute;

// const BodyContainer = styled.div`
//   margin-left: 150px;
//   width: calc(100% - 150px);
//   overflow-y: scroll;
// `;
