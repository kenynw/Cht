# 茶汇通3.x版安卓客户端

基于MVP模式封闭了部分框架。定义了一套开发规范。并提供了基于这套规范的Activity，Fragment，Presenter等父类及控件和API等，完成APP开发过程中大量繁琐工作。
主要包含3部分：
ui — Presenter与View层的双向注入。管理了Activity与Presenter的引用关系。让Presenter来控制Activity的显示。
expansion — 包含了对ui层的一系列拓展功能。并提供了数据展示及数据列表展示的开发模版。
model — 数据层，在APP启动时初始化所有model，并提供一个处理数据用的后台Looper线程
