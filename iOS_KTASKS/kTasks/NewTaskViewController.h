//
//  NewTaskViewController.h
//  kTasks
//
//  Created by Kenny Lo on 13/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NewTaskViewController : UIViewController
@property (weak, nonatomic) IBOutlet UITextField *nameField;
@property (weak, nonatomic) IBOutlet UITextField *noteField;
@property (weak, nonatomic) IBOutlet UIImageView *completeImage;
- (IBAction)addAction:(id)sender;
- (IBAction)bgClick:(id)sender;
@property (nonatomic, retain) NSString *completed;
@property (nonatomic, retain) NSString *username;
@end
