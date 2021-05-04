/**
 * 定制一个指令 - 其中封装jqueryui中的 datePicker指令
 */
// 自定义一个 名称为datePicker 的指令
actionApp.directive('datePicker',function(){
    return {
        restrict: 'AC', // 为自定义的指令添加限制 - 限制为：属性指令 与 样式指令；   手段：restrict属性
        // 定义指令的具体内容 - 手段：link方法；   特征：link方法中可以使用 当前scope、当前元素以及元素属性
        link:function(scope,elem,attrs){
           // scope.treeObj = $.fn.zTree.init(elem, scope.settings);
            // 初始化 jqueryui中的datePicker组件
            elem.datepicker();
        }
    };
});


