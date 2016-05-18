DoCitten
========

![DoCitten Kitten](docs/docitten.jpg)

[Eleanor](https://imgur.com/K8E10eQ), by [goodmorningmrholmes](https://imgur.com/user/goodmorningmrholmes) via [imgur](https://imgur.com/K8E10eQ).

License
-------
DoCitten is licensed under a BSD 3-Clause License. See [LICENSE.md](LICENSE.md)
for details.

Compiling
---------

First, make sure that the submodules are initialised. This can be done be cloning
with --recursive, or initialising/updating the sub-repo with:

    git submodule update --init --recursive

The project can be make from the command line using
make

- ```make compile```    - Creates the class files in ```build/```
- ```make package```    - Creates/copies JAR archives to ```dist/```
- ```make test-build``` - Creates class files for the tests
- ```make test```       - Runs test classes that have been compiled
- ```make clean```      - Removes all class and JAR files

It can also be imported into an IDE of your choice.
Two projects will likely need to be created - one at the root of the working
tree (DoCitten), and one in ```lib/irc``` (InternetRelayCats), and yet
another in ```lib/irc/lib/mewler```.
In all cases, Netbeans Java Project with existing sources project wizard
will correctly create the projects with

- ```src/``` listed as a source folder
- ```test/``` listed as a test sources folder

Note that you will to tell the IDE that DoCitten needs InternetRelayCats as a
library, and the DoCitten's test packages rely both of InternetRelayCats main
code _and_ test packages. You will also need to tell the IDE that DoCitten and
InternetRelayCats both need Mewler in turn.

Running
-------

DoCitten currently takes input only in the form of command line arguments, and
whatever processing the Services perform.
The first argument is the server to connect to; any further arguments are taken
as channels to join.

Joining using different logins, ports, with SSL, etc. are in the works.

Some modules require command line properties to be set. Consult the
documentation on any of the modules that you are using for more information.

The program can be stopped by entering the line 'quit' via standard input.

Bug Reporting
-------------

Bug reports / suggestions for improvement are accept via:

- patches
- pull requests
- git hub issues
- direct email to me
- moaning on an irc channel I'm in
- inline comments
- IPoAC

in descending order of preference.
