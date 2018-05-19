var app = angular.module('userregistrationsystem', ['ngRoute', 'ngResource']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'homeController',
            templateUrl: '/template/home.html'
        })
        .when('/list-all-users', {
            templateUrl: '/template/listuser.html',
            controller: 'listUserController'
        }).when('/register-new-user', {
        templateUrl: '/template/userregistration.html',
        controller: 'registerUserController'
    }).when('/update-user/:id', {
        templateUrl: '/template/userupdation.html',
        controller: 'usersDetailController'
    }).when('/login', {
        templateUrl: '/login/login.html',
        controller: 'loginController'
    }).when('/logout', {
        templateUrl: '/login/login.html',
        controller: 'loginController'
    }).otherwise({
        redirectTo: '/login'
    });
});

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
    // $httpProvider.interceptors.push('AuthInterceptor');
}]);
