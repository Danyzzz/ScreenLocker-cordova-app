# Cordova Screen Locker Plugin
The plugin helps you to lock and unlock device screen programmatically.

Thanks to kitolog for original project!
This is a different version for me and my test.

#Sample

function successCallback(){
  console.log('success');
  //do some staff
};

function errorCallback(e){
  console.log('error');
  console.log(e);
};

window.screenLocker.unlock(successCallback, errorCallback);

#License

This software is released under the Apache 2.0 License.

© 2015 AppLurk, All rights reserved
