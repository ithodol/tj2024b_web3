package todo.controller;

import todo.model.dto.TodoDto;
import todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day04/todos")
@RequiredArgsConstructor
@CrossOrigin("*") // 플러터 dio 테스트 용도
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

    // 6. 전체 조회 + 페이징처리
    @GetMapping("/page")
    public List<TodoDto> todoFindByPage(
            // @RequestParam(defaultValue = "초기값" ) : 만약 매개변수값이 존재하지 않으면 초기값 대입
            @RequestParam(defaultValue = "1") int page, // page : 현재 조회할 페이지 번호 / 초기값 = 1
            @RequestParam(defaultValue = "3") int size){ // size : 현재 조회할 페이지당 자료 개수 / 초기값 = 3
        return todoService.todoFindByPage(page, size);
    }

    // 7. 제목 검색 조회1 ( 입력한 값이 *일치* 한 제목 조회 )
    // http://localhost:8080/day04/todos/search1?title=운동하기
    @GetMapping("/search1")
    public List<TodoDto> search1( @RequestParam String title ){
        return todoService.search1( title );
    }
    // 8. 제목 검색 조회2 ( 입력한 값이 *포함* 된 제목 조회 )
    // http://localhost:8080/day04/todos/search2?keyword=운동
    @GetMapping("/search2")
    public List<TodoDto> search2( @RequestParam String keyword ){
        return todoService.search2( keyword );
    }







} // class end
