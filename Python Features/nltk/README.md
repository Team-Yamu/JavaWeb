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


### argv
      
      no argv
      print the Json
      
      -save={json_file_name}
      Default: False
      Exp : Save to the Json format in main.py folder
      
      -post={http://link_here}
      Default: False
      Exp : Post the json th link
      
