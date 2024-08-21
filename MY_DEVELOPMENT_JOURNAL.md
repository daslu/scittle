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

