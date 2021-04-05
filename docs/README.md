```
# Sign Up
POST http://localhost:9091/api/signup
Content-Type: application/json

{
  "email": "ab@lazy.miu",
  "password": "noop",
  "role": {
    "name": "John Doe",
    "telephoneNumber": "82376578221",
    "address": "1000 st, Fairfield, IA, 57239",
    "currentJob": "Software Engineer",
    "currentCompany": "Microsoft",
    "roleName": "ALUMNI"
  }
}


###
#Login

POST http://127.0.0.1:9091/api/login
Content-Type: application/json

{
  "email": "ab@lazy.miu",
  "password": "noop"
}

###
# Post a job link
POST http://127.0.0.1:9091/api/postjob/joblink
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYkBsYXp5Lm1pdSIsImlhdCI6MTYxNzU2MzI5OCwiZXhwIjoxNjE3NjQ5Njk4fQ.7-rez5v9ujyqCtzgTpEX0CDZmdOAq8NrZ-XQ6iRr_O3Hqa_utP11k9baA4pVoo4vBFeh34Zbgtxu1albXlVTNA



```