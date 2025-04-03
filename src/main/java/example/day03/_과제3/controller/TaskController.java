package example.day03._과제3.controller;

import example.day03._과제3.model.dto.CourseDto;
import example.day03._과제3.model.dto.StudentDto;
import example.day03._과제3.model.entity.CourseEntity;
import example.day03._과제3.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day03/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;


    @PostMapping("/course")
    public boolean coursePost(@RequestBody CourseDto courseDto){
        System.out.println("TaskController.coursePost");
        System.out.println("CourseDto = " + courseDto);
        return taskService.coursePost(courseDto);
    }

    @GetMapping("/course")
    public List<CourseDto> courseGet(){
        return taskService.courseGet();
    }


    @PostMapping("/student")
    public boolean studentPost(@RequestBody StudentDto studentDto){
        System.out.println("TaskController.studentPost");
        System.out.println("studentDto = " + studentDto);
        return taskService.studentPost(studentDto);
    }

    @GetMapping("/student")
    public List<StudentDto> studentGet(@RequestParam int cno) {
        return taskService.studentGet(cno);
    }
}
