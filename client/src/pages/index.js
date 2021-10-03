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
  const [updateName, setUpdateName] = useState("");
  const [updateCourse, setUpdateCourse] = useState("");
  const [updateYear, setUpdateYear] = useState(0);
  const [updateEmail, setUpdateEmail] = useState("");
  const [students, setStudents] = useState([]);
  const toast = useToast();
  const [estuds, setEStuds] = useState({
    id: "",
    name: "",
    course: "",
    year: "",
    email: "",
  });

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

  const updateStudent = (id) => {
    // alert(Ename + " " + id);
    const student = {
      name,
      email,
      course,
      year,
    };
    student.name = updateName;
    student.email = updateEmail;
    student.course = updateCourse;
    student.year = updateYear;

    axios
      .put("http://localhost:8080/student/update/" + id, student)
      .then(() => {
        console.log(student);
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

  const editStudent = (student) => {
    setEStuds(student);
    onEditOpen();
  };

  useEffect(() => {
    axios.get("http://localhost:8080/student/view").then((response) => {
      setStudents(response.data);
    });
  }, []);

  // }
  const { isOpen, onOpen, onClose } = useDisclosure();

  const {
    isOpen: isEditOpen,
    onOpen: onEditOpen,
    onClose: onEditClose,
  } = useDisclosure();

  const {
    isOpen: isViewOpen,
    onOpen: onViewOpen,
    onClose: onViewClose,
  } = useDisclosure();
  return (
    <Box bg="gray.500" h="100vh" w="100%">
      <Modal isOpen={isEditOpen} onClose={onEditClose}>
        <ModalContent>
          <ModalHeader>Edit Student</ModalHeader>
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
                Edit student
              </Heading>
              <Text fontSize={{ base: "sm", sm: "md" }}>Name</Text>
              <FormControl>
                <Input
                  placeholder={estuds.name}
                  type="text"
                  value={updateName}
                  onChange={(e) => setUpdateName(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Course</Text>
              <FormControl>
                <Input
                  placeholder={estuds.course}
                  type="text"
                  value={updateCourse}
                  onChange={(e) => setUpdateCourse(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Year</Text>
              <FormControl>
                <Input
                  placeholder={estuds.year}
                  type="text"
                  value={updateYear}
                  onChange={(e) => setUpdateYear(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Email Address</Text>
              <FormControl id="email">
                <Input
                  placeholder={estuds.name}
                  type="email"
                  value={updateEmail}
                  onChange={(e) => setUpdateEmail(e.target.value)}
                />
              </FormControl>
            </Stack>
          </ModalBody>
          <ModalFooter>
            <Button
              bg={"gray.200"}
              color={"black"}
              shadow="2xl"
              _hover={{
                bg: "blue.500",
              }}
              onClick={() => {
                updateStudent(estuds.id);
              }}
            >
              Update student
            </Button>
            <Button colorScheme="blue" mr={3} onClick={onEditClose}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>

      <Button onClick={onOpen}>Add Students</Button>

      <Button onClick={onViewOpen}>List of Students</Button>

      <Modal isOpen={isViewOpen} onClose={onViewClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>List of Students</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <ol>
              {students.map((student) => (
                <li key={student.id}>{student.name}</li>
              ))}
            </ol>
          </ModalBody>
          <ModalFooter>
            <Button colorScheme="blue" mr={3} onClick={onViewClose}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>

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
              key={student.id}
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
                  onClick={() => {
                    editStudent(student);
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
