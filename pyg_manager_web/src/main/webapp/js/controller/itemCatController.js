 //控制层 
app.controller('itemCatController' ,function($scope,$controller  ,typeTemplateService ,itemCatService){
	
	$controller('baseController',{$scope:$scope});//继承

	$scope.grade=1; //记录当前列表显示的是第几级列表数据，默认是第一级

	$scope.entity1=null; //接收面包屑上一级分类对象
	$scope.entity2=null; //接收面包屑上二级分类对象


	$scope.parentId=0; //即将保存商品分类对象的parentId 初始值是1
	// 点击查询下级按钮时grade的加1
	$scope.setGrade=function (grade,pojo) {
        $scope.grade = grade;

        $scope.parentId=pojo.id;

        // grade是1时 $scope.entity1和$scope.entity2没有值
        if($scope.grade==1){
            $scope.entity1=null;
            $scope.entity2=null;
        }
        // grade是2时 对$scope.entity1赋值 pojo
        // grade是3时 对$scope.entity2赋值 pojo
		if($scope.grade==2){
            $scope.entity1=pojo;
            $scope.entity2=null;
		}
		if($scope.grade==3){
            $scope.entity2=pojo;
		}

    }

	// 根据parentId查询分类数据
	$scope.findByParentId=function (parentId) {
        itemCatService.findByParentId(parentId).success(function (response) {
			$scope.list=response;
        })
    }

    $scope.findTypeTemplateList=function () {
        typeTemplateService.findAll().success(function (response) {
			$scope.typeTemplateList=response;
        })
    }



    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(pageNum,pageSize){			
		itemCatService.findPage(pageNum,pageSize).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象
		// 向$scope.entity中追加一个parentId属性并且赋值
        // $scope.entity.parentId=11;
        $scope.entity['parentId']= $scope.parentId;

		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
                    // $scope.reloadList();//重新加载
					$scope.findByParentId($scope.parentId);
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(pageNum,pageSize){			
		itemCatService.search(pageNum,pageSize,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});	
