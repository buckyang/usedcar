//
//  NSObject+Reflect.m
//  CDSFDataBase
//
//  Created by shulianyong on 13-2-20.
//  Copyright (c) 2013年 cdsf. All rights reserved.
//

#import "NSObject+Reflect.h"
#import <objc/runtime.h>

@implementation NSObject (Reflect)

extern BOOL isNull(id value)
{
    if (!value) return YES;
    if ([value isKindOfClass:[NSNull class]]) return YES;
    
    return NO;
}

- (instancetype)initWithJsonString:(NSString*)aJsonString
{
    NSError* initError = nil;
    id obj = [NSJSONSerialization JSONObjectWithData:[aJsonString dataUsingEncoding:NSUTF8StringEncoding]
                                             options:kNilOptions
                                               error:&initError];
    return [self initWithDictionary:obj];
}

- (instancetype)initWithDictionary:(NSDictionary*)aDicValue
{
    if (isNull(aDicValue)) {
        return nil;
    }
    self = [self init];
    if (self) {
        [self reflectDataFromOtherObject:aDicValue];
    }
    return self;
}


+ (instancetype)createInstance
{
    return [[[self class] alloc] init];
}

- (NSArray*)propertyKeys
{
    Class tempClass = [self class];
    NSMutableArray *keys = [[NSMutableArray alloc] init];
    while (tempClass !=[NSObject class]) {
        @autoreleasepool {
            
            NSArray *tempKeys = [self propertyKeysFromClass:tempClass];
            if (tempKeys.count>0) {
                [keys addObjectsFromArray:tempKeys];
            }
            
        }
        tempClass = [tempClass superclass];
    }
    return keys;
}

- (NSArray*)propertyKeysFromClass:(Class)aClass
{
    unsigned int outCount, i;
    objc_property_t *properties = class_copyPropertyList(aClass, &outCount);
    NSMutableArray *keys = [[NSMutableArray alloc] initWithCapacity:outCount];
    for (i = 0; i < outCount; i++) {
        objc_property_t property = properties[i];
        NSString *propertyName = [[NSString alloc] initWithCString:property_getName(property) encoding:NSUTF8StringEncoding];
        
        //如果存在Set函数，才加入到keys中
        NSString *firstValue = [propertyName substringToIndex:1];
        firstValue = [firstValue uppercaseString];
        NSString *temp = [NSString stringWithFormat:@"%@%@",firstValue,[propertyName substringFromIndex:1]];
        
        NSString *tempSet = [NSString stringWithFormat:@"set%@:",temp];
        if([self respondsToSelector:NSSelectorFromString(tempSet)])
        {
            [keys addObject:propertyName];
        }
    }
    free(properties);
    return keys;
}

- (BOOL)reflectDataFromOtherObject:(NSObject*)dataSource
{
    BOOL ret = NO;
    for (NSString *key in [self propertyKeys]) {        
        @autoreleasepool {
            if ([dataSource isKindOfClass:[NSDictionary class]]) {
                ret = ([dataSource valueForKey:key]==nil)?NO:YES;
            }
            else
            {
                ret = [dataSource respondsToSelector:NSSelectorFromString(key)];
            }
            if (ret) {
                id propertyValue = [dataSource valueForKey:key];
                
                //该值不为NSNULL，并且也不为nil
                if (![propertyValue isKindOfClass:[NSNull class]] && propertyValue!=nil)
                {
                    [self setValue:propertyValue forKey:key];
                }
            }
        }
        
    }
    return ret;
}

#pragma mark --------------- deep Copy
- (void)encodeWithCoder:(NSCoder *)aCoder
{
    NSArray *allKeys = self.propertyKeys;
    id objValue = nil;
    for (NSString *key in allKeys) {
        objValue = [self valueForKey:key];
        [aCoder encodeObject:objValue forKey:key];
    }
}

- (id)initWithCoder:(NSCoder *)aDecoder
{
    self=[self init];
    if (self) {
        NSArray *allKeys = self.propertyKeys;
        id objValue = nil;
        for (NSString *key in allKeys) {
            objValue = [aDecoder decodeObjectForKey:key];
            [self setValue:objValue forKey:key];
        }
    }
    return self;
}

- (instancetype)deepCopy
{
    NSData *tempData =  [NSKeyedArchiver archivedDataWithRootObject:self];
    id copyValue = nil;
    if (tempData) {
        copyValue= [NSKeyedUnarchiver unarchiveObjectWithData: tempData];
    }
    return copyValue;
}

@end
