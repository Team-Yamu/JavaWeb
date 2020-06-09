from bin.CNewsSummary import CNewsPaper3k
import argparse

if __name__ == '__main__':
    parse = argparse.ArgumentParser()
    parse.add_argument("target", help="link in here", type=str)
    parse.add_argument("-save", help="save to json", required=False, default=False)
    parse.add_argument("-print", help="print to json", required=False, default=True)
    parse.add_argument("-post", help="post to url", required=False, default=False)

    parse.add_argument('-k', help="Set Keyword", action='store_true')
    parse.add_argument('-k', help="Set Keyword", action='store_true')
    parse.add_argument('-s', help="Set Summary", action='store_true')
    parse.add_argument('-p', help="Set Publish Date", action='store_true')
    parse.add_argument('-a', help="Set Article Text", action='store_true')
    parse.add_argument('-t', help="Set Top Image Link", action='store_true')
    parse.add_argument('-m', help="Set Movies Link", action='store_true')
    parse.add_argument('-all', help="Set all args", action='store_true')

    args = parse.parse_args()

    news = CNewsPaper3k(args.target)

    if args.all:
        news.setKeyword()
        news.setSummary()
        news.setPublishDate()
        news.setArticleText()
        news.setTopImage()
        news.setMovies()

    if args.k:
        news.setKeyword()

    if args.s:
        news.setSummary()

    if args.p:
        news.setPublishDate()

    if args.a:
        news.setArticleText()

    if args.t:
        news.setTopImage()

    if args.m:
        news.setMovies()

    if args.save:
        news.save(args.save)
        exit(1)

    if args.post:
        news.post(args.post)
        exit(1)

    if args.print:
        news.print()
        exit(1)
