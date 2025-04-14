package example.book_app.controller;

import example.book_app.model.dto.BookDto;
import example.book_app.model.dto.ReviewDto;
import example.book_app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {
    private final ReviewService reviewService;

//    @PostMapping
//    public ReviewDto rCreate(@RequestBody ReviewDto reviewDto){
//        return reviewService.rCreate(reviewDto);
//    }
//
//    @GetMapping
//    public List<ReviewDto> rFindAll(){
//        return reviewService.rFindAll();
//    }
//
//    @DeleteMapping
//    public boolean rDelete(@RequestParam int id, @RequestParam String rpassword){
//        return reviewService.rDelete(id, rpassword);
//    }
}
