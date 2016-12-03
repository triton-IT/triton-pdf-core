#!/bin/bash

if [ "$TRAVIS_BRANCH" == "master" ]; then
  mvn deploy --settings ./deploy/settings.xml
fi