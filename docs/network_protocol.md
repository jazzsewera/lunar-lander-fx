# SLAP
## Simple LunarLander Accompanying Protocol

When designing this protocol, I followed some basic rules I stumbled upon in
my coding career:
- The protocol shall be universally understood and portable – therefore it is
  text-based
- It shall be easily debugged – therefore the request is very similar to HTTP


## The Structure

### Request

All the request commands are case-insensitive.

Being similar to HTTP, the request commands are as follows:
- `GET` – to get a map from the server
- `PUSH` - to push a score to the server
- In the future there will be also a `PULL` command to get a score list from
  the server

Also, `GET` command does not need any further arguments – they are ignored.

`PUSH` command takes a nick as a first argument and a score as a second, e.g.
`PUSH Octopuss 1337`.

`PULL` does not need any further arguments – they are ignored as in `GET`.

All the commands are regex-checked, e.g. `^GET.*`, case-insensitive for `GET`.
It means that everything like: `GET`, `GET MAP`, `getmap`, `Get the eff outta
here, I'm playing minecraft!` will work as long as the letters Gee - Ee - Tee
are the first three letters of the request string.


### Response

The response type varies depending on the request type:
- For `GET` – a json string with all level maps
- For `PUSH` - simply `OK` string
- For `PULL` - there will be a json string with the scoreboard


## Debugging

First, launch the server with the Gradle command

```bash
./gradlew runserver
```

on *NIX systems, or

```batch
gradlew.bat runserver
```

on Windows. Remember to do it from the root directory of the project.

Requests can be created in any modern web browser, e.g. in Firefox:

1. Go to the `address:port` of the server, e.g.: `127.0.0.1:21370`
2. You should see a json with level maps. It happens so, because HTTP GET
   request shares the same structure as GET command in SLAP.
3. To perform other requests, go to Menu → Web Developer → Network, perform a
   request if you don't see any and select one. Here you can Edit and Resend
   it, providing the desired command in request method.

Pro tip: You can also perform a request with a little help of JavaScript in
web console:

```js
fetch("")
  .then(data => { return data.json() })
  .then(res => { console.log(res) });
```

You can also test for different commands (methods):

```js
fetch("", {method: "PUSH"})
  .then(data => { return data.text() })
  .then(res => { console.log(res) });
```
