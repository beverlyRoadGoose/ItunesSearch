#!/usr/bin/env bash

rm -rf docs/*
gradle javadoc
cp -r build/docs/javadoc/* docs