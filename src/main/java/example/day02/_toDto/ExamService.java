package example.day02._toDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {
    private final ExamEntity1Repository examEntity1Repository;

    public boolean post(ExamDto examDto){
        // 1. DTO에 영속성(자바객체<->매핑상태) 부여 X -> DTO를 ENTITY로 변환하기
        // 비영속성
        ExamEntity1 examEntity1 = examDto.toEntity();
        // 영속성
        examEntity1Repository.save(examEntity1);
        return true;
    }

    // entity -> dto 변환
    public List<ExamDto> get(){
        // 1.  모든 영속된/레코드 조회
        List<ExamEntity1> entity1List = examEntity1Repository.findAll();
        // 2. 모든 영속된 엔티티 대신에 dto를 반환함 == 스트림
        return entity1List.stream() // 스트림 생성
                .map( entity -> entity.toDto() ) // 람다식 표현식
                // .map( ExamEntity1::toDto ) // 메소드 레퍼런스 표현식
                .collect( Collectors.toList());

        // 2-1. 원초적인 방법
//        List<ExamDto> examDtos = new ArrayList<>();
//        for(int i = 0; i < entity1List.size(); i++) {
//            ExamEntity1 entity1 = entity1List.get(i);
//            ExamDto examDto = entity1.toDto();
//            examDtos.add(examDto);
//        }
//        return examDtos;
    }
}
