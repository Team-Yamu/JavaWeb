# 필수 사항
> - Pull Request 필수
> - 실행되지 않기 때문에 코드만 참고
> - 현재 test기능만 추가
>   - src/net 하위 폴더,파일은 서블릿 파일들
>   - web/views/test/ 하위 파일은 뷰용
<br><br>
# test3 예제 내용
> - ## **요청**<br>
  사용자 입력페이지 링크 이동 -> 현재 db의 단어 데이터를 list 형태로 뷰로 전달
  <br> -> 뷰에서는 입력 부분과 단어 리스트를 보여줌
  <br> 사용자 단어 입력 -> db에 있는 데이터 인지 판단 -> 
  <br>case 있는 경우 : db의 json 데이터를 파싱 -> 뷰로 파싱한 데이터 전달
  <br>case 없는 경우 : python 을 호출 -> db에 데이터를 insert -> json 형태 string 파싱 ->  뷰로 전달
