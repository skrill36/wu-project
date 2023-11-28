wuApp.controller("loginController", [
  "$scope",
  "$location",
  "$http",
  function ($scope, $location, $http) {
    $scope.loginUser = {
      emailId: "",
      password: "",
    };

    $scope.login = function (form) {
      console.log($scope.loginUser);
      $http
        .post("http://localhost:8080/api/signin", $scope.loginUser)
        .then(function (response) {
          console.log(response.data);
          $scope.responseMessage = response.data.message;
          $scope.status = response.data.status;
          if ($scope.loginForm.$valid && $scope.status === true) {
            $scope.responseMessage = null;
            $location.path("/dashboard");
          }
        })
        .catch(function (error) {
          console.error("Error:", error);
          $scope.responseMessage = "Login failed";
          $scope.loginUser = {};
          form.$setPristine();
          form.$setUntouched();
        });
    };
  },
]);
