# wejuai-config-server
配置中心服务

### 结构
- configrepo中存放项目配置文件，其名称和项目名称一致可以自动识别对应名称
- 最外部是gradle构建配置
- resources中是本项目的启动配置参数

### 配置文件描述
- 配置加载会从`application.yml`开始，再去加载对应项目的配置文件，profile在项目实际启动时制定也是先加载无profile的配置文件，但是如果就算profile无额外配置内容，也必须有空文件否则无法启动
- 具体项目配置都可以在对应项目的config文件夹中的配置文件中找到使用的方式

### 本地运行
1. resources中配置git地址以及账号密码，并且给与读取权限，`configrepo`文件路径是可以修改的
2. prod的profile中配置账号密码，在线上版本中引用配置的项目会根据密码拉取配置，防止配置泄露，不过一般都是纯内网服务
3. gradle编译通过后运行`ConfigServer.java`的`main()`


### docker build以及运行
- 运行gradle中的docker build task
- 如果配置了其中的第三方仓库可以运行docker push，会先build再push
- 运行方式 docker run {image name:tag}，默认是运行的profile为dev，可以通过环境变量的方式修改，默认启动配置参数在Dockerfile中