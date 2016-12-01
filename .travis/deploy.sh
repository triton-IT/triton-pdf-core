#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_fe4a810adfdc_key -iv $encrypted_fe4a810adfdc_iv -in ./.travis/codesigning.asc.enc -out ./.travis/codesigning.asc -d
    gpg --fast-import ./.travis/codesigning.asc
    mvn deploy -P release --settings ./.travis/settings.xml
fi