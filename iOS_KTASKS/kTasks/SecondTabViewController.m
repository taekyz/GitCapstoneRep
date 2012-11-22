//
//  SecondTabViewController.m
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import "SecondTabViewController.h"

@interface SecondTabViewController ()

@end

@implementation SecondTabViewController
@synthesize username, responseData, userNameField, firstNameField, lastNameField, passwordField, userData;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    [userNameField setEnabled:NO];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    username = [defaults objectForKey:@"username"];
	[self getData];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void) getData{
    
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/getUserDetails?";
    NSString *strRR = [NSString stringWithFormat:@"%@%@%@", myURL, @"username=", username];
    //connect
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        //Call servlet to get data
        NSData* data = [NSData dataWithContentsOfURL:
                        [NSURL URLWithString:strRR]
                        ];
        //Parse json into dictionary
        NSDictionary* json = nil;

        if (data) {
            json = [NSJSONSerialization
                    JSONObjectWithData:data
                    options:kNilOptions
                    error:nil];
        }
        responseData = (NSMutableArray *) json;
        NSLog(@"%@", userData);
        

        

        //disconenct
        dispatch_async(dispatch_get_main_queue(), ^{
            userData = [responseData objectAtIndex:0];
            NSLog(@"%@", userData);
            userNameField.text = [userData objectForKey:@"username"];
            firstNameField.text = [userData objectForKey:@"firstName"];
            lastNameField.text = [userData objectForKey:@"lastName"];
            passwordField.text = [userData objectForKey:@"password"];

        });
    });
}

- (void)viewDidUnload {
    [self setUserNameField:nil];
    [self setFirstNameField:nil];
    [self setLastNameField:nil];
    [self setPasswordField:nil];
    [super viewDidUnload];
}

- (void) bgClick:(id)sender{
    [userNameField resignFirstResponder];
    [firstNameField resignFirstResponder];
    [lastNameField resignFirstResponder];
    [passwordField resignFirstResponder];
    
}


- (IBAction)updateAction:(id)sender {
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/updateUser?";
    
    NSString *ands = @"&";
    NSString *strRR = [NSString stringWithFormat:@"%@%@%@%@%@%@%@%@%@%@%@%@", myURL, @"username=", username, ands, @"password=", passwordField.text, ands, @"firstName=", firstNameField.text, ands, @"lastName=", lastNameField.text];
    //connect
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        //Call servlet to get data
        //NSString* response = [NSString stringWithContentsOfURL:[NSURL URLWithString:strRR] encoding:NSASCIIStringEncoding error:nil];
        NSData* data = [NSData dataWithContentsOfURL:[NSURL URLWithString:strRR]];
        NSString* response = [[NSString alloc] initWithData:data encoding:NSASCIIStringEncoding];
        response = [response stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
        
        NSLog(@"%@",response);
        //disconenct
        dispatch_async(dispatch_get_main_queue(), ^{
            //load data
            if (response) {
                if ([response isEqualToString:@"OK"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Update User Details" message:@"Update Done" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                        [alert show];
                } else if ([response isEqualToString:@"UPDATE_FAILED"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Update User Details" message:@"Update Failed, Try Again!" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];                } else if ([response isEqualToString:@"NO_USER"]){
                        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Update User Details" message:@"No User Found" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                        [alert show];                }
            }
        });
    });

}
@end
