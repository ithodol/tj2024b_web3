package example.book_app.service;

import example.book_app.model.dto.ReviewDto;
import example.book_app.model.entity.BookEntity;
import example.book_app.model.entity.ReviewEntity;
import example.book_app.model.repository.BookRepository;
import example.book_app.model.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public ReviewDto rCreate(ReviewDto reviewDto) {
        BookEntity bookEntity = bookRepository.findById(reviewDto.getId()).orElse(null);
        if (bookEntity == null) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(reviewDto.getRpassword());
        reviewDto.setRpassword(encodedPassword);

        ReviewEntity reviewEntity = reviewDto.toEntity(bookEntity);
        ReviewEntity create = reviewRepository.save(reviewEntity);

        return create.toDto();
    }

    public List<ReviewDto> rFindById(int id){
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity == null) return Collections.emptyList();

        // 여기서 findByRid는 Optional로 반환되므로, 그에 맞게 처리
        List<ReviewEntity> reviewEntities = reviewRepository.findByRid(id)
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());

        List<ReviewDto> idReviewList = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntities) {
            idReviewList.add(reviewEntity.toDto());
        }
        return idReviewList;
    }

    public boolean rDelete(int id, int rid, String rpassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity == null) {
            return false;
        }

        return reviewRepository.findByRid(rid)
                .filter(entity -> entity.getBook().equals(bookEntity))
                .filter(entity -> encoder.matches(rpassword, entity.getRpassword()))
                .map(entity -> {
                    reviewRepository.deleteById(rid);
                    return true;
                })
                .orElse(false);
    }
}
