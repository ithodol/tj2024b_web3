package example.day01._과제;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    // 등록
    public  boolean post(TaskEntity taskEntity){
        System.out.println("TaskService.post");
        System.out.println("taskEntity = " + taskEntity);

        TaskEntity taskEntityList = taskRepository.save(taskEntity);
        return true;
    }



    // 전체 조회
    public List<TaskEntity> get(){
        List<TaskEntity> TaskList = taskRepository.findAll();
        return TaskList;
    }


    // 특정도서 정보 수정
    public boolean put(TaskEntity taskEntity){
        taskRepository.save(taskEntity);
        return true;
    }


    // 특정 도서 삭제
    public boolean delete(int id){
        taskRepository.deleteById(id);
        return true;
    }
}
