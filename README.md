# simplypdf-core

[![Build Status](https://travis-ci.org/web4enterprise/simplypdf-core.svg?branch=master)](https://travis-ci.org/web4enterprise/simplypdf-core)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.web4enterprise:simplypdf-core)](https://sonarqube.com/dashboard/index/com.web4enterprise:simplypdf-core)
[![Javadocs](http://javadoc.io/badge/com.web4enterprise/simplypdf-core.svg)]
(http://javadoc.io/doc/com.web4enterprise/simplypdf-core)

## What is simplypdf-core ?
Simplypdf-core is a simple low-level API for creating PDFs.
It is named "simplypdf" because it is simply a PDF library and that the design is made to be very simple to use.

Simpypdf-core use absolute positionning, no line wrapping is calculated, new pages are not created automatically it only renders a PDF. In short, layouting is not in the scope of simplypdf-core.
For layouting possibilities, use [simplypdf-layout](https://github.com/web4enterprise/simplypdf-layout).

### Why another PDF API ?
There is already great "low level" PDF APIs in java, but all are difficult to use and provides more a "wrapper" for PDF format than a real API. In other terms, lot of them exposes all the complexity of the raw PDF format.
simplypdf-core is created to be still a low-level API but is intended to be simpler to use by creating a more abstracted API.

Some other APIs have a "high level" design but simplypdf-core is not intended to compete with them.

If you're interested in a high level API, you can take a look at [simplypdf-layout](https://github.com/web4enterprise/simplypdf-layout).

## What license is used for simplypdf-core
Simplypdf-core is under Apache 2.0 license.
This means that you can use simplypdf-core either in a non-commercial or commercial product and can modify it.
Read the full license [here](http://www.apache.org/licenses/LICENSE-2.0).

## What are the plans for simplypdf-core
The goal of simplypdf-core is to abstract the more common PDF functionalities.

Today, simplypdf-core is capable of the following.
* Create a valid PDF 1.7 document.
* Adding pages.
* Adding texts with base 14 Adobe fonts.
* Adding non transparent images.
* Adding lines and curves.

Future versions will permit to load custom fonts, handle transparent images, links, anchors and different PDF formats, particularly PDF-A.

Other PDFs capabilities like adding 3d content, videos, etc. will not be implemented. Simplypdf-core goal is to be capable to draw anything that can be drawn on a paper page, no more, but not less.

You can track advancement of project in a "kanban style" for any version [here](https://github.com/web4enterprise/simplypdf-core/projects).

### Can I use simplypdf-core on android ?
The response is no for the moment, because I use AWT to load graphics. It does not mean that rest of code is fully compatible with android, but if it is not, it shouldn't be hard to change.
Android support is intended but will probably be done after version 1 of [simplypdf-layout](https://github.com/web4enterprise/simplypdf-layout).

## Where can I get documentation ?
Please read "documentation.pdf" in project files for more information on the API.
This documentation is created by using this library and for a concrete example you can find the code used to render it in the unit test "PdfTest.java".

You can also take a look at project [wiki](https://github.com/web4enterprise/simplypdf-core/wiki) for getting started information.

Javadoc is available [here](http://javadoc.io/doc/com.web4enterprise/simplypdf-core/1.0.0).

## What about quality ?
I do my best to provide the best quality for design, code and documentation.
Quality is super important for me, so if documentation is not clear, if design or code has a flaw, if my English writting is not good enough :blush:, if you find a bug or if you find anything else that can be improved, you can [create an issue](https://github.com/web4enterprise/simplypdf-core/issues).

## Where can I get support ?
If documentation is not sufficient, just create an issue, I will reply to issue and update documentation if needed.

For professional support and services, use the web site [web4enterprise.com](http://web4enterprise.com).

## Gettting started
Binary, sources and javadoc is available on maven central.Just include the following dependency.
### Using maven
```xml
<dependency>
    <groupId>com.web4enterprise</groupId>
    <artifactId>simplypdf-core</artifactId>
    <version>1.0.0</version>
</dependency>
```
### Using gradle
    compile 'com.web4enterprise:simplypdf-core:1.0.0'
### Using another dependency manager
Please visit [Maven central](http://search.maven.org/#artifactdetails%7Ccom.web4enterprise%7Csimplypdf-core%7C1.0.0%7Cjar) to find the definition for your repository manager.
