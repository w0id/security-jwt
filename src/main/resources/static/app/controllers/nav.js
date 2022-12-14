angular.module('jwtApp')
    .controller('NavController', function ($http, $scope, AuthService, $state, $rootScope) {
        if (localStorage['user']) {
            $scope.user = AuthService.user;
        }
        $scope.$on('LoginSuccessful', function () {
            $scope.user = AuthService.user;
        });
        $scope.$on('LogoutSuccessful', function () {
            $scope.user = null;
        });
        $scope.logout = function () {
            AuthService.user = null;
            localStorage.removeItem('user');
            localStorage.removeItem('token');
            $rootScope.$broadcast('LogoutSuccessful');
            $state.go('login');
        };
    });