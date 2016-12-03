# simplypdf-core

[![Build Status](https://travis-ci.org/web4enterprise/simplypdf-core.svg?branch=master)](https://travis-ci.org/web4enterprise/simplypdf-core)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.web4enterprise:simplypdf-core)](https://sonarqube.com/dashboard/index/com.web4enterprise:simplypdf-core)

## What is simplypdf-core ?
Simplypdf-core is a simple low-level API for creating PDFs.
It is named "simplypdf" because it is simply a PDF library and that the design is made to be very simple to use.

Simpypdf-core use absolute positionning, no line wrapping is calculated, new page are not creating. Layouting is not in the scope of simplypdf-core.
For layouting possibilities, use [simplypdf-layout](https://github.com/web4enterprise/simplypdf-layout).

### Why another PDF API ?
There is already great "low level" PDF APIs in java, but all are difficult to use and provides more a "wrapper" for PDF format than a real API. In other terms, lot of them exposes all the complexity of raw PDF format.
simplypdf-core is created to be still a low-level API but is intended to be simpler to use by creating a more abstract API.

Some other API have a "high level" design but simplypdf-core is not intended to compete with them.

If you're interested in a high level API, you can take a look at [simplypdf-layout](https://github.com/web4enterprise/simplypdf-layout).

## What are the plans for simplypdf-core
The goal of simplypdf-core is to abstract the more common PDF functionalities.
* Create a PDF document.
* Adding pages.
* Adding texts.
* Adding images.
* Adding lines and curves.

Other PDFs capabilities like adding 3d content, videos, etc. will not be implemented. Simplypdf-core goal is to be capable to draw anything that can be drawn on a paper page, no more.

You can track advancement of project in a "kanban style" for any version [here](https://github.com/web4enterprise/simplypdf-core/projects).

## Where can I get documentation ?
Please read "documentation.pdf" in project files for more information on the API.
This documentation is created by using this library and for a concrete example you can find the code used to render it in the unit test "Pdftest.java"

You can also take a look at project [wiki](https://github.com/web4enterprise/simplypdf-core/wiki) for getting started information.

## WHat about quality ?
I do my best to provide the better quality for design, code and documentation.
Quality is super important for me, so if documentation is not clear, if design or code has a flaw, if my English writting is not good enough :blush:, if you find a bug or if you find anything else that can be improved, you can [create an issue](https://github.com/web4enterprise/simplypdf-core/issues).

## Where can I get support ?
If documentation is not sufficient, just create an issue, I will reply to issue and update documentation if needed.

For professional support and services, use the web site [web4enterprise.com](http://web4enterprise.com).

