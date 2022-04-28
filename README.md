# DiffingAPI

API 1: store the first JSON
localhost:8080/v1/diff/{id}/left

API 2: store the second JSON
localhost:8080/v1/diff/{id}/right

API 3: git diff result
localhost:8080/v1/diff/{id}

steps to run:

if you don't have Homebrew installed, use the following instruction to install Homebrew

git clone https://github.com/Homebrew/brew homebrew

eval "$(homebrew/bin/brew shellenv)"

brew update --force --quiet

chmod -R go-w "$(brew --prefix)/share/zsh"

use this instruction to install Maven

brew install maven

mvn update

cd diff

mvn clean install

mvn compile

mvn spring-boot:run
