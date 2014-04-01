//
//  NSObject+Reflect.h
//  CDSFDataBase
//
//  Created by shulianyong on 13-2-20.
//  Copyright (c) 2013年 cdsf. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject (Reflect)

extern BOOL isNull(id value);

+ (instancetype)createInstance;

- (instancetype)initWithJsonString:(NSString*)aJsonString;
- (instancetype)initWithDictionary:(NSDictionary*)aDicValue;

- (NSArray*)propertyKeys;

/* 从字典中，将相应的字段反射到实体相应的字段中，用于自动绑定值
 */
- (BOOL)reflectDataFromOtherObject:(NSObject*)dataSource;

#pragma mark-------------- deep Copy
- (instancetype)deepCopy;

@end
