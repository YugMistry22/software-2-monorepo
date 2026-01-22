# Troubleshooting Guide

You may run into a variety of problems while using VSCode to run Java code.
I will document as many of the problems as we've run into here, so you can
browse them. To make this guide easier to browse, I've broken it up into
two main sections: one for general things to try and one for specific
problems.

## Common Troubleshooting Steps

Whenever a student approaches me with a problem, these are the typical steps
I take to ensure everything is in order. You can try them yourself to
speed up the debugging process.

### Make Sure That You Have Opened the Correct Folder in VSCode

Sometimes students end up with a software-2-monorepo folder inside of another
software-2-monorepo folder. You need to open the innermost folder. When you open
VSCode, the `.vscode` folder as well as the `homeworks`, `labs`, and `projects`
folders should all be at the root of the repo.

### Make Sure That You Have All of the Recommended Extensions Installed

It's very easy to verify if you have all of the recommended extensions
installed. You can open the command palette using <kbd>CTRL</kbd> +
<kbd>SHIFT</kbd> + <kbd>P</kbd>. Then, you can type "Show Recommended",
which should pull up the "Extensions: Show Recommended Extensions" command.
From there, you can verify that all the extensions are already installed
under the "Workspace Recommendations" tab. Ignore the "Other Recommendations"
tab.

### Make Sure You Don't Have Oracle's Java Extension Enabled

To execute Java code in VSCode, we are using Red Hat's Java extension.
Oracle has one of their own, which seems to conflict with Red Hat's
extension. Therefore, it is recommended to disable it. To do that,
open the extensions panel using <kbd>CTRL</kbd> + <kbd>SHIFT</kbd> +
<kbd>X</kbd>. Then, search for Java. Oracle's extension should be near
the top. Make sure it is not enabled.

### Try Cleaning the Workspace

Sometimes binaries will get left behind or corrupted, which will confuse Java's
build system. You can often resolve a lot of problems by cleaning the workspace.
To do that, open the command palette using <kbd>CTRL</kbd> + <kbd>SHIFT</kbd> +
<kbd>P</kbd>. Then, search for "Clean Workspace". The actual command you want to
run is "Java: Clean Java Language Server Workspace."

### Make Sure You Don't Have the Code Runner Extension Enabled

> **Note**: you may not have this issue anymore as the settings.json file
> stops the Code Runner extension from overriding the run button. That said,
> it doesn't hurt to check.

Many students have seemingly used VSCode before. As a result, sometimes students
have an extension called "Code Runner" installed. It adds the ability to run
code from a variety of languages, but it doesn't know where the components.jar
is. This is a problem because Code Runner also seems to steal the run button.
You can verify this by clicking the dropdown next to the run button. It will
likely show "Run Code", "Run Java", and "Debug Java". By default, "Run Code" is
executed, which causes the code to fail due to the missing components. You can
fix this issue by disabling the Code Runner extension.

## Specific Problems

If none of the solutions above worked for you, consider looking for
your specific problem below.

### Unit Testing Is Not Working

Sometimes you might run into an issue where unit testing is not working.
One possible solution is to clean the workspace by clicking on the
three dots next to the Java Projects tab in the Explorer window and select
"Clean Workspace". You may also be able to replicate this by searching
for "Clean Workspace" in the command palette (i.e., `CTRL + SHIFT + P`).

If cleaning the workspace does not work, there may be alternative problems.
For example, VSCode and Java have a nasty bug where file paths that include
special characters, such as characters from other languages like Chinese or
Hebrew, cause unit testing to fail. See
[this thread](https://github.com/microsoft/vscode-java-test/issues/1159)
for discussion around the issue. The short term solution is to move the repo to
a folder without special characters in the path.

Finally, VSCode (or the Java extensions we're using) do not seem to hook into
the play button correctly. As a result, you may get an error about there being
no main method. That is okay! The solution is to right-click the test file and
select `Run Tests` or open the test file and click the green play button next
the test you want to run (note: if you're not sure where to look, take a peek
at the line numbers).

### Terminal Tab Is Missing in VSCode

Sometimes you will go looking for a tab at the top of the VSCode window, and
it will appear to be missing. No worries, it's probably hidden in the `...`
menu. Click that and you should see the terminal tab as well as anything else
that might be missing.

### Extension Cannot Be Installed Due to Incompatible Version

Sometimes extensions include restrictions on which version of VSCode
they will support. The solution is generally straightfoward: update
VSCode.

However, you may find that you can't update VSCode, specifically on MacOS.
As it turns out, Apple cares a lot about security, so Macs have a feature in
which applications in your download folder are considered read-only. As a
result, you may not be able to update VSCode. The solution is to take VSCode
out of your downloads and move it into your applications. You will need to
force quit VSCode afterwards to ensure the changes take effect.

### VSCode Cannot Find Components

There are generally two solutions to this problem. The first solution is to
verify that the root folder in VSCode only contains folders in this repo
(i.e., labs, projects, etc.). If these folders are nested in some other folder,
then you need to reopen the correct folder using the `Open Folder` option
in the File menu of VSCode.

However, chances are that you already did this correctly. The next problem
is that you may have added an extension (or already have one enabled) that
conflicts with one of the recommended extensions. Historically, the extension
that breaks imports is the Java extension provided by Oracle, which has the
identifier `oracle.oracle-java`. If you disable this extension, your VSCode
should begin to pick up the libraries in your `lib` folder.

### VSCode Will Not Make a Java Project on the University PCs

Some of you may have wanted to run the monorepo on the university systems
for convenience. You can do this, but it requires you to be a little mindful.
Specifically, VSCode is installed on the university systems as well as Java.
Therefore, you should have enough to run Java code. However, there seems to
be some nasty issue where the Java extension misinterprets the path of your
Java file, which throws a "FileNotFoundException". You can fix this by
moving the monorepo to the root of your user share (i.e., your `U:` drive).
Here's what an example path might look like:
`U:\Projects\software-2-monorepo-2024.08.07`.

Note that as far as I can tell, there is no git on these systems. I was able
to install it directly, but it installed it on the `C:` drive. I don't have
enough familiarity with these systems to know if that is just a local drive
or a network drive. That said, it's likely possible to download an archived
version of git, whose path you can give to VSCode.
