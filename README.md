# The Software 2 Monorepo

Welcome to the Software 2 Monorepo! The purpose of this repo is to provide you
with all of your assessments—apart from the exams—in one place.

As a quick heads up, you may find this document easier to read if you render it.
A quick way to render this file in VSCode is to click the preview button in the
top right corner of the window (note: it looks like the splitscreen button,
but it has a magnifying glass on it).

Alternatively, you can open a rendered view of this document by pressing
<kbd>CTRL</kbd> + <kbd>K</kbd>, releasing those buttons, then pressing
<kbd>V</kbd>. Though, you may not need to do any of this if you're viewing this
document on GitHub. It should render the README for you by default.

## How to Use This Repo

In general, the repo should work for you as-is, but there are two things you
will need to do to get everything working:

1. Install the recommended extensions by using the shortcut <kbd>CTRL</kbd> +
   <kbd>SHIFT</kbd> + <kbd>P</kbd>, search for "Recommended Extensions",
   click the "Show Recommended Extensions", and install all of the recommended
   extensions.
2. Install the latest JDK for your system
   [directly from Oracle's website][jdk-download].

If you want, you can get ahead of some later labs by also doing the following:

1. Install [GitHub Desktop][github-desktop]
2. Install Ivanti Secure VPN from [ETS website][ets-website]

Read the rest of this section for more information.

### Install Recommended Extensions

<!-- TODO: install recommended extensions and delete this comment -->

Upon opening this repo in VSCode, you should have been prompted to install all
of the recommended extensions. Hopefully, you selected **yes**. However, if not,
you can still press <kbd>CTRL</kbd> + <kbd>SHIFT</kbd> + <kbd>P</kbd> to open
the command palette and search for "Recommended Extensions". Once you run the
"Show Recommended Extensions" command, you should see a list of extension.
Please install all of the suggested extensions. Specifically, here are the
following extensions you will be installing and why (feel free to skip ahead,
if you don't care):

1. **Extension Pack for Java** (vscjava.vscode-java-pack): this extension pack
   provides everything you need to run Java code in VSCode.
2. **Checkstyle for Java** (shengchen.vscode-checkstyle): this extension
   provides continuity in software style across the software sequence courses.
3. **Markdown PDF** (yzane.markdown-pdf): this extension offers the ability to
   export markdown files, such as the homework files, as PDFs for submission.
4. **Print** (pdconsec.vscode-print): this extension offers the ability to
   export code as a PDF for submission.
5. **BugsLang** (therenegadecoder.bugslang): this extension offers crude syntax
   highlighting for BugsLang programs.
6. **TODO Tree** (gruntfuggly.todo-tree): this extension offers to track every
   task you need to complete in a nice tree.
7. **VSCode PDF** (tomoki1207.pdf): this extension allows you to view PDFs
   within VSCode.
8. **ZIP Tools** (adamraichu.zip-viewer): this extension allows you to create
   a zip file by right clicking a project folder.

### Install the Java JDK

<!-- TODO: install latest JDK and delete this comment -->

Also, it is likely you will need to download a Java JDK. To check, you can type
the following into the terminal:

```sh
java --version
```

You should be greeted with something like the following, if Java is already
installed:

```sh
java 21.0.1 2023-10-17 LTS
Java(TM) SE Runtime Environment (build 21.0.1+12-LTS-29)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.1+12-LTS-29, mixed mode, sharing)
```

If you don't have Java, VSCode may prompt you to install it, but the website you're
sent to isn't great. Therefore, it's probably best to just download a JDK
[directly from Oracle's website][jdk-download].

## Repo Contents

With your system setup, the rest of this document gives you an overview of
what you'll find inside. The primary folders you'll care about
are **homeworks**, **projects**, and **labs**. In the following sections,
we'll take a look at each of these folders in more detail.

### Homeworks

In total, there are 37 homework assignments in software 2. Considering that
we only meet 52 times in a semester, that's **a lot** of homework assignments.
Luckily, unlike software 1, these homework assignments are less conceptual
and more practical. Specifically, most of the assignments are preparation for
the labs (e.g., writing tests for kernel implementations).

As you'll see when you open [the homeworks folder](homeworks/), there are 37
(+1) subfolders, one for each of the homework assignments. In general, you can
choose to do the homeworks however you like, but we recommend that you use the
homework templates in each of these subfolders. As a fair warning, however, like
this README, the homework templates are written in markdown. To smooth things
over for you, we have included the Markdown PDF extension, which will render
the markdown for you as a PDF that you can submit on Carmen. Better yet,
this will be done for you every time you save the homework, so you can guarantee
the PDF is the latest version of your submission. With each save, the PDF will
render directly in the homework subfolder.

If you're nervous about using these templates, we've included a bonus assignment
that will walk you through how to complete the homework templates. **This
assignment will not be submitted and is worth no points**.

### Projects

In total, there are 10 project assignments in software 2. Overall, this is one
fewer project assignment than software 1. As an additional wrinkle, most of
these projects are done in pairs, with the exception of project 1. At this time,
I don't have a solution for the issue of sharing these projects via git. My
first idea was to create a `.gitignore` which excludes everything but the 9
shared projects. However, if you change teams at any point, you will have to
start a new monorepo or lose all of your project work. However, if you go the
per project route, there's not an easy way to make use of the VSCode GUI for
git. However, it does seem that VSCode can handle git repos for each project
through its autodetection feature. Though, I just realized that you can share
labs as well, so I'm not totally sure how to go about this just yet.

At any rate, when you open [the projects folder](projects/), you'll see 10
subfolders, one for each project. Assuming you have all the correct libaries
in [the lib folder](lib/), these projects should be ready to go. Before starting
a project, I would recommend opening the docs folder (e.g.,
[Project 1's Docs](projects/01-word-counter/doc/)). In each docs folder, I have
provided a variety of markdown resources. For example, you can take a peek at
the [Project 1 Rubric](projects/01-word-counter/doc/01-word-counter-rubric.md),
which will tell you how you are being assessed. Likewise, you can also take a
peek at the [Project 1 Checklist][project-1-checklist], which is a helpful
document for verifying that you've done everything required of you before
submission. Otherwise, that's everything there is to know about the projects
folder.

### Labs

In total, there are 27 lab assignments in software 2. Like software 1, these
labs are not graded. You are expected to do them as a part of your learning, but
as an added incentive, the code from the labs is usually applicable to future
projects. Therefore, completing the labs will ensure you're prepared for the
projects, which will likely result in a better overall grade. Also, because the
homework assignments are typically preparation for the labs, your general
workflow will be: 1) complete the homework, 2) use the homework to complete the
lab, and 3) use the lab to complete the project.

As with homeworks and projects, the labs can be found in
[the labs folder](labs/). Once opened, you will see all 28 labs in subfolders.
There are a couple of labs of note:

- **Lab 16**: in this lab, we will be holding a competition between Bugs World
  programs. You will notice that there is no lab on the course schedule. I have
  added a lab folder for you here in the template, so you have some place to
  track your Bugs World programs. This will also be a space where you can make
  use of the Bugs World syntax highlighting extension.
- **Labs 24 & 25**: for students who do not complete the portfolio project,
  there is a weeklong lab. For everyone else, these labs will be for working
  through your ideas.

Otherwise, all of these folders should be complete and map to the labs on
the schedule.

## Not Included in this Monorepo

The general purpose of this monorepo is to provide you with all activities and
assessments for the semester. As a result, there are a few items not included:

- **Lecture Slides**: lecture slides are something I maintain on my end as a
  part of my teaching portfolio. You can find all of these slides on Carmen
  under Files and Slides.
- **Exams**: like lecture slides, exams can found on Carmen and will be
  administered during a specific window of time during the semester.
- **APIs**: at various points throughout the semester, you will need to make use
  of the OSU API. None of the source code or the javadocs are provided here but
  can be accessed through the course website.

If there is anything you feel like should be included in this monorepo but is
not, let me know!

[ets-website]: https://ets.osu.edu/ets-wiki
[github-desktop]: https://desktop.github.com/download/
[jdk-download]: https://www.oracle.com/java/technologies/downloads/
[project-1-checklist]: projects/01-word-counter/doc/01-word-counter-checklist.md
