# [7. JPA 객체지향쿼리](https://www.youtube.com/watch?v=bEtTpCviSc4&list=PL9mhQYIlKEhfpMVndI23RwWTL9-VL-B7U&index=7)

# JPA는 다양한 쿼리 방법을 지원

## **JPQL**(Java Persistence Query Language)

- 가장 단순한 조회 방법
- SQL이랑 똑같다! 하지만 객체를 대상으로 한다는 차이가 있다.
  - SQL은 테이블을 대상으로 한다
- DB 벤더에 종속적이지 않다
  - 해당 DB의 방언으로 변환됨
- 엔티티와 속성은 **대소문자 구분**함!
- FROM절에 테이블명이 아닌 클래스명!
  - 별칭은 필수!
- 객체그래프 탐색
  - ex) member.getTeam().getMembers()

  ```java
  // 검색
  // Member Table이 아니라 Member Entity!
  String jpql = "SELECT m FROM Member m WHERE m.name LIKE '%양희찬%'"
  
  List<Member> resultMembers = em.createQuery(jpql, Member.class).getResultList();
  ```

### 프로젝션

- **엔티티 프로젝션**
  - SELECT m FROM Member m
  - SELECT m.**team** FROM Member m
- **단순 값 프로젝션**
  - SELECT m.username, m.age FROM Member m
- **new 명령어** : 단순 값을 DTO로 바로 조회
  - SELECT **new** jpabook.jpql.UserDTO(m.username, m.age) FROM Member m
- DISTINCT로 중복 제거

### 페이징 API

- setFirstResult(int startPosition)
  - 시작할 위치
- setMaxResult(int maxResult)
  - 조회할 데이터 수

### 조인

- 내부조인
  - SELECT m FROM Member m [INNER] JOIN m.team t
- 외부조인
  - SELECT m FROM Member m LEFT [OUTER] JOIN m.team t
- 세타조인
  - SELECT COUNT(m) FROM Member m, Team t WHERE m.username = t.name
- 페치조인 : 엔티티 객체 그래프를 조회하는 방법
  - 현업에서 진짜 많이 씀
  - LAZY Loading 시의 N + 1문제 해결!
    - N(요소마다 다시 SELECT) + 1(처음 SELECT)
  - 일단 LAZY Loading으로 바른 다음, **최적화를 위해 한 번에 가져와야겠다!** 싶을 때 씀
  - 별칭을 사용할 수 없음
  - SELECT m FROM Member m JOIN fetch m.team t
    - m(member)을 조회하는데 t(team)까지 같이 가져옴!

### @NamedQuery

```java
// XML에도 넣을 수 있지만 요즘은 이게 대세!
// 뭘 굳이 XML까지 가냐!
@NamedQuery(
    name = "Member.findByUsername",
    query = "SELECT m FROM Member m WHERE m.username = :username"
)
public class Member{
    //...
}

List<Member> resultList = 
    em.createNamedQuery("Member.findByUsername", Member.class)
        .setParameter("username", "양희찬")
        .getResultList();
```

- 기존에는 SQL이 문자열 형태라서 **컴파일시점, 로딩시점에서 에러를 찾아내기 힘들었다**.
  - 하지만 @NamedQuery를 사용함으로써 **컴파일시점에 파싱**을 하여 에러를 찾아낸다.
- Spring Data JPA의 @Query도 @NamedQuery로 동작함!

## JPA Criteria

## **QueryDSL**

## Native SQL

## JDBC API 직접 사용

# 더 알아보자

- 세타조인
- 오라클은 페이징하기 빡셈??
  - 3중 셀렉트??