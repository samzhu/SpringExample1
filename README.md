SpringExample1
==============

  使用Spring+Hibernate範例

  搭配 http://www.jsonschema2pojo.org/ 來產生物件
  產生jar物件可與Android共用
  
  符合RESTful 的控制器接口

  改為使用alibaba的fastjson做物件跟json轉換
  號稱比jackson跟gjson更快，並不須依靠註解轉換，一般JavaBean即可轉換

  使用註解 @Valid 進行資料驗證

  使用Hibernate的CurrentSession，生命週期由Hibernate控制，但是這樣必須添加@Transactional()，
  是否連查詢都會開交易控制這段要確認會不會有效能問題

  參考來源
  http://langmnm.iteye.com/blog/2078439
  http://blog.csdn.net/wdyr321/article/details/17129869
