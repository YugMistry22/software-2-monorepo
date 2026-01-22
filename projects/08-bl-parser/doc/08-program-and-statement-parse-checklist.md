# Project 8 Checklist

Project 8 is the Program and Statement Parser. The following information may be
helpful in getting started:

- Estimated time to complete the project (based on 0 reviews): ???
- Link to the project description: [Project 08][project]

When your team is finished, check this list to ensure you have
completed all the tasks for this assignment.

## Getting Started Tasks

- [ ] We have followed the recommended approach for getting started
  - [ ] We have read this document top to bottom
  - [ ] We have reviewed the project instructions for this assignment
  - [ ] We have reviewed the rubric for this assignment
  - [ ] We have considered the estimated time to complete the assignment
  - [ ] We have referred to the recursive-descent parsing slides
  - [ ] We have referred to testing resources that could help me write a systematic test plan
    - [ ] We have referred to the [QueueOnSequence lab][queue-on-sequence]
    - [ ] We have referred to the [5 Beginner Tricks for Writing Your Own Tests][5-tricks-for-testing] article

## Ongoing Tasks

- [ ] We have followed the project instructions
  - [ ] We have setup the Eclipse project according to the [project description][project]
  - [ ] We have implemented the Program Parser
    - [ ] We have implemented the private static method `parseInstruction()`
    - [ ] We have implemented the public instance method `parse()`
    - [ ] We have made sure the implementation throws errors for syntax errors
    - [ ] We have made sure the implementation throws errors for the additional criteria:
      - [ ] We have checked that the identifier at the end of the program is the same as the identifier at the beginning
      - [ ] We have checked that the identifer at the end of each new instruction definition is the same as the identifier at the beginning
      - [ ] We have checked that the name of each new user-defined instruction is unique
      - [ ] We have checked that the name of each new user-defined instruction is not a primitive instruction name
  - [ ] We have implemented the Statement Parser
    - [ ] We have implemented all of the private static methods:
      - [ ] We have completed `parseIf()`
      - [ ] We have completed `parseWhile()`
      - [ ] We have completed `parseCall()`
    - [ ] We have implemented all of the public instance methods:
      - [ ] We have completed `parseBlock()`
      - [ ] We have completed `parse()`
    - [ ] We have made sure the implementation throws errors for syntax errors
- [ ] We have developed a systematic test plan, knowing it will not be graded
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
[project]: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/projects/program-statement-parser/program-statement-parser.html
[queue-on-sequence]: https://cse22x1.engineering.osu.edu/2231/web-sw2/extras/instructions/version-control/version-control.html
[5-tricks-for-testing]: https://therenegadecoder.com/code/beginner-tricks-for-writing-your-own-unit-tests/
