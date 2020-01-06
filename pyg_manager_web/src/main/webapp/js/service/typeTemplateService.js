app.service("typeTemplateService",function($http){
	
	
	this.findAll=function(){
		return $http.get('../data/typeTemplateList.json');	  // TODO测试数据	
	}
	
	this.search=function(pageNo,pageSize,searchEntity){
		return $http.post("../data/typeTemplate.json",searchEntity);  // TODO测试数据
	}
	
	this.add=function(entity){
		return $http.post("../typeTemplate/add",entity);
	}
	
	this.update=function(entity){
		return $http.post("../typeTemplate/update",entity);
	}
	
	
	this.findOne=function(id){
		return $http.get("../data/typeTemplate_one.json"); // TODO测试数据
	}
	
})