app.controller("indexController",function ( $scope,indexService) {
    //从SpringSecurity中去获取当前登录用户。
    $scope.showName=function () {
        indexService.showName().success(function (response) {
            $scope.username = JSON.parse(response);
        })
    }
})