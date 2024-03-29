package utcn.labs.sd.bankingservice.core.configuration;

import springfox.documentation.service.Tag;

public final class SwaggerTags {

    public static final String BANKING_SERVICE_TAG = "Banking Service Overview";
    public static final Tag BANKING_SERVICE = new Tag(BANKING_SERVICE_TAG,
            "All Banking related resources", 0);


    public static final String ACCOUNT_TAG = "Account";
    public static final Tag ACCOUNT = new Tag(ACCOUNT_TAG, "Resource: Account");

    public static final String CLIENT_TAG = "Client";
    public static final Tag CLIENT = new Tag(CLIENT_TAG, "Resource: Client");

    public static final String EMPLOYEE_TAG = "Employee";
    public static final Tag EMPLOYEE = new Tag(EMPLOYEE_TAG, "Resource: Employee");
    
    public static final String BILL_TAG = "Bill";
    public static final Tag BILL = new Tag(BILL_TAG, "Resource: Bill");
    
    public static final String ACTIVITY_TAG = "Activity";
    public static final Tag ACTIVITY = new Tag(ACTIVITY_TAG, "Resource: Activity");
    
    public static final String LOGIN_TAG = "Login";
    public static final Tag LOGIN = new Tag(LOGIN_TAG, "Resource: Login");
   

    /**
     * Don't allow class instantiation
     */
    private SwaggerTags() {
        // hide from instantiation
    }
}
