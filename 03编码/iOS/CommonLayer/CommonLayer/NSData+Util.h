//
//  NSData+Util.h
//  VVM
//
//  Created by shulianyong on 12/03/30.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface NSData (Util)

/**
 *  @brief 判断是否为空
 *
 *  @param aData 需要判断的数据
 *
 *  @return 是否为空
 */
+ (BOOL)isEmpty:(NSData*)aData;

/**
 *  @brief base64编码
 *
 *  @param aString   需要编码的字符串
 *  @param aEncoding NSStringEncoding值
 *
 *  @return 编码后的数据
 */
+ (NSData*)base64Encode:(NSString*)aString encoding:(NSStringEncoding)aEncoding;

/**
 *  @brief base64编码
 *
 *  @param aData 需要编码的字符串
 *
 *  @return 编码后的数据
 */
+ (NSData*)base64Encode:(NSData*)aData;

/**
 *  @brief base64解码
 *
 *  @param aBase64 base64字符串
 *
 *  @return 解码后的数据
 */
+ (NSData*)base64Decode:(id)aBase64;

@end
