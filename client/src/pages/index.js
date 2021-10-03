import {
  Button,
  FormControl,
  Flex,
  Heading,
  Input,
  Stack,
  Text,
  Box,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  Avatar,
  useToast,
} from "@chakra-ui/react";
import React, { useState, useEffect } from "react";
import axios from "axios";
export default function Index() {
  const [name, setName] = useState("");
  const [course, setCourse] = useState("");
  const [year, setYear] = useState(0);
  const [email, setEmail] = useState("");
  const [students, setStudents] = useState([]);
  const toast = useToast();

  const addStudent = (e) => {
    e.preventDefault();
    const student = {
      name,
      email,
      course,
      year,
    };
    axios.post("http://localhost:8080/student/add", student).then(() => {
      toast({
        title: "Student Add",
        description: "Added Student Successfully!",
        position: "top",
        status: "success",
        duration: "5000",
        isClosable: "false",
      });
      onClose();
    });
  };
  useEffect(() => {
    axios.get("http://localhost:8080/student/view").then((response) => {
      setStudents(response.data);
    });
  });
  const deleteStudent = (id) => {
    axios.delete("http://localhost:8080/student/delete/" + id).then(() => {
      toast({
        title: "Student Delete",
        description: "Student added successfully",
        position: "top",
        status: "success",
        duration: 5000,
        isClosable: false,
      });
      onClose();
    });
  };

  const updateStudent = (e, id) => {
    axios.post("http://localhost:8080/student/update/" + id).then(() => {
      toast({
        title: "Student Update",
        description: "Student Updated successfully",
        position: "top",
        status: "success",
        duration: 5000,
        isClosable: false,
      });
      onClose();
    });
  };

  useEffect(() => {
    axios.get("http://localhost:8080/student/view").then((response) => {
      setStudents(response.data);
    });
  });

  const { isOpen, onOpen, onClose } = useDisclosure();

  const {
    isOpen: isEditOpen,
    onOpen: onEditOpen,
    onClose: onEditClose,
  } = useDisclosure();
  return (
    <Box bg="gray.300" h="100vh" w="100%">
      <Button position="absolute" onClick={onOpen}>
        Add Student
      </Button>

      <Button position="absolute" onClick={onOpen}>
        Add Student
      </Button>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Add Student</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <Stack
              spacing={3}
              w={"full"}
              maxW={"md"}
              bg="gray.300"
              rounded={"xl"}
              boxShadow={"lg"}
              p={6}
              my={10}
            >
              <Heading lineHeight={1} fontSize={{ base: "2xl", md: "3xl" }}>
                Add student
              </Heading>
              <Text fontSize={{ base: "sm", sm: "md" }}>Name</Text>
              <FormControl>
                <Input
                  placeholder="Name"
                  type="text"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Course</Text>
              <FormControl>
                <Input
                  placeholder="Course"
                  type="text"
                  value={course}
                  onChange={(e) => setCourse(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Year</Text>
              <FormControl>
                <Input
                  placeholder="Year"
                  type="text"
                  value={year}
                  onChange={(e) => setYear(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Email Address</Text>
              <FormControl id="email">
                <Input
                  placeholder="Email address"
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </FormControl>
            </Stack>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="blue" mr={3} onClick={onClose}>
              Close
            </Button>
            <Button
              bg={"green.400"}
              color={"white"}
              _hover={{
                bg: "blue.500",
              }}
              onClick={addStudent}
            >
              Add student
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>

      <Flex minH={"100vh"} align={"center"} justify={"center"} bg="gray.500">
        <Stack
          spacing={4}
          w={"full"}
          maxW={"80%"}
          bg="gray.300"
          rounded={"xl"}
          boxShadow={"lg"}
          p={6}
          my={12}
          display="inline-block"
        >
          {students.map((student) => (
            <Box
              maxW={"320px"}
              w={"full"}
              bg="gray.300"
              boxShadow={"2xl"}
              rounded={"lg"}
              p={6}
              textAlign={"center"}
              display="inline-block"
            >
              <Avatar
                size={"xl"}
                alt={"Avatar Alt"}
                mb={4}
                pos={"relative"}
                _after={{
                  content: '""',
                  w: 4,
                  h: 4,
                  bg: "green.300",
                  border: "2px solid white",
                  rounded: "full",
                  pos: "absolute",
                  bottom: 0,
                  right: 3,
                }}
              />
              <Heading fontSize={"2xl"} fontFamily={"body"}>
                {student.name}
              </Heading>
              <Text fontWeight={600} color={"gray.500"} mb={4}>
                {student.course}-{student.year}
              </Text>
              <Text textAlign={"center"} px={3}>
                Email: {student.email}
              </Text>

              <Stack mt={8} direction={"row"} spacing={4}>
                <Button
                  flex={1}
                  fontSize={"sm"}
                  rounded={"full"}
                  _focus={{
                    bg: "gray.200",
                  }}
                >
                  Edit Info
                </Button>
                <Button
                  flex={1}
                  fontSize={"sm"}
                  rounded={"full"}
                  bg={"blue.400"}
                  color={"white"}
                  boxShadow={
                    "0px 1px 25px -5px rgb(66 153 225 / 48%), 0 10px 10px -5px rgb(66 153 225 / 43%)"
                  }
                  _hover={{
                    bg: "blue.500",
                  }}
                  _focus={{
                    bg: "blue.500",
                  }}
                  onClick={() => {
                    deleteStudent(student.id);
                  }}
                >
                  Delete
                </Button>
              </Stack>
            </Box>
          ))}
        </Stack>
      </Flex>
    </Box>
  );
}
