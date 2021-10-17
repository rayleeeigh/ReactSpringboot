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
  Spacer,
  Grid,
} from "@chakra-ui/react";
import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Index() {
  //Student
  const [id, setID] = useState("");
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
  const [student, setStudent] = useState({});

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

  //Contact
  const [contactAddress, setContactAddress] = useState("");
  const [contactFirstname, setContactFirstname] = useState("");
  const [contactLastname, setContactLastname] = useState("");
  const [contactNumber, setContactNumber] = useState("");
  const [contacts, setContacts] = useState([]);

  //Subject
  const [subjectname, setSubjectName] = useState("");
  const [subjectView, setSubjectView] = useState([]);
  const [subjectStudents, setSubjectStudents] = useState([]);
  //Checkboxes
  const [studentCheck, setStudentCheck] = useState(true);
  const [instructorCheck, setInstructorCheck] = useState(true);
  const [subjectCheck, setSubjectCheck] = useState(true);
  const toast = useToast();

  //Search State
  const [search, setSearch] = useState("");

  //Student CRUD
  const addStudent = (e) => {
    e.preventDefault();
    const student = {
      firstName: firstname,
      lastName: lastname,
      email: email,
      course: course,
      year: year,
      instructor_id: null,
    };

    axios
      .put(
        "http://localhost:8080/instructor/assignInstructor/" + instructor,
        student
      )
      .then(() => {
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
    student.firstName = updateFirstName;
    student.lastName = updateLastName;
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
      firstName: instructorfirstname,
      lastName: instructorlastname,
      email: instructoremail,
    };

    axios.post("http://localhost:8080/instructor/add", instructorr).then(() => {
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
  function subjectOpen(studentID) {
    onSubjectOpen();
    axios
      .get("http://localhost:8080/student/view/" + studentID)
      .then((response) => {
        setStudent(response.data);
      });
    axios.get("http://localhost:8080/subject/view").then((response) => {
      setSubjects(response.data);
    });
  }

  const addSubject = (e) => {
    e.preventDefault();
    const subjectt = {
      name: subjectname,
    };

    axios.post("http://localhost:8080/subject/add", subjectt).then(() => {
      toast({
        title: "Subject Add",
        description: "Added Subject Successfully!",
        position: "top",
        status: "success",
        duration: "5000",
        isClosable: "false",
      });
      axios.get("http://localhost:8080/subject/view").then((response) => {
        setSubjects(response.data);
      });
      onAddSubjectClose();
    });
  };

  function addSubjectToStudent(subjectID, studentID) {
    axios
      .put(
        "http://localhost:8080/subject/enroll/" +
          subjectID +
          "/students/" +
          studentID
      )
      .then(() => {
        toast({
          title: "Subject Add",
          description: "Added Subject Successfully!",
          position: "top",
          status: "success",
          duration: "5000",
          isClosable: "false",
        });
        viewStudentsFromSubject(studentID);
        onSubjectClose();
      });
  }

  function removeSubjectFromStudent(subjectID, studentID) {
    axios
      .put(
        "http://localhost:8080/student/student/" +
          studentID +
          "/deletedSubject/" +
          subjectID
      )
      .then(() => {
        toast({
          title: "Subject Removed",
          description: "Remove Subject Successfully!",
          position: "top",
          status: "success",
          duration: "5000",
          isClosable: "false",
        });
        axios.get("http://localhost:8080/student/view").then((response) => {
          setStudents(response.data);
        });
        onViewSubjectClose();
      });
  }

  //Contact CRUD
  const addContact = (e) => {
    e.preventDefault();
    const contactt = {
      firstname: contactFirstname,
      lastName: contactLastname,
      number: contactNumber,
      address: contactAddress,
    };

    axios.post("http://localhost:8080/contact/add", contactt).then(() => {
      toast({
        title: "Contact Add",
        description: "Added Contact Successfully!",
        position: "top",
        status: "success",
        duration: "5000",
        isClosable: "false",
      });
      // axios.get("http://localhost:8080/contact/view").then((response) => {
      //   setSubjects(response.data);
      // });
      onAddSubjectClose();
    });
  };

  //View Functions
  function viewStudents() {
    if (!studentCheck) {
      axios.get("http://localhost:8080/student/view").then((response) => {
        setStudents(response.data);
      });
    } else {
      setStudents([]);
    }
    setStudentCheck(!studentCheck);
  }

  function viewStudentsFromSubject(studentID) {
    onViewSubjectOpen();
    axios
      .get("http://localhost:8080/subject/allSubjects/student/+" + studentID)
      .then((response) => {
        setSubjectView(response.data);
        setID(studentID);
      });
  }

  function viewInstructors() {
    if (!instructorCheck) {
      axios.get("http://localhost:8080/instructor/view").then((response) => {
        setInstructors(response.data);
      });
    } else {
      setInstructors([]);
    }
    setInstructorCheck(!instructorCheck);
  }

  function viewSubjects() {
    if (!subjectCheck) {
      axios.get("http://localhost:8080/subject/view").then((response) => {
        setSubjects(response.data);
      });
    } else {
      setSubjects([]);
    }
    setSubjectCheck(!subjectCheck);
  }

  //Search Function
  const searchStudents = (e) => {
    setSearch(e.target.value);
    axios
      .get("http://localhost:8080/student/viewStudent/get?name=" + search)
      .then((response) => {
        setStudents(response.data);
      });
  };

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

  useEffect(() => {
    axios.get("http://localhost:8080/contact/view").then((response) => {
      setContacts(response.data);
    });
  }, []);

  useEffect(() => {
    axios.get("http://localhost:8080/contact/view").then((response) => {
      setContacts(response.data);
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

  const {
    isOpen: isContactOpen,
    onOpen: onContactOpen,
    onClose: onContactClose,
  } = useDisclosure();

  const {
    isOpen: isViewSubjectOpen,
    onOpen: onViewSubjectOpen,
    onClose: onViewSubjectClose,
  } = useDisclosure();

  const {
    isOpen: isAddSubjectOpen,
    onOpen: onAddSubjectOpen,
    onClose: onAddSubjectClose,
  } = useDisclosure();

  return (
    <Box bg="gray.500" h="100vh" w="100%">
      <Center>
        <Grid templateColumns="repeat(4,1fr)" gap={4} paddingBottom="20px">
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
                  <Select
                    placeholder="Select Instructor"
                    onChange={(e) => setInstructor(e.target.value)}
                  >
                    {instructors.map((instructor) => (
                      <option key={instructor.id} value={instructor.id}>
                        {instructor.firstName} {instructor.lastName}
                      </option>
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
                    {student.firstName}'s Subjects
                  </Heading>
                  {subjects.map((subject) => (
                    <Stack mt={8} direction={"row"} spacing={4}>
                      <Text>{subject.name}</Text>
                      <Spacer />
                      <Button
                        bg={"green.400"}
                        color={"white"}
                        _hover={{
                          bg: "blue.500",
                        }}
                        onClick={() =>
                          addSubjectToStudent(subject.id, student.id)
                        }
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

          <Button onClick={onAddSubjectOpen}>Add Subject</Button>

          <Modal isOpen={isAddSubjectOpen} onClose={onAddSubjectClose}>
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
                    Add Subject
                  </Heading>
                  <Text fontSize={{ base: "sm", sm: "md" }}>Subject Name</Text>
                  <FormControl>
                    <Input
                      placeholder="Subject Name"
                      type="text"
                      value={subjectname}
                      onChange={(e) => setSubjectName(e.target.value)}
                    />
                  </FormControl>
                </Stack>
              </ModalBody>

              <ModalFooter>
                <Button colorScheme="blue" mr={3} onClick={onAddSubjectClose}>
                  Close
                </Button>
                <Button
                  bg={"green.400"}
                  color={"white"}
                  _hover={{
                    bg: "blue.500",
                  }}
                  onClick={addSubject}
                >
                  Add
                </Button>
              </ModalFooter>
            </ModalContent>
          </Modal>

          <Button onClick={onContactOpen}>Add Contacts</Button>

          <Modal isOpen={isContactOpen} onClose={onContactClose}>
            <ModalOverlay />
            <ModalContent>
              <ModalHeader>Add Contact</ModalHeader>
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
                    Add Contact
                  </Heading>
                  <Text fontSize={{ base: "sm", sm: "md" }}>
                    Contact First Name
                  </Text>
                  <FormControl>
                    <Input
                      placeholder="First Name"
                      type="text"
                      value={contactFirstname}
                      onChange={(e) => setContactFirstname(e.target.value)}
                    />
                  </FormControl>
                  <Text fontSize={{ base: "sm", sm: "md" }}>
                    Contact Last Name
                  </Text>
                  <FormControl>
                    <Input
                      placeholder="Last Name"
                      type="text"
                      value={contactLastname}
                      onChange={(e) => setContactLastname(e.target.value)}
                    />
                  </FormControl>
                  <Text fontSize={{ base: "sm", sm: "md" }}>
                    Contact Address
                  </Text>
                  <FormControl>
                    <Input
                      placeholder="Address"
                      type="text"
                      value={contactAddress}
                      onChange={(e) => setContactAddress(e.target.value)}
                    />
                  </FormControl>
                  <Text fontSize={{ base: "sm", sm: "md" }}>
                    Contact Number
                  </Text>
                  <FormControl>
                    <Input
                      placeholder="Contact Number"
                      type="text"
                      value={contactNumber}
                      onChange={(e) => setContactNumber(e.target.value)}
                    />
                  </FormControl>
                </Stack>
              </ModalBody>

              <ModalFooter>
                <Button colorScheme="blue" mr={3} onClick={onAddSubjectClose}>
                  Close
                </Button>
                <Button
                  bg={"green.400"}
                  color={"white"}
                  _hover={{
                    bg: "blue.500",
                  }}
                  onClick={addContact}
                >
                  Add
                </Button>
              </ModalFooter>
            </ModalContent>
          </Modal>

          <Modal isOpen={isViewSubjectOpen} onClose={onViewSubjectClose}>
            <ModalOverlay />
            <ModalContent>
              <ModalHeader>View Subject</ModalHeader>
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
                    Add Subject
                  </Heading>
                  {subjectView.map((subject) => (
                    <Stack mt={8} direction={"row"} spacing={4}>
                      <Text>{subject.name}</Text>
                      <Spacer />
                      <Button
                        bg={"green.400"}
                        color={"white"}
                        _hover={{
                          bg: "blue.500",
                        }}
                        onClick={() => removeSubjectFromStudent(subject.id, id)}
                      >
                        Remove
                      </Button>
                    </Stack>
                  ))}
                </Stack>
              </ModalBody>

              <ModalFooter>
                <Button colorScheme="blue" mr={3} onClick={onAddSubjectClose}>
                  Close
                </Button>
                <Button
                  bg={"green.400"}
                  color={"white"}
                  _hover={{
                    bg: "blue.500",
                  }}
                  onClick={addSubject}
                >
                  Add
                </Button>
              </ModalFooter>
            </ModalContent>
          </Modal>
        </Grid>
      </Center>
      <Center>
        <Flex display="block" align={"center"} justify={"center"} bg="gray.500">
          <Checkbox
            isChecked={studentCheck}
            onChange={viewStudents}
            value="student"
          >
            Student's Name
          </Checkbox>
          <Checkbox
            isChecked={instructorCheck}
            onChange={viewInstructors}
            value="instructor"
          >
            Instructor
          </Checkbox>
          <Checkbox
            isChecked={subjectCheck}
            onChange={viewSubjects}
            value="subject"
          >
            subject
          </Checkbox>
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
          <Heading>Students</Heading>
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
                  onClick={() => subjectOpen(student.id)}
                >
                  Add subjects
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
                  onClick={() => viewStudentsFromSubject(student.id)}
                >
                  View Subjects
                </Button>
              </Stack>
            </Box>
          ))}
          <Heading>Instructors</Heading>
          <Stack spacing={4} w={"full"} maxW={"80%"} display="inline-block">
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
                key={instructor.id}
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
                  {instructor.firstName} {instructor.lastName}
                </Heading>
                <Text textAlign={"center"} px={3}>
                  Email: {instructor.email}
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
          </Stack>
          <Heading>Subjects</Heading>
          <Stack spacing={4} w={"full"} maxW={"80%"} display="inline-block">
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
                  {subject.name}
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
          <Heading>Contacts</Heading>
          <Stack spacing={4} w={"full"} maxW={"80%"} display="inline-block">
            {contacts.map((contact) => (
              <Box
                maxW={"320px"}
                w={"full"}
                bg="gray.300"
                boxShadow={"2xl"}
                rounded={"lg"}
                p={6}
                textAlign={"center"}
                display="inline-block"
                key={contact.id}
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
                  {contact.firstname} {contact.lastName}
                </Heading>
                <Text textAlign={"center"} px={3}>
                  Number: {contact.number}
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
          </Stack>
        </Stack>
      </Flex>
    </Box>
  );
}
