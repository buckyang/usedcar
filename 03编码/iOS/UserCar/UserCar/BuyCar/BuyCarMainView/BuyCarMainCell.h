//
//  BuyCarMainCell.h
//  UserCar
//
//  Created by 舒联勇 on 14-4-16.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BuyCarMainCell : UITableViewCell

/**
 *  @brief 展示图片
 */
@property (weak, nonatomic) IBOutlet UIImageView *imgTitle;

/**
 *  @brief 价格
 */
@property (weak, nonatomic) IBOutlet UILabel *lblPrice;

/**
 *  @brief 总公里数
 */
@property (weak, nonatomic) IBOutlet UILabel *lblTotalMileage;

/**
 *  @brief 新车购买时间
 */
@property (weak, nonatomic) IBOutlet UILabel *lblFirstTime;

//

@end
