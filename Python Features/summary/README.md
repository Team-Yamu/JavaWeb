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
"hash" : String
"keyword" : String[]
"summary" : String
"publish_date" : String
"text" : String
"top_image" : String
"movies" : String[]
```

---
## Json 내부 내용 설명


####기 제공 

hash : url 을 인코딩 후 sha256로 암호화하여 String 으로 리턴합니다.

---

####선택 제공

keyword : 해당 뉴스의 키워드들을 나열한 리스트입니다

summary : 해당 뉴스의 요약본입니다

publish_date : 해당 뉴스가 쓰여진 날짜입니다.

text : 해당 뉴스의 전문입니다.

top_image : 해당 뉴스이 최상단 이미지 링크입니다.

movies : 해당 뉴스에 내포되어있는 모든 비디오 링크입니다.

---
### argv
      
      no argv
      print the Json
      
      -save={title}
        -> title_{sha256}.json
       Exp : link를 sha256으로 암호화하여 해당 암호화된 값을 가지는 .json 파일을 만듭니다.
      
      -post={http://link_here}
      Exp : Post the json th link
            json 파일을 해당 링크로 post합니다.
            
      -all : 모든 파라미터를 추가합니다.
      
      -k : 키워드를 json 파일에 추가합니다
      
      -s : 요약본을 json 파일에 추가합니다.
      
      -p : 쓰여진 날짜를 json 파일에 추가합니다.
      
      -a : 뉴스 기사 전문을 json 파일에 추가합니다.
      
      -t : 최 상단 이미지 링크를 json 파일에 추가합니다.
      
      -m : 기사 내부의 모든 영상 링크를 json 파일에 추가합니다. (잘 안됨)
      
      
