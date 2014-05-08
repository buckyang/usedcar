//
//  UIBarButtonItem+ImageTitle.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-16.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UIBarButtonItem+ImageTitle.h"

@implementation UIBarButtonItem (ImageTitle)

#pragma mark  －－－－－－－－－－设置返回按扭样式

+ (UIBarButtonItem*)barBackWithTarget:(UIViewController*)target
{
    return [self customBarWithTarget:target.navigationController
                        withSelector:@selector(popViewControllerAnimated:)
                           withImage:[UIImage imageNamed:@"img_Back.png"] withTitle:nil];
}


+ (UIBarButtonItem*)customBarWithTarget:(id)target
                           withSelector:(SEL)aSelector
                              withImage:(UIImage*)aImage
                              withTitle:(NSString*)aTitle
{
    UIButton *btnCustom = [UIButton buttonWithType:UIButtonTypeCustom];
    [btnCustom setImage:aImage forState:UIControlStateNormal];
    
    btnCustom.bounds = CGRectMake(0, 0, aImage.size.width, 20);
    if (![NSString isEmpty:aTitle]) {
        [btnCustom setTitle:aTitle forState:UIControlStateNormal];
        
        
        NSMutableAttributedString *attrStr =  [[NSMutableAttributedString alloc] initWithString:aTitle];
        
        NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle defaultParagraphStyle] mutableCopy];
        paragraphStyle.minimumLineHeight = 20;
        paragraphStyle.paragraphSpacing = 5;
        [attrStr addAttribute:NSFontAttributeName value:btnCustom.titleLabel.font range:NSMakeRange(0, aTitle.length)];
        
        CGSize  strSize = CGSizeZero;
        NSRange range = NSMakeRange(0, attrStr.length);
        NSDictionary *dic = [attrStr attributesAtIndex:0 effectiveRange:&range]; // 获取该段attributedString的属性字典
        strSize = [aTitle boundingRectWithSize:CGSizeMake(200, 1000.f)
                                          options:NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingUsesFontLeading // 文本绘制时的附加选项
                                       attributes:dic
                                          context:nil].size;
        btnCustom.bounds = CGRectMake(0, 0, strSize.width+aImage.size.width, 20);
        
    }
    
    btnCustom.contentHorizontalAlignment = UIControlContentHorizontalAlignmentLeft;
    
    if (target) {        
        [btnCustom addTarget:target action:aSelector forControlEvents:UIControlEventTouchUpInside];
    }
    
    
    UIBarButtonItem *barBack = [[UIBarButtonItem alloc] initWithCustomView:btnCustom];
    return barBack;
}

@end
