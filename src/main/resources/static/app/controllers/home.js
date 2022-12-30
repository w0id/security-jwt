angular.module('jwtApp')

    .controller('HomeController', function ($scope, $document, $http) {
        $scope.filter = {}

        $scope.loadProducts = function (page) {
            $scope.page = page;
            $http({
                url: 'api/v1/products',
                method: 'GET',
                params: {
                    min: $scope.filter ? $scope.filter.min : null,
                    max: $scope.filter ? $scope.filter.max : null,
                    p: $scope.filter ? $scope.page : null
                }
            })
                .then(function (response) {
                    $scope.productsList = response.data.content;
                    $scope.page = response.data.pageable.pageNumber + 1;
                    $scope.totalPages = response.data.totalPages;
                    if ($scope.page > $scope.totalPages) {
                        $scope.page = $scope.totalPages -1;
                    }
                    $scope.count = response.data.content.length;
                    $scope.loadCartItems()
                });
        };

        $scope.loadCartItems = function () {
            $http.get('api/v1/cart_items')
                .then(function (response) {
                    $scope.cart = response.data;
                });
        };

        $scope.addToCart = function (id) {
            $http({
                url: 'api/v1/cart_items',
                method: 'POST',
                params: {
                    id: id
                }
            }).then(function (response) {
                        $scope.loadCartItems();
                    });
        }

        $scope.changeQuantity = function (id, quantity) {
            $http({
                url: 'api/v1/cart_items',
                method: 'PUT',
                params: {
                    id: id,
                    quantity: quantity
                }
                  }).then(function (response) {
                      $scope.loadCartItems();
            })
        }

        $scope.deleteFromCart = function (id) {
            $http({
                url: 'api/v1/cart_items',
                method: 'DELETE',
                params: {
                    id: id
                }
            })
                .then(function (response) {
                    $scope.loadCartItems();
                });
        };

        $scope.setActive = function (static) {
            if (static == null) {
                var header = $document[0].getElementById("pagination");
                var btns = header.getElementsByClassName("page-item");
                for (var i = 0; i < btns.length; i++) {
                    btns[i].addEventListener("click", function () {
                        var current = $document[0].getElementsByClassName("active");
                        current[0].className = current[0].className.replace(" active", "");
                        this.className += " active";
                    });
                }
            } else {
                document.getElementsByClassName("active")[0].classList.toggle("active");
                document.getElementsByClassName("static")[0].classList.toggle("active");
            }
        }

        $scope.changePage = function (page) {
            $scope.setActive();
            $scope.loadProducts(page);
        }

        $scope.loadProducts(1);
    }).controller('range', function ($scope, $document) {
    $scope.$parent.filter = $scope;
    $(".js-range-slider").ionRangeSlider({
        onFinish: function (data) {
            $scope.min = data.from
            $scope.max = data.to
            $scope.$parent.setActive(true);
            $scope.$parent.loadProducts(1);
        }
    });
})