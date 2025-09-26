# jaste
An extremely simple "pastebin" server purposed for interaction via Curl

# Usage:

Running:

```sh
curl -X POST -d "Hello World!" http://localhost:8081/upload
```

Will yield a URL which allows you to access the submitted string.

# Other examples:
## Uploading a text file:
```sh
curl -X POST -d "$(cat helloworld.cpp)" http://localhost:8081/upload
```

## Triggering the example filter.txt
```sh
curl -X POST -d "SeparateWordsByLine" http://localhost:8081/upload
```
