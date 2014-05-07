//
//  FindPasswordContrainer.h
//  UserCar
//
//  Created by 舒联勇 on 14-5-7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FindPasswordContrainer : UIViewController

@property (nonatomic,strong) NSString *SegueIdentifierFirst;
@property (nonatomic,strong) NSString *SegueIdentifierSecond;

/**
 *  @brief 切换界面
 */
- (void)swapViewControllers;

@end
