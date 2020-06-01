"""
파이썬 라이브러리인 nltk를 json 형식으로 리턴해주기 위한 파일입니다.
"""
import nltk
import json
import requests
import re
from urllib import parse
from bs4 import BeautifulSoup


class dictionary:
    def __init__(self, text):
        self.text = text


    def en2kr(self):
        self.text = self.text.replace("ﬂ", "fl")
        session = requests.Session()
        header = {"User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5)\
                    AppleWebKit 537.36 (KHTML, like Gecko) Chrome",
                  "Accept": "text/html,application/xhtml+xml,application/xml;\
                    q=0.9,imgwebp,*/*;q=0.8"}

        encText = parse.quote(self.text)
        url = "https://dic.daum.net/search.do?q=" + encText
        req = session.get(url, headers=header)
        bs0bj = BeautifulSoup(req.text, "html.parser")

        try:
            box = bs0bj.find("div", class_="cleanword_type kuek_type")
            box = box.find('ul', class_="list_search")
            box = str(box.find_all("span", class_="txt_search"))
            box = re.sub('<.+?>', '', box, 0).strip()
            box = box.replace(" ", "").replace("[", "").replace("]", "").replace(",", " ")
            box = box.split(" ")
            return box

        except AttributeError:
            return None


class CJsonNLTK:
    def __init__(self, target: str):
        self.target = target
        self.dictionary = {'target': self.target, 'target_trans': dictionary(text=self.target).en2kr()}

    def setDefinition(self) -> None:
        """
        정의, 단어의 성분, 예제를 추가합니다.
        :return:
        """
        self.dictionary['synsets'] = {}
        i = 0
        for synset in nltk.corpus.wordnet.synsets(self.target):
            i += 1
            self.dictionary['synsets'][i] = {}
            self.dictionary['synsets'][i]['definition'] = synset.definition()
            self.dictionary['synsets'][i]['pos'] = synset.pos()
            self.dictionary['synsets'][i]['examples'] = []
            examples = []
            for e in synset.examples():
                examples.append(e)
            self.dictionary['synsets'][i]['examples'] = list(set(examples))

    def setSynonymsAntonyms(self) -> None:
        """
        비슷한 의미를 가진 단어, 반대의 비슷한 의미를 가진 단어를 리턴합니다.
        :return:
        """
        synonyms = []
        antonyms = []
        for syn in nltk.corpus.wordnet.synsets(self.target):
            for lemma in syn.lemmas():
                synonyms.append(lemma.name())
                if lemma.antonyms():
                    antonyms.append(lemma.antonyms()[0].name())

        # 중복 제거
        self.dictionary['synonyms'] = list(set(synonyms))
        self.dictionary['antonyms'] = list(set(antonyms))

    def save2Json(self, file_name: str) -> None:
        """
        dictionary 형식을 json 파일로 저장합니다.
        :param file_name: 저장할 파일 이름
        :return: none
        """
        with open(f'./{file_name}.json', 'w', encoding='UTF-8-sig') as f:
            json.dump(self.dictionary, f, ensure_ascii=False)

    def print2Json(self) -> None:
        """
        dictionary 형식을 json 형식으로 출력합니다. (개행 없음)
        :return:
        """
        print(json.dumps(self.dictionary, ensure_ascii=False))

    def postJson(self, url: str) -> None:
        """
        해당 url로 json을 post하고 요청 응답을 콘솔에 출력합니다.
        :param url: target url
        :return: none
        """
        req = requests.post(url=url, data=json.dumps(self.dictionary, ensure_ascii=False))
        print(req.status_code)
