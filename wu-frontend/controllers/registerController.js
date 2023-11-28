wuApp.controller("registerController", [
  "$scope",
  "$http",
  function ($scope, $http) {
    $scope.user = {
      firstName: "",
      lastName: "",
      emailId: "",
      password: "",
    };

    $scope.signin = function (form) {
      console.log($scope.user);
      $http
        .post("http://localhost:8080/api/signup", $scope.user)
        .then(function (response) {
          console.log(response.data);
          if (response.data.status === true) {
            $scope.errorMessage = null;
            $scope.responseMessage = response.data.message;
            $scope.user = {
              firstName: "",
              lastName: "",
              emailId: "",
              password: "",
            };
            form.$setPristine();
            form.$setUntouched();
          }
        })
        .catch(function (error) {
          console.error(error);
          $scope.user = {};
          form.$setPristine();
          form.$setUntouched();

          $scope.errorMessage = "User Registeration Failed!";
          $scope.responseMessage = null;
        });
    };
  },
]);
