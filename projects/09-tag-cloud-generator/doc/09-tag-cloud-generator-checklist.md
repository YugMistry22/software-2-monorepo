# Project 9 Checklist

Project 9 is the Tag Cloud Generator. The following information may be
helpful in getting started:

- Estimated time to complete the project (based on 0 reviews): ???
- Link to the project description: [Project 09][project]

When you're finished, check this list to ensure you have
completed all the tasks for this assignment.

## Getting Started Tasks

- [ ] We have followed the recommended approach for getting started
  - [ ] We have read this document top to bottom
  - [ ] We have reviewed the project instructions for this assignment
  - [ ] We have reviewed the rubric for this assignment
  - [ ] We have considered the estimated time to complete the assignment
  - [ ] We have reviewed the following resources from Software 1, which may help complete project 9
    - [ ] We have reviewed the [Words and Separators homework][words-and-seps-homework]
    - [ ] We have reviewed the [Words and Separators lab][words-and-seps-lab]
    - [ ] We have reviewed [Project 10: Glossary][project-10]

## Ongoing Tasks

- [ ] We have followed the project instructions
  - [ ] We have created an Eclipse project called something related to word counting
  - [ ] We have written a program which prompts the user for key information
    - [ ] We have asked the user for the name of an input file
    - [ ] We have asked the user for the name of an output file
    - [ ] We have asked the user for the number of words to include in the output file
  - [ ] We have verified that user input is valid (e.g., the number of words is a positive integer)
  - [ ] We have written a program which produces an HTML output file in the form of [a tag cloud][tag-cloud]
  - [ ] We have only used the approved OSU data structures:
    - [ ] We have used `SimpleReader` and `SimpleWriter` for input and output
    - [ ] We have used `Map` for tracking words and counts
    - [ ] We have used `SortingMachine` for sorting needs
- [ ] We have considered best practices
  - [ ] We have added our names in separate `@author` tags
  - [ ] We have addressed all spotbugs and checkstyle warnings
    - [ ] We have *ignored* all warnings related to serializable when creating a comparator for this project only
  - [ ] We have written an appropriate collection of methods to create the tag cloud
  - [ ] We have limited our suffering by storing `Map.Pair`s in our `SortingMachine`
  - [ ] We have written our comparators to be consistent with `.equals()`
- [ ] We have tested our project with a variety of input files, knowing that our testing will not be graded
  - [ ] We have used some of the provided text files found [here][text-files]

## Submission Tasks

- [ ] We have followed the submission instructions
  - [ ] We have created a zip archive of our project directory
  - [ ] We have created a PDF of our Word Counter file(s)
  - [ ] We have submitted the zip archive to Carmen
  - [ ] We have submitted the PDF(s) to Carmen

## Optional Tasks

- [ ] We have filled out the the [feedback form][feedback-form] for this project, rubric, and/or checklist


[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[project]: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/projects/tag-cloud-generator/tag-cloud-generator1.html
[tag-cloud]: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/projects/tag-cloud-generator/data/importance.html
[words-and-seps-homework]: https://cse22x1.engineering.osu.edu/2221/web-sw1/assignments/homeworks/next-word-or-separator.html
[words-and-seps-lab]: https://cse22x1.engineering.osu.edu/2221/web-sw1/extras/instructions/glossary-start/glossary-start.html
[project-10]: https://cse22x1.engineering.osu.edu/2221/web-sw1/assignments/projects/glossary/glossary.html
[text-files]: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/projects/tag-cloud-generator/data/
