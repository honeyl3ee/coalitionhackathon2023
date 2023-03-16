import { Button, Space } from "antd";

const LoginButton = () => {
  const requestLogin = async () => {
    window.location.href = "http://localhost:8080/oauth2/authorization";
  };

  return (
    <div>
      <div className="title">로그인</div>
      <div className="subtitle">로그인할 계정을 선택하세요</div>
      <Space
        direction="vertical"
        style={{
          width: "80%",
        }}
      >
        <Button onClick={requestLogin} type="primary" block>
          Sign in with 42
        </Button>
      </Space>
    </div>
  );
};

export default LoginButton;
