# Advent of Code solved by ChatGPT in Kotlin

## Important Notes

* All tasks were solved by ChatGPT with minor fixes from human where the fix was obvious.
* No refactoring or code restyling was made apart from renaming some methods.
* No code/performance optimizations were made manually, but in some cases such optimizations were requested from
  ChatGPT.
* In the majority of cases the first working implementation was accepted without trying to make the solution more
  elegant.
* The primary goal was to understand how quickly ChatGPT is able to solve relatively simple coding tasks with complex
  descriptions.

## Observations

* In the majority of cases ChatGPT was unable to solve a task at first try. Usually it required human programmer to test
  the code, understand the problem and either fix it manually or request ChatGPT to fix it.
* ChatGPT is quite bad at fixing the defects, especially the complicated ones. It tends to go in circles proposing the
  same solutions again and again without trying to think outside this loop.
* ChatGPT is fundamentally unable to test the code it suggests even if requested explicitly. This inability leads to
  providing the erroneous code again and again.
* ChatGPT is unable to even check the proposed code for compilability. In Kotlin some standard core classes share names
  with Java core classes but have different methods, e.g. ArrayDeque. ChatGPT is unable to actually distinguish them and
  tries to use them interchangeably causing compilation errors.

## Task-specific observations

* __2022, day 5__: Hours were spent trying to get correct crates stacks input parsing from ChatGPT without any luck. It
  was always trying to parse the lines of crates not columns. And even if explained explicitly that the crates are
  stacked in columns the parsing was incorrect. Had to give up and write the parsing code manually.  
