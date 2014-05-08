//
//  UIImage+Util.h
//  VVM
//
//  Created by shulianyong on 12/03/30.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>


@interface UIImage (UIImage_Util)

/**
 *  @brief 缩小图片大小
 *
 *  @param aRect 大小
 *
 *  @return 处理后的图片
 */
- (UIImage*)shrinkImage:(CGRect)aRect;

/**
 *  @brief 设置缩略图
 *
 *  @param aImage 源图片
 *
 *  @return 处理后的图片
 */
- (UIImage *)generatePhotoThumbnail:(UIImage *)aImage;

/**
 *  @brief //计算适合的大小。并保留其原始图片大小
 *
 *  @param aThisSize 当前图片大小
 *  @param aSize     容器框大小
 *
 *  @return 适合的大小
 */
+ (CGSize)fitSize:(CGSize)aThisSize inSize:(CGSize)aSize;

/**
 *  @brief 返回调整的缩略图
 *
 *  @param aImage    源图片
 *  @param aViewsize 容器大小
 *
 *  @return 调整的缩略图
 */
+ (UIImage *)image:(UIImage *)aImage fitInSize:(CGSize)aViewsize;


/**
 *  @brief 居中图片
 *
 *  @param aImage    源图片
 *  @param aViewsize 展示大小
 *
 *  @return 居中的缩略图
 */
+ (UIImage *)image:(UIImage *)aImage centerInSize:(CGSize)aViewsize;


/**
 *  @brief 填充图片
 *
 *  @param aImage    源图片
 *  @param aViewsize 需要填充的大小
 *
 *  @return 填充的缩略图
 */
+ (UIImage *)image:(UIImage *)aImage fillSize:(CGSize)aViewsize;


/**
 *  @brief 让图片变小
 *
 *  @param aInImage 源图片
 *  @param aSize    略小的大小
 *
 *  @return 变小后的图片
 */
+ (UIImage *)rescaleToSize:(UIImage *)aInImage toSize:(CGSize)aSize;

/**
 *  @brief 图片保存为jpg文件
 *
 *  @param aImage    源图片
 *  @param aFilePath 存放位置
 *
 *  @return 是否保存成功
 */
+ (BOOL) writeToImage:(UIImage*)aImage toFileAtPath:(NSString *)aFilePath;

@end
