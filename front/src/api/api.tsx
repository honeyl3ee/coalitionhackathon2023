import axios from "axios";

const apiURL = "http://localhost:8080";

const instance = axios.create({
  baseURL: apiURL,
  withCredentials: true,
});

instance.interceptors.response.use(
  (res) => {
    return res;
  },
  (error) => {
    // if (error.response.status === 401) return reissueToken(error);
    return Promise.reject(error);
  }
);

export default instance;
