/**
 * 
 */
// 定义模块 actionApp，并添加所依赖的 路由模块ngRoute
var actionApp = angular.module('actionApp',['ngRoute']);



// 对路由进行配置，并添加所依赖的$routeProvider 进行配置
actionApp.config(['$routeProvider' , function($routeProvider) {
	
	$routeProvider.when('/oper', { // 路由名称
		controller: 'View1Controller', // 路由控制器的名称
		templateUrl: 'views/view1.html', // 视图真正的地址
	}).when('/directive', {
		controller: 'View2Controller',
		templateUrl: 'views/view2.html',
	});

}]);
