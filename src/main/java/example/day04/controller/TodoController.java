package example.day04.controller;

import example.day04.model.dto.TodoDto;
import example.day04.model.entity.TodoEntity;
import example.day04.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day04/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 1. 등록
    @PostMapping
    public TodoDto todoSave(@RequestBody TodoDto todoDto){
        return todoService.todoSave(todoDto);
    }

    // 2. 전체조회
    @GetMapping
    public List<TodoDto> todoFindAll(){
        return todoService.todoFindAll();
    }

    // 3. 개별 조회
    @GetMapping("/view")
    public TodoDto todoFindById(@RequestParam int id){
        return todoService.todoFindById(id);
    }

    // 4. 개별 수정
    @PutMapping
    public TodoDto todoUpdate(@RequestBody TodoDto todoDto){
        return todoService.todoUpdate(todoDto);
    }

    // 5. 개별 삭제
    @DeleteMapping
    public boolean todoDelete(@RequestParam int id){
        return todoService.todoDelete(id);
    }

}
