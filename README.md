# About this branch

This branch, [daslu/scittle emmy_viewers_dev1 branch](https://github.com/daslu/scittle/tree/emmy_viewers_dev1), was forked from [reedho/scittle my_playground branch](https://github.com/reedho/scittle/tree/my_playground), that was forked from [babashka/scittle main branch](https://github.com/babashka/scittle).

It offers the drafts of Scittle plugins for Emmy and Emmy-Viewers.

The plugins are under [plugins/emmy](plugins/emmy) and [plugins/emmy_viewers](plugins/emmy_viewers).

To try them out, we will need some aditional setup, as demonstrated in [plugins/demo_emmy](plugins/demo_emmy).

```bash
cd plugins/demo_emmy
npm install
bb release
cd resources/public
python -m http.server 
```    

Then, you may browesr `localhost:8000` and edit
[plugins/emmy/resources/public/index.html](plugins/demo_emmy/resources/public/index.html)
[plugins/emmy/resources/public/example.cljs](plugins/demo_emmy/resources/public/example.cljs).


# Scittle

The [Small Clojure Interpreter](https://github.com/babashka/sci) exposed for usage in script tags.

Try it out on [CodePen](https://codepen.io/Prestance/pen/PoOdZQw)!

See [Github pages](https://babashka.org/scittle/) for usage.

See
[babashka-scittle-guestbook](https://github.com/kloimhardt/babashka-scittle-guestbook)
for a minimal full stack web application.

See [releases](https://github.com/babashka/scittle/releases) for links to
[JSDelivr](https://www.jsdelivr.com) to get versioned artifacts.

## Serving assets

To serve assets you can use the
[babashka.http-server](https://github.com/babashka/http-server) dependency (with
babashka or Clojure JVM):

``` clojure
(require '[babashka.http-server :as http])
(http/serve {:port 1341 :dir "resources/public"})
@(promise) ;; wait until process is killed
```

### nREPL

See [doc/nrepl](doc/nrepl).

## Tasks

Run `bb tasks` to see all available tasks:

```
$ bb tasks
The following tasks are available:

clean   Start from clean slate.
dev     Development build. Starts webserver and watches for changes.
prod    Builds production artifacts.
release Updates Github pages with new release build.
```

## Credits

Idea by Arne Brasseur a.k.a [plexus](https://github.com/plexus).

## License

Copyright © 2021 - 2022 Michiel Borkent

Distributed under the EPL License. See LICENSE.
