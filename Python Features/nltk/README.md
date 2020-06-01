# README

## NLTK Result -> json format

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
"synsets" : map
"synonyms" : String[]
"antonyms" : String[]
```
---
## Json 내부 내용 설명
target : 검색한 단어입니다.

synsets : 단어에 대한 뜻, 형태소, 예문을 담은 딕셔너리의 집합체입니다. 

synonyms : 리스트 형태의 비슷한 단어의 모음입니다.

antonyms : 리스트 형태의 반대의 의미를 가진 단어의 모음입니다.

[ !! synonyms 와 antonyms 는 한 묶음 입니다. !! ]



### argv
      
      no argv
      print the Json
      
      -save={json_file_name}
      Default: False
      Exp : Save to the Json format in main.py folder
            main.py 의 위치에 json 파일을 저장합니다.
      
      -post={http://link_here}
      Default: False
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
```
    