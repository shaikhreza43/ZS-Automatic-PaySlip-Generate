app.config(["$routeProvider","$locationProvider",function($routeProvider,$locationProvider){

    $routeProvider.when('/',{
        controller:'apsgCtrl',
        templateUrl:"index.html"
    })
    .when('/dashboard',{
        templateUrl:'dashboard.html'
    }).when('/login',{
        controller:'apsgCtrl',
        templateUrl:'login.html'
    }).when('/logout',{
        controller:'logOutCtrl',
        templateUrl:'logout.html'
    }).otherwise({
        redirectTo : 'index.html'
    });;

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
      }); //Remove the '#' from URL

}]);