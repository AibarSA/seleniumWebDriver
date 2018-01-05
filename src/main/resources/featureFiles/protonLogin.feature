Feature: proton test


@smokeTest
Scenario Outline: login to protonmail and creating new message and save it to draft then send it from drafts
Given user navigates to protonmail home page
When  click login button and enters user credentials and submits login form
And   user creates new mail with "<recipient>" , "<subject>" , "<textContent>" and saves it in draft
Then  check presence draft with same "<recipient>" , "<subject>" , "<textContent>" and send the message


Examples:
|recipient                |subject    |textContent                              |
|aibar.abilchanov@mail.ru |From Aibar |Test automation engineers in da house!!! |