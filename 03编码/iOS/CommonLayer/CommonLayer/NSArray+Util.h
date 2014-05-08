//
//  NSArray+Util.h
//  VVM
//
//  Created by shulianyong on 12/03/30.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface NSArray (Util)

/**
 *  @brief 是否为空
 *
 *  @return 是否为空
 */
- (BOOL)isEmpty;

/**
 *  @brief 将数据用特殊符号隔断
 *
 *  @param aCharacter 特殊符号
 *
 *  @return 隔断后的字符串
 */
- (NSString*) stringWithCharacter:(NSString*)aCharacter;

@end
