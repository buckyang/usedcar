//
//  NSString+Util.h
//  VVM
//
//  Created by shulianyong on 12/03/30.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (Util)

/**
 *  @brief 是否为空
 *
 *  @param aString 需要判断的字符串
 *
 *  @return 是否为空
 */
+ (BOOL)isEmpty:(NSString*)aString;

/**
 *  @brief URL编码
 *
 *  @param aSource 需要编码的字符串
 *
 *  @return 编码后的字符串
 */
+ (NSString*)urlEncode:(NSString*)aSource;

/**
 *  @brief URL解码
 *
 *  @param aSource 需要解码字符串
 *
 *  @return 解决后的字符串
 */
+ (NSString*)urlDecode:(NSString*)aSource;

/**
 *  @brief 将16进制字符串转换成uint型
 *
 *  @param aHex 16进制字符串
 *
 *  @return 转换后的uint值
 */
+ (unsigned int)convertHexString:(NSString*)aHex;

/**
 *  @brief base64编码
 *
 *  @param aData 需要编码数据
 *
 *  @return 编码后的值
 */
+ (NSString*)base64Encode:(NSData*)aData;

/**
 *  @brief base64编码
 *
 *  @param aString   需要编码数据
 *  @param aEncoding NSStringEncoding值
 *
 *  @return 编码后的值
 */
+ (NSString*)base64Encode:(NSString*)aString encoding:(NSStringEncoding)aEncoding;

/**
 *  @brief base64解码
 *
 *  @param aBase64   需要解码的数据
 *  @param aEncoding NSStringEncoding值
 *
 *  @return 解码后的值
 */
+ (NSString*)base64Decode:(NSString*)aBase64 encoding:(NSStringEncoding)aEncoding;

/**
 *  @brief base64解码
 *
 *  @param aBase64 需要解码的数据
 *
 *  @return 解码后的值
 */
+ (NSString*)base64Decode:(NSString*)aBase64;

/**
 *  @brief 字符串长度
 *
 *  @param aString 需要计算的字符串
 *
 *  @return 字符串长度
 */
+ (NSUInteger)length:(NSString*)aString;

/**
 *  @brief 是否只是Ascii
 *
 *  @param aString 判断的字符串
 *
 *  @return 是否只是Ascii
 */
+ (BOOL)isAsciiOnly:(NSString*)aString;

/**
 *  @brief 正则表达式
 *
 *  @param aFormat      表达式
 *  @param aValueString 判断的字符串
 *
 *  @return 是否满足正则表达式
 */
+ (BOOL) regexWithFormat:(NSString*)aFormat ValueString:(NSString*)aValueString;

/**
 *  @brief MD5计算
 *
 *  @param aValue 需要MD5的字符串
 *
 *  @return md5值
 */
+ (NSString*) MD5:(NSString*)aValue;

/**
 *  @brief 获取新的UUID
 *
 *  @return 新uuid
 */
+ (NSString*)UUID;

/**
 *  @brief 电话号码转换
 *
 *  @param aValue 需要转换的字符串
 *
 *  @return 转换后的11位的电话号码
 */
+ (NSString *) phoneNumFormat:(NSString*)aValue;

/**
 *  @brief 获取一个字符串内的，所有邮件地址
 *
 *  @param aText 需要提取的字符串
 *
 *  @return 提取结果
 */
+ (NSArray*)emails:(NSString*)aText;

/**
 *  @brief 让字符串不为空
 *
 *  @param aValue 需要处理的字符串
 *
 *  @return 不为空的字符串
 */
+ (NSString *) valueNotNull:(NSString*)aValue;

/**
 *  @brief 对比字符串大小
 *
 *  @param aBaseString  当前字符串
 *  @param aMarchString 对比字符串
 *
 *  @return 是否当前字符串大
 */
+ (BOOL) marchStringForSearch:(NSString*)aBaseString withMarch:(NSString*)aMarchString;

/**
 *  @brief 中文字符串的首字母
 *
 *  @param aString 字文字符串
 *
 *  @return 首字母
 */
+ (NSString *) firstLetter:(NSString*)aString;


#pragma mark 文件路径管理

/**
 *  @brief Document文件夹详细路径
 *
 *  @return Document文件夹详细路径
 */
+ (NSString*)documentFolderPath;

/**
 *  @brief Document文件夹中的一个文件夹，不存在就添加
 *
 *  @param aFolderName 文件夹名
 *
 *  @return 路径
 */
+ (NSString*)pathInDocument:(NSString*)aFolderName;

/**
 *  @brief cache文件夹路径
 *
 *  @return cache文件夹路径
 */
+ (NSString*)cacheFolderPath;

/**
 *  @brief cache内的图片文件夹
 *
 *  @return 图片文件夹
 */
+ (NSString*)imageFolderInCache;

/**
 *  @brief 获取文件名
 *
 *  @param aPath 文件路径
 *
 *  @return 文件名
 */
+ (NSString*)fileNameInPath:(NSString*)aPath;

/**
 *  @brief 去前后空格
 *
 *  @return 去空格后的值
 */
- (NSString*)trim;

/**
 *  @brief 判断是否是数字字符串
 *
 *  @param aString 需要判断的字符串
 *
 *  @return 是否是数字
 */
+ (BOOL)isNumberString:(NSString*)aString;

/**
 *  @brief UDID,唯一标识
 *
 *  @return 唯一标识
 */
+ (NSString*)UDID;

@end
