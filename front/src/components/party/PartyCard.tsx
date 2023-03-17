import { PartyInfo } from "pages/party";
import Card from "@mui/joy/Card";
import AspectRatio from "@mui/joy/AspectRatio";
import CardOverflow from "@mui/joy/CardOverflow";
import Box from "@mui/joy/Box";
import Button from "@mui/joy/Button";
import IconButton from "@mui/joy/IconButton";
import Typography from "@mui/joy/Typography";
import BookmarkAdd from "@mui/icons-material/BookmarkAddOutlined";
import { Divider } from "@mui/material";
import Person from "@mui/icons-material/Person";
import format from "date-fns/format";
import { differenceInHours, differenceInMinutes } from "date-fns";

type PartyProps = {
  party: PartyInfo; // 부모컴포넌트에서 import 해온 타입을 재사용 해 줍시다.
};

// flex 사용법
// https://oneroomtable.tistory.com/entry/flex-%EC%86%8D%EC%84%B1-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95-%EC%A2%8C%EC%B8%A1-%EC%9A%B0%EC%B8%A1-%EC%A4%91%EC%95%99-%EC%A0%95%EB%A0%AC-%EB%93%B1

// { title }: PartyInfo => PartyInfo 객체에서 title(string) 변수만 가져오겠다
// { party }: PartyProps => wrapper PartyProps 객체에서 party(PartyInfo) 객체를 가져오겠다.
const PartyCard = ({ party }: PartyProps): JSX.Element => {
  const timer = (): string => {
    const diffHours = differenceInHours(new Date(), party.created_date);
    const diffMinutes = differenceInMinutes(new Date(), party.created_date);
    if (diffHours === 0) return diffMinutes + " minutes ago";
    else return diffHours + " hours ago";
  };

  return (
    <Card
      variant="outlined"
      sx={
        party.status
          ? { width: 320, marginTop: 1, bgcolor: "#ffffff" }
          : { width: 320, marginTop: 1, bgcolor: "#999999" }
      }
    >
      <Box sx={{ display: "flex" }}>
        <Typography level="h1" fontSize="md" sx={{ mb: 0.5 }}>
          {party.title}
        </Typography>
        <Typography level="body2" sx={{ marginLeft: "auto" }}>
          {party.writer}
        </Typography>
      </Box>
      <Box sx={{ display: "flex", gap: 1, py: 1, alignItems: "center" }}>
        <Person fontSize="small" />
        <Typography
          level="body3"
          sx={{ fontWeight: "md", color: "text.secondary" }}
        >
          {party.current} / {party.max}
        </Typography>

        <Typography sx={{ color: "#aaaaaa" }}>|</Typography>
        <Typography
          level="body3"
          sx={{ fontWeight: "md", color: "text.secondary" }}
        >
          ~ {format(party.due_date, "yyyy-MM-dd HH:mm:00")}
        </Typography>
        {party.status ? (
          <Button
            variant="solid"
            size="sm"
            color="primary"
            aria-label="Explore Bahamas Islands"
            sx={{ ml: "auto", fontWeight: 600 }}
            disabled={new Date() > party.due_date}
          >
            참여하기!
          </Button>
        ) : (
          ""
        )}
      </Box>
      <CardOverflow
        variant="soft"
        sx={{
          display: "flex",
          gap: 1.5,
          py: 1,
          px: "var(--Card-padding)",
          bgcolor: "background.level1",
        }}
      >
        <Typography
          level="body3"
          sx={{ fontWeight: "md", color: "text.secondary" }}
        >
          # {party.category}
        </Typography>
        <Typography
          level="body3"
          sx={{ fontWeight: "md", color: "text.secondary", marginLeft: "auto" }}
        >
          {timer()}
        </Typography>
      </CardOverflow>
    </Card>
  );
};

export default PartyCard;
