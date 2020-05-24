1.pom文件
  module标签
  parent标签
  
2.启动提示无法找到主类
  由于project structure中父工程的source folder被设置为
  子模块的source
  这样就导致运行子模块的某个类，在子模块的输出路径中找不到
  解决方法: 删除父模块中的source folder
  