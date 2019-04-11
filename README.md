# SMS x Discord Client

This is required alongside the [server](https://github.com/EmotionalLove/SMS_x_Discord). 

# Usage

Download the release from the releases tab in this repo and use `java -jar <the jar>.jar` to run it. A config will generate and it will exit. Edit the config with the desired information and then re-run it. Easy. You'll also have to set up and sign up for SendGrid.

# Config

```yml
originEmail: # the email for msgs to be sent from, should be something@yourdomain.com
destinationEmail: # The email for messages to be delievered too, should be 7021112222@sms.att.net (or whatever your carrier uses)
sendgridToken: # SendGrid API Key
discordToken: # Discord Auth Token
channelToListen: # ID of the private dm channel you want to be communicating with
```

# SendGrid setup

You'll need to set up a Inbound parse webhook for the server part of SMS x Discord, and direct it to the POST url as described in that readme.