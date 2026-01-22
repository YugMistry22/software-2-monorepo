# Project 3 Checklist

Project 3 is Map with Hashing. The following information may be
helpful in getting started:

- Estimated time to complete the project (based on 0 reviews): ???
- Link to the project description: [Project 03][project]

When your team is finished, check this list to ensure you have
completed all the tasks for this assignment.

## Getting Started Tasks

- [ ] We have followed the recommended approach for getting started
  - [ ] We have read this document top to bottom
  - [ ] We have reviewed the project instructions for this assignment
  - [ ] We have reviewed the rubric for this assignment
  - [ ] We have considered the estimated time to complete the assignment
  - [ ] We have referred to testing resources that could help us write a systematic test plan
    - [ ] We have referred to the [QueueOnSequence lab][queue-on-sequence]
    - [ ] We have referred to the [5 Beginner Tricks for Writing Your Own Tests][5-tricks-for-testing] article

## Ongoing Tasks

- [ ] We have followed the project instructions
  - [ ] We have setup the Eclipse project according to the [project description][project]
  - [ ] We have implemented all of the private methods
    - [ ] We have completed `createNewRep()`
    - [ ] We have completed `mod()`
  - [ ] We have implemented the two Map constructors
  - [ ] We have implemented all of the kernel methods:
    - [ ] We have completed `add()`
    - [ ] We have completed `remove()`
    - [ ] We have completed `removeAny()`
    - [ ] We have completed `value()`
    - [ ] We have completed `hasKey()`
    - [ ] We have completed `size()`
  - [ ] We have adhered to the representation invariant listed in the `@convention` tag in our methods
        (i.e., our methods do not modify the representation in a way that violates the convention)
  - [ ] We have adhered to the abstraction function listed in the `@correspondence` tag in our methods
        (i.e., our methods do not assume an interpretation of the representation that differs from the correspondence)
- [ ] We have tested our project code (**note**: tests may fail if not all kernel methods are implemented)
  - [ ] We have developed a systematic test plan for the Map4 implementation (**note**: consider a "0, 1, many" approach)
    - [ ] We have tested `add()`
    - [ ] We have tested `remove()`
    - [ ] We have tested `removeAny()`, taking care to not test implementation details
    - [ ] We have tested `value()`
    - [ ] We have tested `hasKey()`
    - [ ] We have tested `size()`
    - [ ] We have tested the default constructor
    - [ ] We have tested the integer constructor by creating additional Map4Test# files
- [ ] We have considered best practices
  - [ ] We have added our names in separate `@author` tags
  - [ ] We have addressed all spotbugs and checkstyle warnings
  - [ ] We have regularly committed changes to the project files using version control with good commit messages
  - [ ] We have regularly updated our local copies of the project files using version control and handled any merge conflicts

## Submission Tasks

- [ ] We have followed the submission instructions
  - [ ] We have created a zip archive of our project directory
  - [ ] We have created PDF(s) of our project file(s)
  - [ ] We have submitted the zip archive to Carmen
  - [ ] We have submitted the PDF(s) to Carmen

## Optional Tasks

- [ ] We have filled out the the [feedback form][feedback-form] for this project, rubric, and/or checklist


[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[project]: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/projects/map-with-hashing/map-with-hashing.html
[queue-on-sequence]: https://cse22x1.engineering.osu.edu/2231/web-sw2/extras/instructions/version-control/version-control.html
[5-tricks-for-testing]: https://therenegadecoder.com/code/beginner-tricks-for-writing-your-own-unit-tests/
