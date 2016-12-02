#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_0ff34349d09f_key -iv $encrypted_0ff34349d09f_iv -in release/signingkey.enc -out release/signingkey.asc -d
    gpg --fast-import release/signingkey.asc
    mvn deploy -P release --settings release/settings.xml
fi