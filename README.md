# About this branch

This branch was forked from [reedho/scittle my_playground branch](https://github.com/reedho/scittle/tree/my_playground), that was forked from [babashka/scittle main branch](https://github.com/babashka/scittle).

It offers the drafts of Scittle plugins for Emmy and Emmy-Viewers.

The plugins are under [plugins/emmy](plugins/emmy) and [plugins/emmy_viewers](plugins/emmy_viewers).

A work in progress demo of these plugins is at [reedho/scittle-emmy-viewers-demo](https://github.com/reedho/scittle-emmy-viewers-demo) repo, which is published to [https://ev-demo.datafy.id/](https://ev-demo.datafy.id/).

## TODO

- Make all of Emmy-Viewers examples work.
- Package css links as part of the plugin (currently we directly link them it from the example HTML below).
- Figure out how to package the plugin as a library for recommended use.
- ~~Figure out the reason for some strange characters that make the Emmy-Viewers cljs break in Scittle (currently we [remove them](https://github.com/daslu/scittle/blob/2b6177e/plugins/demo_emmy/bb.edn#L43) in the build process).~~ [[Fixed](https://clojurians.slack.com/archives/C6N245JGG/p1724829585794869?thread_ts=1724772275.771389&cid=C6N245JGG)]

## Usage

To try the plugins out, we will need some aditional setup, as demonstrated in [plugins/demo_emmy](plugins/demo_emmy). Here is how to use it:

```bash
cd plugins/demo_emmy
npm install
bb release
cd resources/public
python -m http.server
```

Then, you may browse `localhost:8000` and edit
[plugins/emmy/resources/public/index.html](plugins/demo_emmy/resources/public/index.html) and
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

```clojure
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
