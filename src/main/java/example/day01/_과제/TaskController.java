package example.day01._과제;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day01/taskbook")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // 등록
    @PostMapping
    public boolean post(@RequestBody TaskEntity taskEntity){
        boolean result = taskService.post(taskEntity);
        return result;
    }

    // 전체 조회
    @GetMapping
    public List<TaskEntity> get(){
        return taskService.get();
    }


    // 특정도서 정보 수정
    @PutMapping
    public boolean put(@RequestBody TaskEntity taskEntity){
        boolean result = taskService.put(taskEntity);
        return result;
    }


    // 특정 도서 삭제
    @DeleteMapping
    public boolean delete(@RequestParam int id){
        boolean result = taskService.delete(id);
        return result;
    }
}
