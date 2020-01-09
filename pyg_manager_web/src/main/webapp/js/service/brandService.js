//主要是和后端进行交互，不和html以及数据的绑定进行交互。
app.service("brandService",function ($http) {
    this.reloadList = function (currentPage,itemsPerPage) {
        return $http.get("../brand/findPage?pageNo=" + currentPage + "&pageSize=" + itemsPerPage);
    }
    //页面加载时查询所有品牌数据方法
    this.findAll = function () {
        return $http.get("../brand/findAll");
    }
    //新增方法
    this.add=function (brand) {
        return $http.post("../brand/add",brand);
    }
    //修改方法
    this.update=function (brand) {
       return $http.post("../brand/update",brand);
    }

    //根据品牌Id，查询品牌数据。用于数据的回显
    this.findOne=function (id) {
       return $http.get("../brand/findOne?id="+id);
    }
       //删除数据，真实中应该是逻辑删除，而不是真正的删除。其实就是更新。
    this.delete=function (selectIds) {
        if(selectIds.length==0){
            alert("请选择要删除的品牌数据")
            return;
        }else{
            window.confirm("确定要删除数据吗？");
           return  $http.post("../brand/delete?ids="+selectIds);
        }
    }

})