package example.day03._과제3.service;

import example.day03._과제3.model.dto.CourseDto;
import example.day03._과제3.model.dto.StudentDto;
import example.day03._과제3.model.entity.CourseEntity;
import example.day03._과제3.model.entity.StudentEntity;
import example.day03._과제3.model.repository.CourseRepository;
import example.day03._과제3.model.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public boolean coursePost(CourseDto courseDto){
        System.out.println("TaskService.coursePost");
        System.out.println("courseDto = " + courseDto);
        // 1. DTO -> entity 변환
        CourseEntity courseEntity = courseDto.toEntity();
        // 2. 해당 entity .save() 하기
        CourseEntity saveEntity = courseRepository.save(courseEntity); // 반환값 : 영속된 객체
        // 3. 결과 확인
        if(saveEntity.getCno() > 0){
            return true; // 만약 영속 결과 cno(과정번호)가 존재하면 성공
        }
        return false;
    }

    public List<CourseDto> courseGet(){
        // 1. 모든 과정 조회
        List<CourseEntity> courseEntityList = courseRepository.findAll();
        // 2. 모든 과정의 엔티티를 DTO로 변환
        List<CourseDto> courseDtoList = courseEntityList.stream()
                                                        .map(entity -> entity.toDto())
                                                        .collect(Collectors.toList());
        // 3.
        return courseDtoList;
    }

    public boolean studentPost(StudentDto studentDto){
        // 1. 학생 dto -> 학생 entity 변환
        StudentEntity studentEntity = studentDto.toEntity();
        // 2. 학생 entity save
        StudentEntity saveEntity = studentRepository.save(studentEntity);
        if(saveEntity.getSno() < 1){
            return false;
        }
        // 3. 특정한 과정 entity 조회
        CourseEntity courseEntity = courseRepository.findById(studentDto.getCno()). orElse(null);
        if(courseEntity == null){
            return false;
        }
        System.out.println(courseEntity);
        // 4. 등록된 학생 entity의 특정한 과정 entity 대입 <FK대입>
        saveEntity.setCourseEntity(courseEntity); // 단방향 멤버변수에 과정 엔티티 대입하기 (FK 대입)
        // 5.
        return true;
    }

    public List<StudentDto> studentGet( int cno) {
        // 1. cno 를 이용하여 과정 엔티티 찾기
        CourseEntity courseEntity = courseRepository.findById(cno).orElse(null);
        if(courseEntity == null){
            return null;
        }
        // 2. 찾은 엔터티 안에 참조 중인 학생 목록
        List<StudentEntity> studentEntityList = courseEntity.getStudentEntityList();
        // 3. DTO 변환
        List<StudentDto> studentDtoList = studentEntityList.stream()
                .map(entity -> entity.toDto())
                .collect(Collectors.toList());
        // 4.
        return studentDtoList;
    }


}
