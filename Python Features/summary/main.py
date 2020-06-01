from bin.CNewsSummary import CNewsPaper3k
import argparse

if __name__ == '__main__':
    parse = argparse.ArgumentParser()
    parse.add_argument("target", help="link in here", type=str)
    parse.add_argument("-save", help="save to json", required=False, default=False)
    parse.add_argument("-print", help="print to json", required=False, default=True)
    parse.add_argument("-post", help="post to url", required=False, default=False)

    args = parse.parse_args()

    news = CNewsPaper3k(args.target)

    if args.save:
        news.save(args.save)
        exit(1)

    if args.post:
        news.post(args.post)
        exit(1)

    if args.print:
        news.print()
        exit(1)