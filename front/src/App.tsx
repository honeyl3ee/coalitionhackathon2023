import React, { useState } from "react";
import "styles/App.scss";
import Sheet from "@mui/joy/Sheet";
import Login from "./pages/login";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import MainRoute from "./Route";
import instance from "api/api";
import NavBar from "components/common/NavBar";
import { Box } from "@mui/material";

const App = (): JSX.Element => {
  const loadUserData = () => {
    try {
      console.log("loadUserData");
      const token = localStorage.getItem("accessToken");
      const userId = localStorage.getItem("userId");

      if (!token || !userId) return;

      /* eslint-disable dot-notation */
      instance.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    } catch (e) {
      console.log("localStorage is not working");
    }
  };

  loadUserData();

  return (
    <Box className="full">
      <BrowserRouter>
        <NavBar />
        <Sheet
          sx={{
            width: "90%",
            height: "85%",
            mx: "auto", // margin left & right
            my: 3, // margin top & botom
            py: 1, // padding top & bottom
            px: 1, // padding left & right
            display: "flex",
            flexDirection: "column",
            gap: 2,
            borderRadius: "sm",
            boxShadow: "md",
            alignItems: "center",
          }}
          variant="outlined"
        >
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/*" element={<MainRoute />} />
          </Routes>
        </Sheet>
      </BrowserRouter>
    </Box>
  );
};
export default App;
