package com.icia.member.repository;


import com.icia.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository를 붙이지 않음, JpaRepository 상속 받으면 자동적으로 자바객체(빈)으로 생성되므로

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    //interface는 추상매서드(abstract method)만 정의함
    // 즉 실행블록(실행내용)을 정의하지 못함({}는 사용 못함)
    // 추상메서드(abstract method)
    Optional<MemberEntity>  findByMemberEmail(String memberEmail);
    // select * from member_table where member_email=?
    //jpa에서 이름 규칙 정하여 퀴리문 지정하니 이름 철자 틀리면 안됨


    //select * from member_table where member_email=? and member_password=?
    Optional<MemberEntity>  findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);


}
