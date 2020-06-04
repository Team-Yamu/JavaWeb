# README


# T I P

1. 단어 자체의 오타가 있는지 확인하는 방법은 -ss 인자를 주어서 synsets 내부 pages가 0: int 이면 오타가 됩니다.

---

### Req
```bash
nltk
requests
```

### TODO:
```bash
$ python
>> import nltk
>> nltk.download()
```

### USE EXAMPLE 
```bash
$ python main.py {word} {argv}
```
## Json Format
```java
"target" : String
"hash" : String
"target_trans" : String[]
"synsets" : map
"synonyms" : String[]
"antonyms" : String[]
```
---
## Json 내부 내용 설명

### 기본 제공

target : 검색한 단어입니다.

hash : target sha256 으로 변환된 값입니다.

target_trans : target 의 뜻이 담긴 한국어 리스트입니다.

### 선택 제공

synsets : 단어에 대한 뜻, 형태소, 예문을 담은 딕셔너리의 집합체입니다. 

synonyms : 리스트 형태의 비슷한 단어의 모음입니다.

antonyms : 리스트 형태의 반대의 의미를 가진 단어의 모음입니다.

[ !! synonyms 와 antonyms 는 한 묶음 입니다. !! ]



### argv
      
      no argv
      print the Json
      
      -save={title}
        -> title_{sha256}.json
       Exp : link를 sha256으로 암호화하여 해당 암호화된 값을 가지는 .json 파일을 만듭니다.
      
      -post={http://link_here}
      Exp : Post the json th link
            json 파일을 해당 링크로 post합니다.
      
      -ss
        Set Synset
        Synset를 json 파일에 추가합니다.
      
      -soao 
        Set Synonyms & Antonyms
        Synonyms 와 Antonyms를 json 파일에 추가합니다.
      
      
```bash
# Example

$ python main.py hello -ss -soao 
$ python main.py hello -ss
$ python main.py hello -soao
$ python main.py hello
```
    
