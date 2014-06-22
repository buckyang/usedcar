//
//  AccessLayer.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "AccessBase.h"
#import "../../Entity/Entity/EntityBase.h"
#import "../../Entity/Entity/UserInfo.h"

@implementation AccessBase

- (AFHTTPRequestOperationManager*)httpClient
{
    static AFHTTPRequestOperationManager *httpClient = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        httpClient = [[AFHTTPRequestOperationManager alloc] initWithBaseURL:[NSURL URLWithString:httpUrl]];
        httpClient.requestSerializer = [AFHTTPRequestSerializer serializer];
        httpClient.responseSerializer = [AFJSONResponseSerializer serializer];
    });
    return httpClient;
}

- (NSString*)accessToken:(UserInfo*)aUserInfo
{
    UInt64 timeNow = [[NSDate date] timeIntervalSince1970]*1000;
    NSString *md5Value = [NSString stringWithFormat:@"%@%@%lld",aUserInfo.accessToken,PrivateKey,timeNow];
    md5Value = [[NSString MD5:md5Value] lowercaseString];
    
    NSString *base64Value = [NSString stringWithFormat:@"%@%lld&%lld",md5Value,aUserInfo.userId,timeNow];
    base64Value = [NSString base64Encode:base64Value encoding:NSUTF8StringEncoding];
    
    return base64Value;
}


- (void)accessURL:(NSString*)aUrl withParameters:(NSMutableDictionary*)parameters withCallback:(HttpCallback)aCallback
{
    parameters[@"deviceId"] = [NSString UDID];
    
    if ([UserInfo shareInstance].logined && ![NSString isEmpty:[UserInfo shareInstance].accessToken]) {
        parameters[@"accessToken"] = [self accessToken:[UserInfo shareInstance]];
    }
    
    INFO(@"%@ Parameters:\n%@",aUrl,parameters);
    
    [self.httpClient POST:aUrl
               parameters:parameters
                  success:^(AFHTTPRequestOperation *operation, id responseObject) {
                      INFO(@"%@ Parameters:\n%@ Resonse:%@",aUrl,parameters,responseObject);
                      
                      
                      EntityBase *base = [[EntityBase alloc] initWithDictionary:responseObject];
                      if (base.executionResult) {
                          aCallback(responseObject,HTTPAccessStateSuccess);
                      }
                      else
                      {
                          aCallback(base,HTTPAccessStateFail);
                      }
    }
                  failure:^(AFHTTPRequestOperation *operation, NSError *error) {
                      
                      EntityBase *base = [[EntityBase alloc] init];
                      base.message = @"网络连接失败";
                      base.executionResult = NO;
                      aCallback(base,HTTPAccessStateDisconnection);
                      
                      INFO(@"%@ Parameters:\n%@ Error:%@",aUrl,parameters,error);
                  }];
}


@end
