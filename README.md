# triton-pdf-core

## What is triton-pdf-core ?
triton-pdf-core is a simple low-level API for creating PDFs.

It is designed to be able to create a PDF easily using absolute positioning. It's perfect to create a static PDF file from scratch.
If you need laying possibilities and easier styling, use [triton-pdf-layout](https://github.com/tritonit/triton-pdf-layout) instead.

### Why another PDF API ?
There is already great "low level" PDF APIs in java, but all of them are difficult to use and provides more a "wrapper" for PDF format than a real API. In other terms, a lot of them exposes all the complexity of the raw PDF format.
[triton-pdf-core](https://github.com/tritonit/triton-pdf-core) is created to be a low-level PDF API but is intended to be as simple to use as possible.
[triton-pdf-core](https://github.com/tritonit/triton-pdf-core) is also free to use, even for a commercial use. So, don't hesitate to give it a try.

Some other APIs have a "high level" design but triton-pdf-core is not intended to compete with them.

If you're interested in a high level API, you can take a look at [triton-pdf-layout](https://github.com/tritonit/triton-pdf-layout).

## What license is used for triton-pdf-core
triton-pdf-core is under Apache 2.0 license.
This means that you can use triton-pdf-core either in a non-commercial or commercial product and can modify it.
Read the full license on [apache website](https://www.apache.org/licenses/LICENSE-2.0).

## What are the plans for triton-pdf-core
The goal of triton-pdf-core is to abstract the more common PDF functionalities.

Today, triton-pdf-core is capable of the following.
* Create a valid PDF and [PDF/A](https://en.wikipedia.org/wiki/PDF/A) 1.7 document.
* Adding pages with any format in most common units (mm, cm, inch, PDF native unit).
* Adding texts with base 14 Adobe fonts.
* Adding images (with support for the transparency).
* Adding lines and curves (To generate charts for instance).

Other PDFs capabilities like adding 3d content, videos, etc. will not be implemented. [triton-pdf-core](https://github.com/tritonit/triton-pdf-core) goal is to be capable to draw anything that can be drawn on a paper page, no more, but no less.

You can track advancement of project in a "kanban style" for any version on github [project page](https://github.com/tritonit/triton-pdf-core/projects).

### Can I use triton-pdf-core on android ?
Yes, [triton-pdf-layout](https://github.com/tritonit/triton-pdf-layout) is a pure java library without any external dependency and no dependency on platform-dependent code.

## Where can I get documentation ?
Please read "documentation.pdf" in project files for more information on the API.
This file also contains a simple example below.
File documentation.pdf is created by using this library and for a concrete example you can find the code used to render it in the unit test "PdfTest.java".

You can also take a look at project [wiki](https://github.com/tritonit/triton-pdf-core/wiki) for getting started information.

## What about quality ?
We do our best to provide the best quality for design, code and documentation.
Quality is one of the application aspects the most important for us, so if documentation is not clear, if design or code has a flaw, if you find a bug or if you find anything else that can be improved, you can [create an issue](https://github.com/tritonit/triton-pdf-core/issues).
We have worked on many [IEC 62304](https://www.iso.org/standard/38421.html) and [FDA](https://www.fda.gov/) compliant projects and do our best to apply these standards to this project.

### Which quality metrics are used ?
We know that quality experts are better than us to define metrics.
That's why we use defaults rules of IntelliJ and SonarCloud on our projects.

### How to consult project quality metrics ?
Metrics can be found on sonarcloud page for [this project](https://sonarcloud.io/dashboard?id=triton-IT_triton-pdf-core)

## Where can I get support ?
If documentation is not sufficient, just create an issue, We will reply to it and update documentation if needed.

For professional support and services, use the website [tritonit.tech](https://tritonit.tech).

## More information about the PDF format:
https://www.adobe.com/devnet/pdf/pdf_reference.html

## Hello world example
    //Create an output stream to write PDF to.
    try(OutputStream out = new FileOutputStream("hello-world.pdf")) {
        //Instanciate a new PDF.
        Pdf pdf = new Pdf();
    
        //Create the first page in A4 format (210mm x 297mm) 
        Page page1 = pdf.createPage(210.0f, 297.0f);
    
        //Add text at position x=20, y = 277, with font size 12 and text.
        page1.addText(20, 277, 12, "Hello ISO 32000 !");
    
        //Write PDF content to output stream
        pdf.write(out);
    }
