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
  Select,
  Checkbox,
  Center,
  Spacer
} from "@chakra-ui/react";
import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Index() {
  //Student
  const [firstname, setFirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [course, setCourse] = useState("");
  const [year, setYear] = useState(0);
  const [email, setEmail] = useState("");
  const [updateFirstName, setUpdateFirstName] = useState("");
  const [updateLastName, setUpdateLastName] = useState("");
  const [updateCourse, setUpdateCourse] = useState("");
  const [updateYear, setUpdateYear] = useState(0);
  const [updateEmail, setUpdateEmail] = useState("");

  //Instructor
  const [instructorfirstname, setInstructorFirstName] = useState("");
  const [instructorlastname, setInstructorLastName] = useState("");
  const [instructoremail, setInstructorEmail] = useState("");

  const [students, setStudents] = useState([]);
  const [instructors, setInstructors] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [instructor, setInstructor] = useState(null);
  const [estuds, setEStuds] = useState({
    id: "",
    first_name: "",
    last_name: "",
    course: "",
    year: "",
    email: "",
  });

  //Checkboxes
  const [studentCheck,setStudentCheck]=useState(true);
  const [instructorCheck,setInstructorCheck]=useState(true);
  const [subjectCheck,setSubjectCheck]=useState(true);
  const toast = useToast();

  //Search State
  const [search,setSearch]=useState("");

  //Student CRUD
  const addStudent = (e) => {
    e.preventDefault();
    const student = {
      firstName: firstname,
      lastName: lastname,
      email: email,
      course: course,
      year: year,
      instructor_id: null
    };

    axios.put("http://localhost:8080/student/enrollStudent/"+instructor,student).then(() => {
      toast({
        title: "Student Add",
        description: "Added Student Successfully!",
        position: "top",
        status: "success",
        duration: "5000",
        isClosable: "false",
      });
      axios.get("http://localhost:8080/student/view").then((response) => {
        setStudents(response.data);
      });
      onClose();
    });
  };

  const deleteStudent = (id) => {
    axios.delete("http://localhost:8080/student/delete/" + id).then(() => {
      toast({
        title: "Student Delete",
        description: "Student deleted successfully",
        position: "top",
        status: "success",
        duration: 5000,
        isClosable: false,
      });
      axios.get("http://localhost:8080/student/view").then((response) => {
        setStudents(response.data);
      });
      onClose();
    });
  };

  const updateStudent = (id) => {
    const student = {
      first_name: "",
      last_name: "",
      email,
      course,
      year,
    };
    student.first_name = updateFirstName;
    student.last_name = updateLastName;
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

  //Instructor CRUD
  const addInstructor = (e) => {
    e.preventDefault();
    const instructorr = {
      instructor_first_name: instructorfirstname,
      instructor_last_name: instructorlastname,
      instructor_email:instructoremail
    };

    axios.post("http://localhost:8080/instructor/add",instructorr).then(() => {
      toast({
        title: "Instructor Add",
        description: "Instructor Student Successfully!",
        position: "top",
        status: "success",
        duration: "5000",
        isClosable: "false",
      });
      axios.get("http://localhost:8080/instructor/view").then((response) => {
        setInstructors(response.data);
      });
      onInstructorClose();
    });
  };
  //Subject CRUD
  function subjectOpen(){
    onSubjectOpen();
  }


  //View Functions
  function viewStudents (){
    if (!studentCheck){
      axios.get("http://localhost:8080/student/view").then((response) => {
        setStudents(response.data);
      });
    }
    else{
      setStudents([]);
    }
    setStudentCheck(!studentCheck)
  }

  function viewInstructors (){
    if (!instructorCheck){
      axios.get("http://localhost:8080/instructor/view").then((response) => {
        setInstructors(response.data);
      });
    }
    else{
      setInstructors([]);
    }
    setInstructorCheck(!instructorCheck)
  }

  function viewSubjects (){
    if (!subjectCheck){
      axios.get("http://localhost:8080/subject/view").then((response) => {
        setSubjects(response.data);
      });
    }
    else{
      setSubjects([]);
    }
    setSubjectCheck(!subjectCheck)
  }

  //Search Function
  const searchStudents = (e)=>{
    setSearch(e.target.value)
    axios.get("http://localhost:8080/student/viewStudent/get?name="+search).then((response)=>{
      setStudents(response.data);
    })
  }

  //Populate Screen
  useEffect(() => {
    axios.get("http://localhost:8080/student/view").then((response) => {
      setStudents(response.data);
    });
  }, []);

  useEffect(() => {
    axios.get("http://localhost:8080/instructor/view").then((response) => {
      setInstructors(response.data);
    });
  }, []);

  useEffect(() => {
    axios.get("http://localhost:8080/subject/view").then((response) => {
      setSubjects(response.data);
    });
  }, []);

  
  //Modal
  const { isOpen, onOpen, onClose } = useDisclosure();
  const {
    isOpen: isEditOpen,
    onOpen: onEditOpen,
    onClose: onEditClose,
  } = useDisclosure();
  const {
    isOpen: isInstructorOpen,
    onOpen: onInstructorOpen,
    onClose: onInstructorClose,
  } = useDisclosure();
  
  const {
    isOpen: isSubjectOpen,
    onOpen: onSubjectOpen,
    onClose: onSubjectClose,
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
              <Text fontSize={{ base: "sm", sm: "md" }}>First Name</Text>
              <FormControl>
                <Input
                  placeholder={estuds.name}
                  type="text"
                  value={updateFirstName}
                  onChange={(e) => setUpdateFirstName(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Last Name</Text>
              <FormControl>
                <Input
                  placeholder={estuds.name}
                  type="text"
                  value={updateLastName}
                  onChange={(e) => setUpdateLastName(e.target.value)}
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
              <Text fontSize={{ base: "sm", sm: "md" }}>First Name</Text>
              <FormControl>
                <Input
                  placeholder="First Name"
                  type="text"
                  value={firstname}
                  onChange={(e) => setFirstName(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Last Name</Text>
              <FormControl>
                <Input
                  placeholder="Last Name"
                  type="text"
                  value={lastname}
                  onChange={(e) => setLastName(e.target.value)}
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
              <Select placeholder="Select Instructor" onChange={(e) => setInstructor(e.target.value)}>
              {instructors.map((instructor) => (
                <option key={instructor.id} value={instructor.instructor_id}>{instructor.instructor_first_name} {instructor.instructor_last_name}</option>
              ))}
              </Select>
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

      <Button onClick={onInstructorOpen}>Add Instructor</Button>

      <Modal isOpen={isInstructorOpen} onClose={onInstructorClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Add Instructor</ModalHeader>
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
                Add Instructor
              </Heading>
              <Text fontSize={{ base: "sm", sm: "md" }}>First Name</Text>
              <FormControl>
                <Input
                  placeholder="First Name"
                  type="text"
                  value={instructorfirstname}
                  onChange={(e) => setInstructorFirstName(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Last Name</Text>
              <FormControl>
                <Input
                  placeholder="Last Name"
                  type="text"
                  value={instructorlastname}
                  onChange={(e) => setInstructorLastName(e.target.value)}
                />
              </FormControl>
              <Text fontSize={{ base: "sm", sm: "md" }}>Email</Text>
              <FormControl>
                <Input
                  placeholder="Instructor Email"
                  type="text"
                  value={instructoremail}
                  onChange={(e) => setInstructorEmail(e.target.value)}
                />
              </FormControl>
            </Stack>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="blue" mr={3} onClick={onInstructorClose}>
              Close
            </Button>
            <Button
              bg={"green.400"}
              color={"white"}
              _hover={{
                bg: "blue.500",
              }}
              onClick={addInstructor}
            >
              Add Instructor
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>

      <Modal isOpen={isSubjectOpen} onClose={onSubjectClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Add Subjects</ModalHeader>
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
                Student's Subjects
              </Heading>
              {subjects.map((subject) => (
              <Stack mt={8} direction={"row"} spacing={4}>
                <Text>{subject.subject_name}</Text>
                <Spacer/>
                <Button
                  bg={"green.400"}
                  color={"white"}
                  _hover={{
                    bg: "blue.500",
                  }}
                >
                  Add
                </Button>
              </Stack>
              ))}
            
            </Stack>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="blue" mr={3} onClick={onSubjectClose}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
      
      <Center>
      <Flex display="block" align={"center"} justify={"center"} bg="gray.500">
          <Checkbox isChecked={studentCheck} onChange={viewStudents} value="student">Student's Name</Checkbox>
          <Checkbox isChecked={instructorCheck} onChange={viewInstructors} value="instructor">Instructor</Checkbox>
          <Checkbox isChecked={subjectCheck} onChange={viewSubjects} value="subject">subject</Checkbox>
      </Flex>
      </Center>
      <Center>
      <Input
            placeholder="Search..."
            type="text"
            value={search}
            onChange={searchStudents}
            display="block"
            w="40%"
      />
      </Center>

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
                {student.firstName} {student.lastName}
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

              <Stack mt={8} direction={"row"} spacing={4}>
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
                  onClick={subjectOpen}
                >
                  Add subjects
                </Button>
              </Stack>
            </Box>
          ))}

          <Stack
            spacing={4}
            w={"full"}
            maxW={"80%"}
            display="inline-block"
          >
          {instructors.map((instructor) => (
            <Box
              maxW={"320px"}
              w={"full"}
              bg="gray.300"
              boxShadow={"2xl"}
              rounded={"lg"}
              p={6}
              textAlign={"center"}
              display="inline-block"
              key={instructor.instructor_id}
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
                {instructor.instructor_first_name} {instructor.instructor_last_name}
              </Heading>
              <Text textAlign={"center"} px={3}>
                Email: {instructor.instructor_email}
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
                >
                  Delete
                </Button>
              </Stack>
            </Box>
          ))}

          {subjects.map((subject) => (
            <Box
              maxW={"320px"}
              w={"full"}
              bg="gray.300"
              boxShadow={"2xl"}
              rounded={"lg"}
              p={6}
              textAlign={"center"}
              display="inline-block"
              key={subject.subject_id}
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
                {subject.subject_name}
              </Heading>

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
                >
                  Delete
                </Button>
              </Stack>
            </Box>
          ))}
          </Stack>
        </Stack>
      </Flex>
    </Box>
  );
}
