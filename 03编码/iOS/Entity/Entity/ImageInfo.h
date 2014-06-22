//
//  ImageInfo.h
//  Entity
//
//  Created by 舒联勇 on 14/6/7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ImageInfo : NSObject

/**
 *  @brief 图片Id
 */
@property (nonatomic) NSInteger imageId;
/**
 *  @brief 图片地址
 */
@property (nonatomic,strong) NSString *imageUrl;

@end
