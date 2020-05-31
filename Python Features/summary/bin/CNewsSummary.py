from newspaper import Article
import json
import requests
from django.core.serializers.json import DjangoJSONEncoder


class CNewsPaper3k:
    def __init__(self, url, language='en'):
        if language is not None:
            self._article = Article(url=url, language=language)
        else:
            self._article = Article(url=url)

        self._article.download()
        self._article.parse()
        self._article.nlp()
        self.dictionary = {}

    def _setKeyword(self) -> None:
        self.dictionary['keyword'] = self._article.keywords

    def _setSummary(self) -> None:
        self.dictionary['summary'] = self._article.summary

    def _setPublishDate(self) -> None:
        self.dictionary['publish_date'] = self._article.publish_date

    def _setArticleText(self) -> None:
        self.dictionary['text'] = self._article.text

    def _setTopImage(self) -> None:
        self.dictionary['top_image'] = self._article.top_image

    def _setMovies(self) -> None:
        self.dictionary['movies'] = self._article.movies

    def post(self, url):
        self._setKeyword()
        self._setSummary()
        self._setPublishDate()
        self._setArticleText()
        self._setTopImage()
        self._setMovies()

        requests.post(url=url, json=json.dumps([self.dictionary], cls=DjangoJSONEncoder))

    def print(self):
        self._setKeyword()
        self._setSummary()
        self._setPublishDate()
        self._setArticleText()
        self._setTopImage()
        self._setMovies()
        print(json.dumps([self.dictionary], cls=DjangoJSONEncoder))

    def save(self, file_name):
        self._setKeyword()
        self._setSummary()
        self._setPublishDate()
        self._setArticleText()
        self._setTopImage()
        self._setMovies()
        data = json.dumps([self.dictionary], cls=DjangoJSONEncoder)
        f = open(f"{file_name}.json", 'w')
        f.write(data)
        f.close()