package example.day01._리포지토리;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day01/jpa")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    // 등록
    @PostMapping
    public boolean post(@RequestBody ExamEntity examEntity){
        // 영속/매핑 전
        boolean result = examService.post(examEntity);
        return result;
    }


    // 전체 조회
    @GetMapping
    public List<ExamEntity> get(){
        return examService.get();
    }

    // 수정
    @PutMapping
    public boolean put(@RequestBody ExamEntity examEntity){
        boolean result = examService.put(examEntity);
        return result;
    }

    // 삭제
    @DeleteMapping
    public boolean delete(@RequestParam String id){
        boolean result = examService.delete(id);
        return result;
    }

}
