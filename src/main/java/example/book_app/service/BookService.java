package example.book_app.service;

import com.fasterxml.jackson.core.PrettyPrinter;
import example.book_app.model.dto.BookDto;
import example.book_app.model.entity.BookEntity;
import example.book_app.model.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    // 추천 도서 등록
    public BookDto create(@RequestBody BookDto bookDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(bookDto.getPassword());
        bookDto.setPassword(encodedPassword);
        System.out.println("비밀번호 입력값: " + bookDto.getPassword());

        BookEntity bookEntity = bookDto.toEntity();
        BookEntity saveEntity = bookRepository.save(bookEntity);
        if(saveEntity.getId() >= 1){
            return saveEntity.toDto();
        }else {
            return null;
        }

    }

    // 추천 도서 전체 조회
    public List<BookDto> findAll(){
        List<BookEntity> bookEntityList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        return bookRepository.findAll().stream()
                .map(BookEntity::toDto)
                .collect(Collectors.toList());
    }

    // 추천 도서 상세 조회
    public BookDto findById(int id){
        return bookRepository.findById(id)
                .map(BookEntity::toDto)
                .orElse(null);
    }

    // 추천 도서 개별 수정
    public BookDto update(BookDto bookDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return bookRepository.findById(bookDto.getId())
                .filter(entity -> encoder.matches(bookDto.getPassword(), entity.getPassword()))
                .map(entity -> {
                            entity.setTitle(bookDto.getTitle());
                            entity.setWriter(bookDto.getWriter());
                            entity.setMemo(bookDto.getMemo());
                            return entity.toDto();
                })
                .orElse(null);
    }


    // 추천 도서 개별 삭제
    public boolean delete(int id, String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return bookRepository.findById(id)
                .filter(entity -> encoder.matches(password, entity.getPassword()))
                .map( (entity) -> {
                    bookRepository.deleteById(id);;
                    return true;
                })
                .orElse(false);
    }

}
