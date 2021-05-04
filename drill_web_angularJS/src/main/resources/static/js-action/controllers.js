/**
 * 
 */
// 定义控制器 View1Controller, 并注入依赖 $rootScope、$scope、$http
actionApp.controller('View1Controller', ['$rootScope', '$scope', '$http', function($rootScope, $scope,$http) {
    // 监听 $viewContentLoaded 事件 - 手段：使用 $scope.$on()方法 - 作用：在页面内容加载完成后，再进行一些额外的操作
    $scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
    

    // 核心逻辑
    // 在scope中定义方法 search - 手段：$scope.<method_name> - 用法：在页面上使用 ng-click来调用
    $scope.search = function(){
      // 获取页面中model的值 - 手段：$scope.<modelName>
      personName = $scope.personName;
      // 向服务端的指定地址（search）发送GET请求 - 手段：使用 $http.get
      $http.get('search',{
    	  params:{personName:personName} // 为GET请求添加参数 - 手段：params:{<参数:值>}
      }).success(function(data){ // 定义 当请求成功时的回调函数  - 手段：.success(<callback_function>)
         // 处理服务端返回的数据 - 手段：使用回调方法的参数data；
          // 把服务端返回的数据赋值给 页面中的model - 手段：$scope.<model_name> - 特征：模型与视图显示 之间是相互绑定的(但其中一方发生变化时，其他一方也随之改变)
    	 $scope.person=data;
      });;
     
    };
}]);

actionApp.controller('View2Controller', ['$rootScope', '$scope',  function($rootScope, $scope) {
    $scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
}]);


