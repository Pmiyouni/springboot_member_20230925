20230925_MemberProject 1
20230925_MemberProject
Member Project
1. 프로젝트 기본 정보
   a. project name: member_20230925
   b. package: com.icia.member
2. Class
   a. HomeController
   b. MemberController
   c. MemberService
   d. MemberRepository
   e. MemberEntity
   f. MemberDTO
3. html
   a. index.html을 제외한 다른 페이지는 memberPages 폴더에 위치함.
   b. index.html: 시작페이지(회원가입, 로그인 페이지, 목록출력을 위한 링크 있음)
   c. memberSave.html: 회원가입 페이지
   i. 회원가입정보: 계정(memberEmail), 비밀번호(memberPassword), 이름
   (memberName), 생년월일(memberBirth), 전화번호(memberMobile)
   ii. 생년월일은 input type=”date” 로 하고 DTO, Entity에서는 모두 String으로 할 것.
   d. memberLogin.html: 로그인 페이지
   e. memberList.html: 회원목록 출력 페이지
   f. memberMain.html: 로그인하면 접근하는 페이지
   g. memberUpdate.html: 수정페이지
4. urls
   url http method Function parameters 처리 후 결과 페이지
   / get 시작페이지 - index.html
   /member/save get 회원가입 페이지
   출력
- memberSave.html
  /member/save post 회원가입 처리 MemberDTO memberLogin.html
  /member/login get 로그인 페이지
  출력
- memberLogin.html
  20230925_MemberProject 2
  /member/login post 로그인 처리
  memberEmail,
  memberPassword memberMain.html
  /members get 회원목록 출력 - memberList.html
  /member/{id} get 상세조회 id memberDetail.html
  /member/axios/{id} get 상세조회(axios) id
  /member/delete/{id} get 회원삭제 id memberList.html
  /member/{id} delete
  회원삭제(axios
  delete요청)
  id
  /member/update/{id} get 수정화면 출력 id memberUpdate.html
  /member/update post 수정처리 MemberDTO memberDetail.html
  /member/{id} put
  수정처리(axios
  put요청)
  MemberDTO memberDetail.html
  /member/dup-check post 이메일 중복체크 memberEmail
1. rest API
   a. 회원조회: /member/id (get)
   b. 회원수정: /member/id (put)
   c. 회원삭제: /member/id (delete)
5. 주요 기능
   a. 회원가입을 성공하면 로그인 페이지를 출력한다.
   b. 로그인을 성공하면 memberMain.html 을 출력한다.
6. table (테이블은 따로 만드는 것이 아니라 MemberEntity로)
   a. table name: member_table
   b. column
   i. id(pk): bigint, auto_increment
   ii. member_email: varchar(50), unique, not null
   iii. member_password: varchar(20), not null
   iv. member_name: varchar(20), not null
   v. member_birth: varchar(20)
   vi. member_mobile: varchar(30)
7. 추가기능(수업으로 진행)
   a. 인터셉터(Interceptor)
   i. 로그인 하지 않은 사용자가 myPage 등으로 직접 접근을 하려고 하면 로그인 페이지
   로 넘기도록 처리
   ii. 로그인을 하고 나면 기존에 요청했던 페이지로 넘겨줌