angular.module('jwtApp')
    .controller('RegisterController', function ($http, $scope, AuthService) {
        $scope.submit = function () {
            $scope.register_selected=true;
            $http.post('http://localhost:9099/register', $scope.appUser).success(function (res) {
                $scope.appUser = null;
                $scope.confirmPassword = null;
                $scope.register.$setPristine();
                $scope.message = "Успешная регистрация!";
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
    });