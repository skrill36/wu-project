var wuApp = angular.module("wuApp", ["ngRoute", "ui.bootstrap"]);

//ROUTING
wuApp.config(function ($routeProvider) {
  $routeProvider
    .when("/", {
      templateUrl: "views/home.html",
    })
    .when("/login", {
      templateUrl: "views/login.html",
      controller: "loginController",
    })
    .when("/registeration", {
      templateUrl: "views/registeration.html",
      controller: "registerController",
    })
    .when("/dashboard", {
      templateUrl: "views/dashboard.html",
      controller: "dashboardController",
    })
    .when("/dashboard/profile", {
      templateUrl: "views/profile.html",
      controller: "profileController",
    })
    .otherwise({ redirectTo: "/" });
});

//DIRECTIVES

wuApp.directive("basicNav", function () {
  return {
    templateUrl: "directives/navbar.html",
    replace: true,
  };
});
