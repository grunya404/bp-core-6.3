//
// HYBOrderStatusUpdateElementList.m
// [y] hybris Platform
//
// Copyright (c) 2000-2016 hybris AG
// All rights reserved.
//
// This software is the confidential and proprietary information of hybris
// ("Confidential Information"). You shall not disclose such Confidential
// Information and shall use it only in accordance with the terms of the
// license agreement you entered into with hybris.
//
// Warning:This file was auto-generated by OCC2Ojbc.
//

#import "HYBOrderStatusUpdateElementList.h"
#import "NSValueTransformer+MTLPredefinedTransformerAdditions.h"

#import "HYBOrderStatusUpdateElement.h"


@implementation HYBOrderStatusUpdateElementList

+ (instancetype)orderStatusUpdateElementListWithParams:(NSDictionary*)params {

NSError *error = nil;
HYBOrderStatusUpdateElementList *object = [MTLJSONAdapter modelOfClass:[HYBOrderStatusUpdateElementList class] fromJSONDictionary:params error:&error];

if (error) {
    NSLog(@"Couldn't convert JSON to model HYBOrderStatusUpdateElementList");
    return nil;
}

return object;
}

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
   return @{
@"orderStatusUpdateElements" : @"orderStatusUpdateElements"
};
}

+ (NSValueTransformer *)orderStatusUpdateElementsJSONTransformer {
return [NSValueTransformer mtl_JSONArrayTransformerWithModelClass:[HYBOrderStatusUpdateElement class]];
}






@end