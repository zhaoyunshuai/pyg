// 表现层  和页面数据交互
app.controller("brandController",function ($scope,brandService,$controller) {


    $controller("baseController",{$scope:$scope}); //brandController继承baseController的语法 extends

    $scope.findAll=function () {
        brandService.findAll().success(function (response) {
            $scope.list = response;
        })
    }


    // 保存方法
    $scope.save=function () {
        var obj=null;
        if($scope.entity.id!=null){
            obj = brandService.update($scope.entity);
        }else{
           obj = brandService.add($scope.entity);
        }
        obj.success(function (response) {
            // response ={success:true|false,message:"添加成功"|"添加失败"}
            if(response.success){
                // 列表的数据刷新
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        })
    }

    // 根据id查询对象
    $scope.findOne=function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity=response;
        })
    }


    // 删除
    $scope.dele=function () {
        // 判断数组中是否有值
        if($scope.selectIds.length==0){
            alert("请选择您需要删除的数据!");
            return;
        }
        var flag = window.confirm("确定要删除您选择的数据吗?");
        if(flag){
           brandService.dele($scope.selectIds).success(function (response) {
                if(response.success){
                    $scope.reloadList();//刷新列表数据
                    $scope.selectIds=[];// 清空数组
                }else{
                    alert(response.message);
                }
            })
        }
    }

    $scope.searchEntity={};

    $scope.search=function (pageNo,pageSize) {
        // $scope.searchEntity   页码  条数
        brandService.search(pageNo,pageSize,$scope.searchEntity).success(function (response) {
            // response:分页后的结果  当前也得数据list 总条数  {total:100,rows:[{},{}]}
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }



})