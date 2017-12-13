package com.example.shop.okhttp;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 可以实现 添加公共请求参数
 */
public class LoggingInterceptor implements Interceptor {


  @Override public Response intercept(Chain chain) throws IOException {
    //首先取到Request
    Request request = chain.request();
    Response response = null;
    Request requestProcess = null ;
    if("GET".equals(request.method())){
      String url =  request.url().toString() + "&source=android";
      Request.Builder builder =  request.newBuilder() ;
      builder.get().url(url);
      requestProcess =  builder.build();
      response = chain.proceed(requestProcess);
    } else {
      FormBody.Builder builder = new FormBody.Builder() ;
      RequestBody requestBody =  request.body() ;
      if(requestBody instanceof FormBody){
        FormBody formBody = (FormBody)requestBody ;
        for (int i=0;i<formBody.size();i++){
          builder.add(formBody.encodedName(i),formBody.encodedValue(i));
        }
        builder.add("source","android");
      }
       requestProcess =  request.newBuilder().url(request.url().toString()).post(builder.build()).build() ;
      response = chain.proceed(requestProcess);
    }




    return response;
  }
}