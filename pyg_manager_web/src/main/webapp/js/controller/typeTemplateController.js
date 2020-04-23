 //控制层 
app.controller('typeTemplateController' ,function($scope,$controller ,brandService ,specificationService ,typeTemplateService){

	/**
	var user = {username:"zhangsan",age:30};
	var user1 = {"username":"zhangsan","age":30};
	var user2 = '{"username":"zhangsan","age":30}';

	// 把对象转成字符串 JSON.stringify()
	// 把字符串转成对象 JSON.parse()
	// alert(JSON.stringify(user) +"---"+  JSON.stringify(user1));
	alert( JSON.parse(user2).username);

	 */

	$controller('baseController',{$scope:$scope});//继承

	// 把数组变成字符串 [{"id":31,"text":"富光"},{"id":33,"text":"希乐"}]------》富光，希乐
	$scope.arraryToString=function (array) {
        // array=  [{"id":31,"text":"富光"},{"id":33,"text":"希乐"}]
        array = JSON.parse(array);
		var str ="";
        for (var i = 0; i < array.length; i++) {
        	if(i==array.length-1){
                str+=array[i].text;
			}else{
                str+=array[i].text+",";
			}
        }
        return str;
    }


	// $scope.brandList={data:[{"id":"1","text":"华为"},{"id":"2","text":"小米"},{"id":"3","text":"锤子"}]};
    // [{"firstChar":"L","id":1,"name":"联想"},{"firstChar":"H","id":2,"name":"华为"}]
	$scope.findBrandList=function () {
        brandService.findBrandList().success(function (response) {
            //response的格式： [{"id":"1","text":"华为"},{"id":"2","text":"小米"},{"id":"3","text":"锤子"}]
            $scope.brandList={data:response};
        })
    }
	$scope.findSpecList=function () {
        specificationService.findSpecList().success(function (response) {
            //response的格式： [{"id":"1","text":"华为"},{"id":"2","text":"小米"},{"id":"3","text":"锤子"}]
            $scope.specList={data:response};
        })
    }

    // 动态添加扩展属性 数据上：向$scope.entity.customAttributeItems中添加一个对象
    $scope.addCustomAttributeItems=function () {
        $scope.entity.customAttributeItems.push({});
    }
    // 动态删除扩展属性 数据上：从$scope.entity.customAttributeItems中移除一个对象
    $scope.deleCustomAttributeItems=function (index) {
        $scope.entity.customAttributeItems.splice(index,1);
    }

    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		typeTemplateService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(pageNum,pageSize){			
		typeTemplateService.findPage(pageNum,pageSize).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		typeTemplateService.findOne(id).success(
			function(response){
				// 把字符串转成json对象 数组				var 对象 = JSON.parse(字符串);
                response.brandIds = JSON.parse(response.brandIds);
                response.specIds = JSON.parse(response.specIds);
                response.customAttributeItems = JSON.parse(response.customAttributeItems);

				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=typeTemplateService.update( $scope.entity ); //修改  
		}else{
			serviceObject=typeTemplateService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		typeTemplateService.dele( $scope.selectIds ).success(
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
		typeTemplateService.search(pageNum,pageSize,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});	
