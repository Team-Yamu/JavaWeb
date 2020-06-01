from newspaper import Article
import json
import requests
from django.core.serializers.json import DjangoJSONEncoder


class CNewsPaper3k:
    def __init__(self, url, language='en'):
        if language is not None:
            self.article = Article(url=url, language=language)
        else:
            self.article = Article(url=url)

        self.article.download()
        self.article.parse()
        self.article.nlp()
        self.dictionary = {}

    def setKeyword(self) -> None:
        self.dictionary['keyword'] = self.article.keywords

    def setSummary(self) -> None:
        self.dictionary['summary'] = self.article.summary

    def setPublishDate(self) -> None:
        self.dictionary['publish_date'] = self.article.publish_date

    def setArticleText(self) -> None:
        self.dictionary['text'] = self.article.text

    def setTopImage(self) -> None:
        self.dictionary['top_image'] = self.article.top_image

    def setMovies(self) -> None:
        self.dictionary['movies'] = self.article.movies

    def post(self, url):
        requests.post(url=url, json=json.dumps(self.dictionary, cls=DjangoJSONEncoder))

    def print(self):
        print(json.dumps(self.dictionary, cls=DjangoJSONEncoder))

    def save(self, file_name):
        data = json.dumps(self.dictionary, cls=DjangoJSONEncoder)
        f = open(f"{file_name}.json", 'w')
        f.write(data)
        f.close()