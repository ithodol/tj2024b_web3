package example.book_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA 영속성 감사 기능 활성화
                    // 레코드(영속) 등록/수정 시점
// https://docs.google.com/spreadsheets/d/1IH_drVit_WAR54LN4rrmpQPqSxuABwCt7NTOrNfREls/edit?gid=0#gid=0
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class);
    }
}