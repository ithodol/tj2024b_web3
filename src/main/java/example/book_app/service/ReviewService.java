package example.book_app.service;

import example.book_app.model.dto.ReviewDto;
import example.book_app.model.entity.BookEntity;
import example.book_app.model.entity.ReviewEntity;
import example.book_app.model.repository.BookRepository;
import example.book_app.model.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewDto rCreate(ReviewDto reviewDto){
        BookEntity bookEntity = bookRepository.findById(reviewDto.getId())
                .orElse(null);
        if (bookEntity == null){
            return null;
        }

        ReviewEntity reviewEntity = reviewDto.toEntity(bookEntity);

        ReviewEntity create = reviewRepository.save(reviewEntity);
        return create.toDto();
    }

    public List<ReviewDto> rFindById(int id){
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity == null) return Collections.emptyList();

        List<ReviewEntity> reviewEntities = reviewRepository.findByBookId(id);

        List<ReviewDto> idReviewList = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntities) {
            idReviewList.add(reviewEntity.toDto());
        }
        return idReviewList;
    }

    public boolean rDelete(int id){
        boolean result = reviewRepository.existsById(id);
        if (result == true) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
