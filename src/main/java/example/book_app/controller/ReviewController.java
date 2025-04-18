package example.book_app.controller;

import example.book_app.model.dto.BookDto;
import example.book_app.model.dto.ReviewDto;
import example.book_app.model.entity.BookEntity;
import example.book_app.model.entity.ReviewEntity;
import example.book_app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto rCreate(@RequestBody ReviewDto reviewDto){
        return reviewService.rCreate(reviewDto);
    }

    @GetMapping
    public List<ReviewDto> rFindById(@RequestParam int id){
        return reviewService.rFindById(id);
    }

    @DeleteMapping
    public boolean rDelete(@RequestParam int id, @RequestParam int rid, @RequestParam String rpassword){
        return reviewService.rDelete(id, rid, rpassword);
    }
}
