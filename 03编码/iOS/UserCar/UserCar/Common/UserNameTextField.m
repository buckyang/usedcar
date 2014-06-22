//
//  UserNameTextField.m
//  UserCar
//
//  Created by 舒联勇 on 14-6-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserNameTextField.h"
#import <CoreText/CoreText.h>

@implementation UserNameTextField

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (UIImage*)LeftImage
{
    return [UIImage imageNamed:(self.text.length>0)?@"imgLoginUserNameH.png":@"imgLoginUserName.png"];
}

//- (void)drawRect:(CGRect)rect
//{
//    CGContextRef context = UIGraphicsGetCurrentContext();
//    
//    CGContextSaveGState(context);
//    
//    CGContextSetStrokeColorWithColor(context, [UIColor redColor].CGColor);
//    
//    CGContextSetLineWidth(context, 2);
//    
//    CGContextMoveToPoint(context, 40, 0);
//    CGContextAddLineToPoint(context, 40, self.bounds.size.height);
//    CGContextStrokePath(context);
//    
//    CGContextRestoreGState(context);
//    
//    
//    
////    NSRange point =  NSMakeRange(0, self.attributedText.length);
////    NSDictionary *oldDic =  [self.attributedText attributesAtIndex:0 effectiveRange:&point];
////    NSMutableDictionary *fontDic = [NSMutableDictionary dictionaryWithDictionary:oldDic];
////    fontDic[NSKernAttributeName] = @20;
////    
////    self.attributedText = [[NSAttributedString alloc] initWithDictionary:fontDic];
////    [self.text drawInRect:self.bounds withAttributes:fontDic];
//    
//    
////    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithDictionary:fontDic];
////    self.attributedText = attributedString;
//    
//    
////    CGContextSetCharacterSpacing(context, 10);
////    CGContextSetTextDrawingMode(context, kCGTextFill);
//    
//    
////    CGAffineTransform myTextTransform = CGAffineTransformScale(CGAffineTransformIdentity, 1.f, -1.f );
////    CGContextSetTextMatrix (context, myTextTransform);
//    
//    //去掉空行
//    NSString *labelString = self.text;
//    NSString *myString = [labelString stringByReplacingOccurrencesOfString:@"\r\n" withString:@"\n"];
//    
//    //创建AttributeString
//    NSMutableAttributedString *string =[[NSMutableAttributedString alloc]initWithString:myString];
//    
//    //设置字体及大小
//    CTFontRef helveticaBold = CTFontCreateWithName((CFStringRef)self.font.fontName,self.font.pointSize,NULL);
//    [string addAttribute:(id)kCTFontAttributeName value:(__bridge id)helveticaBold range:NSMakeRange(0,[string length])];
//    
//    
//    long number = 20;
//    CFNumberRef num = CFNumberCreate(kCFAllocatorDefault,kCFNumberSInt8Type,&number);
//    
//    [string addAttribute:(NSString*)kCTKernAttributeName value:@20 range:NSMakeRange(0, [string length])];
//    CFRelease(num);
//    
//    //排版
//    CTFramesetterRef framesetter = CTFramesetterCreateWithAttributedString((CFAttributedStringRef)string);
//    CGMutablePathRef leftColumnPath = CGPathCreateMutable();
//    CGPathAddRect(leftColumnPath, NULL ,CGRectMake(0 , 0 ,self.bounds.size.width , self.bounds.size.height));
//    CTFrameRef leftFrame = CTFramesetterCreateFrame(framesetter,CFRangeMake(0, 0), leftColumnPath , NULL);
//
//    
//    CGContextSetTextMatrix(context , CGAffineTransformIdentity);
//    CGContextTranslateCTM(context , 0 ,self.bounds.size.height);
//    CGContextScaleCTM(context, 1.0 ,-1.0);
//
//    //画出文本
//    CTFrameDraw(leftFrame,context);
//    
//    CGPathRelease(leftColumnPath);
//    CFRelease(framesetter);
//    CFRelease(helveticaBold);
//    UIGraphicsPushContext(context);
//    
//}

@end
