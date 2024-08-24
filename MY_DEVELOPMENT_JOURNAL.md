# My Development Journal

## How to repl connect with emacs+cider

The instruction from the doc failed to achieve seamless repl connection which
give use "No cljs REPLs in current session" when evaluating directly from
buffer.

After some tinkering, it can be made to works by using `cider-connect-clj`
instead of `cider-connect-cljs`, set the repl buffer type to `cljs` and it will
link as usual, switch buffer from repl buffer to source buffer one time to
trigger the linkage.

Here is the transcript.

```bash
#// go to scittle repo directory
cd ~/Work/Git/scittle
bb dev

#// create file dev_index.html di resources/public/
#// open browser to http://localhost:1341/dev_index.html

#// from emacs, open the resources/public/cljs/script.cljs
#// Type M-x cider-connect-clj RET localhost RET 1339 RET
#// At this point, the source buffer was not linked into repl buffer yet

#// Type M-x switch-to-buffer RET
#// Choose the correct cider repl buffer and then type RET
#// Type M-x cider-set-repl-type RET cljs RET
#// Then switch back to script.cljs buffer

#// At this point, source and repl buffer now linked
#// Type C-c C-z to swith back and forth between source and repl.
```

## Building emmy plugin

Note: In `plugins/emmy/deps.edn`, we set exclusion to not brings
`org.babashka/sci` and `thheller/shadow-cljs`.

TLDR;

```
cd ~/Work/Git/scittle/plugins/demo_emmy
bb release
```


Successful build log:

```
Building features: scittle/emmy ...
> clojure -Sdeps '{:deps {emmy/deps {:local/root "/Users/ridho/Work/Git/scittle/plugins/emmy"}, scittle/deps {:local/root ""}}}' -M -m shadow.cljs.devtools.cli --force-spawn release main  --config-merge '{:modules {:scittle.emmy {:init-fn scittle.emmy/init, :depends-on #{:scittle}, :entries [emmy.env]}}}'
[:main] Compiling ...
SCI: eliding vars.
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
WARNING: abs already refers to: #'clojure.core/abs in namespace: clojure.math.numeric-tower, being replaced by: #'clojure.math.numeric-tower/abs
Wrote build report to: /Users/ridho/Work/Git/scittle/plugins/demo2/resources/public/js/report.html
[:main] Build completed. (369 files, 239 compiled, 0 warnings, 118,97s)
```

File size comparison between `bb prod` in root dir vs `bb release` in demo_emmy

```
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
