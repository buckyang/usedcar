//
//  UserInfo.h
//  Entity
//
//  Created by 舒联勇 on 14-4-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  @brief 用户信息
 */
@interface UserInfo : NSObject

/**
 *  @brief 用户类型
 */
typedef enum
{
    SIGNONUSERTYPE=1, /**<个人用户 */
    RESELLERUSERTYPE=2 /**<经销商用户 */
}ACCOUNTTYPE;

/**
 *  @brief 经销商类型
 */
typedef enum
{
    RESELLEROLDCARTYPE=1, /**<表示二手车经销商店 */
    RESELLER4STYPE=2 /**<表示4S店 */
}RESELLERTYPE;


/**
 *  @brief 用户信息
 *
 *  @return 用户信息
 */
+ (instancetype)shareInstance;

/**
 *  @brief 已经登录
 */
@property (nonatomic) BOOL logined;

/**
 *  @brief 用户访问令牌
 */
@property (nonatomic,strong) NSString *accessToken;

/**
 *  @brief 用户Id
 */
@property (nonatomic) UInt64 userId;

/**
 *  @brief 用户名
 */
@property (nonatomic,strong) NSString *userName;

/**
 *  @brief 密码
 */
@property (nonatomic,strong) NSString *password;

/**
 *  @brief 重复密码
 */
@property (nonatomic,strong) NSString *repassword      ;

/**
 *  @brief 手机号码
 */
@property (nonatomic,strong) NSString *phone           ;

/**
 *  @brief 手机验证码
 */
@property (nonatomic,strong) NSString *phoneVerifyCode ;

/**
 *  @brief 经销商名称
 */
@property (nonatomic,strong) NSString *resellerName    ;

/**
 *  @brief 1表示二手车经纪公司，2表示4S店
 */
@property (nonatomic) RESELLERTYPE resellerType    ;

/**
 *  @brief 地址
 */
@property (nonatomic,strong) NSString *adress          ;

/**
 *  @brief 昵称
 */
@property (nonatomic,strong) NSString *nickname        ;

/**
 *  @brief 邮件地址
 */
@property (nonatomic,strong) NSString *email           ;

/**
 *  @brief 同意注册条款
 */
@property (nonatomic) BOOL acceptTerm;

/**
 *  @brief 用户类型
 *  个人用户:1
 *  经销商用户:2
 */
@property (nonatomic) ACCOUNTTYPE accountType;

@end
