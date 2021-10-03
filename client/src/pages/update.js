import React from "react";
import {
  Button,
  FormControl,
  Heading,
  Input,
  Stack,
  Text,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
} from "@chakra-ui/react";

const update = () => {
  return (
    <Modal isOpen={isEditOpen} onClose={onEditClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Edit Student</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Stack
            spacing={4}
            w={"full"}
            maxW={"md"}
            bg="gray.300"
            rounded={"xl"}
            boxShadow={"lg"}
            p={6}
            my={12}
          >
            <Heading lineHeight={1.1} fontSize={{ base: "2xl", md: "3xl" }}>
              Edit student Information
            </Heading>
            <Text fontSize={{ base: "sm", sm: "md" }}>Name</Text>
            <FormControl>
              <Input
                placeholder={student.name}
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </FormControl>
            <Text fontSize={{ base: "sm", sm: "md" }}>Course</Text>
            <FormControl>
              <Input
                placeholder={student.course}
                type="text"
                value={student.course}
                onChange={(e) => setCourse(e.target.value)}
              />
            </FormControl>
            <Text fontSize={{ base: "sm", sm: "md" }}>Year</Text>
            <FormControl>
              <Input
                placeholder={student.year}
                type="text"
                value={student.year}
                onChange={(e) => setYear(e.target.value)}
              />
            </FormControl>
            <Text fontSize={{ base: "sm", sm: "md" }}>Email Address</Text>
            <FormControl id="email">
              <Input
                placeholder={student.email}
                type="email"
                value={student.email}
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
            bg={"blue.400"}
            color={"white"}
            _hover={{
              bg: "blue.500",
            }}
            onClick={() => {
              updateStudent(student.id);
            }}
          >
            Update student
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};

export default update;
