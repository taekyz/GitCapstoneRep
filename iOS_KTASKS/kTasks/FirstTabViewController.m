//
//  FirstTabViewController.m
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import "FirstTabViewController.h"
#import "TaskViewController.h"

@interface FirstTabViewController ()

@end

@implementation FirstTabViewController
@synthesize tableView, responseData, username, temp;

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
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    username = [defaults objectForKey:@"username"];
	// Do any additional setup after loading the view.
    [self getData];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void) getData{
    
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/getTasks?";
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
        NSLog(@"%@", json);
        
        //disconenct
        dispatch_async(dispatch_get_main_queue(), ^{
            //load data into table
            @try {
                responseData = (NSMutableArray *) json;
                [tableView reloadData];
            }
            @catch (NSException *exception) {
                [[[UIAlertView alloc] initWithTitle:@"Error"
                                            message:@"Could not parse the JSON feed."
                                           delegate:nil
                                  cancelButtonTitle:@"Close"
                                  otherButtonTitles: nil] show];
                NSLog(@"Exception: %@", exception);
            }
        });
        
    });
}

#pragma mark – Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView

{
    
    // Return the number of sections.
    
    return 1;
    
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section

{
    
    // Return the number of rows in the section.
    return [responseData count];
    
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath

{
    
    static NSString *CellIdentifier = @"Cell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:CellIdentifier];
    }
    NSDictionary *tasksDict = [responseData objectAtIndex:indexPath.row];
    NSString *name = [tasksDict objectForKey:@"taskName"];
    
    NSString *notes = [tasksDict objectForKey:@"taskNotes"];
    
    cell.textLabel.text = name;
    
    cell.detailTextLabel.text = [NSString stringWithFormat:@"Notes: %@",notes];
    
    return cell;
    
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    temp = indexPath.row;
    [self performSegueWithIdentifier:@"TaskSegue" sender:self];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Make sure your segue name in storyboard is the same as this line
    if ([[segue identifier] isEqualToString:@"TaskSegue"])
    {
        // Get reference to the destination view controller
        TaskViewController *vc = [segue destinationViewController];
        
        // Pass any objects to the view controller here, like...
        NSDictionary *tasksDict = [responseData objectAtIndex:temp];
        [vc setName:[tasksDict objectForKey:@"taskName"]];
        [vc setNote:[tasksDict objectForKey:@"taskNotes"]];
        [vc setCompleted:[tasksDict objectForKey:@"completed"]];
        [vc setTaskId:[tasksDict objectForKey:@"taskId"]];
    }
}

- (IBAction)refreshTable:(id)sender {
    [self getData];
}

- (IBAction)addTask:(id)sender {
    [self performSegueWithIdentifier:@"newTaskSegue" sender:self];
}
@end
