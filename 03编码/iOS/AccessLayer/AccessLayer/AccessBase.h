//
//  AccessLayer.h
//  AccessLayer
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AFNetworking/AFNetworking.h"

static NSString *httpUrl=@"http://112.124.62.114:8080/usedcar/";


typedef enum
{
	HTTPAccessStateDefault = 0, /**<默认 */
	HTTPAccessStateSuccess = 1, /**< 成功 */
	HTTPAccessStateFail = 2, /**< 失败 */
	HTTPAccessStateDisconnection = 3 /**< 网络联接失败 */
}
HTTPAccessState;

typedef void(^HttpCallback)(id info,HTTPAccessState isSuccess);


@interface AccessBase : NSObject

- (AFHTTPRequestOperationManager*)httpClient;

@end
