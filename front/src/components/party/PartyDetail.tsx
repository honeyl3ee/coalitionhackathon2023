import { useState } from "react";
import { Box, Typography } from "@mui/joy";
import { Divider } from "@mui/material";
import PersonCounter from "./PersonCounter";
import TimeCounter from "./TimeCounter";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import GroupsIcon from "@mui/icons-material/Groups";
import { Button } from "@mui/material";

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
  // response = getDetail();
  // if (response === undefined)
  // return <WrongPage />;

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

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: 1,
        width: "80%",
        height: "100%",
      }}
    >
      <Typography sx={{ fontSize: "30px", marginY: 5 }}>
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
      <Button variant="contained" onClick={() => {}}>
        참여할래요
      </Button>
    </Box>
  );
};

export default PartyDetail;
