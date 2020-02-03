//主要是和后端进行交互，不和html以及数据的绑定进行交互。
app.service("specificationService",function ($http) {
    this.reloadList = function (currentPage,itemsPerPage) {
        return $http.get("../specification/findPage?pageNo=" + currentPage + "&pageSize=" + itemsPerPage);
    }
    //页面加载时查询所有品牌数据方法
    this.findAll = function () {
        return $http.get("../specification/findAll");
    }
    //新增方法
    this.add=function (specification) {
        return $http.post("../specification/add",specification);
    }
    //修改方法
    this.update=function (specification) {
       return $http.post("../specification/update",specification);
    }

    //根据品牌Id，查询品牌数据。用于数据的回显
    this.findOne=function (id) {
       return $http.get("../specification/findOne?id="+id);
    }
       //删除数据，真实中应该是逻辑删除，而不是真正的删除。其实就是更新。
    this.delete=function (selectIds) {
        if(selectIds.length==0){
            alert("请选择要删除的品牌数据")
            return;
        }else{
            window.confirm("确定要删除数据吗？");
           return  $http.post("../specification/delete?ids="+selectIds);
        }
    };
    this.search=function (currentPage,itemsPerPage,searchEntity) {
        return $http.post("../specification/search?pageNo=" + currentPage + "&pageSize=" + itemsPerPage,searchEntity);
    }
})