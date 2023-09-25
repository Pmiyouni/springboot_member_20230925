package com.icia.member.service;

import com.icia.member.dto.MemberDTO;
import com.icia.member.entity.MemberEntity;
import com.icia.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        return memberRepository.save(memberEntity).getId();
    }

    public boolean login(MemberDTO memberDTO) {
     /*
            DB에서 로그인하는 사용자의 이메일로 조회한 결과를 가져와서
            비밀번호가 일치하는지 비교한 뒤 로그인 성공 여부를 판단

            findByMemberEmail()
            select * from member_table where member_email = ?

            findById()
            => select * from member_table where id = ?
         */
        // 1.
//        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberDTO.getMemberEmail())
//                                                    .orElseThrow(() -> new NoSuchElementException());
        // 2. email, password 둘다 만족하는 조회결과가 있다면 로그인성공, 없다면 로그인실패
        Optional<MemberEntity> optionalMemberEntity =
                memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword());
        if (optionalMemberEntity.isPresent()) {
//            MemberEntity memberEntity = optionalMemberEntity.get();
            return true;
        } else {
            return false;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
//        for(MemberEntity memberEntity: memberEntityList)
//            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        //memberDTOList.add(MemberDTO.toDTO(memberEntity));
        memberEntityList.forEach(entity -> {
            memberDTOList.add(MemberDTO.toDTO(entity));
        });
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            return MemberDTO.toDTO(memberEntity);
        } else {
            return null;
        }
        //MemberEntity memberEntity=memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        // return MemberDTO.toDTO(memberEntity);

    }

    public void update(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
        memberRepository.save(memberEntity);

    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }




//    public String emailCheck(String memberEmail) {
//        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
//        if (byMemberEmail.isPresent()) {
//            return null;
//        } else {
//            return "ok";
//        }
//    }
}
