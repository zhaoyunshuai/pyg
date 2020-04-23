// 业务逻辑层 和后台数据交互 不出现$scope
app.service("specificationService",function ($http) {

    this.findSpecList=function () {
       return $http.get("../specification/findSpecList");
    }
    this.findAll=function () {
       return $http.get("../specification/findAll");
    }

    // 保存方法

    this.add=function (entity) {
       return $http.post("../specification/add",entity);
    }
    this.update=function (entity) {
        return $http.post("../specification/update",entity);
    }



    // 根据id查询对象
    this.findOne=function (id) {
       return $http.get("../specification/fineOne?id="+id);
    }


    // 删除
    this.dele=function (selectIds) {
           return $http.get("../specification/dele?ids="+selectIds);
    }


    this.search=function (pageNo,pageSize,searchEntity) {
        // $scope.searchEntity   页码  条数
        return $http.post("../specification/search?pageNo="+pageNo+"&pageSize="+pageSize,searchEntity);
    }

})