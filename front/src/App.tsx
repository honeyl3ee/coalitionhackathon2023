import "./styles/App.scss";
import React from "react";
import { Layout } from "antd";
import Login from "./pages/login";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import MainRoute from "./Route";

const { Content, Footer } = Layout;

const App = () => {
  return (
    <div className="full">
      <Layout className="outer-frame">
        <Content
          style={{
            padding: "50px 50px",
            width: "800px",
            height: "100%",
          }}
        >
          <Layout className="inner-frame">
            <BrowserRouter>
              <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/*" element={<MainRoute />} />
              </Routes>
            </BrowserRouter>
          </Layout>
        </Content>
      </Layout>
      <Footer
        style={{
          textAlign: "center",
        }}
      >
        Ant Design ©2023 Created by Ant UED
      </Footer>
    </div>
  );
};
export default App;
