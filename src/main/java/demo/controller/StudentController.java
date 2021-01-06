package demo.controller;

import demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentServiceImpl;

    public StudentService getStudentServiceImpl() {
        return studentServiceImpl;
    }

    public void setStudentServiceImpl(StudentService studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }
}
