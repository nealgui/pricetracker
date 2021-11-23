## Branching Strategy: GitLab flow

Our branching strategy will attempt to follow the GitLab flow approach.  

In the variation we hope to use, we will have a master branch and feature branches, where everything (including bug fixes) are merged into the master branch, and new feature or “release” branches are released only when it is in a production-ready, stable state. Resources on GitLab flow describe a flexible, detailed strategy on how to manage different types of environment branches, so the option of adding a production branch (with stable and long-lasting features) is something we can pursue later on in the project. GitLab flow also handles issue tracking with more detail, so there are different stages to issues (e.g. unassigned merge requests, to ask for review without merging) as well as smooth linking and closing of issues.  

One of the main points to remember is that in this method, developers branch off directly from the stable master branch, allowing a more streamlined and continuous delivery for versioned releases. 

This branching strategy involves some of the methodology of Git flow and GitHub flow, with issue trackers and a well-organized and structured branch use being notable changes. As with Git flow, there is a master branch and supporting branches, but this branching strategy is closer to GitHub flow as the number and type of open branches is simpler. There is also more detail on how to handle different types of branching issues.   

The above branching strategy can be found in further detail at [GitLab flow documentation](https://docs.gitlab.com/ee/topics/gitlab_flow.html#environment-branches-with-gitlab-flow).
