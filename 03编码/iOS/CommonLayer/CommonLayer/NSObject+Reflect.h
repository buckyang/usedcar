//
//  NSObject+Reflect.h
//  CDSFDataBase
//
//  Created by shulianyong on 13-2-20.
//  Copyright (c) 2013年 cdsf. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject (Reflect)

/**
 *  @brief 是否为空
 *
 *  @param value 需要判断的实例
 *
 *  @return 是否为空
 */
extern BOOL isNull(id value);


/**
 *  @brief 创建实体
 *
 *  @return 新实体
 */
+ (instancetype)createInstance;

/**
 *  @brief 根据json字符串，获取实体并自动赋值
 *
 *  @param aJsonString json字段串
 *
 *  @return 赋值成功的实体
 */
- (instancetype)initWithJsonString:(NSString*)aJsonString;

/**
 *  @brief 根据Dictionary，获取实体并自动赋值
 *
 *  @param aDicValue Dictionary数据
 *
 *  @return 赋值成功的实体
 */
- (instancetype)initWithDictionary:(NSDictionary*)aDicValue;


/**
 *  @brief 实体可用于赋值的值
 *
 *  @return 实体可用于赋值的值
 */
- (NSArray*)propertyKeys;


/**
 *  @brief 从字典中，将相应的字段反射到实体相应的字段中，用于自动绑定值
 *
 *  @param dataSource 数据源
 *
 *  @return 是否反射成功
 */
- (BOOL)reflectDataFromOtherObject:(NSObject*)dataSource;

#pragma mark-------------- deep Copy
/**
 *  @brief 实现深拷贝
 *
 *  @return 深拷贝值
 */
- (instancetype)deepCopy;

@end
