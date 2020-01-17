## Spring Boot With Redis
    In this example we use redis to store hash id and long url mapping and when then inquiry is made with the hash id, corresponding long
    url is sent back to the client.
    
### Redis
    Redis is a NO-SQL/in-memory data store can be used for different type of application where faster access of data is required.
    It supports replication in cluster hence highly available. 
    
### Example
    Run the ExampleApplication  
    Do post operation passing a url in body: http://localhost:8080/shorturl/url/set  
    Get the long url by passing the hashid returned from above operation : http://localhost:8080/shorturl/url/hashId  
     
    
    
