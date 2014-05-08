//
//  NSDate+Util.h
//  VVM
//
//  Created by shulianyong on 12/03/30.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface NSDate (Util)
//rfc2822 date example Fri, 09 Sep 2005 13:51:39 -0700
+ (NSDate*)dateFromRFC2822:(NSString *)aRFC2822;
+ (NSString*)dateToRFC2822:(NSDate*)aDate;

#pragma mark
#pragma mark ---------------日期格式显示

/**
 *  @brief 时间展示
 *
 *  @param aFormat 时间格式
 *
 *  @return 时间字符串
 */
- (NSString*)descriptionAsFormat:(NSString*)aFormat;

/**
 *  @brief 时间展示
 *
 *  @param aFormat 时间格式
 *  @param aZone   时区
 *
 *  @return 时间字符串
 */
- (NSString*)descriptionAsFormat:(NSString *)aFormat withTimeZone:(NSTimeZone*)aZone;


/**
 *  @brief 按一定格式展示时间
 *  HH:mm:ss
 *  yyyy-MM-dd
 *  MM/dd/yyyy
 *
 *  @param aFormat 日期样式
 *
 *  @return 日期格式值
 */
- (NSString*)descriptionLocalAsFormat:(NSString *)aFormat;


/**
 *  @brief 时间间隔 计算month 和 day
 *
 *  @param aFromDate 开始时间
 *  @param aToDate   结束时间
 *
 *  @return 计算后的值
 */
+ (NSDateComponents*)fromDate:(NSTimeInterval)aFromDate toDate:(NSTimeInterval)aToDate;

/**
 *  @brief 到现在的时间隔
 *
 *  @param aFromDate 开始时间
 *
 *  @return 计算后的值
 */
+ (NSDateComponents*)dateComponentsFromDate:(NSTimeInterval)aFromDate;

/**
 *  @brief 本地时间展示
 *
 *  @param aTime   需要展示的时间戳
 *  @param aFormat 时间展示格式
 *
 *  @return 时间字符串
 */
+ (NSString*)timeIntervalToLocalString:(NSTimeInterval)aTime WithFormat:(NSString*)aFormat;
@end
