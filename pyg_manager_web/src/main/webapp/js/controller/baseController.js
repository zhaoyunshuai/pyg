//主要是和页面进行数据交互

app.controller("baseController" ,function ($scope) {
    // 主要是存放一些公共的代码，基本上每页controller都会用到
    //用于指定分页的相关参数
    $scope.paginationConf  = {
        currentPage: 1,//当前页数
        totalItems: 10,//总共的条数
        itemsPerPage: 10,//每页条数
        perPageOptions: [10, 20, 30, 40, 50, 60],//下拉框中用于选择每页显示的条数
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };

    //复选框的操作
    $scope.selectIds=[];//准备一个数组，用于存放需要删除的数据的id
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
    };
    //用于重新去加载数据
    $scope.reloadList = function () {
        $scope.search( $scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage);

    };

})