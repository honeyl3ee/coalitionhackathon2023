import { useState } from "react";
import Stack from "@mui/joy/Stack";
import Sheet from "@mui/joy/Sheet";
import PartyCard from "components/party/PartyCard";

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
      due_date: new Date(),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: false,
    },
    {
      id: 2,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date(),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: false,
    },
    {
      id: 3,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date(),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: false,
    },
    {
      id: 4,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date(),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: false,
    },
    {
      id: 5,
      title: "저녁에 피자 드실 분",
      category: "배달",
      max: 5,
      current: 3,
      due_date: new Date(),
      created_date: new Date("2023-03-17 22:00:00"),
      writer: "danpark",
      status: false,
    },
  ]);

  return (
    <div style={{ height: "92%" }}>
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
        <Stack sx={{ overflow: "auto", px: 1 }}>
          {list.map((item) => (
            <PartyCard party={item} />
          ))}
        </Stack>
      </Sheet>
    </div>
  );
};

export default Party;
