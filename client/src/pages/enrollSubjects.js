// import {
//   Button,
//   FormControl,
//   Flex,
//   Heading,
//   Input,
//   Stack,
//   Text,
//   Box,
//   HStack,
//   Checkbox,
//   Modal,
//   ModalOverlay,
//   ModalContent,
//   ModalHeader,
//   ModalFooter,
//   ModalBody,
//   ModalCloseButton,
//   useDisclosure,
//   Avatar,
//   useToast,
// } from "@chakra-ui/react";
// import React, { useState, useEffect } from "react";
// import axios from "axios";
// import Sample from "./sample";

// export default function EnrollSubjects() {
//   const [students, setStudents] = useState([]);
//   const [subjects, setSubjects] = useState([]);
//   const [studentCheck, setStudentCheck] = useState();
//   const [subjectCheck, setSubjectCheck] = useState();
//   const [instructorCheck, setInstructorCheck] = useState();

//   useEffect(() => {
//     axios.get("http://localhost:8080/student/view").then((response) => {
//       setStudents(response.data);
//     });
//     axios.get("http://localhost:8080/subject/getSubjects").then((response) => {
//       setSubjects(response.data);
//     });
//   }, []);

//   return (
//     <>
//       <Box bg="gray.500" h="100vh" w="100%">
//         <Flex justify="center">
//           <Box
//             spacing={4}
//             maxW={"80%"}
//             bg="gray.300"
//             rounded={"xl"}
//             boxShadow={"lg"}
//             p={6}
//             display="inline-block"
//             alignSelf="right"
//             alignContent="left"
//           >
//             <HStack spacing={10} direction="row">
//               <Checkbox size="lg" colorScheme="red" isChecked>
//                 Student
//               </Checkbox>
//               <Checkbox size="lg" colorScheme="green">
//                 Subject
//               </Checkbox>
//               <Checkbox size="lg" colorScheme="orange">
//                 Instructor
//               </Checkbox>
//             </HStack>
//           </Box>
//         </Flex>

//         <Flex minH={"100vh"} align={"center"} justify={"center"} bg="gray.500">
//           <Stack
//             spacing={4}
//             w={"full"}
//             maxW={"80%"}
//             bg="gray.300"
//             rounded={"xl"}
//             boxShadow={"lg"}
//             p={6}
//             my={12}
//             display="inline-block"
//           >
//             {students.map((student) => (
//               <Box
//                 maxW={"320px"}
//                 w={"full"}
//                 bg="gray.300"
//                 boxShadow={"2xl"}
//                 rounded={"lg"}
//                 p={6}
//                 textAlign={"center"}
//                 display="inline-block"
//                 key={student.id}
//               >
//                 <Avatar
//                   size={"xl"}
//                   alt={"Avatar Alt"}
//                   mb={4}
//                   pos={"relative"}
//                   _after={{
//                     content: '""',
//                     w: 4,
//                     h: 4,
//                     bg: "green.300",
//                     border: "2px solid white",
//                     rounded: "full",
//                     pos: "absolute",
//                     bottom: 0,
//                     right: 3,
//                   }}
//                 />
//                 <Heading fontSize={"2xl"} fontFamily={"body"}>
//                   {student.name}
//                 </Heading>
//                 <Text fontWeight={600} color={"gray.500"} mb={4}>
//                   {student.course}-{student.year}
//                 </Text>
//                 <Text textAlign={"center"} px={3}>
//                   Email: {student.email}
//                 </Text>

//                 <Stack mt={8} direction={"row"} spacing={4}></Stack>
//               </Box>
//             ))}
//           </Stack>
//         </Flex>
//       </Box>
//     </>
//   );
// }
