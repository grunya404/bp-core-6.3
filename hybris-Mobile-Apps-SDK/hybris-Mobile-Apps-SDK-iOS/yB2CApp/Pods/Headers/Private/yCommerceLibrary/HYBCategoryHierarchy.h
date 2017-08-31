//
// HYBCategoryHierarchy.h
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

#import <Foundation/Foundation.h>
#import "MTLModel.h"
#import "MTLJSONAdapter.h"

#import "HYBAbstractCatalogItem.h"



@interface HYBCategoryHierarchy : HYBAbstractCatalogItem

@property (nonatomic) NSNumber *pageSize;
@property (nonatomic) NSNumber *totalNumber;
@property (nonatomic) NSNumber *currentPage;
@property (nonatomic) NSNumber *numberOfPages;
@property (nonatomic) NSArray *subcategories;
@property (nonatomic) NSArray *products;


+ (instancetype)categoryHierarchyWithParams:(NSDictionary*)params;

@end