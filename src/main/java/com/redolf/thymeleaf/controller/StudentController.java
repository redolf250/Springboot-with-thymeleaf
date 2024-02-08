package com.redolf.thymeleaf.controller;

import com.redolf.thymeleaf.model.Student;
import com.redolf.thymeleaf.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/home")
    @ResponseBody
    private String home(){
        return "Welcome home!";
    }

    @GetMapping("/nav")
    private String Navigation(){
        return "top-navigation";
    }

    @GetMapping("/")
    private String redolf(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("category", service.categories());
        return "student/index";
    }

    @GetMapping("/register")
    private String register(Model model){
        model.addAttribute("student", new Student());
        return "student/register";
    }

    @GetMapping("/records")
    private String load(Model model){
        final List<Student> allStudents = service.findAllStudents();
        model.addAttribute("studentList", allStudents);
        return "student/records";
    }

    @PostMapping("/registration")
    private String registration(@Valid @ModelAttribute("student") Student student, BindingResult errors){
        if(errors.hasErrors()){
//            return new ModelAndView("student/register");
            return "redirect:/student/register";
        }else{
            service.saveStudent(student);
            ModelAndView modelAndView_ = new ModelAndView("/student/records");
            return "redirect:/student/records";
        }

    }

    @GetMapping("/search")
    private String search(@ModelAttribute("student") Student student, Model model, @RequestParam("id") int id){
        System.out.println(id);
//        final Optional<Student> studentByReference = service.findStudentByReference(student.getReference());
//        System.out.println(studentByReference);
//        model.addAttribute("firstname", studentByReference.get().getFirstname());
//        model.addAttribute("lastname", studentByReference.get().getLastname());
//        model.addAttribute("program", studentByReference.get().getProgram());
        return "redirect:/student/";
    }

    @GetMapping("/edit/{reference}")
    private ModelAndView Edit(@PathVariable("reference") String reference, Model model){
        model.addAttribute("student", service.findStudentByReference(reference));
        return new ModelAndView("student/update");
    }

    @GetMapping("/delete/{id}")
    private String Delete(@PathVariable("id") int id){
        service.deleteStudent(id);
        return "redirect:/student/records";
    }

    @GetMapping("/modal")
    private String Modal(){
        return "/student/modal";
    }

}
