package com.icia.member.entity;

import com.icia.member.dto.MemberDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
@Table(name="member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPassword;
    @Column(nullable = false)
    private String memberName;
    @Column
    private String memberBirth;
    @Column
    private String memberMobile;


    //dto->entity 변환매서드
    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        //입력은 MemberDTO 출력은 MemberEntity
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirth(memberEntity.getMemberBirth());
        memberEntity.setMemberMobile(memberEntity.getMemberMobile());
        return memberEntity;
    }
    public static MemberEntity toUpdateEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirth(memberEntity.getMemberBirth());
        memberEntity.setMemberMobile(memberEntity.getMemberMobile());
        return memberEntity;
    }

}







