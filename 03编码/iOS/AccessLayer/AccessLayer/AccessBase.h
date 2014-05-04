//
//  AccessLayer.h
//  AccessLayer
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AFNetworking/AFNetworking.h"

@class UserInfo;

/**
 *  @brief 私钥
 */
static NSString *PrivateKey = @"uja6snx21b";

static NSString *httpUrl=@"http://www.2soce.com:8080";

/**
 *  @brief 请求状态枚举
 */
typedef enum
{
	HTTPAccessStateDefault = 0, /**<默认 */
	HTTPAccessStateSuccess = 1, /**< 成功 */
	HTTPAccessStateFail = 2, /**< 失败 */
	HTTPAccessStateDisconnection = 3  /**< 网络联接失败 */
}
HTTPAccessState;

/**
 *  @brief 网络请求结果
 *
 *  @param info      网络请求返回值
 *  @param isSuccess HTTPAccessState类型，判断是否成功
 */
typedef void(^HttpCallback)(id info,HTTPAccessState isSuccess);


@interface AccessBase : NSObject

- (AFHTTPRequestOperationManager*)httpClient;

/**
 *  @brief 用户访问令牌
 *
 *  @param aUserInfo 用户信息 UserInfo实例
 *
 *  @return 令牌数据
 */
- (NSString*)accessToken:(UserInfo*)aUserInfo;

/**
 *  @brief 网络访问
 *
 *  @param aUrl       地址
 *  @param parameters 参数
 *  @param aCallback  返回值
 */
- (void)accessURL:(NSString*)aUrl withParameters:(NSMutableDictionary*)parameters withCallback:(HttpCallback)aCallback;

@end
