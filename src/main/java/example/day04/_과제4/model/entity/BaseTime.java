package example.day04._과제4.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTime {
    // 생성 날짜시간
    @CreatedDate
    private LocalDateTime createDateTime;

    // 수정 날짜시간
    @LastModifiedDate
    private LocalDateTime updateDateTime;
}
