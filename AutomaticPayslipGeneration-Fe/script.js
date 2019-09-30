var app = angular.module("zs-apsg", ["ngRoute"]);
app.controller("apsgCtrl", function($log, $scope, $http, $location) {
  $log.log("Inside apsgCtrl");

  $scope.formData = {};
  $scope.login = function(formData) {
    console.log(formData);
    $http.post("http://localhost:6500/apsg/login", formData).then(
      function(success) {
        $location.path("/dashboard");
        console.log("Success Message", success);
      },
      function(failure) {
        console.error("Failure Message", failure);
      }
    );
  };
});

app.controller("logOutCtrl",function($log,$scope,$http,$location){

  $log.log("Inside LogOut Controller...");
  $scope.logout=function(){
    $http.get("http://localhost:6500/apsg/logout").then(
      function(success){
        $location.path("/logout");
        console.log("Logout Success",success);
    },function(failure){
      console.log("Error Occured",failure);
    });
  }
});

app.controller("saveCtrl",function($log,$scope,$http,$location){

  $log.log("Inside saveCtrl controller..");
  $scope.save= function(signupData){
    $http.post("http://localhost:6500/apsg/save",signupData).then(
      function(success){
        alert("Saved Success!");
        $location.path("/");
        console.log("Saved Success",success)
    },function(failure){
      alert("Data Couldn\'t be saved.");
      console,error("Failure Message",failure);
    });
  }
});