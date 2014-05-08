//
//  spelling.h
//  VVM
//
//  Created by 联勇 舒 on 12-5-16.
//  Copyright (c) 2012年 Chengdu Sifang Information Technology Co.LTD. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface spelling : NSObject

/**
 *  @brief 拼音首字母
 *
 *  @param hanzi 汉字
 *
 *  @return 首字母
 */
char pinyinFirstLetter(unsigned short hanzi);

@end
