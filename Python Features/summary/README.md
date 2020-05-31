# README

## NLTK Result -> json format

### Req
```bash
newspaper3k
django
requests
```

### TODO:
```bash
$ pip install newspaper3k
$ pip install django
$ pip install requests
```

### USE EXAMPLE 
```bash
$ python main.py {link} {argv}
```
## Json Format
```java
"keyword" : String[]
"summary" : String
"publish_date" : String
"text" : String
"top_image" : String
"movies" : String[]
```

---
## Json 내부 내용 설명
keyword : 해당 뉴스의 키워드들을 나열한 리스트입니다

summary : 해당 뉴스의 요약본입니다

publish_date : 해당 뉴스가 쓰여진 날짜입니다.

text : 해당 뉴스의 전문입니다.

top_image : 해당 뉴스이 최상단 이미지 링크입니다.

movies : 해당 뉴스에 내포되어있는 모든 비디오 링크입니다.

### argv
      
      no argv
      print the Json
      
      -save={json_file_name}
      Default: False
      Exp : Save to the Json format in main.py folder
      
      -post={http://link_here}
      Default: False
      Exp : Post the json th link
      
