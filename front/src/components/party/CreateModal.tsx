import { useState } from "react";
import Modal from "@mui/joy/Modal";
import ModalClose from "@mui/joy/ModalClose";
import ModalDialog from "@mui/joy/ModalDialog";
import { IconButton } from "@mui/joy";
import { Box } from "@mui/material";
import dayjs, { Dayjs } from "dayjs";
import TextField from "@mui/material/TextField";
import { Divider } from "@mui/material";
import { Typography } from "@mui/joy";
import { Button } from "@mui/material";
import ModalInput from "./ModalInput";

type HandleIsOpen = { isOpen: boolean; setIsOpen: Function };

const CreateModal = (props: HandleIsOpen): JSX.Element => {
  const [title, setTitle] = useState<string | null>("");
  const [content, setContent] = useState<string | null>("");
  const [maxCount, setMaxCount] = useState<number | null>(2);
  const [dueDate, setDueDate] = useState<Dayjs | null>(dayjs(new Date()));
  const [category, setCategory] = useState<string | null>("");

  const createParty = () => {
    if (title === "") return alert("제목을 입력해주세요!");
    if (content === "") return alert("내용을 입력해주세요!");
    if (!dueDate || dueDate < dayjs(new Date()))
      return alert("현재 이후의 날짜를 선택해주세요!");
    if (category === "") return alert("카테고리를 선택해주세요!");
    // api 호출
  };

  return (
    <Modal
      open={!!props.isOpen}
      onClose={() => props.setIsOpen(false)}
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <ModalDialog
        aria-labelledby="layout-modal-title"
        aria-describedby="layout-modal-description"
        layout="fullscreen"
        sx={{
          height: "700px",
          width: "450px",
          margin: "0 auto",
          gap: 2,
        }}
      >
        <ModalClose />
        <Typography id="layout-modal-title" component="h2">
          모임 추가
        </Typography>
        <Divider />
        <TextField
          placeholder="제목"
          onChange={(e) => {
            if (e) {
              const target = e.target as HTMLInputElement;
              setTitle(target.value);
            }
          }}
        />
        <TextField
          placeholder="내용을 작성해주세요"
          multiline
          rows={5}
          onChange={(e) => {
            if (e) {
              const target = e.target as HTMLInputElement;
              setContent(target.value);
            }
          }}
        />
        <ModalInput state={maxCount} setState={setMaxCount} type="maxCount" />
        <ModalInput state={dueDate} setState={setDueDate} type="dueDate" />
        <ModalInput state={category} setState={setCategory} type="category" />
        <Button variant="contained" onClick={createParty} sx={{ marginTop: 5 }}>
          생성
        </Button>
      </ModalDialog>
    </Modal>
  );
};

export default CreateModal;
