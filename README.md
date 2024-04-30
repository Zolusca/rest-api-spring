<center>
    
## Rest API (JSON) spring-boot    

</center>

_Note : Project ini merupakan latihan_ 

#### Schema Api
berikut sample schema api yang diinginkan
1. Response sukses
    ```json
     {
       "status": "NOT_FOUND/OK",
       "message": "success insert",
       "data": {
                  "id": "xx",
                  "name": "ccc" 
               }
     }  
    ```
2. Response gagal
    ```json
     {
       "status": "NOT_FOUND/OK",
       "errors": {
                  
               }
     }  
    ```


#### Kebutuhan
1. Database MYSQL
2. Java 8
3. Maven 3+

#### Schema Database
region |----|| country   (One to Many)
```sql
create table region(
    id int(5) primary key auto_increment,
    name varchar(10) not null,
    constraint uq_name unique(name)
)Engine=InnoDb;

create table country(
    id int(5) primary key auto_increment,
    code varchar(2) not null,
    name varchar(20) not null,
    region int(5) not null,
    constraint uq_code unique(code),
    foreign key (region) references region(id) on delete cascade on update cascade
)Engine=InnoDb;
```

#### Setting
1. Application properties : 
    
   Setting nama database,username dan password
