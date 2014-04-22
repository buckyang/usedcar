//
//  AccessLayer.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "AccessBase.h"
#import "../../Entity/Entity/UserInfo.h"

@implementation AccessBase

- (AFHTTPRequestOperationManager*)httpClient
{
    static AFHTTPRequestOperationManager *httpClient = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        httpClient = [[AFHTTPRequestOperationManager alloc] initWithBaseURL:[NSURL URLWithString:httpUrl]];
        httpClient.requestSerializer = [AFJSONRequestSerializer serializer];
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


@end
