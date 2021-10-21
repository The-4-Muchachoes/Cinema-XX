# Sprint 2 Status

During Sprint 2 we have finished every requirement
presented by the product owner. Some endpoints are 
still due for some optimisation as there are too many
available properties in certain requests and 
responses. For example, at the moment the response 
when creating a new account contains the encrypted
password (as was also mentioned in our video). Most 
of our current issues can be fixed by better use
of DTOs, but there are also a few places that need
better handling of bad user inputs which result 
in Internal Server Errors instead of a proper 
response

One other aspect of the code that we have not paid
much attention to is automated testing. At this time
only a single test was written during this project.
Manual testing through Postman has been our main
method of testing.

During the Sprint 1 review meeting our AWS EC2 
instance had somehow maxed our its RAM capacity and
froze. It was impossible to even SSH into the instance,
let alone restart the Docker containers that had
also crashed when the instance froze.
This fact was deduced from the Docker exit code.

