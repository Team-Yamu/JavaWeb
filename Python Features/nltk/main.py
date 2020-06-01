from bin.CJsonNLTK import CJsonNLTK
import argparse


if __name__ == '__main__':
    parse = argparse.ArgumentParser()
    parse.add_argument("target", help="word in here", type=str)
    parse.add_argument("-save", help="save to json", required=False, default=False)
    parse.add_argument("-print", help="print to json", required=False, default=True)
    parse.add_argument("-post", help="post to url", required=False, default=False)

    args = parse.parse_args()

    jnltk = CJsonNLTK(args.target)
    jnltk.setDefinition()
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

