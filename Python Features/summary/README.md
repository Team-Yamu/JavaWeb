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


### argv
      
      no argv
      print the Json
      
      -save={json_file_name}
      Default: False
      Exp : Save to the Json format in main.py folder
      
      -post={http://link_here}
      Default: False
      Exp : Post the json th link
      
