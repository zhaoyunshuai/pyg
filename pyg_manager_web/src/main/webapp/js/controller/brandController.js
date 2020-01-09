//主要是和页面进行数据交互
app.controller("brandController" ,function ($scope,brandService) {
    //分页第四步：添加一个paginationConf对象
    $scope.paginationConf  = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50, 60],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };

    $scope.reloadList = function () {
        brandService.reloadList($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        })
    }
    //页面加载时查询所有品牌数据方法
    $scope.findAll = function () {
        brandService.findAll().success(function (response) {
            $scope.list = response;
        })
    }
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
        })
    }

    //根据品牌Id，查询品牌数据。用于数据的回显
    $scope.findOne=function (id) {
        brandService.findOne(id).success(function (resonse) {
            $scope.brand=resonse;
        })
    }

    $scope.selectIds=[];//准备一个数组，用于存放需要删除的数据的id
    //更新复选框
    $scope.updateSelection=function (event,id) {
        //$event.target--复选框(点击的对象)
        if(event.target.checked){
            //push方法，向数组中添加数据
            $scope.selectIds.push(id);
        }else{
            var idx=$scope.selectIds.indexOf(id);
            //splice("删除元素的索引",需要删除的个数)方法
            $scope.selectIds.splice(idx,1);
        }
    }
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
    }

})