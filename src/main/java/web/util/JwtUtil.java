package web.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component // Spring 컨테이너에 빈 등록
public class JwtUtil {

    // 비밀키 알고리즘 : HS256알고리즘, HS512알고리즘
    // private String secretKey = "인코딩된 HS512 비트 키";
        // (1) 개발자가 임의로 지정한 키
    //private String secretKey = "qP9sLxV3tRzWn8vMbKjUyHdGcTfEeXcZwAoLpNjMqRsTuVyBxCmZkYhGjFlDnEpQzFgXt9pMwX8Sx7CtQ5VtBvKmA2QwE3D";
        // (2) 라이브러리 이용한 임의의 키
    // Keys.secretKeyFor(SignatureAlgorithm.알고리즘명);
    private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired // 빈 주입
    private StringRedisTemplate stringRedisTemplate;

    // [1] JWT 토큰 발급
    public String createToken(String memail){
        String token = Jwts.builder()
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
        // + 중복 로그인 방지 하고자 웹서버 가 아닌 Redis 에 토큰 정보 저장 ( 분산 처리 , MSA구축 , AI 속도 등등  )
        // (1) Redis에 토큰 저장하기.  .opsForValue().set(key , value ); , .opsForValue().set( 계정식별정보 , 토큰 );
        stringRedisTemplate.opsForValue().set("JWT:"+memail , token , 24 , TimeUnit.HOURS ); // 토큰 유지시간 과 일치
        // (2) 현재 Redis에 저장된 key 들을 확인 , .keys("*") : 현재 redis의 저장된 모든 key 반환
        System.out.println( stringRedisTemplate.keys("*") );
        // (3) 현재 Reids에 저장된 특정한 key의 값 확인 .opsForValue().get( key );
        System.out.println( stringRedisTemplate.opsForValue().get("JWT:"+memail) );
        return token;
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

            // + 중복 로그인 방지 하고자 Redis 에서 최근 로그인된 토큰 확인
            String memail = claims.getSubject(); // + 현재 전달받은 토큰의 저장된 회원정보(이메일)
            // (1) 레디스에서 최신 토큰 가져오기
            String redisToken = stringRedisTemplate.opsForValue().get("JWT:"+memail);
            // (2) 현재 전달받은 토큰과 레디스에 저장된 토큰 비교  , 두 토큰이 같으면
            if( token.equals( redisToken ) ){ return  memail; } // 현재 로그인상태 정상(중복 로그인이 아니다.)
            // (3) 만약에 두 토큰이 다르면 아래 코드에 null이 리턴된다. ( 토큰 불일치 또는 중복 로그인 감지 )
            else{  System.out.println(" >> 중복 로그인 감지 또는 토큰 없음");  }

        }catch (ExpiredJwtException e){
            // 토큰이 만료 되었을 때 예외 클래스
            System.out.println(">> JWT 토큰 기한 만료 : " + e);
        }catch (JwtException e){
            // 그 외 모든 토큰 예외 클래스
            System.out.println(">> JWT 예외 : " + e);
        }
        return null; // 유효하지 않은 토큰인 경우, 오류 발생시 null 반환
    }

    // [3] 로그아웃 시 redis에 저장된 토큰 삭제 서비스
    public void deleteToken( String memail ){
        stringRedisTemplate.delete( "JWT:"+memail );
    }

}
