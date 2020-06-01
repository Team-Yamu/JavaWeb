from bin.CJsonNLTK import CJsonNLTK
import argparse


if __name__ == '__main__':
    parse = argparse.ArgumentParser()
    parse.add_argument("target", help="word in here", type=str)
    parse.add_argument("-save", help="save to json", required=False, default=False)
    parse.add_argument("-print", help="print to json", required=False, default=True)
    parse.add_argument("-post", help="post to url", required=False, default=False)
    parse.add_argument('-ss', action='store_true')
    parse.add_argument('-soao', action='store_true')

    args = parse.parse_args()

    jnltk = CJsonNLTK(args.target)

    if args.ss:
        jnltk.setDefinition()

    if args.soao:
        jnltk.setSynonymsAntonyms()

    if args.save:
        jnltk.save2Json(args.save)
        exit(1)

    if args.post:
        jnltk.postJson(args.post)
        exit(1)

    if args.print:
        jnltk.print2Json()
        exit(1)

