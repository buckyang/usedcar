//
//  Entity.m
//  Entity
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "EntityBase.h"

@implementation EntityBase

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
    self=[super init];
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
