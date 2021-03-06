package com.example.server.controller;
import com.example.server.exception.StudentNotFoundException;
import com.example.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.server.service.StudentService;


import java.util.List;

@RestController //used to create RESTful APIs. Combination of @Controller and @ResponseBody
@RequestMapping("/student") //used to map HTTP requests to handler methods.
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student){ //@RequestBody binds the HTTP request body to the handler method paramater "student"
        studentService.saveStudent(student);
    }

    @GetMapping("/view/{id}")
    public Student getStudentById(@PathVariable("id") Integer id){
        Student student = studentService.findById(id).orElseThrow(()->new StudentNotFoundException("Student with "+id+" is not found!"));
        return student;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentService.removeStudent(id);
    } //@PathVariable indicates that the method parameter "id" will be retrieved from request URI Template

    @GetMapping("/view") // or @RequestMapping("/view",method = RequestMethod.GET). it maps our GET request.
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")//or @RequestMapping(method = RequestMethod.PUT) used for mapping our HTTP request "update/{id}" onto updateStudent() handler method.
    public void update(@PathVariable Integer id, @RequestBody Student student){
        studentService.updateStudent(id,student);
    }

    @GetMapping("/viewStudent/{name}")
    public List<Student> viewStudents(@RequestParam String name){
        List<Student> student = studentService.searchStudent(name);
        return student;
    }

    @PutMapping("/addInstructor/{instructorID}/students/{studentID}")
    public Student assignInstructorToStudent(
            @PathVariable int instructorID,
            @PathVariable int studentID
    ){
        System.out.println(instructorID);
        return studentService.assignInstructor(instructorID,studentID);
    }

    @PutMapping("/enroll/{subjectID}/students/{studentID}")
    public Student enrollStudentSubject(
            @PathVariable int subjectID,
            @PathVariable int studentID
    ){
        return studentService.enrollStudent(subjectID,studentID);
    }

    //this function will assign an existing contact for a student//
    @PutMapping("/contact/{contactID}/students/{studentID}")
    public Contact assignContactStudent(
            @PathVariable int contactID,
            @PathVariable int studentID
    ){
        return studentService.assignContact(contactID,studentID);
    }

    //this function will get all the students from a specific subject//
    @GetMapping("/view/subject/{subjectID}")
    public List<Student> getAllStudents(@PathVariable int subjectID){
        return studentService.getAllStudentsFromSubject(subjectID);
    }

    //this function will remove a subject from the student's list of subjects//
    @PutMapping("/student/{studentID}/deletedSubject/{subjectID}")
    public String deleteSubjectFromStudent(@PathVariable int studentID, @PathVariable int subjectID){
        return studentService.removeSubjectFromStuds(subjectID,studentID);
    }

    //this function will remove a contact from a student//
    @PutMapping("/deleteContact/{contactID}/students/{studentID}")
    public String deleteContactFromStudent(@PathVariable int contactID, @PathVariable int studentID){
        return studentService.removeContactFromStudent(contactID,studentID);
    }

    @GetMapping("/getThem")
    public List<Student> getAllStudentThem(){
        return studentService.getAllStudentByEntityManager();
    }

    @GetMapping("/getThem2/{studentID}")
    public Student getTheStudent(@PathVariable int studentID){
        return studentService.getAllStudentUsingCriteria(studentID);
    }

    @GetMapping("/findByName/{name}")
    public List<Student> findTheStudent(@PathVariable String name){
        return studentService.findStudentByName(name);
    }
}
