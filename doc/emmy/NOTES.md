# My Development Journal

## Building emmy and emmy_viewers plugin

```bash
cd <repodir>/plugins/demo_emmy
bb release
```

Note: In `plugins/emmy/deps.edn`, we set exclusion in order to not brings
`org.babashka/sci` and `thheller/shadow-cljs`.

To develop scittle cljs script with repl:

```bash
cd <repodir>/plugins/demo_emmy
bb dev
```

Then browse to `http://localhost:1341/repl.html` and follow tips to connect 
to repl below.


## Tips

### How to repl connect with emacs+cider

The instruction from the doc failed to achieve seamless repl connection which
give use "No cljs REPLs in current session" when evaluating directly from
buffer.

As a workaround for now, it can be made to works by using `cider-connect-clj`
instead of `cider-connect-cljs`, set the repl buffer type to `cljs` and it will
link as usual, switch buffer from repl buffer to source buffer one time to
trigger the linkage.

Step by step instruction:

1. Create an html file to serve the repl in `resources/public` directory,
   for example we create `repl.html` based on [this](../../plugins/demo_emmy/resources/public/repl.html).

2. Start babashka dev task.

```bash
bb dev
```

3. Wait for some time and browse to http://localhost:1341/repl.html. It will create
   connection between cljs dev nrepl server to browser js runtime.

4. From emacs, open cljs file, e.g. `resources/public/cljs/script.cljs`, then
   type `M-x cider-connect-clj RET localhost RET 1339 RET`. At this point, the repl 
   should be started successfully but the source buffer was not linked into
   repl buffer yet.

5. Go to repl repl buffer with `M-x switch-to-buffer`, and pick the correct repl
   that is started in 3.

6. Set the repl buffer to cljs by typing `M-x cider-set-repl-type RET cljs RET`.

7. Switch back to source buffer with `M-x switch-to-buffer` again.

8. At this point, the source and repl buffer now linked so that we can for example
   type `C-c C-z` to switch back and forth between source and repl buffer.


### Some Notes from Slack Discussion

- `expand` is necessary if you want to show, e.g., `(mafs/of-x {:y sin})`, that
  doesn’t actually make sense alone, so expand wraps it in the components that
  make it all work.

- Every emmy-viewers form is a block of sci-able reagent code, so the first step
  is to just print the data structure, then get expand working, then get eval working.

```clojure
(mafs/of-x {:x e/cos}) ;; this is emmy-viewers form, it returns a block of sci-able reagent code.
(pprint (mafs/of-x {:x e/cos})) ;; this will show the reagent hiccup
(pprint (meta (mafs/of-x {:x e/cos}))) ;; check its metadata
(pprint (ev/expand (mafs/of-x {:x e/cos}))) ;; expand read the metadata and will wrap accourdingly
```

- What's the removing of metadata about? Metadata removal was because sub-pieces
  have rendering functions attached to them as metadata, and sci tried to
  serialize that and failed, so I stripped it all so only the data structures
  remained. This is so if you provided, say, a bare plot without a wrapping
  scene it could supply its own wrapping scene.


### Build output size comparison

File size comparison between `bb prod` in root dir vs `bb release` in `plugins/demo_emmy`
as of `a99cf25`.

```text
#// scittle/resources/public/js
-rw-r--r--  1 ridho  staff   9.4K Aug 23 14:47 scittle.nrepl.js
-rw-r--r--  1 ridho  staff    75K Aug 23 14:47 scittle.reagent.js
-rw-r--r--  1 ridho  staff    91K Aug 23 14:47 scittle.promesa.js
-rw-r--r--  1 ridho  staff   103K Aug 23 14:47 scittle.cljs-ajax.js
-rw-r--r--  1 ridho  staff   118K Aug 23 14:47 scittle.pprint.js
-rw-r--r--  1 ridho  staff   123K Aug 23 14:47 scittle.re-frame.js
-rw-r--r--  1 ridho  staff   862K Aug 23 14:47 scittle.js

#// scittle/plugins/demo2/resources/public/js
-rw-r--r--  1 ridho  staff   5.7K Aug 24 08:46 scittle.pprint.js
-rw-r--r--  1 ridho  staff   9.3K Aug 24 08:46 scittle.nrepl.js
-rw-r--r--  1 ridho  staff    74K Aug 24 08:46 scittle.reagent.js
-rw-r--r--  1 ridho  staff    92K Aug 24 08:46 scittle.promesa.js
-rw-r--r--  1 ridho  staff   104K Aug 24 08:46 scittle.cljs-ajax.js
-rw-r--r--  1 ridho  staff   123K Aug 24 08:46 scittle.re-frame.js
-rw-r--r--  1 ridho  staff   1000 Aug 24 08:46 scittle.js
-rw-r--r--  1 ridho  staff   2.4M Aug 24 08:46 scittle.emmy.js
```


### Some errors encountered

#### We can not simply requiring `emmy.viwer.sci`

If we require `emmy.viewer.sci` in the [init plugin](plugins/emmy_viewers/src/scittle/emmy_viewers.cljs) file, 
build will fail with this error:

```
The required namespace "nextjournal.clerk.viewer" is not available, it was required by "emmy/clerk.cljc".
```

Solution for now is copy paste the `emmy.viwer.sci/install!` in our init plugin. Also we have to exclude
`emmy.jsxgraph` otherwise it will error like below, because jsxgraph only work for nodejs.

```
The required JS dependency "process" is not available, it was required by "node_modules/jsxgraph/distrib/jsxgraphcore.js".

Dependency Trace:
        scittle/emmy_viewers.cljs
        jsxgraph/sci.cljs
        jsxgraph/core.cljs
        node_modules/jsxgraph/distrib/jsxgraphcore.js
```
