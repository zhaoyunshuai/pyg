// 业务逻辑层 和后台数据交互 不出现$scope
app.service("brandService",function ($http) {


    /**
     * 此方法是在添加模板时需要的 要求数据格式是 [{"id":"1","text":"华为"},{"id":"2","text":"小米"},{"id":"3","text":"锤子"}]
     */
    this.findBrandList=function () {
       return $http.get("../brand/findBrandList");
    }

    this.findAll=function () {
        // $http.get("../brand/findAll").success(function (response) {
        //     $scope.list = response;
        // })
       return $http.get("../brand/findAll");
    }

    // 保存方法

    this.add=function (entity) {
       return $http.post("../brand/add",entity);
    }
    this.update=function (entity) {
        return $http.post("../brand/update",entity);
    }



    // 根据id查询对象
    this.findOne=function (id) {
       return $http.get("../brand/fineOne?id="+id);
    }


    // 删除
    this.dele=function (selectIds) {
           return $http.get("../brand/dele?ids="+selectIds);
    }


    this.search=function (pageNo,pageSize,searchEntity) {
        // $scope.searchEntity   页码  条数
        return $http.post("../brand/search?pageNo="+pageNo+"&pageSize="+pageSize,searchEntity);
    }

})