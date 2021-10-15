package com.example.server.service;

import com.example.server.model.Instructor;
import com.example.server.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAllInstructors(){
       return instructorRepository.findAll();
   }

    @Override
    public Optional<Instructor> findByEmail(String email){
        return instructorRepository.findByEmail(email);
    }

    @Override
    public Optional<Instructor> findById(Integer id){
        return instructorRepository.findById(id);
    }

    @Override
    public void saveInstructor(Instructor instructor){
        Instructor newInstructor = new Instructor();
        newInstructor.setFirstName(instructor.getFirstName());
        newInstructor.setLastName(instructor.getLastName());
        newInstructor.setEmail(instructor.getEmail());

        newInstructor.setStudents(instructor.getStudents());
        Instructor savedInstructor = instructorRepository.save(newInstructor);

        if(instructorRepository.findById(savedInstructor.getId()).isPresent()){
            System.out.println("Successful");
        }else{
            System.out.println("FAIL");
        }
    }

    @Override
    public void removeInstructorById(Integer id){
        if(instructorRepository.findById(id).isPresent()){
            instructorRepository.deleteById(id);
            if(instructorRepository.findById(id).isPresent()){
                System.out.print("Failed to delete the specific record");
            }else{
                System.out.println("Successfully deleted Instructor!");
            }
        }else{
            System.out.println("No records found");
        }
    }

}
