from newspaper import Article
import json
import requests
from django.core.serializers.json import DjangoJSONEncoder
import hashlib
import base64

class CNewsPaper3k:
    def __init__(self, url: str, language='en'):
        if language is not None:
            self.article = Article(url=url, language=language)
        else:
            self.article = Article(url=url)
        self.article.download()
        self.article.parse()
        self.article.nlp()

        # HASHING URL
        _sha256 = hashlib.sha256()
        _sha256.update(url.encode())
        self._hash = _sha256.hexdigest()
        # HASHING DONE

        self.dictionary = {'hash': self._hash}

    def setKeyword(self) -> None:
        self.dictionary['keyword'] = self.article.keywords

    def setSummary(self) -> None:
        self.dictionary['summary'] = self.article.summary

    def setPublishDate(self) -> None:
        self.dictionary['publish_date'] = self.article.publish_date

    def setArticleText(self) -> None:
        self.dictionary['text'] = self.article.text.replace("\n", "").replace("\t", "").replace("\r", "")

    def setTopImage(self) -> None:
        base64_bytes = base64.b64encode(requests.get(self.article.top_image).content)
        base64_string = base64_bytes.decode('utf-8')
        raw_data = {"base64_top_img": base64_string}
        self.dictionary['top_image'] = raw_data

    def setMovies(self) -> None:
        self.dictionary['movies'] = self.article.movies

    def post(self, url):
        requests.post(url=url, json=json.dumps(self.dictionary, cls=DjangoJSONEncoder, ensure_ascii=False))

    def print(self):
        print(json.dumps(self.dictionary, cls=DjangoJSONEncoder, ensure_ascii=False))

    def save(self, text):
        f = open(f"{text}_{self._hash}.json", 'w', encoding='UTF-8-sig')
        f.write(json.dumps(self.dictionary, cls=DjangoJSONEncoder, ensure_ascii=False))
        f.close()
