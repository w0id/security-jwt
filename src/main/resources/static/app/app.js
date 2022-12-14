angular.module('jwtApp', ['ui.router'])

    .run(function (AuthService, $rootScope, $state, $http) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            if (!AuthService.user) {
                if (localStorage['token'] && localStorage['user']) {
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
                    AuthService.user = JSON.parse(localStorage.getItem('user'));
                    event.preventDefault();
                    $state.go('home');
                } else {
                    if (toState.name != 'login' && toState.name != 'register') {
                        event.preventDefault();
                        $state.go('login');
                    } else if (toState.name == 'register') {
                        $rootScope.selected_register = true;
                        $rootScope.selected_login = false;
                    } else if (toState.name == 'login') {
                        $rootScope.selected_register = false;
                        $rootScope.selected_login = true;
                    }
                }
            } else {
                if (toState.data) {
                    var hasAccess = false;
                    angular.forEach(toState.data, function (value) {
                        for (var i = 0; i < AuthService.user.authorities.length; i++) {
                            var role = AuthService.user.authorities[i];
                            if (value.role == role.authority || value == role.authority) {
                                hasAccess = true;
                                break;
                            }
                            if (hasAccess) {
                                break;
                            }
                        }
                    })
                    if (!hasAccess) {
                        event.preventDefault();
                        $state.go('access-denied');
                    }
                }
            }
        });
    });