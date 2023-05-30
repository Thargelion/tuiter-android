# tuiter-android

Android Application For Tuiter, like Twitter but this really works.

## Use Cases

### User Creation

1. Will try to get the user from Shared Preferences. If true, will try to get the user data from the
   Repository. If false, will send the user to the User Creation form.
2. The creation form will ask the user for a desired username and will send it to the API.

## Scaffolding

The project is built using Google's recommended architecture for Android applications[1] so it has a
UI, and Data layers. Domain was not used because it was not necessary.

## Repository Workflows

There are three Github workflows:

1. Android Linter: Will execute gradle lint on PRs.
2. Test Coverage: Will execute tests and report the coverage on PRs.
3. Deployment: Will build a release with the current APK when a tag is pushed.

1: https://developer.android.com/topic/architecture