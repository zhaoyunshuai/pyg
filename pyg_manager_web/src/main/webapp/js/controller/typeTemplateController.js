app.controller("typeTemplateController",function($scope,$controller,specificationService,brandService,typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
	 $scope.search=function(pageNo,pageSize){
		 typeTemplateService.search(pageNo,pageSize,$scope.searchEntity).success(function(response){
			 $scope.paginationConf.totalItems = response.total;
			 $scope.list = response.rows;
		 })
	 }
	 
	 
	 $scope.save=function(){
		 var obj=null;
		 if($scope.entity.id!=null){
			 obj = typeTemplateService.update($scope.entity) ;
		 }else{
			 obj = typeTemplateService.add($scope.entity) ;
		 }
		 obj.success(function(response){
			 if(response.success){
				 $scope.reloadList();
			 }else{
				 alert(response.message);
			 }
			 
		 })
	 }
	 

	 
	 $scope.findOne=function(id){
		 typeTemplateService.findOne(id).success(function(response){
			/* response.brandIds = JSON.parse(response.brandIds);
			 response.specIds = JSON.parse(response.specIds);
			 response.customAttributeItems = JSON.parse(response.customAttributeItems);
			 */
			 $scope.entity = response;
		 })
	 }
})