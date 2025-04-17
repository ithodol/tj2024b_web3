package web.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Spring 컨테이너에 빈 등록
public class JwtUtil {

    // 비밀키 알고리즘 : HS256알고리즘, HS512알고리즘
    // private String secretKey = "인코딩된 HS512 비트 키";
        // (1) 개발자가 임의로 지정한 키
    //private String secretKey = "qP9sLxV3tRzWn8vMbKjUyHdGcTfEeXcZwAoLpNjMqRsTuVyBxCmZkYhGjFlDnEpQzFgXt9pMwX8Sx7CtQ5VtBvKmA2QwE3D";
        // (2) 라이브러리 이용한 임의의 키
    // Keys.secretKeyFor(SignatureAlgorithm.알고리즘명);
    private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // [1] JWT 토큰 발급
    public String createToken(String memail){
        return Jwts.builder()
                // 토큰에 넣을 내용물
                .setSubject(memail) // 로그인 성공한 회원의 이메일

                // 토큰이 발급된 날짜
                    // new Date() : 자바에서 제공하는 현재 날짜 클래스
                .setIssuedAt(new Date())

                // 토큰 만료 시간
                    // 밀리초(1000/1) / new Date(System.currentTimeMillis()) : 현재시간의 밀리초
                    // new Date(System.currentTimeMillis() * (1000 * 초 * 분 * 시))
                .setExpiration(new Date(System.currentTimeMillis() * (1000 * 60 * 60* 24))) // 1일의 토큰 유지 기간

                // 지정한 비밀키로 암호화
                .signWith(secretKey)

                // 위 정보로 JWT 토큰 생성, 반환
                .compact();
    }

    // [2] JWT 토큰 검증
    public String validateToken(String token){
        try{
            Claims claims = Jwts.parser() // 1. .parser() : JWT 토큰 검증 함수
                    // 2. 검증을 위한 비밀키 넣기
                        // .setSigningKey(비밀키)
                    .setSigningKey(secretKey)

                    // 3. 검증 실행, 검증 실패시 예외 발생
                    .build()

                    // 4. 검증할 토큰 해석, 실패시 예외 발생
                    .parseClaimsJws(token)

                    // 5. 검증된 claims 객체 생성 후 반환
                    .getBody();

            // claims 안에는 다양한 token 정보가 들어있음
            System.out.println(claims.getSubject()); // 토큰에 저장된 (로그인된)회원 이메일
            return claims.getSubject();

        }catch (ExpiredJwtException e){
            // 토큰이 만료 되었을 때 예외 클래스
            System.out.println(">> JWT 토큰 기한 만료 : " + e);
        }catch (JwtException e){
            // 그 외 모든 토큰 예외 클래스
            System.out.println(">> JWT 예외 : " + e);
        }
        return null; // 유효하지 않은 토큰인 경우, 오류 발생시 null 반환
    }

}
