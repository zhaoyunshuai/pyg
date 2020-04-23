app.controller("baseController",function ($scope) {
    $scope.paginationConf = {
        currentPage: 1,  //当前页码
        totalItems: 10, //总条数
        itemsPerPage: 10, //每页显示的条数
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){ //1、加载paginationConf对象触发  2、属性值改变时
            // $scope.selectIds=[]; //翻页时把数组清空
            $scope.reloadList();//重新加载
        }
    };

    $scope.reloadList=function () {
        // 需要两个参数：
        // $scope.paginationConf.currentPage;//页码
        // $scope.paginationConf.itemsPerPage;//每页显示的条数
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);

    }
    $scope.selectIds=[]; //准备一个空数组，放的是准备删除数据的ids
    // 点击复选框方法
    $scope.updateSelections=function (event,id) {
        // event.target//点击的对象（复选框）
        if(event.target.checked){  //勾选
            // 向数组中添加id  push方法
            $scope.selectIds.push(id);
        }else{//取消勾选
            // 从数组中移除id  splice
            // $scope.selectIds.splice(id所处的索引值，删除的个数);
            // "qwerty" 't'的索引    "qwerty".indexOf('t');
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index,1);
        }
    }

    // 全选
    $scope.checkedAll=function (event) {
        if(event.target.checked){  //全选按钮的勾选
            // 把当前页list中的每个对象的id放入到数组中
            for (var i = 0; i < $scope.list.length; i++) {
                var id = $scope.list[i].id;
                // 判断id是否在数组中存在，如果不存在再push
                if(!$scope.ifChecked(id)){
                    $scope.selectIds.push(id);
                }

            }
        }else{  //全选按钮的取消勾选
            $scope.selectIds=[];
        }

    }

    // 判断是否勾选  从数据上考虑：判断id是否在数组中
    $scope.ifChecked=function (id) {
       var index = $scope.selectIds.indexOf(id);
       if(index==-1){  //id没有在数组中
           return false;
       }else{
           return true;
       }
    }

    //判断全选的复选框的勾选状态  判断依据：当前页的list的长度和数组的长度比较  举例：list的长度10  数组的长度9
    $scope.ifCheckedAll=function () {
        // 当前页list的每个对象的id是否都出现在数组中，但凡有一个不存在就是未勾选的状态 return false
        for (var i = 0; i < $scope.list.length; i++) {
            var id =$scope.list[i].id;
            // 判断id是否在数组中
            if(!$scope.ifChecked(id)){
                return false;
            }
        }
        return true;
    }

})