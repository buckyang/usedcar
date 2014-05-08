//
//  UploadImage.h
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AccessBase.h"

typedef void(^UploadProcess)(float aProcessValue);
static NSString *uploadURL = @"/product/imageUpload.json";
@interface UploadImage : AccessBase

+ (void)uploadImageWithData:(NSData*)aData withUploadProcess:(UploadProcess)processBlock;

@end
