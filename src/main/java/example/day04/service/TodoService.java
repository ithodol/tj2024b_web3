package example.day04.service;

import example.day04.model.Repository.TodoRepository;
import example.day04.model.dto.TodoDto;
import example.day04.model.entity.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 1. 등록
    public TodoDto todoSave( TodoDto todoDto){
        // (1) dto -> entity 변환
        TodoEntity todoEntity = todoDto.toEntity();
        // (2) entity 를 save(영속화/db레코드 매칭/등록)한다
        TodoEntity saveEntity = todoRepository.save(todoEntity);
        // (3) save 로 부터 반환된 엔티티(영속화)된 결과가 존재하면
        if(saveEntity.getId() >= 1){ // id가 존재하면 (save를 했으면)
            return saveEntity.toDto(); // entity를 dto로 변환하여 반환
        }else {
            return null; // 존재하지 않으면 null
        }
    }


    // 2. 전체조회
    public List<TodoDto> todoFindAll(){
        // ============================== [방법1] 일반 반복문 ==============================
        // (1) 모든 entity 조회, findAll()
        List<TodoEntity> todoEntityList = todoRepository.findAll();
        // (2) 모든 entity 리스트를 dto 리스트로 변환한다
        List<TodoDto> todoDtoList = new ArrayList<>(); // (2-1) dto 리스트 생성
        for (int index = 0; index < todoEntityList.size(); index++){ // (2-2) entity 리스트 순회
            TodoDto todoDto = todoEntityList.get(index).toDto(); // (2-3) index번째 entity를 dto로 변환
            todoDtoList.add(todoDto); // (2-4) dto 리스트에 저장
        }
        // (3) 결과 반환
        return todoDtoList;

        // ============================== [방법2] stream ==============================
//        return todoRepository.findAll().stream().map(TodoEntity::toDto).collect(Collectors.toList());
    }

    // 3. 개별 조회
    public TodoDto todoFindById(int id) {
        // ============================== [방법1] 일반적인 ==============================
        // (1) pk 이용한 entity 조회, .findById()
        // Optional 클래스 : null 제어하는 메소드들을 제공하는 클래스
        Optional<TodoEntity> oprional = todoRepository.findById(id);
        // (2) 조회한 결과가 존재하면 / .isPresent()
        TodoEntity todoEntity;
        if (oprional.isPresent()) {
            // (3) Optional 에서 entity 꺼내기
            todoEntity = oprional.get();
            // (4) dto 변환
            TodoDto todoDto = todoEntity.toDto();
            // (5) 결과 반환
            return todoDto;
        }
        return null;

        // ============================== [방법2] stream ==============================
//        return todoRepository.findById(id)
//                .map(TodoEntity::toDto) // optional의 데이터가 null이 아니면 map 실행하여 dto로 변환 후 반환
//                .orElse(null); // optional의 데이터가 null이면 null 반환
    }


    // 4. 개별 수정 + @Transactional
    public TodoDto todoUpdate(TodoDto todoDto) {
        // ============================== [방법1] 일반적인 ==============================
        // (1) 수정할 id로 엔티티 조회
        Optional<TodoEntity> optional = todoRepository.findById(todoDto.getId());
        // (2) 존재하면 수정, 존재하지 않으면 null 반환
        if(optional.isPresent()){
            // (3) 엔티티 꺼내기
            TodoEntity todoEntity = optional.get();
            // (4) 엔티티 수정 / 입력받은 dto 값을 엔티티에 대입하기
            todoEntity.setTitle(todoDto.getTitle());
            todoEntity.setContent(todoDto.getContent());
            todoEntity.setDone(todoDto.isDone()); // boolean 의 getter / getXXX [x], isXXX[O]
            return todoEntity.toDto(); // dto로 변환 후 반환
        }
        return null;

        // ============================== [방법2] stream ==============================
//        return todoRepository.findById(todoDto.getId())
//                .map( // findById 결과의 optional 데이터가 존재하면
//                        entity -> { // 람다식 함수 / () -> {}
//                            entity.setTitle(todoDto.getTitle());
//                            entity.setContent(todoDto.getContent());
//                            entity.setDone(todoDto.isDone());
//                            return entity.toDto();
//                })
//                // findById 결과의 optional 데이터가 존재하지 않으면
//                .orElse(null);
    }


    // 5. 개별 삭제
    public boolean todoDelete(int id){
        // ============================== [방법1] 일반적인 ==============================
        // (1) id를 이용하여 엔티티 (존재여부)조회하기
            // findById 반환타입 : Optional / existsById(존재하는지) 반환타입 : boolean
        boolean result = todoRepository.existsById(id);
        // (2) 만약 존재하면
        if(result == true){
            // (3) 영속성 제거 / .deleteById(pk);
            todoRepository.deleteById(id);
            return true; // 삭제 성공
        }
        return false; // 존재하지 않으면 삭제 실패

        // ============================== [방법2] stream ==============================
//        return todoRepository.findById(id)
//                .map((entity) -> {
//                    todoRepository.deleteById(id);
//                    return true;
//                })
//                .orElse(false);
    }

}
