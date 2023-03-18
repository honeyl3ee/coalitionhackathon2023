import { useState } from "react";
import PartyCard from "components/party/PartyCard";
import CreateModal from "components/party/CreateModal";
import Stack from "@mui/joy/Stack";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import { Box } from "@mui/joy";
import { IconButton } from "@mui/joy";
import { Typography } from "@mui/joy";
import { Divider } from "@mui/material";

export type PartyInfo = {
  id: number;
  title: string;
  category: string;
  max: number;
  current: number;
  due_date: Date;
  created_date: Date;
  writer: string;
  status: boolean;
};

const Party = (): JSX.Element => {
  const [list, setList] = useState<PartyInfo[]>([
    {
      id: 0,
      title: "SSDC 같이 참석할 분",
      category: "컨퍼런스",
      max: 3,
      current: 1,
      due_date: new Date("2023-03-22 23:00:00"),
      created_date: new Date("2023-03-17 23:00:00"),
      writer: "danpark",
      status: true,
    },
    {
      id: 1,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date("2023-03-17 23:00:00"),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: true,
    },
    {
      id: 2,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date("2023-03-23 23:00:00"),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: true,
    },
    {
      id: 3,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date("2023-03-23 23:00:00"),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: true,
    },
    {
      id: 4,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date("2023-03-23 23:00:00"),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: true,
    },
    {
      id: 5,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date("2023-03-23 23:00:00"),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: true,
    },
  ]);

  const [isOpen, setIsOpen] = useState<boolean>(false);

  return (
    <>
      <Stack sx={{ overflow: "auto", px: 1, height: "90%" }}>
        {list.map((item) => (
          <PartyCard party={item} />
        ))}
      </Stack>
      <IconButton
        aria-label="delete"
        variant="plain"
        onClick={() => {
          setIsOpen(true);
        }}
      >
        <AddCircleIcon fontSize="large" />
      </IconButton>
      <CreateModal isOpen={isOpen} setIsOpen={setIsOpen} />
    </>
  );
};

export default Party;
