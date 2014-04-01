//
//  AccessLayer.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "AccessBase.h"

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

@end
