# HackerDetectionSystem

Spring Boot application that provide a REST service with the operation 'parseLine'.
This operation receives a String with one log line of a external signin process. 
The format of this log line is "ip,date,action,username".
  IP look like 80.238.9.179 Date is in the epoch format like 1336129471 Action is one of the following:
      SIGNIN_SUCCESS or SIGNIN_FAILURE Username is a String like Pedro.Lopez
  
The operation return the IP address if any suspicious activity is identified or null if the activity appears to be normal.
It determines an IP like suspicious if that has attempted a failed login 5 or more times within a 5 minute period.
 
Use Redis as a Database.
