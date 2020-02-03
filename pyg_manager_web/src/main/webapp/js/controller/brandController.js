//主要是和页面进行数据交互

app.controller("brandController" ,function ($scope,brandService,$controller) {

  $controller("baseController",{$scope:$scope});//继承的固定语法

    //页面加载时查询所有品牌数据方法
    $scope.findAll = function () {
        brandService.findAll().success(function (response) {
            $scope.list = response;
        })
    };
    //保存||修改方法
    $scope.save=function () {
        var obj=null;
        if($scope.brand.id!=null){
            obj= brandService.update($scope.brand);
        }else{
            obj= brandService.add($scope.brand);

        }
        obj.success(function (result) {
            if(result.success){
                $scope.reloadList();
                // alert(result.msg);
            }else{
                alert(result.msg);
            }
        });
    };

    //根据品牌Id，查询品牌数据。用于数据的回显
    $scope.findOne=function (id) {
        brandService.findOne(id).success(function (resonse) {
            $scope.brand=resonse;
        })
    };
    //删除数据，真实中应该是逻辑删除，而不是真正的删除。其实就是更新。
    $scope.delete=function () {
        if($scope.selectIds.length==0){
            alert("请选择要删除的品牌数据")
            return;
        }else{
           brandService.delete($scope.selectIds).success(function (result) {
                if(result.success){
                    $scope.reloadList();
                    $scope.selectIds=[];
                }else{
                    alert(result.msg);
                }
            })
        }
    };


    //模糊查询
    $scope.searchEntity={};
    $scope.search=function () {
        brandService.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage,$scope.searchEntity).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })

    }
})