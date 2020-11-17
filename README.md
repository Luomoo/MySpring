# Spring

## 动态代理
自己模拟动态代理
大致步骤
1. 生成java文件
2. 编译java到class文件
3. 使用URLClassLoader将class文件加载到JVM

不需要手动创建类文件（因为一旦手动创建类文件，就会产生类爆炸），
通过接口反射生成一个类文件，
然后调用第三方的编译技术，
动态编译这个产生的类文件成class文件，
继而利用UrlclassLoader(因为这个动态产生的class不在工程当中所以需要使用UrlclassLoader)
把这个动态编译的类加载到jvm当中，最后通过反射把这个类实例化。

缺点：首先要生成文件

缺点：动态编译文件 class

缺点：需要一个URLclassloader

软件性能的最终体现在IO操作

## IOC

- [x] 根据XML注入bean

- [x] 使用default-autowire根据类型自动注入