package example.book_app.controller;

import example.book_app.model.dto.BookDto;
import example.book_app.model.entity.BookEntity;
import example.book_app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    // 추천 도서 등록
    @PostMapping
    public BookDto create(@RequestBody BookDto bookDto){
        return bookService.create(bookDto);
    }
    
    // 추천 도서 전체 조회
    @GetMapping
    public List<BookDto> findAll(){
        return bookService.findAll();
    }


    // 추천 도서 상세 조회
    @GetMapping("/view")
    public BookDto findById(@RequestParam int id){
        return bookService.findById(id);
    }
    
    // 추천 도서 개별 수정
    @PutMapping
    public BookDto update(@RequestBody BookDto bookDto){
        return bookService.update(bookDto);
    }
    
    // 추천 도서 개별 삭제
    @DeleteMapping
    public boolean delete(@RequestParam int id, @RequestParam String password){
        return bookService.delete(id, password);
    }
}
