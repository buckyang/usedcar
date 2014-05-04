//
//  UploadImage.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UploadImage.h"

@implementation UploadImage


+ (void)uploadImageWithData:(NSData*)aData withUploadProcess:(UploadProcess)processBlock;
{
    NSString *urlString = [httpUrl stringByAppendingString:uploadURL];
    NSError *error = nil;
    NSMutableURLRequest *request = [[AFHTTPRequestSerializer serializer] multipartFormRequestWithMethod:@"POST"
                                                                                              URLString:urlString
                                                                                             parameters:nil
                                                                              constructingBodyWithBlock:^(id<AFMultipartFormData> formData) {
                                                                                                  [formData appendPartWithFormData:aData name:@"image"];
                                                                                             }
                                                                                                  error:&error];
    AFURLSessionManager *manager = [[AFURLSessionManager alloc] initWithSessionConfiguration:[NSURLSessionConfiguration defaultSessionConfiguration]];
    
    
    NSProgress *progress = nil;
    
    [manager setTaskDidSendBodyDataBlock:^(NSURLSession *session, NSURLSessionTask *task, int64_t bytesSent, int64_t totalBytesSent, int64_t totalBytesExpectedToSend) {
        
        float progressValue = 1.0f*progress.completedUnitCount/progress.totalUnitCount;
        INFO(@"downProgress.totalUnitCount:%lld  downProgress.completedUnitCount:%lld  progress:%f",progress.totalUnitCount,progress.completedUnitCount,progressValue);
        processBlock(progressValue);
    }];
    
    NSURLSessionUploadTask *uploadTask = [manager uploadTaskWithStreamedRequest:request progress:&progress completionHandler:^(NSURLResponse *response, id responseObject, NSError *error) {
        if (error) {
            INFO(@"Error: %@", error);
        } else {
            INFO(@"%@ %@", response, responseObject);
        }
    }];
    
    [uploadTask resume];
}

@end
