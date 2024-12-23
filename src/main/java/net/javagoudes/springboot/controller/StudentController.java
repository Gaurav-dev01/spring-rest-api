package net.javagoudes.springboot.controller;

import net.javagoudes.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("studentInfo")
public class StudentController {

    //    http://localhost:8080/student
//    return java bean
/*    @GetMapping("oneStudInfo")
    public Student getStudent() {
        Student student = new Student(1, "Gaurav", "Chaudhari");

        return student;

    }*/

    //    ResponseEntity(c) - is a generic class used to send HTTP status-code at the time of returning.
//    using ResponseEntity(c), above method write as follows:
    @GetMapping("respStudentInfo")
    public ResponseEntity<Student> getStudentInfo() {
        Student student = new Student(1, "Gaurav", "Chaudhari");

//        below both return way are correct
//        way first
//        return new ResponseEntity<>(student, HttpStatus.OK);

//        way second
//        return ResponseEntity.ok(student);

//        by using custome header:
        return ResponseEntity.ok().header("custom-header", "gaurav").body(student);
    }


//    Get the student details

    /*@GetMapping("studentList")
    public List<Student> getStudents() {
        List<Student> listOfStudent = new ArrayList<Student>();
        listOfStudent = studentList();

        return listOfStudent;
    }*/
//    ResponseEntity - is used to send HTTP status or HTTP code with the response. It is a generic HTTP response class.
//    above method by using ResponseEntity(c)

    @GetMapping("respStudentList")
    public ResponseEntity<List<Student>> respGetStudentList() {
        List<Student> studList = new ArrayList<Student>();
        studList = studentList();

        return ResponseEntity.ok().header("custom-header", "Gaurav Chaudhari").body(studList);
    }

    //    Return a list of student
    public List<Student> studentList() {
        List<Student> students = Arrays.asList(new Student(1, "Gaurav", "Chaudhari"),
                new Student(2, "Neha", "Chauhan"),
                new Student(3, "Shweta", "Verma"),
                new Student(3, "Payal", "Shivpuje"),
                new Student(4, "Neelam", "Ramchandani"),
                new Student(5, "Kanchan", "Rajput"),
                new Student(6, "Ritu", "Patil"));

        System.out.println("\nGiven student list: ");
        students.forEach(student -> System.out.println(student));

        return students;
    }

//    REST API with @PathVariable use: it used to bind the variable from URI with the method parameter.

    @GetMapping("{id}")
    public Student studentpathVariable(@PathVariable int id) {
        return new Student(id, "Gaurav", "Chaudhari");
    }

    //        REST API with RequestParam: it is used to handled or extract multiple query parameter in a request URL
//    URL = http://localhost:8080/students/query?id=1&firstName=Gaurav&lastName=Chaudhari
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

//    REST API that handled HTTP POST request
//    @PostMapping - is used to create a new resource.
//    @RequestBody - is responsible to retrieving the HTTP request body and automatically convert JSON object into Java object. Internally it uses HTTP MessageConverters to convert the request's body.

    /*@PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;

    }*/

    //    above method using ResponseEntity(c)
    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);

    }

    //    @PutMapping - used to updating existing resource.
/*    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable int id) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;

    }*/

    //    above method using ResponseEntity(c)
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return ResponseEntity.ok(student);

    }

    //    @DeleteMapping - used to deleting existing resource.
   /* @DeleteMapping("students/{id}")
    public String deleteStudent(@PathVariable int id) {
        System.out.println("\nStudent id = " + id);
        return "Student with id: " + id + " deleted successfully.!";

    }*/

    //    above method using ResponseEntity(c)
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        System.out.println("\nStudent id = " + id);
        return ResponseEntity.ok("Student with id: " + id + " deleted successfully.!");
    }
}