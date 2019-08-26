[English](/README.md) | [中文](/README.cn.md)
# MyMVP
A simple Android MVP base framework.

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

## 1. Environment:
1. Android Studio: 3.4.0+
2. Gradle: 5.1.1+
	> Gradle's version is depending on Android Studio's version

## 2. Job done:
1. Base HTTP RESTful API client.
	> use okhttp3 lib
2. Base 'Model' template with a customized data class, with add/delete/modify/fetch/filter functions.
3. Base 'Presenter' with most important life-cycle functions same with android Fragment, using android broadcast to deal with async-response from HTTP request.
4. Base 'Presenter' with data create/modify/delete functions and data list fetch function.
4. Base 'View' extending android Fragment, calling life-cycle function of Presenter in each Fragment's life-cycle function.
5. A simple example to fetch user list from server and show as list. The server and API is fixed in code, please change them for test.

## 3. Job undo:
1. HTTP error process
2. Model fetching list by page
3. The logic of model management in presenter is not so good...
4. Something else...
----
# Attention:
1. The server's API must be RESTful API, and GET method returns result as an array even if only one.
2. The 'Presenter' already has the reference of the 'View', use it directly please. If you save the 'View' reference in sub 'Presenter', please remember to release it before onDestroy() of 'Presenter', so that the 'View' can be recycled by GC.
----
# Diagram:
## Class defination:
![Class](/design/BaseMVP.png)
## Initialization process:
![Initialization](/design/Initialization.png)
## Finalization process:
![Finalization](/design/Finalization.png)
## User action process:
![UserAction](/design/UserAction.png)