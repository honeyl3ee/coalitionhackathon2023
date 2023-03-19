import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import qs from "query-string";
import Loading from "components/common/Loading";
import instance from "api/api";
import "styles/index.scss";

const Home = (): JSX.Element => {
  const navigate = useNavigate();

  const getAccessToken = async () => {
    const params = qs.parse(window.location.search);
    let response;
    // response = await LoginService.login42.issueAccessToken(params.code);
    // localStorage.setItem("ftAccessToken", response.data.ftAccessToken);
    // response = await LoginService.login.issueAccessToken({
    //   ftAccessToken: response.data.ftAccessToken,
    // });
    // localStorage.setItem("accessToken", response.data.accessToken);
    // instance.defaults.headers.common[
    //   "Authorization"
    // ] = `Bearer ${response.data.accessToken}`;
    // setTimeout(() => {
    //   navigate("/party");
    // }, 1000);
  };

  useEffect(() => {
    getAccessToken();
  }, []);
  return (
    <div className="redirect-container">
      <div className="redirect-box">
        <Loading />
        <h3>로그인 중입니다...</h3>
      </div>
    </div>
  );
};

export default Home;
