app.service("indexService",function ($http) {
    this.showName=function () {
        return $http.get("../index/showName");
    }
})