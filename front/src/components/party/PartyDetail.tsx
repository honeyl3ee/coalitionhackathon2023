import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Box, Typography } from "@mui/joy";
import { Divider } from "@mui/material";
import PersonCounter from "./PersonCounter";
import TimeCounter from "./TimeCounter";
import { Button } from "@mui/material";
import { IconButton } from "@mui/joy";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import GroupsIcon from "@mui/icons-material/Groups";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import PartyService from "api/PartyService";
import { useLocation } from "react-router-dom";

// TODO : type을 관리하는 파일에 모아서 관리하기 + naming
export type PartyDetailInfo = {
  id: number;
  title: string;
  content: string;
  category: string;
  max: number;
  current: number;
  due_date: Date;
  created_date: Date;
  writer: string;
  participant: string[];
  status: boolean;
};

const PartyDetail = (): JSX.Element => {
  const navigate = useNavigate();
  const location = useLocation();
  const id: number = parseInt(location.pathname.split("/")[2]);

  const [detail, setDetail] = useState<PartyDetailInfo>({
    id: 0,
    title: "SSDC 같이 참석할 분",
    content: "안녕하세요!\n12월 3일에 열리는 SSDC에 함께 참석하실 분 있나요?",
    category: "컨퍼런스",
    max: 3,
    current: 3,
    due_date: new Date("2023-03-22 23:00:00"),
    created_date: new Date("2023-03-17 23:00:00"),
    writer: "danpark",
    participant: ["wonlim", "chanhyle"],
    status: true,
  });
  const [isCheck, setIsCheck] = useState<boolean>(false);
  const [isWriter, setIsWriter] = useState<boolean>(false);

  const isParticipant = (user: string): boolean => {
    for (const index in detail.participant) {
      if (detail.participant[index] === user) return true;
    }
    return false;
  };

  const getPartyDetail = async () => {
    // const response = PartyService.getPartyDetail(id); // 없는 페이지일 경우 404를 받도록?
    // setDetail(response.data);
    // GET /user/me 호출
    const user: string = "chanhyle";

    if (user === detail.writer) setIsWriter(true);
    else if (isParticipant(user)) setIsCheck(true);
    else setIsCheck(false);
  };

  useEffect(() => {
    getPartyDetail();
  }, [detail]);

  return (
    <>
      <Box sx={{ width: "100%" }}>
        <IconButton
          variant="plain"
          onClick={() => {
            navigate("/");
          }}
        >
          <KeyboardArrowLeftIcon />
        </IconButton>
      </Box>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: 1,
          width: "80%",
          height: "100%",
        }}
      >
        <Typography sx={{ fontSize: "30px", marginY: 3 }}>
          {detail.title}
        </Typography>
        <PersonCounter current={detail.current} max={detail.max} />
        <TimeCounter dueDate={detail.due_date} />
        <Box sx={{ display: "flex", gap: 1 }}>
          <AccountCircleIcon />
          <Typography
            level="body2"
            sx={{ fontWeight: "md", color: "text.secondary" }}
          >
            {detail.writer}
          </Typography>
        </Box>
        <Box sx={{ display: "flex", gap: 1 }}>
          <GroupsIcon />
          <Typography
            level="body2"
            sx={{ fontWeight: "md", color: "text.secondary" }}
          >
            {detail.participant.map((item) => item + " ")}
          </Typography>
        </Box>
        <Divider sx={{ marginY: 1 }} />
        <Box sx={{ marginY: 1, height: "30%" }}>
          <Typography sx={{ fontSize: "20px" }}>{detail.content}</Typography>
        </Box>
        {isCheck ? (
          <Button
            variant="contained"
            color="error"
            disabled={isWriter}
            onClick={() => {
              setIsCheck(false);
            }}
          >
            참여 취소할래요
          </Button>
        ) : (
          <Button
            variant="contained"
            disabled={isWriter}
            onClick={() => {
              setIsCheck(true);
            }}
          >
            참여할래요
          </Button>
        )}
      </Box>
    </>
  );
};

export default PartyDetail;
