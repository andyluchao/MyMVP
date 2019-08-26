[English](/README.md) | [中文](/README.cn.md)
# MyMVP
一个简单的MVP基础框架
[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

## 1. 开发环境:
1. Android Studio: 3.4.0+
2. Gradle: 5.1.1+
	> Gradle的版本依赖于Android Studio的版本

## 2. 已完成工作:
1. HTTP RESTful API client基类.
	> 使用okhttp3库
2. Model模板基类，指定data的类型，包括增/删/改/查/过滤等接口
3. Presenter基类，包含与Fragment主要生命周期类似的接口，并使用android广播来处理HTTP的异步响应
4. Presenter基类提供了create/modify/delete三种接口来操作数据，内部根据数据的类型自动找到对应的model来执行动作
5. View基类，继承自Fragment，在生命周期函数中调用presenter对应的生命周期函数
6. 一个简单的example，显示后台user列表，后台server的API和IP代码中固定了，需要手动修改成本地测试环境，或直接返回测试数据

## 3. 未完成工作:
1. HTTP错误码的处理
2. Model基类fetch接口支持按page获取
3. Presenter基类中对于model的管理不太人性化，主要是查找model不那么方便
4. 其他待续
----
# 注意:
1. 后台server的API必须遵守RESTful API规则，GET方法API返回的数据必须是数组，及时只有一个数据
2. Presenter基类中已经持有View类的引用，可以直接使用，如果Presenter子类中额外持有View的引用，请确保在Presenter调用onDestroy前释放了该引用，以便GC回收View对象。
----
# 架构图:
## 类关系图:
![类间关系](/design/BaseMVP.png)
## 初始化时序图:
![初始化](/design/Initialization.png)
## 终止化时序图:
![终止化](/design/Finalization.png)
## 用户操作时序图:
![用户操作](/design/UserAction.png)